package demo01;
/*
 * currentThread()静态方法：查看当前使用的线程
 */
public class Thread3 {

	public static void main(String[] args) {
		Thread t=Thread.currentThread();
		System.out.println("运行main方法的线程是："+t);
		//dosome();
		
		Thread t2=new Thread(){
			public void run(){
				Thread t=currentThread();
				System.out.println("我们创建的线程是："+t);
				dosome();
			}
		};
		
		t2.start();
		
		System.out.println("name"+t.getName());
	}

	public static void dosome(){
		Thread t=Thread.currentThread();
		System.out.println("运行dosome方法的线程是："+t);
	}
}
