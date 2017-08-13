package cube.QQ;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;


public class QQNet {
	String session_value;
	String Cookie;
	
	public String getCookie() {
		return Cookie;
	}

	public void setCookie(String cookie) {
		Cookie = cookie;
	}

	public String getSession_value() {
		return session_value;
	}

	public void setSession_value(String session_value) {
		this.session_value = session_value;
	}

	public QQNet(){
		
	}
	
	public String QQSendGet(String Url,String param){
		String result = "";
        BufferedReader in = null;
		String urlNameString = Url + "?" + param;
		try {
			URL myUrl = new URL(urlNameString); 
			URLConnection urlConn = myUrl.openConnection(); 
			urlConn.setRequestProperty("accept", "*/*");
			urlConn.setRequestProperty("connection", "Keep-Alive");
			urlConn.setRequestProperty("user-agent",
			        "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0");
			urlConn.setRequestProperty("Host", "ptlogin4.web2.qq.com");
			if(Cookie!=null){
				urlConn.setRequestProperty("Cookie", Cookie);
				System.out.println("WantedHeader:  "+Cookie);
				System.out.println("Header:  "+urlConn.getRequestProperty("Cookie"));
				
			}
			urlConn.connect();

			// 获取所有响应头字段
			Map<String, List<String>> map = urlConn.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
			    System.out.println(key + "--->" + map.get(key));
			}
			if(urlConn.getHeaderField("Set-Cookie")!=null && !urlConn.getHeaderField("Set-Cookie").equalsIgnoreCase("")){
			    String tmpCookie=map.get("Set-Cookie").toString();
				Cookie=tmpCookie.substring(1, tmpCookie.length()-1);
			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					urlConn.getInputStream(),"UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
			    result += line;
			}
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
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
	
	public String QQSendPost(String Url,String param,String reffer){
		String result = "";
        BufferedReader in = null;
		String urlNameString = Url + "?" + param;
		try {
			URL myUrl = new URL(urlNameString); 
			URLConnection urlConn = myUrl.openConnection(); 
			urlConn.setRequestProperty("accept", "*/*");
			urlConn.setRequestProperty("connection", "Keep-Alive");
			urlConn.setRequestProperty("user-agent",
			        "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0");
			urlConn.setRequestProperty("Referer", reffer);
			urlConn.setRequestProperty("Host", "d.web2.qq.com");
			if(Cookie!=null){
				urlConn.setRequestProperty("Cookie", Cookie);
				System.out.println("WantedHeader:  "+Cookie);
				System.out.println("Header:  "+urlConn.getRequestProperty("Cookie"));
				
			}
			urlConn.setDoInput(true);
			urlConn.setDoOutput(true);
			urlConn.connect();

			// 获取所有响应头字段
			Map<String, List<String>> map = urlConn.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
			    System.out.println(key + "--->" + map.get(key));
			}
			if(urlConn.getHeaderField("Set-Cookie")!=null && !urlConn.getHeaderField("Set-Cookie").equalsIgnoreCase("")){
			    String tmpCookie=map.get("Set-Cookie").toString();
				Cookie=tmpCookie.substring(1, tmpCookie.length()-1);
			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					urlConn.getInputStream(),"UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
			    result += line;
			}
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
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
}
