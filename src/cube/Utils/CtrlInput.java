package cube.Utils;

public class CtrlInput {
    
    public static String getInput(){
    	//���黺��
        byte[] b = new byte[1024];
        //��Ч���ݸ���
        int n = 0;
        try{
                          //��ȡ����
                          n = System.in.read(b);
                          //ת��Ϊ�ַ���
                          String s = new String(b,0,n - 2);
                          return s;
        }catch(Exception e){}
		return "";

    }
	
}
