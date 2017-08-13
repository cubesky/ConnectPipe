package cube.XMPP;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smackx.ServiceDiscoveryManager;
import org.jivesoftware.smackx.muc.HostedRoom;
import org.jivesoftware.smackx.muc.MultiUserChat;
import org.jivesoftware.smackx.muc.RoomInfo;
import org.jivesoftware.smackx.packet.DiscoverInfo;
import org.jivesoftware.smackx.packet.DiscoverItems;

import cube.Main;

public class XMPPHelper {
	static XMPPConnection connection;
	static ChatManager cm;
	static Chat c;
	static String host="";
	static String Target="";
	static boolean debug=false;
	static boolean state=false;
	static boolean error=false;
	static String user="";
	static String password="";
	static String TargetMUC="";
	static MultiUserChat muc;
	static XMPPhaveMessage xmpplistener;
	static boolean isMUC=false;
	static String MUCPassword="";
	
	public XMPPHelper(String Host,String User,String Password,String WantedTarget,XMPPhaveMessage x,boolean usedebug,boolean isMuiltChat){
		XMPPConnection.DEBUG_ENABLED = Main.debug ;
		debug=usedebug;
		TargetMUC=WantedTarget;
		Target=WantedTarget;
		host=Host;
		xmpplistener=x;
		user=User;
		password=Password;
		isMUC=isMuiltChat;
		
	}
	
	public interface XMPPhaveMessage {
		public void XMPPhaveMessageL(String Str);
		
		public void XMPPhaveMessageFromMUC(String From,String Str);
		
		public void XMPPLoginSuccessful();
		
		public void XMPPLoginFail(String des);
		
		public void XMPPSendOK();
		
		public void XMPPSendFail(String str);
	}
	
	public void connect(){
		XMPPConnect(host,user,password);
	}
	
	public String getName(){
		return connection.getUser();
	}
	
	
	public boolean getState(){
		return state;
	}
	
	public boolean getError(){
		return error;
	}
	
	
	//��NekoIM���ܵ���Ϣ����
	private static void addListener() {         
        PacketFilter filterMessage = new PacketTypeFilter(Message.class);    
        PacketListener myListener = new PacketListener() { //������

			@Override
			public void processPacket(Packet packet) {
				String Raw=packet.getFrom();
            	String RawSep=Raw.substring(0, Target.length());
            	if(debug){
            		String a[]=Raw.split("/");
            		if(!a[0].equalsIgnoreCase(Target)){
            			a[0]=a[0]+"  ��������Ŀ�꣬�������ݣ�";
            		}
            		Debug("���յ��������ԣ� "+a[0]);
            	}
            	
                if(RawSep.equalsIgnoreCase(Target)){
                	Debug("���յ�����: " + ((Message) packet).getBody().trim()); //���ܵ�����
                	xmpplistener.XMPPhaveMessageL(((Message) packet).getBody().trim());
                }
				
			}      
        };
        //connection.addPacketListener(myListener, filterMessage); 
        connection.addPacketListener(myListener, filterMessage);   
    }  
	
	static void ReciveLoginFlag(boolean login,String Des){
		if(login){
			Debug("���ӳɹ�");
			Presence presence = new Presence(Presence.Type.available);
			connection.sendPacket(presence);
			if(isMUC){
				try {
					StartChatWithOri();
					state=true;
					
				} catch (XMPPException e) {
					// TODO �Զ����ɵ� catch ��
					Debug("����ʧ�� ,ԭ���� "+e.getMessage());
					xmpplistener.XMPPLoginFail(e.getMessage());
				}
			}else{
				StartChatWithPerson(); //��������
				addListener(); //ע�����ż�����
				state=true;
				xmpplistener.XMPPLoginSuccessful();
			}
			
		}else{
			Debug("����ʧ�� ,ԭ���� "+Des);
			xmpplistener.XMPPLoginFail(Des);
		}
	}
	
	private static void StartChatWithPerson(){ //������Ŀ�������
		cm=connection.getChatManager();
		c=cm.createChat(Target, new MessageListener(){

			@Override
			public void processMessage(Chat arg0, Message arg1) {
				arg1.getBody();
			}
			
		});
		Debug("��ɵ�Ŀ�� "+Target+" ������");
	}
	
