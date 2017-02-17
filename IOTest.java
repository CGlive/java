package demo01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class IOTest {

	public static void main(String[] args) {
		String s[]={"好久不见","最近好吗","常联系"};
		File file=new File("D:/xt.txt");//创建一个文件对象
		try{
			//创建FileWriter对象
			FileWriter fw=new FileWriter(file);
			//创建BufferedWriter对象
			BufferedWriter bw=new BufferedWriter(fw);
			for(int i=0;i<s.length;i++){
				bw.write(s[i]);
				bw.newLine();
			}
			bw.close();
			fw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		try{
			//创建FileReader对象
			FileReader fr=new FileReader(file);
			BufferedReader br=new BufferedReader(fr);
			String str=null;
			int k=0;
			while((str=br.readLine())!=null){
				k++;
				System.out.println("第"+k+"行:"+str);
			}
			br.close();
			fr.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
