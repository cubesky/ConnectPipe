package cube.QQ;

public class DecodeQQ {
	
	//ptui_checkVC('0','!SSQ','\x00\x00\x00\x00\x1b\xc4\x5c\x57', '516c66919afe08060d2e560ad7ec2d554ad197089d15ad8b272e8f1e6fe8a7b0fac63404b95a09a24aca42bd929ddfdd');
	//Done
	public static String[] LoginVC(String get){
		String a=get.substring(13,get.length()-2);
		System.out.println(a);
		String[] b=a.split(",");
		if(b.length==3){
	//		b[2].substring(1);
		}else{
		  b[3]=b[3].substring(1);
		}
		for(int c=0;c<=b.length-1;c++){
			if(b[c]!=null && !b[c].equals("'"))
				b[c]=b[c].substring(1, b[c].length()-1);
			System.out.println(b[c]);
		}
		return b;
	}
	public static String[] Login2(String str){
		String s=str.substring(7, str.length()-1);
		String[] ss=s.split(",");
		String st[]=new String[ss.length];
		int i=0;
		for(String c:ss){
			st[i]=c.substring(1,c.length()-1);
			i++;
		}
		return st;
		
	}
}
