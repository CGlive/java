package com.tarena.shoot;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Arrays;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;
//主程序类//
public class ShootGame extends JPanel {
	public static final int WIDTH=400;//窗口宽
	public static final int HEIGHT=654;//窗口高
	public static final int START=0;
	public static final int RUNNING=1;
	public static final int PAUSE=2;
	public static final int GAME_OVER=3;
	private int state=0;
	
	public static BufferedImage background;//背景图
	public static BufferedImage start;//启动图
	public static BufferedImage pause;//暂停图
	public static BufferedImage gameover;//游戏结束图
	public static BufferedImage airplane;//敌机
	public static BufferedImage bee;//蜜蜂
	public static BufferedImage bullet;//子弹
	public static BufferedImage hero0;//英雄机0
	public static BufferedImage hero1;//英雄机1
	
	private Hero hero=new Hero();
	private FlyingObject[] flyings={};
	private Bullet[] bullets={};
	

	static{//初始化静态资源(加载图片)
		try{//读取图片到静态图片变量中
			background=ImageIO.read(ShootGame.class.getResource("background.png"));
			start=ImageIO.read(ShootGame.class.getResource("start.png"));
			pause=ImageIO.read(ShootGame.class.getResource("pause.png"));
			gameover=ImageIO.read(ShootGame.class.getResource("gameover.png"));
			airplane=ImageIO.read(ShootGame.class.getResource("airplane.png"));
			bee=ImageIO.read(ShootGame.class.getResource("bee.png"));
			bullet=ImageIO.read(ShootGame.class.getResource("bullet.png"));
			hero0=ImageIO.read(ShootGame.class.getResource("hero0.png"));
			hero1=ImageIO.read(ShootGame.class.getResource("hero1.png"));
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
     int flyEnteredIndex=0;
	//创建敌人(敌机+小蜜蜂)对象
	public static FlyingObject nextOne(){
		Random rand=new Random();
		int type=rand.nextInt(20);
		if(type==0){//随机数为0，则返回蜜蜂对象
			return new Bee();
		}else{
			return new  Airplane();
		}
	}
	//敌人(敌人+小蜜蜂)入场
	public void enterAction(){
		flyEnteredIndex++;
		if(flyEnteredIndex % 40==0){
			FlyingObject obj=nextOne();
			flyings=Arrays.copyOf(flyings,flyings.length+1);
			flyings[flyings.length-1]=obj;
		}
	}
	//飞行物走一步
	public void stepAction(){//10毫秒走一次
		hero.step();
		for(int i=0;i<flyings.length;i++){
			flyings[i].step();//敌人走一步
		}
		for(int i=0;i<bullets.length;i++){
			bullets[i].step();//子弹走一步
		}
	}
	int shootIndex=0;
	public void shootAction(){
		shootIndex++;
		if(shootIndex%30==0){
			Bullet[] bs=hero.shoot();
			bullets=Arrays.copyOf(bullets,bullets.length+bs.length);
			System.arraycopy(bs, 0,bullets,bullets.length-bs.length,bs.length);
		}
	}
	int score=0;
    public void bangAction(){
		for(int i=0;i<bullets.length;i++){
			bang(bullets[i]);
		}
	}
	public void bang(Bullet bullet){
		int index=-1;
		for(int i=0;i<flyings.length;i++){
			FlyingObject f=flyings[i];
			if(f.shootBy(bullet)){
				index=i;
				break;
			}
		}
		if(index!=-1){
			FlyingObject one=flyings[index];
			if(one instanceof Enemy){
				Enemy e=(Enemy)one;
				score+=e.getScore();
			}
			if(one instanceof Award){
				Award a=(Award)one;
				int type=a.getType();
				switch(type){
				case Award.DOUBLE_FIRE:
					hero.addDoubleFire();
					break;
				case Award.LIFE:
					hero.addLife();
					break;
				}
			}
			FlyingObject t=flyings[index];
			flyings[index]=flyings[flyings.length-1];
			flyings[flyings.length-1]=t;
			flyings=Arrays.copyOf(flyings,flyings.length-1);
		}
	}
	public void outOfBoundsAction(){
		int index=0;
		FlyingObject[] flyingLives=new FlyingObject[flyings.length];
		for(int i=0;i<flyings.length;i++){
			FlyingObject f=flyings[i];
			if(!f.outOfBounds()){
				flyingLives[index]=f;
				index++;
				
			}
		}
		flyings=Arrays.copyOf(flyingLives,index);
		index=0;
		Bullet[] bulletLives=new Bullet[bullets.length];
		for(int i=0;i<bullets.length;i++){
			Bullet b=bullets[i];
			if(!b.outOfBounds()){
				bulletLives[index]=b;
				index++;
				
			}
		}
	}
	public void checkGameOverAction(){
		if(isGameOver()){
			state=GAME_OVER;
		}
	}
	public boolean isGameOver(){
		for(int i=0;i<flyings.length;i++){
			FlyingObject f=flyings[i];
			if(hero.hit(f)){
				hero.subtractLife();
				hero.setDoubleFire(0);
				FlyingObject t=flyings[i];
				flyings[i]=flyings[flyings.length-1];
				flyings=Arrays.copyOf(flyings,flyings.length-1);
			}
		}
		return hero.getLife()<=0;
	}
	private Timer timer;//定时器
	private int intervel=10;//时间的间隔/毫秒
	//启动程序的执行
    public void action(){
		MouseAdapter l=new MouseAdapter(){
			public void mouseMoved(MouseEvent t){
				if(state==RUNNING){
				int x=t.getX();//鼠标的X坐标
				int y=t.getY();//鼠标的Y坐标
				hero.moveTo(x, y);
				}
			}
			public void mouseClicked(MouseEvent t){
			   switch(state){
			   case START:
				   state=RUNNING;
				   break;
			   case GAME_OVER:
				   score=0;
				   hero= new Hero();
				   flyings =new FlyingObject[0];
				   bullets=new Bullet[0];
				   state=START;
				   break;
			   }
			}
			public void mouseExited(MouseEvent t){
				if(state==RUNNING){
					state=PAUSE;
				}
			}
			public void mouseEntered(MouseEvent t){
				if(state==PAUSE){
					state=RUNNING;
				}
			}
		};
		this.addMouseListener(l);
		this.addMouseMotionListener(l);
		timer=new Timer();
		timer.schedule(new TimerTask(){
			public void run(){
				if(state==RUNNING){
				enterAction();//敌人入场
				stepAction();//飞行物走一步
				shootAction();//英雄机发射子弹
				bangAction();
				outOfBoundsAction();
				checkGameOverAction();
				}
				repaint();
			}
		},intervel,intervel);
	}
	//重写paint()方法//
    public void paint(Graphics g){
    	g.drawImage(background,0,0,null);//画背景图
    	paintHero(g);//画英雄机
    	paintFlyingObjects(g);//画敌人(敌机+小蜜蜂)
    	paintBullets(g);
    	paintScore(g);
    	paintState(g);
    }
    public void paintHero(Graphics g){
    	g.drawImage(hero.image,hero.x,hero.y,null);
    }
    public void paintFlyingObjects(Graphics g){
    	for(int i=0;i<flyings.length;i++){
    		FlyingObject f=flyings[i];
    		g.drawImage(f.image,f.x,f.y,null);
    	}
    }
    public void paintBullets(Graphics g){
    	for(int i=0;i<bullets.length;i++){
    		Bullet b=bullets[i];
    		g.drawImage(b.image,b.x,b.y,null);
    	}
 	
    }
    public void paintScore(Graphics g){
    	g.setColor(new Color(0xFF0000));
    	g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,22));
    	g.drawString("SCORE:"+score,10,25);
    	g.drawString("LIFE: "+hero.getLife(), 10, 45);
    }
    public void paintState(Graphics g){
    	switch(state){
    	case START:
    		g.drawImage(start,0,0,null);
    		break;
    	case PAUSE:
    		g.drawImage(pause,0,0,null);
    		break;
    	case GAME_OVER:
    		g.drawImage(gameover,0,0,null);
    		break;
    	}
    }
    public static void main(String[] args) {
		JFrame frame=new JFrame("Fly");//创建窗口对象
		ShootGame game=new ShootGame();//创建面板对象
		frame.add(game);//将面板添加到窗口中
		frame.setSize(WIDTH,HEIGHT);//设置窗口大小
		frame.setAlwaysOnTop(true);//置于顶端
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);//设置窗口可见
		
		game.action();//启动程序执行
		
    }

}
