package cube.QQ;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import cube.Utils.Resource;

public class QQ implements cube.QQ.VCodeSwing.QQInterface {
	QQGet qg;
	String QQNum;
	String QQPwd;
	public static final String HEXSTRING = "0123456789ABCDEF"; 

	String ptuin;
	boolean needCode=true;
	static String CodeUrl;
	String Code;
	QQNet qn=new QQNet();


	public QQ(String QQNum,String QQPwd){
		qg=new QQGet(QQNum);
		this.QQNum=QQNum;
		this.QQPwd=QQPwd;
	}

	public void QQLogin(){
		String o=qn.QQSendGet("https://ssl.ptlogin2.qq.com/check",qg.QQLogin1());
		String[] deo=DecodeQQ.LoginVC(o);
		if(deo[0].equals("0")){
			needCode=false;
		}else{
			needCode=true;
		}
		if(needCode){
			Code="";
			ptuin=deo[2];
			CodeUrl=deo[1];
			System.out.println(CodeUrl);
			VCodeSwing.ShowCode("https://ssl.captcha.qq.com/getimage?"+qg.QQGetCodeImage(),this/*new QQ()*/);
		}else{
			Code=deo[1];
			ptuin=deo[2];
			CodeUrl=deo[3];
			QQLogin2();
		}


	}

	@Override
	public void CodeOK(String Code) {
		this.Code=Code;

		QQLogin2();
	}

	@Override
	public void CodeFail() {
		System.exit(0);

	}
	public String mdP(){    

		/*String nptuin=ptuin.replace("\\x", "");
		String nptt = null;
		String[] ppt=new String[8];
		for(int y=0;y<=14;y=y+2){
			ppt[y/2]=nptuin.substring(y,y+2);
		}
		for(String ii:ppt){
			nptt+=HexAsc.convertHexToString(ii);
		}
		String i;
		System.out.println("QQPwd: "+QQPwd+"\nCode: "+Code.toUpperCase()+"\nNPTT: "+nptt+"\nPTUIN: "+ptuin);
		try {
			i = MD5.ToMD5(MD5.ToMD5(hexchar2bin(MD5.ToMD5(QQPwd))+nptt)+Code.toUpperCase());
		} catch (UnsupportedEncodingException e) {
			// TODO 自动生成的 catch 块
			return null;
		}*/
		Object t = null ;  
		try {    
			ScriptEngineManager m = new ScriptEngineManager();    
			ScriptEngine se = m.getEngineByName("javascript"); 
			Resource assistjs=new Resource("/cube/assist/encodepwd.js");
			se.eval(assistjs.getResource());    
			t = se.eval("QXWEncodePwd(\""+ptuin+"\",\""+QQPwd+"\",\""+Code+"\");");    
			return t.toString();    
		}catch (Exception e) {    
			e.printStackTrace();    
		}     
		return t.toString();    
		//return i;
	}   
	private void QQLogin2(){
		System.out.println(Code);
		//String dc=StringEscapeUtils.unescapeHtml(ptuin.replace("\\x", "\\u00"));
		//System.out.println(Hex());

		System.out.println(mdP());
		System.out.println(CodeUrl);
		qn.setSession_value("ptvfsession="+CodeUrl+";");
		String mdP=mdP();
		System.out.println("mdPassword: "+mdP);
		String QG=new QQGet(QQNum).QQLogin2(mdP, Code);
		String l=qn.QQSendGet("https://ssl.ptlogin2.qq.com/login",QG);
		String[] p=DecodeQQ.Login2(l);
		if(p[0].equals("0")){
			try {
				QQLogin3(p);
			} catch (UnsupportedEncodingException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}else{
			System.out.println(p[2]);
		}
	} 
	public void QQLogin3(String[] p) throws UnsupportedEncodingException{
		System.out.println("\nGCallBack: \n"+qn.QQSendGet(p[2], ""));
		String season=qn.getCookie();
		String ys=season.substring(season.indexOf("ptwebqq=")+8,season.indexOf(";"));
		String o=qn.QQSendPost("http://d.web2.qq.com/channel/login2", qg.QQLogin3(ys), "http://d.web2.qq.com/proxy.html?v=20130916001&callback=1&id=2");
		System.out.println(qg.QQLogin3(ys)+"\n\n"+o);
	}  
} 
