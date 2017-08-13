package cube.bukkitplugin;

import java.util.logging.Level;

import org.bukkit.plugin.java.JavaPlugin;

import cube.Utils.FileReadUtils;

public class PluginStart extends JavaPlugin {

	PluginListener listen;
    int canuse=0;
	
	@Override
	public void onDisable() {
		if(canuse==1){
			if(listen.protool.equalsIgnoreCase("xmpp")){
				listen.xmpp.XMPPDisConnect();
			}
		}else if(canuse==2){
			for(String u:listen.userirc.keySet()){
				listen.userirc.get(u).disconnect();
			}
			listen.userirc.clear();
			cube.Utils.DelayTask.Delay();
		}
		super.onDisable();
	}

	@Override
	public void onEnable() {
		this.saveDefaultConfig();
		listen=new PluginListener(this);
		String[] u=FileReadUtils.readFileByLines(this.getDataFolder().toString()+"\\config.yml");
	    log(u.toString());
	    if(u.length<3){
	    	log("配置文件格式错误");
	    }else if(u.length==3){
	    	if(u[0].equalsIgnoreCase("irc")){
	    		listen.isXMPP=false;
	    		String[] p=u[1].split(":");
	    		if(p.length==2){
	    			listen.ircPort=Integer.valueOf(p[1]);
	    		}
	    		listen.ircHost=p[0];
	    		listen.ircChannel=u[2];
	    		canuse=2;
	    	}else{
	    		log("配置文件错误");
	    	}
	    }else if(u.length==5){
	    	if(u[0].equalsIgnoreCase("xmpp")){
	    		listen.protool="xmpp";
	    		listen.HOST=u[1];
	    		listen.USER=u[2];
	    		listen.PASSWORD=u[3];
	    		listen.Target=u[4];
	    		canuse=1;
	    	}else{
	    		log("不支持其他协议");
	    	}
	    }else if(u.length==6){
	    	if(u[0].equalsIgnoreCase("xmpp")){
	    		listen.protool="xmpp";
	    		listen.HOST=u[1];
	    		listen.USER=u[2];
	    		listen.PASSWORD=u[3];
	    		listen.Target=u[4];
	    		canuse=1;
	    		if(u[5].equalsIgnoreCase("true")){
	    			listen.isMUC=true;
	    		}else{
	    			listen.isMUC=false;
	    		}
	    	}else{
	    		log("无法识别");
	    	}
	    }else if(u.length==7){
	    	if(u[0].equalsIgnoreCase("xmpp")){
	    		listen.protool="xmpp";
	    		listen.HOST=u[1];
    			listen.USER=u[2];
    			listen.PASSWORD=u[3];
    			String[] g=u[4].split("§");
    			if(g.length!=2){
    				return;
    			}
    			listen.reglx=g[1];
    			listen.Target=g[0];
    			if(u[5].equalsIgnoreCase("true")){
	    			listen.isMUC=true;
	    		}else{
	    			listen.isMUC=false;
	    		}
    			canuse=1;
    			if(listen.protool.equalsIgnoreCase("xmpp")){
    				listen.MUCPassword=u[6];
    			}else{
    				log("无法识别");
    			}
	    	}
	    }else{
	    	log("格式错误");
	    }
	    if(canuse==1){
	    	listen.conn();
	    }else if(canuse==2){
	    	listen.conn();
	    	log("IRCConnection");
	    }else{
	    	log("无法使用");
	    }
		
		super.onEnable();
	}

	public void log(String str){
		getLogger().log(Level.INFO, str);
	}

}