	private static void StartChatWithOri() throws XMPPException{ //������Ŀ�������
		muc = new MultiUserChat(connection, TargetMUC);   
		   //���ҷ���   
            Debug(connection.getServiceName());   
            List<String> col;
			try {
				col = getConferenceServices(connection.getServiceName(), connection);
				for (Object aCol : col) {   
                String service = (String) aCol;   
                 //��ѯ�������ϵ�������   
                Collection<HostedRoom> rooms = MultiUserChat.getHostedRooms(connection, service);  
                for(HostedRoom room : rooms) {   
                    //�鿴Room��Ϣ   
                    System.out.println(room.getName() + " - " +room.getJid());   
                    RoomInfo roomInfo = MultiUserChat.getRoomInfo(connection, room.getJid());  
                    if(roomInfo != null) {   
                        Debug(roomInfo.getOccupantsCount() + " : " + roomInfo.getSubject());  
                    }     
                 }     
            } 
				muc.addMessageListener(new PacketListener() {   
		            @Override   
		            public void processPacket(Packet packet) {   
		                Message message = (Message) packet;   
		                Debug(message.getFrom() + " : " + message.getBody().trim());; 
		                xmpplistener.XMPPhaveMessageFromMUC(message.getFrom(), message.getBody().trim());
		            }   
		        }); 
				Debug(user+" "+MUCPassword+" "+TargetMUC);
		        muc.join(user, MUCPassword);
				Debug("��ɵ�Ŀ�������� "+TargetMUC+" ������");
				xmpplistener.XMPPLoginSuccessful();
			} catch (Exception e) {
				xmpplistener.XMPPLoginFail(e.getMessage());
			}  
            
        
		
	}
	
	public void SendMessage(String Message){ //������Ϣ
		try {
			Debug("׼����������");
			if(isMUC){
				muc.sendMessage(Message);
			}else{
				c.sendMessage(Message);
			}
			Debug("���ͳɹ�\n����Ŀ��Ϊ��"+ Target +"\n���͵�����Ϊ�� "+Message);
			xmpplistener.XMPPSendOK();
		} catch (XMPPException e) {
			Debug("����ʧ�� , ԭ���� " + e.getMessage());
			xmpplistener.XMPPSendFail(e.getMessage());
		}
	}
	
	
	private static void XMPPConnect(String Host,String User,String Password){ //�������߳�����NekoIM
		XMPPLogin xmpplogin=new XMPPLogin();
		xmpplogin.Host=Host;
		xmpplogin.User=User;
		xmpplogin.Password=Password;
		Thread xmpp=new Thread(xmpplogin);
		xmpp.start();
	}
	public void XMPPDisConnectAndExit(){ //�Ͽ����Ӳ��˳�����
		Debug("׼���Ͽ����Ӳ��˳�����");
		System.out.println("�ǳ�");
		connection.disconnect();
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			Debug("�߳��쳣����");
		}
		Debug("�ѶϿ�");
		System.exit(0);
	}
	public void XMPPDisConnect(){ //�Ͽ�����
		Debug("׼���Ͽ�����");
		System.out.println("�ǳ�");
		connection.disconnect();
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			Debug("�߳��쳣����");
		}
		Debug("�ѶϿ�");
	}
	
	public Chat getChat(){
		return c;
	}
	
	static void Debug(String str){
		if(debug){
		  System.out.println(str);
		}
	}

	static void Debug(boolean state2) {
		if(debug){
			System.out.println(state2);
		}
	}
	
	public void DebugF(Object o){
		if(debug){
			System.out.println(o);
		}
	}
	
	public void setMUCPassword(String Password){
		MUCPassword=Password;
	}
	 public static  List<String> getConferenceServices(String server, XMPPConnection connection) throws Exception {  
	     List<String> answer = new ArrayList<String>();   
	            ServiceDiscoveryManager discoManager = ServiceDiscoveryManager.getInstanceFor(connection);  
	             DiscoverItems items = discoManager.discoverItems(server);   
	            for (Iterator<DiscoverItems.Item> it = items.getItems(); it.hasNext();) {  
	                 DiscoverItems.Item item = (DiscoverItems.Item)it.next();   
	                if (item.getEntityID().startsWith("conference") || item.getEntityID().startsWith("private")) {  
	                     answer.add(item.getEntityID());   
	                }   
	                else {   
	                    try {   
	                        DiscoverInfo info = discoManager.discoverInfo(item.getEntityID());  
	                         if (info.containsFeature("http://jabber.org/protocol/muc")) {  
	                             answer.add(item.getEntityID());   
	                        }   
	                    }   
	                    catch (XMPPException e) {   
	                    }   
	                }   
	            }   
	            return answer;   
	        }  
}

class XMPPLogin implements Runnable //���߳�
{
	String Host="";
	String User="";
	String Password="";
	boolean login=false;
	String des="";
	@Override
	public void run() {
		
		try {
			
			XMPPHelper.Debug("�������ӷ�����..... \n������������ \nĿ��������"+Host+" \n�û�����"+User+" \n���룺"+Password);
			ConnectionConfiguration configconn=new ConnectionConfiguration(Host);
			configconn.setSASLAuthenticationEnabled(false);
			configconn.setSendPresence(false);
			configconn.setReconnectionAllowed(true);      
			XMPPHelper.connection = new XMPPConnection(configconn);
			XMPPHelper.connection.connect();
		//	XMPPHelper.connection.login(User,Password);
			XMPPHelper.connection.login(User, Password, "CubeSky-ConnectPipe");
			login=true;
			
		} catch (Exception e) {
			//e.printStackTrace();
			des=e.getMessage();
			XMPPHelper.error=true;
			login=false;
			XMPPHelper.ReciveLoginFlag(login,des);
		}
		
		XMPPHelper.ReciveLoginFlag(login,des);
	}
}

