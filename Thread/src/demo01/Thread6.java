package demo01;
/**
 * �ػ��̣߳��ֽ�����̨�߳�
 * ʹ������ǰ̨�߳�û������
 * ��ͬ�����ڽ�����ʱ�����������е�����ǰ̨�̶߳�����ʱ��
 * ���ۺ�̨�߳��Ƿ������ж�Ҫǿ�ƽ����������ս������̡�
 * @author dell
 *
 */
public class Thread6 {

	public static void main(String[] args) {
		Thread rose=new Thread(){
			public void run(){
				for(int i=0;i<10;i++){
					System.out.println("rose:Let me go!");
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				System.out.println("rose:��������������...");
				System.out.println("Ч������ͨ��");
			}
		};
		Thread jack=new Thread(){
			public void run(){
				while(true){
					System.out.println("jack:you jump...");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		rose.start();
		jack.setDaemon(true);//��̨�߳�
		jack.start();
	}
}
