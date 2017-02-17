package demo01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class IOTest {

	public static void main(String[] args) {
		String s[]={"�þò���","�������","����ϵ"};
		File file=new File("D:/xt.txt");//����һ���ļ�����
		try{
			//����FileWriter����
			FileWriter fw=new FileWriter(file);
			//����BufferedWriter����
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
			//����FileReader����
			FileReader fr=new FileReader(file);
			BufferedReader br=new BufferedReader(fr);
			String str=null;
			int k=0;
			while((str=br.readLine())!=null){
				k++;
				System.out.println("��"+k+"��:"+str);
			}
			br.close();
			fr.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
