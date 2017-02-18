package demo01;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class JoinTest extends JFrame {
    private Thread threadA;
    private Thread threadB;  //定义两个线程
    final JProgressBar progressBar=new JProgressBar();//定义两个进度组件
    final JProgressBar progressBar2=new JProgressBar();
	int count=0;
	public static void main(String[] args) {
		init(new JoinTest(),100,100);
	}
	public JoinTest(){
		super();
		//将进度条设置在窗体最北面
		getContentPane().add(progressBar,BorderLayout.NORTH);
		//将进度条设置在窗体最南面
		getContentPane().add(progressBar2,BorderLayout.SOUTH);
		progressBar.setStringPainted(true);//设置进度条显示数字字符
		progressBar2.setStringPainted(true);
		//使用匿名内部类形式初始化Thread实例
		threadA=new Thread(new Runnable(){
			int count=0;
			public void run(){
				while(true){
					progressBar.setValue(++count);//设置进度条的当前值
					try{
						Thread.sleep(100);//使线程A休眠100毫秒
						threadB.join();//使线程B调用join()方法
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
		});
		threadA.start();//启动线程A
		threadB=new Thread(new Runnable(){
			int count=0;
			public void run(){
				while(true){
					progressBar2.setValue(++count);
					try{
						Thread.sleep(100);
					}catch(Exception e){
						e.printStackTrace();
					}
					if(count==100){
						break;
					}
				}
			}
		});
		threadB.start();
	}
	//设置窗体的各种属性的方法
	public static void init(JFrame frame,int width,int height){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width,height);
		frame.setVisible(true);
	}
}
