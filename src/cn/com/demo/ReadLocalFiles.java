package cn.com.demo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

public class ReadLocalFiles {

	public static void main(String[] args) throws Exception {
		//readLocaResource();
		readAndWriteProperties();
	}
	/**
	 * 读取并且生成properties文件
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	private static void readAndWriteProperties() throws IOException, FileNotFoundException {
		Properties prop = new Properties();
		InputStream in = ReadLocalFiles.class.getResourceAsStream("a.properties");
		prop.load(in);//加载属性列表
		Iterator<String> it=prop.stringPropertyNames().iterator();
		while(it.hasNext()){
			String key=it.next();
			System.out.println(key+":"+prop.getProperty(key));
		}
		in.close();
		
		///保存属性到b.properties文件
		FileOutputStream oFile = new FileOutputStream("src/cn/com/demo/b.properties", true);//true表示追加打开
		prop.setProperty("phone", "10086");
		prop.store(oFile, "The New properties file");
		oFile.close();
	}
	/**
	 * 读取本地文件
	 * @throws IOException
	 */
	private static void readLocaResource() throws IOException {
		InputStream in = ReadLocalFiles.class.getResourceAsStream("a.xml");
		int len = 0;
		byte[] b = new byte[1024];
		while (-1 != (len = in.read(b))) {
			System.out.println(new String(b,0,len));  
		}
	}
	
}
