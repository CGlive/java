package com.tarena.shoot;
import java.util.Random;
//С�۷䣺�Ƿ����Ҳ�ǽ���
public class Bee extends FlyingObject implements Award {
	private int xSpeed=1;//x�߲�����
	private int ySpeed=2;//y�߲�����
	private int awardType; //��������
	//���췽��//
	public Bee(){
		image=ShootGame.bee;//ͼƬ
		width=image.getWidth();//��
		height=image.getHeight();//��
		Random rand=new Random();//���������
		x=rand.nextInt(ShootGame.WIDTH-this.width);//0����Ļ�Ŀ�-֮���������۷��
		y=-this.height;
		awardType=rand.nextInt(2);
		
	}
	//��дgetType()����//
	public int getType(){
		return awardType;//���ؽ�������
	}
public void step(){
    x+=xSpeed;
    y+=ySpeed;
    if(x>ShootGame.WIDTH-this.width){
    	xSpeed=-1;
    }
    if(x<0){
    	xSpeed=1;
    }
    }
public boolean outOfBounds(){
	return this.y>ShootGame.HEIGHT;
}

}
