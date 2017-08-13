package cube.Utils;

public class Asc {
	public String Asc2Str(int s){
		String q=String.valueOf(s);
	    return String.valueOf((char)Integer.parseInt(q));

	}
	public Integer Str2Asc(String i){
		 //asciiתString
	  char[] u=i.toCharArray();
      return (int)u[0];
	}
	 
}