package cube.bukkitplugin;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jibble.pircbot.IrcException;
import org.jivesoftware.smack.Chat;

import cube.IRC.IRCBot;
import cube.IRC.IRCBot.IRCBothaveMessage;
import cube.XMPP.XMPPHelper;
import cube.XMPP.XMPPHelper.XMPPhaveMessage;

public class PluginListener implements Listener,XMPPhaveMessage,IRCBothaveMessage {
	@SuppressWarnings("unused")
	private PluginStart plugin;
	public XMPPHelper xmpp;
	public String protool="";
	public String MUCPassword="";
	String Target="";
	String HOST="";
	String USER="";
	String PASSWORD="";
	public static boolean debug=false;
	boolean isMUC=false;
	boolean login=false;
	boolean isXMPP=true;
	String ircHost="";
	int ircPort=6667;
	String ircChannel="";
	Chat c;
	String reglx="";
	Map<String,IRCBot> userirc = new HashMap<String,IRCBot>();

	public PluginListener(PluginStart plugin)
	{
		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
		this.plugin = plugin;

	}

	public PluginListener() {
		// TODO 自动生成的构造函数存根
	}

	public void conn(){
		if(isXMPP){
			Thread con = new Thread(new Runnable(){

				@Override
				public void run() {
					xmpp=new XMPPHelper(HOST,USER,PASSWORD,Target,new PluginListener(),debug,isMUC); //连接
					if(MUCPassword.equalsIgnoreCase("")){

					}else{
						xmpp.setMUCPassword(MUCPassword);
					}
					xmpp.connect();
				}

			});

			con.start();
		}else{

		}
	}

	@EventHandler(priority=EventPriority.NORMAL, ignoreCancelled=true)
	public void onPlayerLogin(PlayerLoginEvent event){
		if(!isXMPP){
			IRCBot irc=new IRCBot(event.getPlayer().getName(), new PluginListener());
			irc.setDb(true);
			try {
				if(ircPort!=6667){
					irc.connect(ircHost, ircPort);
				}else{
					irc.connect(ircHost);
				}
				cube.Utils.DelayTask.Delay();
				irc.joinChannel(ircChannel);
				cube.Utils.DelayTask.Delay();
			} catch (IOException | IrcException e) {

				e.printStackTrace();
			}

			userirc.put(event.getPlayer().getName(), irc);
		}
	}
	
	@EventHandler(priority=EventPriority.NORMAL, ignoreCancelled=true)
	public void onPlayerQuit(PlayerQuitEvent event){
		if(!isXMPP){
			userirc.get((String)event.getPlayer().getName()).disconnect();
			userirc.remove((String)event.getPlayer().getName());
		}
	}

	@EventHandler(priority=EventPriority.NORMAL, ignoreCancelled=true)
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		if (event.isCancelled())
		{
			return;
		}

		String Str=event.getMessage();
		if(isXMPP){
			if(isMUC){
				final String FS=Str;
				Thread t=new Thread(new Runnable(){

					@Override
					public void run() {
						xmpp.SendMessage(FS);
					}

				});
				t.start();
			}else{
				if(Str.startsWith(reglx)){
					if(Str.length()<=3){
						Str=" ";
					}else{
						Str=Str.substring(3);
					}
					final String FS=Str;
					Thread t=new Thread(new Runnable(){

						@Override
						public void run() {
							xmpp.SendMessage(FS);
						}

					});
					t.start();
				}
			}
		}else{
			userirc.get((String)event.getPlayer().getName()).sendMessage(ircChannel, Str);;
		}

	}

	@Override
	public void XMPPhaveMessageL(String Str) {
		Bukkit.getServer().broadcastMessage("§a[XMPP]§b ["+Target+"] §c"+Str);

	}

	@Override
	public void XMPPLoginSuccessful() {


	}

	@Override
	public void XMPPLoginFail(String des) {
		Thread con = new Thread(new Runnable(){

			@Override
			public void run() {
				xmpp=new XMPPHelper(HOST,USER,PASSWORD,Target,new PluginListener(),debug,isMUC); //连接  
			}

		});

		con.start();

	}

	@Override
	public void XMPPSendOK() {
		// TODO 自动生成的方法存根

	}

	@Override
	public void XMPPSendFail(String str) {
		// TODO 自动生成的方法存根

	}

	@Override
	public void XMPPhaveMessageFromMUC(String From, String Str) {
		String[] c=From.split("/");
		Bukkit.getServer().broadcastMessage("§a[XMPP]§b ["+c[1]+"] §c"+Str);
	}

	@Override
	public void IRChaveMessage(String channel, String sender, String login,
			String hostname, String message) {
		Bukkit.getServer().broadcastMessage("§a[IRC]§b ["+channel+"/"+sender+"] §c"+message);

	}
}
