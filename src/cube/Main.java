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

    //ȫ������
	//static String Target="�ͥ��ʥ���@nekonazo.com";
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
	//˽������
	static String Target="";
	//static String Target="neverwin@nekonazo.com";
	static String HOST="";
	static String USER="";
	//static String USER="CubeSky";
	static String PASSWORD="";
	static String MUCPassword;
	/**
	 * XMPP���ӷ���
	 * 
	 * ʵ��XMPPhaveMessage�ӿڣ�����дXMPPhaveMessageL\XMPPhaveMessageFromMUC\XMPPLoginSuccessful\XMPPLoginFail\XMPPSendOK\XMPPSendFail����
	 * 
	 * ʵ����XMPPHelper
	 * XMPPHelper xmpp=new XMPPHelper(����,�û���,����,����Ŀ��,ʵ�ֽӿڵ���,��������,�Ƿ�Ϊ��������); 
	 * 
	 * ���Ϊ�������첢������������������������ǰ�����趨
	 * xmpp.setMUCPassword(��������);
	 * 
	 * ����
	 * xmpp.connect();
	 * 
	 * ������Ϣ
	 * xmpp.SendMessage(��Ϣ);
	 * 
	 * �ر����Ӳ��˳�
	 * xmpp.XMPPDisConnectAndExit();
	 * 
	 * 
	 * 
	 * IRC���ӷ���
	 * 
	 * ʵ��IRCBothaveMessage�ӿڣ�����дIRChaveMessage����
	 * 
	 * ����IRCʵ��
	 * IRCBot irc=new IRCBot(����,ʵ�ַ�������,Debug);
	 * 
	 * ���ӷ�����
	 * irc.connect(������[,�˿�]);
	 * 
	 * ����Ƶ��
	 * irc.joinChannel(Ƶ��);
	 *
	 * ������Ϣ
	 * irc.sendMessage(Ƶ��,��Ϣ);
	 * 
	 * �Ͽ�����
	 * irc.disconnect();
	 * 
	 * 
	 * 
	 * MQTT���ӷ���
	 * 
	 * TODO
	 * 
	 * 
	 * 
	 * ֧�ֽű�����
	 * 
	 * JavaScript
	 * 
	 * ʵ����JSEngine
	 * JSEngine js=new JSEngine(JS�ļ�·��);
	 *
	 * ����ͨ��Getter���ScriptEngine��Invocableʵ��
	 * 
	 * Ruby
	 * 
	 * ʵ����RubyEngine
	 * RubyEngine ruby=new RubyEngine(RU�ļ�·��);
	 *
	 * ����ͨ��Getter���ScriptEngine��Invocableʵ��
	 * 
	 * Java
	 * 
	 * ʵ����JEngine
	 * JEngine j=new JEngine(Java�ļ�·��);
	 *
	 * ����ͨ��Getter���ScriptEngine��Invocableʵ��
	 * 
	 * Python
	 * 
	 * ʵ����JSEngine
	 * PYEngine py=new PYEngine(py�ļ�·��);
	 *
	 * ����ͨ��Getter���ScriptEngine��Invocableʵ��
	 * 
	 * ��һ��ʹ��py�ļ��ķ���
	 * PY.DoPY(py�ļ�);
	 * 
	 * 
	 * �Զ�������
	 * 
	 * ʵ����AutoControlHelper���������쳣AWTException
	 * AutoControlHelper ach = new AntoControlHelper();
	 * 
	 * ���¼�
	 * ach.pressKey(��);
	 * 
	 * Shift����
	 * ach.pressKeyWithShift(��);
	 * 
	 * Ctrl����
	 * ach.pressKeyWithCtrl(��);
	 * 
	 * �ر�Ӧ��
	 * ach.closeApplication();
	 * 
	 * ������png,jpg��
	 * ach.ScreenCapture(�ļ�·��,�ļ���ʽ);
	 * 
	 * ����������
	 * ach.robotdelay();
	 * 
	 * ����ƶ�
	 * ach.MouseMove(x,y);
	 * 
	 * ������
	 * ach.MouseLClick();
	 * 
	 * ����Ҽ�
	 * ach.MouseRClick();
	 * 
	 * ��ȡ��Ļx,y���괦��ɫ
	 * ach.getColor(x,y);
	 * 
	 * 
	 * 
	 * Http����
	 * 
	 * ���� GET ����
     * String s=HttpRequest.sendGet("http://localhost:6144/Home/RequestString", "key=123&v=456");
     *  
     * ���� POST ����
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
			// TO DO �Զ����ɵ� catch ��
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
			// TO DO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		System.exit(0);
		*/
		QQ qq=new QQ("2591616916","maxmax");
		qq.QQLogin();
		/*
		System.out.println("��ӭʹ��XMPPConnector");
		while(true){
			String u="";
			u=CtrlInput.getInput();
			String s="";
			if(u.startsWith("xmpp")){
				if(u.length()==4){
					String help="XMPPЭ������-���� \n\n";
					help=help+"��¼XMPP\n";
					help=help+"login ���� �û��� ���� ����Ŀ��\n\n";
					help=help+"ע��XMPP\n";
					help=help+"logoff\n\n";
					help=help+"�˳�\n";
					help=help+"quit\n\n";
					help=help+"������Ϣ\n";
					help=help+"send ��Ϣ\n";
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
					Debug("�����˳�");
					System.exit(0);
				}
			}else if(s.substring(0, 4).equalsIgnoreCase("send")){
				if(login){
					String r=s.substring(4);
					xmpp.SendMessage(r);
				}else{
					Debug("���ȵ�¼");
					System.out.println("���ȵ�¼");
				}
				
			}else if(s.substring(0, 5).equalsIgnoreCase("login")){
				if(login){
					Debug("���Ѿ���¼��");
					System.out.println("���Ѿ���¼");
				}else{
					String[] r=s.split(" ");
					if(r.length<5){
						Debug("��������ȷ");
						System.out.println("��������ȷ");
					}else{
						HOST=r[1];
						USER=r[2];
						PASSWORD=r[3];
						Target=r[4];
						if(HOST.equals("") || USER.equals("") || PASSWORD.equals("") || Target.equals("") ||HOST==null || USER==null || PASSWORD==null ||Target==null ){
							Debug("��������ȷ");
							System.out.println("��������ȷ");
						}else{
							Thread con = new Thread(new Runnable(){

								@Override
								public void run() {
									
									xmpp=new XMPPHelper(HOST,USER,PASSWORD,Target,new Main(),debug,isMUC); //����  
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
					Debug("����û�е�¼");
					System.out.println("���ȵ�¼");
				}
			}else{
				Debug("��Ч����");
				System.out.println("û���ҵ��������");
			}
			}
			}else{
				System.out.println("Ŀǰֱ�����г����֧��XMPPЭ��");
			}
			
			
			
			
			
		}
		*/
		
	}


	//������Ϣ��ʾģ��
	public static void Debug(String string) {
		if(debug){
			  System.out.println(string);
		}
	}




