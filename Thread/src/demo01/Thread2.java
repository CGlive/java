package demo01;
/*
 * 使用匿名内部类创建线程
 */
public class Thread2 {

	public static void main(String[] args) {
		//第一种模式
		Thread t1=new Thread(){
			public void run(){
				for(int i=0;i<1000;i++){
					System.out.println("你是谁啊？");
				}
			}
		};
		
		
		//第二中模式
//		Runnable r=new Thread(){
//			public void run(){
//				for(int i=0;i<100;i++){
//					System.out.println("我是查水表的！");
//				}
//			}
//		};
//		Thread t2=new Thread(r);
//		t2.start();
		
		Thread t2=new Thread(new Runnable(){
			public void run(){
				for(int i=0;i<1000;i++){
					System.out.println("我是查水表的！");
				}
			}
		});
		t1.setPriority(Thread.MAX_PRIORITY);
		t2.setPriority(Thread.MIN_PRIORITY);
		t1.start();
		t2.start();
	}
}
