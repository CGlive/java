package demo01;
/*
 * ʹ�������ڲ��ഴ���߳�
 */
public class Thread2 {

	public static void main(String[] args) {
		//��һ��ģʽ
		Thread t1=new Thread(){
			public void run(){
				for(int i=0;i<1000;i++){
					System.out.println("����˭����");
				}
			}
		};
		
		
		//�ڶ���ģʽ
//		Runnable r=new Thread(){
//			public void run(){
//				for(int i=0;i<100;i++){
//					System.out.println("���ǲ�ˮ��ģ�");
//				}
//			}
//		};
//		Thread t2=new Thread(r);
//		t2.start();
		
		Thread t2=new Thread(new Runnable(){
			public void run(){
				for(int i=0;i<1000;i++){
					System.out.println("���ǲ�ˮ��ģ�");
				}
			}
		});
		t1.setPriority(Thread.MAX_PRIORITY);
		t2.setPriority(Thread.MIN_PRIORITY);
		t1.start();
		t2.start();
	}
}
