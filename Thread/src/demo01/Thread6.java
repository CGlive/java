package demo01;
/**
 * 守护线程，又叫做后台线程
 * 使用上与前台线程没有区别。
 * 不同点在于结束的时机：当进程中的所有前台线程都结束时，
 * 无论后台线程是否还在运行都要强制结束，并最终结束进程。
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
				System.out.println("rose:啊啊啊啊啊啊啊...");
				System.out.println("效果：噗通！");
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
		jack.setDaemon(true);//后台线程
		jack.start();
	}
}
