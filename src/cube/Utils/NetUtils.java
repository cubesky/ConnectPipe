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
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性  
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.setUseCaches(true);
            
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }

            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(),"UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }

        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
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
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(),"UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
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
     * 根据图片网络地址获取图片的byte[]类型数据
     *
     * @param urlPath
     *            图片网络地址
     * @return 图片数据
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
     * 读取InputStream数据，转为byte[]数据类型
     * @param is
     *            InputStream数据
     * @return 返回byte[]数据
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
