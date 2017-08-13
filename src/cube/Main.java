package cube;

import org.jivesoftware.smack.Chat;

import cube.IRC.IRCBot.IRCBothaveMessage;
import cube.MQTT.MQTTConnection.IMQTT;
import cube.QQ.QQ;
import cube.XMPP.XMPPHelper;
import cube.XMPP.XMPPHelper.XMPPhaveMessage;



public class Main implements IRCBothaveMessage,XMPPhaveMessage,IMQTT{
	/**
	 * @param args
	 */

    //全局配置
	//static String Target="ネコナゾ娘@nekonazo.com";
//	static String Target="CubeSky@nekonazo.com";
	//static String Target="neverwin@nekonazo.com";
//	static String HOST="nekonazo.com";
//	static String USER="ChatPipe";
	//static String USER="CubeSky";
//	static String PASSWORD="maxmax";
	public static boolean debug=false;
	static boolean login=false;
	static XMPPHelper xmpp;
	static Chat c;
	static boolean isMUC=false;
	//私有配置
	static String Target="";
	//static String Target="neverwin@nekonazo.com";
	static String HOST="";
	static String USER="";
	//static String USER="CubeSky";
	static String PASSWORD="";
	static String MUCPassword;
	/**
	 * XMPP连接方法
	 * 
	 * 实现XMPPhaveMessage接口，并重写XMPPhaveMessageL\XMPPhaveMessageFromMUC\XMPPLoginSuccessful\XMPPLoginFail\XMPPSendOK\XMPPSendFail方法
	 * 
	 * 实例化XMPPHelper
	 * XMPPHelper xmpp=new XMPPHelper(主机,用户名,密码,连接目标,实现接口的类,开启调试,是否为多人聊天); 
	 * 
	 * 如果为多人聊天并且聊天室设有密码请在连接前进行设定
	 * xmpp.setMUCPassword(房间密码);
	 * 
	 * 连接
	 * xmpp.connect();
	 * 
	 * 发送信息
	 * xmpp.SendMessage(信息);
	 * 
	 * 关闭连接并退出
	 * xmpp.XMPPDisConnectAndExit();
	 * 
	 * 
	 * 
	 * IRC连接方法
	 * 
	 * 实现IRCBothaveMessage接口，并重写IRChaveMessage方法
	 * 
	 * 创建IRC实例
	 * IRCBot irc=new IRCBot(名字,实现方法的类,Debug);
	 * 
	 * 连接服务器
	 * irc.connect(服务器[,端口]);
	 * 
	 * 加入频道
	 * irc.joinChannel(频道);
	 *
	 * 发送消息
	 * irc.sendMessage(频道,消息);
	 * 
	 * 断开连接
	 * irc.disconnect();
	 * 
	 * 
	 * 
	 * MQTT连接方法
	 * 
	 * TODO
	 * 
	 * 
	 * 
	 * 支持脚本运行
	 * 
	 * JavaScript
	 * 
	 * 实例化JSEngine
	 * JSEngine js=new JSEngine(JS文件路径);
	 *
	 * 可以通过Getter获得ScriptEngine和Invocable实例
	 * 
	 * Ruby
	 * 
	 * 实例化RubyEngine
	 * RubyEngine ruby=new RubyEngine(RU文件路径);
	 *
	 * 可以通过Getter获得ScriptEngine和Invocable实例
	 * 
	 * Java
	 * 
	 * 实例化JEngine
	 * JEngine j=new JEngine(Java文件路径);
	 *
	 * 可以通过Getter获得ScriptEngine和Invocable实例
	 * 
	 * Python
	 * 
	 * 实例化JSEngine
	 * PYEngine py=new PYEngine(py文件路径);
	 *
	 * 可以通过Getter获得ScriptEngine和Invocable实例
	 * 
	 * 另一种使用py文件的方法
	 * PY.DoPY(py文件);
	 * 
	 * 
	 * 自动化操作
	 * 
	 * 实例化AutoControlHelper，并捕获异常AWTException
	 * AutoControlHelper ach = new AntoControlHelper();
	 * 
	 * 按下键
	 * ach.pressKey(键);
	 * 
	 * Shift按键
	 * ach.pressKeyWithShift(键);
	 * 
	 * Ctrl按键
	 * ach.pressKeyWithCtrl(键);
	 * 
	 * 关闭应用
	 * ach.closeApplication();
	 * 
	 * 截屏（png,jpg）
	 * ach.ScreenCapture(文件路径,文件格式);
	 * 
	 * 机器人休眠
	 * ach.robotdelay();
	 * 
	 * 鼠标移动
	 * ach.MouseMove(x,y);
	 * 
	 * 鼠标左键
	 * ach.MouseLClick();
	 * 
	 * 鼠标右键
	 * ach.MouseRClick();
	 * 
	 * 获取屏幕x,y坐标处颜色
	 * ach.getColor(x,y);
	 * 
	 * 
	 * 
	 * Http请求
	 * 
	 * 发送 GET 请求
     * String s=HttpRequest.sendGet("http://localhost:6144/Home/RequestString", "key=123&v=456");
     *  
     * 发送 POST 请求
     * String sr=HttpRequest.sendPost("http://localhost:6144/Home/RequestPostString", "key=123&v=456");
     *  
	 * 
	 * 
	 * 
	 */
	
