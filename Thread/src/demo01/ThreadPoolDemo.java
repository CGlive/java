package demo01;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo {

	public static void main(String[] args) {
		//创建固定长度的线程池，包含两个线程
		ExecutorService threadPool=Executors.newFixedThreadPool(2);
	   	 
   	 for(int i=0;i<5;i++){
   		 Runnable r=new Runnable(){
   			public void run(){  
   				Thread t=Thread.currentThread();
   				System.out.println(t.getName()+"正在执行任务！");
   				try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						System.out.println("任务被中断了！");
					}
                   System.out.println(t.getName()+"任务执行完毕！");
   			}
                   
   		 };
   		 //将任务指派给线程池执行
   		 threadPool.execute(r);
   		 
   	 }
   	 
   	 threadPool.shutdown();
	}
}
