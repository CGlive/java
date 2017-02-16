package com.tarena.shoot;
//子弹类：是飞行物//
public class Bullet extends FlyingObject {
     private int speed=3;//走步的步数
     //构造方法x:子弹的x y:子弹的y//
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
