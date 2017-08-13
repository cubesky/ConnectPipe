package cube.QQ;

public class QQGet {
	String QQ;
    public QQGet(String QQNum){
    	QQ=QQNum;
    }
    public String QQLogin1(){
    	return "uin="+QQ+"&appid=1003903&js_ver=10034&js_type=0&login_sig=ofR4sNSa0LC6nv*MOHhEaMDlO8Wqce*Hc0kV*C*I6rX723QW2aUblshffxqkbUJW&u1=http%3A%2F%2Fweb2.qq.com%2Floginproxy.html&r=0.513474546611996\n";
    }
    
    public String QQGetCodeImage(){
    	return "aid=1003903&r=0.577911190026398&uin="+QQ;
    }
    
    public String QQLogin2(String p,String vc){
    	
    	return "u="+QQ+"&p="+p+"&verifycode="+vc+"&webqq_type=10&remember_uin=1&login2qq=1&aid=1003903&u1=http%3A%2F%2Fweb2.qq.com%2Floginproxy.html%3Flogin2qq%3D1%26webqq_type%3D10&h=1&ptredirect=0&ptlang=2052&daid=164&from_ui=1&pttype=1&dumy=&fp=loginerroralert&action=4-32-51344&mibao_css=m_webqq&t=1&g=1&js_type=0&js_ver=10039&login_sig=KH4fakHI6SnDXGst9i5o4UIc7lewWvbkAYD4tYd*SguEHS11g33XtPdDKlZ-DZh4";
    }
    public String QQLogin3(String season){
    	return "r=%7B%22ptwebqq%22%3A%22"+season+"%22%2C%22clientid%22%3A53999199%2C%22psessionid%22%3A%22%22%2C%22status%22%3A%22online%22%7D";
    }
}
