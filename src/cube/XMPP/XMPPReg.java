package cube.XMPP;



import org.jivesoftware.smack.AccountManager;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

public class XMPPReg {

	public static String Reg(String host,String Name,String Password){
		 try {
			ConnectionConfiguration config = new ConnectionConfiguration(host);
			 XMPPConnection connection = new XMPPConnection(config);
			 config.setSASLAuthenticationEnabled(false);
			 config.setSendPresence(false);
			 config.setReconnectionAllowed(true);      
			 connection.connect(); 
			 AccountManager amgr = connection.getAccountManager();
			 amgr.createAccount(Name, Password);
			 return "OK";
		} catch (XMPPException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			return e.getMessage();
		}
	}
}
