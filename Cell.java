package Day06;
//格子类
public class Cell {
	int row;
	int col;
	Cell(int row,int col){
		
	}
	public String getCellInfo(){
		return row+","+col;
	}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*//对象(格子)共有的属性/特征----成员变量
	int row;//行号
	int col;//列号
	
	//对象共有的行为---方法
	void drop(){//下落一格
		row++;
	}void moveLeft(int n){//左移n格(方法写活用参数)
		col-=n;
	}void moveRight(int n){//右移n格
		col+=n;
	}String getCellInfo(){//返回格子位置信息(返回值为字符串类型)
		return row+","+col;
	}*/


