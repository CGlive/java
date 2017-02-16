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
//��������//
public class ShootGame extends JPanel {
	public static final int WIDTH=400;//���ڿ�
	public static final int HEIGHT=654;//���ڸ�
	public static final int START=0;
	public static final int RUNNING=1;
	public static final int PAUSE=2;
	public static final int GAME_OVER=3;
	private int state=0;
	
	public static BufferedImage background;//����ͼ
	public static BufferedImage start;//����ͼ
	public static BufferedImage pause;//��ͣͼ
	public static BufferedImage gameover;//��Ϸ����ͼ
	public static BufferedImage airplane;//�л�
	public static BufferedImage bee;//�۷�
	public static BufferedImage bullet;//�ӵ�
	public static BufferedImage hero0;//Ӣ�ۻ�0
	public static BufferedImage hero1;//Ӣ�ۻ�1
	
	private Hero hero=new Hero();
	private FlyingObject[] flyings={};
	private Bullet[] bullets={};
	

	static{//��ʼ����̬��Դ(����ͼƬ)
		try{//��ȡͼƬ����̬ͼƬ������
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
	//��������(�л�+С�۷�)����
	public static FlyingObject nextOne(){
		Random rand=new Random();
		int type=rand.nextInt(20);
		if(type==0){//�����Ϊ0���򷵻��۷����
			return new Bee();
		}else{
			return new  Airplane();
		}
	}
	//����(����+С�۷�)�볡
	public void enterAction(){
		flyEnteredIndex++;
		if(flyEnteredIndex % 40==0){
			FlyingObject obj=nextOne();
			flyings=Arrays.copyOf(flyings,flyings.length+1);
			flyings[flyings.length-1]=obj;
		}
	}
	//��������һ��
	public void stepAction(){//10������һ��
		hero.step();
		for(int i=0;i<flyings.length;i++){
			flyings[i].step();//������һ��
		}
		for(int i=0;i<bullets.length;i++){
			bullets[i].step();//�ӵ���һ��
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
	private Timer timer;//��ʱ��
	private int intervel=10;//ʱ��ļ��/����
	//���������ִ��
    public void action(){
		MouseAdapter l=new MouseAdapter(){
			public void mouseMoved(MouseEvent t){
				if(state==RUNNING){
				int x=t.getX();//����X����
				int y=t.getY();//����Y����
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
				enterAction();//�����볡
				stepAction();//��������һ��
				shootAction();//Ӣ�ۻ������ӵ�
				bangAction();
				outOfBoundsAction();
				checkGameOverAction();
				}
				repaint();
			}
		},intervel,intervel);
	}
	//��дpaint()����//
    public void paint(Graphics g){
    	g.drawImage(background,0,0,null);//������ͼ
    	paintHero(g);//��Ӣ�ۻ�
    	paintFlyingObjects(g);//������(�л�+С�۷�)
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
		JFrame frame=new JFrame("Fly");//�������ڶ���
		ShootGame game=new ShootGame();//����������
		frame.add(game);//�������ӵ�������
		frame.setSize(WIDTH,HEIGHT);//���ô��ڴ�С
		frame.setAlwaysOnTop(true);//���ڶ���
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);//���ô��ڿɼ�
		
		game.action();//��������ִ��
		
    }

}
