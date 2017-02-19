package demo01;

public class Thread4 {

	public static void main(String[] args) {
		final Table table=new Table();
		Thread t1=new Thread(){
			public void run(){
				while(true){
					int bean=table.getBean();
					System.out.println(this.getName()+":"+bean);
				}
			}
		};
		Thread t2=new Thread(){
			public void run(){
				while(true){
					int bean=table.getBean();
					System.out.println(this.getName()+":"+bean);
				}
			}
		};
		t1.start();
		t2.start();
	}
}

class Table{
	private int beans=20;
	public synchronized int getBean(){
		if(beans==0){
			throw new RuntimeException("Ã»¶¹×ÓÁË");
		}
	    Thread.yield();
		return beans--;
	}
}
