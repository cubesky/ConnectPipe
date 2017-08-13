package cube.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class Resource {
		String res;
		public Resource(String r){
			res=r;
		}
        public String getResource() throws IOException{
                //返回读取指定资源的输入流
                InputStream is=this.getClass().getResourceAsStream(res); 
                BufferedReader br=new BufferedReader(new InputStreamReader(is));
                String s="";  
                String ss="";
                while((s=br.readLine())!=null)   ss=ss+"\n"+s;
                return ss;
        }
}  