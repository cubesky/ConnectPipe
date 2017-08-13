package cube.IRC;

import org.jibble.pircbot.PircBot;

public class IRCBot extends PircBot {
	
	IRCBothaveMessage bot;
	boolean db=false;
	public boolean isDb() {
		return db;
	}


	public void setDb(boolean db) {
		this.db = db;
	}


	public interface IRCBothaveMessage {
		public void IRChaveMessage(String channel, String sender, String login,
				String hostname, String message);
	}


	public IRCBot(String Name,IRCBothaveMessage inbot){
		this.setName(Name);
		bot=inbot;
		this.setVerbose(db);
		
	}
	

	@Override
	protected void onMessage(String channel, String sender, String login,
			String hostname, String message) {
		bot.IRChaveMessage(channel,sender,login,
				hostname, message);
		super.onMessage(channel, sender, login, hostname, message);
	}

	
	
	
}