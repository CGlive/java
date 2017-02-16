package com.tarena.shoot;
import java.util.Random;
//�л����Ƿ����Ҳ�ǵ���
public class Airplane extends FlyingObject implements Enemy {
	private int speed=2;//�߲��Ĳ���(����һ��˽�л�)
	//���췽��//
	public Airplane(){
		image=ShootGame.airplane;
		width=image.getWidth();
		height=image.getHeight();
		Random rand=new Random();
		x=rand.nextInt(ShootGame.WIDTH-this.width);//x:0�����ڿ���л��Ŀ�
		y=-this.height;//���ĵл��ĸ�
		
	}
	//��дgetScore����//
    public int getScore(){
    	return 5;
    }
    
    public void step(){
       y+=speed;	
    }
    
    public boolean outOfBounds(){
    	return this.y>ShootGame.HEIGHT;
    }
}
