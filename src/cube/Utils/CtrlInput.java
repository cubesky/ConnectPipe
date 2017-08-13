package cube.Utils;

public class CtrlInput {
    
    public static String getInput(){
    	//数组缓冲
        byte[] b = new byte[1024];
        //有效数据个数
        int n = 0;
        try{
                          //读取数据
                          n = System.in.read(b);
                          //转换为字符串
                          String s = new String(b,0,n - 2);
                          return s;
        }catch(Exception e){}
		return "";

    }
	
}
