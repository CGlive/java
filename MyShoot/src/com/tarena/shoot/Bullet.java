package com.tarena.shoot;
//�ӵ��ࣺ�Ƿ�����//
public class Bullet extends FlyingObject {
     private int speed=3;//�߲��Ĳ���
     //���췽��x:�ӵ���x y:�ӵ���y//
     public Bullet(int x,int y){
    	 image=ShootGame.bullet;
    	 width=image.getWidth(); 
    	 height=image.getHeight();
    	 this.x=x;
    	 this.y=y;
    }
     public void step(){
         y-=speed;	
     }
     public boolean outOfBounds(){
     	return this.y<-this.height;
     }

}
