package demo01;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo {

	public static void main(String[] args) {
		//�����̶����ȵ��̳߳أ����������߳�
		ExecutorService threadPool=Executors.newFixedThreadPool(2);
	   	 
   	 for(int i=0;i<5;i++){
   		 Runnable r=new Runnable(){
   			public void run(){  
   				Thread t=Thread.currentThread();
   				System.out.println(t.getName()+"����ִ������");
   				try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						System.out.println("�����ж��ˣ�");
					}
                   System.out.println(t.getName()+"����ִ����ϣ�");
   			}
                   
   		 };
   		 //������ָ�ɸ��̳߳�ִ��
   		 threadPool.execute(r);
   		 
   	 }
   	 
   	 threadPool.shutdown();
	}
}
