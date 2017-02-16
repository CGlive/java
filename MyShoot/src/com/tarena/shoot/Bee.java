package com.tarena.shoot;
import java.util.Random;
//小蜜蜂：是飞行物，也是奖励
public class Bee extends FlyingObject implements Award {
	private int xSpeed=1;//x走步步数
	private int ySpeed=2;//y走步步数
	private int awardType; //奖励类型
	//构造方法//
	public Bee(){
		image=ShootGame.bee;//图片
		width=image.getWidth();//宽
		height=image.getHeight();//高
		Random rand=new Random();//随机数对象
		x=rand.nextInt(ShootGame.WIDTH-this.width);//0到屏幕的宽-之间的随机数蜜蜂宽
		y=-this.height;
		awardType=rand.nextInt(2);
		
	}
	//重写getType()方法//
	public int getType(){
		return awardType;//返回奖励类型
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
