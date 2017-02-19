package demo01;

public class Thread5 {

	public static void main(String[] args) {
		final Shop shop=new Shop();
		Thread t1=new Thread(){
			public void run(){
				try {
					shop.buy();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		Thread t2=new Thread(){
			public void run(){
				try {
					shop.buy();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		t1.start();
		t2.start();
	}
}

class Shop{
	public void buy() throws InterruptedException{
		Thread t=Thread.currentThread();
		System.out.println(t+"正在挑选衣服...");
		Thread.sleep(5000);
		
		synchronized(this){
		System.out.println(t+"正在试衣服...");
		Thread.sleep(5000);
	  }
		System.out.println(t+"结账离开");
	}
	
     
}
