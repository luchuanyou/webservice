package cn.com.demo.down;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Javaԭ����API�����ڷ���HTTP���󣬼�java.net.URL��java.net.URLConnection����ЩAPI�ܺ��á��ܳ��ã�
 * ��������㣻
 * 
 * 1.ͨ��ͳһ��Դ��λ����java.net.URL����ȡ��������java.net.URLConnection�� 2.��������Ĳ��� 3.��������
 * 4.������������ʽ��ȡ�������� 5.�ر�������
 * 
 * @author H__D
 *
 */
public class HttpConnectionUtil {


    /**
     * 
     * @param urlPath
     *            ����·��
     * @param downloadDir
     *            ���ش��Ŀ¼
     * @return ���������ļ�
     */
    public static File downloadFile(String urlPath, String downloadDir) {
        File file = null;
        try {
            // ͳһ��Դ
            URL url = new URL(urlPath);
            // ������ĸ��࣬������
            URLConnection urlConnection = url.openConnection();
            // http��������
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
            // �趨����ķ�����Ĭ����GET
            httpURLConnection.setRequestMethod("GET");
            // �����ַ�����
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            // �򿪵��� URL ���õ���Դ��ͨ�����ӣ������δ�������������ӣ���
            httpURLConnection.connect();

            // �ļ���С
            int fileLength = httpURLConnection.getContentLength();

            // �ļ���
            String filePathUrl = httpURLConnection.getURL().getFile();
            System.out.println("filePathUrl:"+filePathUrl);
            System.out.println(File.separator+File.separatorChar+File.pathSeparator+File.pathSeparatorChar);
            String fileFullName = filePathUrl.substring(filePathUrl.lastIndexOf(File.separatorChar) + 1);

            System.out.println("file length---->" + fileLength+fileFullName);

            URLConnection con = url.openConnection();

            BufferedInputStream bin = new BufferedInputStream(httpURLConnection.getInputStream());

            String path = downloadDir + File.separatorChar + fileFullName;
            file = new File(path);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            OutputStream out = new FileOutputStream(file);
            int size = 0;
            int len = 0;
            byte[] buf = new byte[1024];
            while ((size = bin.read(buf)) != -1) {
                len += size;
                out.write(buf, 0, size);
                // ��ӡ���ذٷֱ�
                // System.out.println("������-------> " + len * 100 / fileLength +
                // "%\n");
            }
            bin.close();
            out.close();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            return file;
        }

    }

    public static void main(String[] args) {

        // �����ļ�����
        downloadFile("http://pic6.huitu.com/res/20130116/84481_20130116142820494200_1.jpg", "D:/");

    }

}