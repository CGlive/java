package demo01;
/*
 * ���ַ��������߳�
 */
public class Thread1 {

	public static void main(String[] args) {
       Thread t1=new MyThread1();
       Runnable r=new MyRunnable();
       Thread t2=new Thread(r);
       t1.start();
       t2.start();
       
	}
}

//�߳�1
class MyThread1 extends Thread{
	public void run(){
		for(int i=0;i<100;i++){
			System.out.println("����˭����");
		}
	}
}

//�߳�2
class MyRunnable implements Runnable{
	public void run(){
		for(int i=0;i<100;i++){
			System.out.println("���ǲ�ˮ��ģ�");
		}
	}
}