//IRC������Ϣ�ص�
@Override
public void IRChaveMessage(String channel, String sender, String login,String hostname, String message) {
	Debug("IRC�ص�-�յ�����");	
}

//XMPP���ո�����Ϣ�ص�
@Override
public void XMPPhaveMessageL(String str) {
	Debug("XMPP�ص�-�յ������������ݣ� "+str);
	System.out.println("���գ�["+str+"]");
}
//XMPP���ܶ���������Ϣ�ص�
@Override
public void XMPPhaveMessageFromMUC(String From, String Str) {
	Debug("XMPP�ص�-�յ������������ݣ� "+From+" : "+Str);
	System.out.println("���գ�"+From+" : "+Str);
}
//XMPP��¼�ɹ��ص�
@Override
public void XMPPLoginSuccessful() {
	login=true;
	c=xmpp.getChat();
	Debug("XMPP�ص�-��½�ɹ�");
	System.out.println("��¼�ɹ�");
}

//XMPP��¼ʧ�ܻص�
@Override
public void XMPPLoginFail(String des) {
	Debug("XMPP�ص�-��¼ʧ�ܣ�ԭ�� ��"+des);
	System.out.println("��¼ʧ��  "+des);
	login=false;
}

//XMPP���ͳɹ��ص�
@Override
public void XMPPSendOK() {
	Debug("XMPP�ص�-���ͳɹ�");
}

//XMPP����ʧ�ܻص�
@Override
public void XMPPSendFail(String str) {
	Debug("XMPP�ص�-����ʧ�ܣ�ԭ�� ��"+str);
	System.out.println("�ոյ�������Ϣ��Ϊ"+str+"����ʧ�ܣ�������");
}


@Override
public void MQTTRecieve(String msg) {
	// TODO �Զ����ɵķ������
	
}


@Override
public void MQTTConnect() {
	// TODO �Զ����ɵķ������
	
}


@Override
public void MQTTConnectFail() {
	// TODO �Զ����ɵķ������
	
}


@Override
public void MQTTDisConnect() {
	// TODO �Զ����ɵķ������
	
}


@Override
public void MQTTTopicSubSuccessful() {
	// TODO �Զ����ɵķ������
	
}


@Override
public void MQTTTopicSubFail() {
	// TODO �Զ����ɵķ������
	
}


@Override
public void MQTTTopicPubSuccessful() {
	// TODO �Զ����ɵķ������
	
}


@Override
public void MQTTTopicPubFail() {
	// TODO �Զ����ɵķ������
	
}


	
}




