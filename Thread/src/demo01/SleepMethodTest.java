package demo01;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JFrame;

public class SleepMethodTest extends JFrame {
    private Thread t;
    //定义颜色数组
    private static Color[] color={Color.BLACK,Color.BLUE,Color.CYAN,
    	                          Color.GREEN,Color.ORANGE,Color.YELLOW,
    	                          Color.RED,Color.PINK,Color.LIGHT_GRAY};
    private static final Random rand=new Random();//创建随机对象
    private static Color getC(){
    	return color[rand.nextInt(color.length)];
    }
	public SleepMethodTest(){
		t=new Thread(new Runnable(){
			int x=30;
			int y=50;
			public void run(){
				while(true){
					try{
						Thread.sleep(1000);
					}catch(InterruptedException e){
						e.printStackTrace();
					}
					//获取组件绘图上下文对象
					Graphics graphics=getGraphics();
					graphics.setColor(getC());//设置绘图颜色
					//绘制直线并递增垂直坐标
					graphics.drawLine(x,y,100,y++);
					if(y>=80){
						y=50;
					}
				}
			}
		});
		t.start();//启动线程
		
	}
	public static void main(String[] args) {
		init(new SleepMethodTest(),100,100);
	}
	//初始化程序界面的方法
	public static void init(JFrame frame,int width,int height){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width, height);
		frame.setVisible(true);
	}
}