	public static void main(String[] args) {
		/*
		try {
			RubyEngine ruby = new RubyEngine("D:\\r.ru");
			System.out.println();
		} catch (ScriptException | FileNotFoundException e) {
			// TO DO 自动生成的 catch 块
			e.printStackTrace();
		}
		System.exit(0);
		Runtime r=Runtime.getRuntime();
		
		*/
		/*try {
			DynamicJava.exec("public class Hello{" +
					"public static void run(){" +
					"System.out.println(\"Dynamic Java\");" +
					"}" +
					"}", "Hello", "run");
		} catch (Exception e) {
			// TO DO 自动生成的 catch 块
			e.printStackTrace();
		}
		System.exit(0);
		*/
		QQ qq=new QQ("2591616916","maxmax");
		qq.QQLogin();
		/*
		System.out.println("欢迎使用XMPPConnector");
		while(true){
			String u="";
			u=CtrlInput.getInput();
			String s="";
			if(u.startsWith("xmpp")){
				if(u.length()==4){
					String help="XMPP协议连接-帮助 \n\n";
					help=help+"登录XMPP\n";
					help=help+"login 域名 用户名 密码 聊天目标\n\n";
					help=help+"注销XMPP\n";
					help=help+"logoff\n\n";
					help=help+"退出\n";
					help=help+"quit\n\n";
					help=help+"发送信息\n";
					help=help+"send 消息\n";
					System.out.println(help);
				}else{
				   s=u.substring(5);
				if(s.length()<4){
					s="InputLowLength";
				}
			if(s.equalsIgnoreCase("quit") || s.equalsIgnoreCase("exit")){
				if(login){
					xmpp.XMPPDisConnectAndExit();
					xmpp=null;
					c=null;
				}else{
					Debug("程序退出");
					System.exit(0);
				}
			}else if(s.substring(0, 4).equalsIgnoreCase("send")){
				if(login){
					String r=s.substring(4);
					xmpp.SendMessage(r);
				}else{
					Debug("请先登录");
					System.out.println("请先登录");
				}
				
			}else if(s.substring(0, 5).equalsIgnoreCase("login")){
				if(login){
					Debug("您已经登录了");
					System.out.println("您已经登录");
				}else{
					String[] r=s.split(" ");
					if(r.length<5){
						Debug("参数不正确");
						System.out.println("参数不正确");
					}else{
						HOST=r[1];
						USER=r[2];
						PASSWORD=r[3];
						Target=r[4];
						if(HOST.equals("") || USER.equals("") || PASSWORD.equals("") || Target.equals("") ||HOST==null || USER==null || PASSWORD==null ||Target==null ){
							Debug("参数不正确");
							System.out.println("参数不正确");
						}else{
							Thread con = new Thread(new Runnable(){

								@Override
								public void run() {
									
									xmpp=new XMPPHelper(HOST,USER,PASSWORD,Target,new Main(),debug,isMUC); //连接  
									xmpp.connect();
								}
								
							});

							con.start();
						}
					 }
				}
			}else if(s.equalsIgnoreCase("logoff")){
				if(login){
					xmpp.XMPPDisConnect();
					xmpp=null;
					c=null;
					login=false;
				}else{
					Debug("您还没有登录");
					System.out.println("请先登录");
				}
			}else{
				Debug("无效输入");
				System.out.println("没有找到这个命令");
			}
			}
			}else{
				System.out.println("目前直接运行程序仅支持XMPP协议");
			}
			
			
			
			
			
		}
		*/
		
	}


	//调试信息显示模块
	public static void Debug(String string) {
		if(debug){
			  System.out.println(string);
		}
	}




//IRC接收消息回调
@Override
public void IRChaveMessage(String channel, String sender, String login,String hostname, String message) {
	Debug("IRC回调-收到数据");	
}

//XMPP接收个人消息回调
@Override
public void XMPPhaveMessageL(String str) {
	Debug("XMPP回调-收到个人聊天数据： "+str);
	System.out.println("接收：["+str+"]");
}
//XMPP接受多人聊天消息回调
@Override
public void XMPPhaveMessageFromMUC(String From, String Str) {
	Debug("XMPP回调-收到多人聊天数据： "+From+" : "+Str);
	System.out.println("接收："+From+" : "+Str);
}
//XMPP登录成功回调
@Override
public void XMPPLoginSuccessful() {
	login=true;
	c=xmpp.getChat();
	Debug("XMPP回调-登陆成功");
	System.out.println("登录成功");
}

//XMPP登录失败回调
@Override
public void XMPPLoginFail(String des) {
	Debug("XMPP回调-登录失败，原因 ："+des);
	System.out.println("登录失败  "+des);
	login=false;
}

//XMPP发送成功回调
@Override
public void XMPPSendOK() {
	Debug("XMPP回调-发送成功");
}

//XMPP发送失败回调
@Override
public void XMPPSendFail(String str) {
	Debug("XMPP回调-发送失败，原因 ："+str);
	System.out.println("刚刚的那条信息因为"+str+"发送失败，请重试");
}


@Override
public void MQTTRecieve(String msg) {
	// TODO 自动生成的方法存根
	
}


@Override
public void MQTTConnect() {
	// TODO 自动生成的方法存根
	
}


@Override
public void MQTTConnectFail() {
	// TODO 自动生成的方法存根
	
}


@Override
public void MQTTDisConnect() {
	// TODO 自动生成的方法存根
	
}


@Override
public void MQTTTopicSubSuccessful() {
	// TODO 自动生成的方法存根
	
}


@Override
public void MQTTTopicSubFail() {
	// TODO 自动生成的方法存根
	
}


@Override
public void MQTTTopicPubSuccessful() {
	// TODO 自动生成的方法存根
	
}


@Override
public void MQTTTopicPubFail() {
	// TODO 自动生成的方法存根
	
}


	
}




