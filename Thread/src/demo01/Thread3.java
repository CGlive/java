package demo01;
/*
 * currentThread()��̬�������鿴��ǰʹ�õ��߳�
 */
public class Thread3 {

	public static void main(String[] args) {
		Thread t=Thread.currentThread();
		System.out.println("����main�������߳��ǣ�"+t);
		//dosome();
		
		Thread t2=new Thread(){
			public void run(){
				Thread t=currentThread();
				System.out.println("���Ǵ������߳��ǣ�"+t);
				dosome();
			}
		};
		
		t2.start();
		
		System.out.println("name"+t.getName());
	}

	public static void dosome(){
		Thread t=Thread.currentThread();
		System.out.println("����dosome�������߳��ǣ�"+t);
	}
}
