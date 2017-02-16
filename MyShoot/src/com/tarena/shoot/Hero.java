package com.tarena.shoot;
import java.awt.image.BufferedImage;
//英雄机：是飞行物//
public class Hero extends FlyingObject {
     private int life;//命
     private int doubleFire;//火力值
     private BufferedImage[] images;//图片数组
     private int index;//图片切换的频率
     //构造方法//
     public Hero(){
    	 image=ShootGame.hero0;
    	 width=image.getWidth();
    	 height=image.getHeight();
    	 x=150;
    	 y=400;
    	 life=3;
    	 doubleFire=0;
    	 images=new BufferedImage[]{ShootGame.hero0,ShootGame.hero1};
    	 index=0;
     }
     public void step(){
     	index++;
     	int a=index/10;
     	int b=a%images.length;
     	image=images[a%images.length];
     }
     
     public Bullet[] shoot(){
    	 int xStep=this.width/4;
    	 if(doubleFire>0){
    		 Bullet[] bullets=new Bullet[2];
    		 bullets[0]=new Bullet(this.x+1*xStep,this.y-20);
    		 bullets[1]=new Bullet(this.x+3*xStep,this.y-20);
    		 doubleFire-=2;
    		 return bullets;
    	 }else{
    		 Bullet[] bullets=new Bullet[1];
    		 bullets[0]=new Bullet(this.x+2*xStep,this.y-20);
    		 return bullets;
    	 }
     }
     
     public void moveTo(int x,int y){
    	this.x=x-this.width/2;
    	this.y=y-this.height/2; 
     }
     
     public void addLife(){
    	 life++;
     }
     public int getLife(){
    	 return life;
     }
     public void subtractLife(){
    	 life--;
     }
     public void addDoubleFire(){
    	 doubleFire +=40;
     }
     public void setDoubleFire(int doubleFire){
    	 this.doubleFire=doubleFire;
     }
     public boolean outOfBounds(){
     	return false;
     }
     public boolean hit(FlyingObject other){
    	 int x1=other.x-this.width/2;
    	 int x2=other.x+other.width+this.width/2;
    	 int y1=other.y-this.height/2;
    	 int y2=other.y+other.height+this.height/2;
    	 int hx=this.x+this.width/2;
    	 int hy=this.y+this.height/2;
    	 return hx>x1 && hx<x2
    			 &&
    			 hy>y1 && hy<y2;
     }
}
