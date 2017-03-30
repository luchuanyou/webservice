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
	 * ��ȡ��������properties�ļ�
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	private static void readAndWriteProperties() throws IOException, FileNotFoundException {
		Properties prop = new Properties();
		InputStream in = ReadLocalFiles.class.getResourceAsStream("a.properties");
		prop.load(in);//���������б�
		Iterator<String> it=prop.stringPropertyNames().iterator();
		while(it.hasNext()){
			String key=it.next();
			System.out.println(key+":"+prop.getProperty(key));
		}
		in.close();
		
		///�������Ե�b.properties�ļ�
		FileOutputStream oFile = new FileOutputStream("src/cn/com/demo/b.properties", true);//true��ʾ׷�Ӵ�
		prop.setProperty("phone", "10086");
		prop.store(oFile, "The New properties file");
		oFile.close();
	}
	/**
	 * ��ȡ�����ļ�
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
