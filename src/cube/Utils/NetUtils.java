package cube.Utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class NetUtils {
	List<String> cookie;
	public static void OpenURL(String url) throws IOException, URISyntaxException{
		URI uri = new java.net.URI(url);  
		java.awt.Desktop.getDesktop().browse(uri); 
	}
	/**
     * ��ָ��URL����GET����������
     * 
     * @param url
     *            ���������URL
     * @param param
     *            ����������������Ӧ���� name1=value1&name2=value2 ����ʽ��
     * @return URL ������Զ����Դ����Ӧ���
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // �򿪺�URL֮�������
            URLConnection connection = realUrl.openConnection();
            // ����ͨ�õ���������  
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // ����ʵ�ʵ�����
            connection.setUseCaches(true);
            
            connection.connect();
            // ��ȡ������Ӧͷ�ֶ�
            Map<String, List<String>> map = connection.getHeaderFields();
            // �������е���Ӧͷ�ֶ�
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }

            // ���� BufferedReader����������ȡURL����Ӧ
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(),"UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }

        } catch (Exception e) {
            System.out.println("����GET��������쳣��" + e);
            e.printStackTrace();
        }
        // ʹ��finally�����ر�������
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
      
        return result;
    }

    /**
     * ��ָ�� URL ����POST����������
     * 
     * @param url
     *            ��������� URL
     * @param param
     *            ����������������Ӧ���� name1=value1&name2=value2 ����ʽ��
     * @return ������Զ����Դ����Ӧ���
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // �򿪺�URL֮�������
            URLConnection conn = realUrl.openConnection();
            // ����ͨ�õ���������
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // ����POST�������������������
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // ��ȡURLConnection�����Ӧ�������
            out = new PrintWriter(conn.getOutputStream());
            // �����������
            out.print(param);
            // flush������Ļ���
            out.flush();
            // ����BufferedReader����������ȡURL����Ӧ
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(),"UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("���� POST ��������쳣��"+e);
            e.printStackTrace();
        }
        //ʹ��finally�����ر��������������
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }    
    
    
    
    
    /**
     * ����ͼƬ�����ַ��ȡͼƬ��byte[]��������
     *
     * @param urlPath
     *            ͼƬ�����ַ
     * @return ͼƬ����
     */
    
  
   public static byte[] getImageFromURL(String urlPath) { 
           byte[] data = null; 
           InputStream is = null; 
           HttpURLConnection conn = null; 
           try { 
               URL url = new URL(urlPath); 
               conn = (HttpURLConnection) url.openConnection(); 
               conn.setDoInput(true); 
               // conn.setDoOutput(true); 
               conn.setRequestMethod("GET"); 
               conn.setConnectTimeout(6000); 
               is = conn.getInputStream(); 
               if (conn.getResponseCode() == 200) { 
                   data = readInputStream(is); 
               } else{ 
                   data=null; 
               } 
           } catch (MalformedURLException e) { 
               e.printStackTrace(); 
           } catch (IOException e) { 
               e.printStackTrace(); 
           } finally { 
               try { 
                   if(is != null){ 
                       is.close(); 
                   }                
               } catch (IOException e) { 
                   e.printStackTrace(); 
               } 
               conn.disconnect();           
           } 
           return data; 
       } 
    
    
        /**
     * ��ȡInputStream���ݣ�תΪbyte[]��������
     * @param is
     *            InputStream����
     * @return ����byte[]����
     */
    
 
   public static byte[] readInputStream(InputStream is) { 
           ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
           byte[] buffer = new byte[1024]; 
           int length = -1; 
           try { 
               while ((length = is.read(buffer)) != -1) { 
                   baos.write(buffer, 0, length); 
               } 
               baos.flush(); 
           } catch (IOException e) { 
               e.printStackTrace(); 
           } 
           byte[] data = baos.toByteArray(); 
           try { 
               is.close(); 
               baos.close(); 
           } catch (IOException e) { 
               e.printStackTrace(); 
           } 
           return data; 
       } 


}
