package com.tarena.shoot;
import java.util.Random;
//敌机：是飞行物，也是敌人
public class Airplane extends FlyingObject implements Enemy {
	private int speed=2;//走步的步数(数据一般私有化)
	//构造方法//
	public Airplane(){
		image=ShootGame.airplane;
		width=image.getWidth();
		height=image.getHeight();
		Random rand=new Random();
		x=rand.nextInt(ShootGame.WIDTH-this.width);//x:0到窗口宽减敌机的宽
		y=-this.height;//负的敌机的高
		
	}
	//重写getScore方法//
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
