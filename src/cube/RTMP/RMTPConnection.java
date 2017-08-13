package cube.RTMP;
/**
 * Copyright (c) 2006 - 2008 Smaxe Ltd (www.smaxe.com).
 * All rights reserved.
 */

import java.util.Map;

import com.smaxe.uv.client.INetConnection;
import com.smaxe.uv.client.INetStream;
import com.smaxe.uv.client.NetStream;
import com.smaxe.uv.client.NetConnection;
import cube.RTMP.UltraNetConnection;
import cube.RTMP.UltraNetStream;
/**
 * <code>ExPlayStream</code> - {@link UltraNetConnection} usage example.
 * <p> Note: The example shows how to 'play' server stream. The played stream
 * is saved to the local flv file.
 * 
 * @author Andrei Sochirca
 */
public class RMTPConnection extends Object
{
	String url="";
	long port=0;
	IRTMP irtmp;
    /*
     * Entry point.
     * 
     * @param args
     */
    /*public static void main(final String[] args)
    {
        // NOTE:
        // you can get Evaluation Key at:
        // http://www.smaxe.com/order.jsf#request_evaluation_key
        // or buy at:
        // http://www.smaxe.com/order.jsf
//        License.setKey("24F11-06023-BE741-00D3A-798B8");
        
        Example app = new Example();
        
        app.start();
    }*/
    
    /**
     * <code>NetConnectionListener</code> - {@link UltraNetConnection} listener implementation.
     */
    public class NetConnectionListener extends NetConnection.ListenerAdapter
    {
        /**
         * Constructor.
         */
        public NetConnectionListener()
        {
        }
        
        @Override
        public void onAsyncError(final INetConnection source, final String message, final Exception e)
        {
            System.out.println("NetConnection#onAsyncError: " + message + " " + e);
            irtmp.onError(message,e);
        }
        
        @Override
        public void onIOError(final INetConnection source, final String message)
        {
            System.out.println("NetConnection#onIOError: " + message);
            irtmp.onError(message, null);
        }
        
        @Override
        public void onNetStatus(final INetConnection source, final Map<String, Object> info)
        {
            System.out.println("NetConnection#onNetStatus: " + info);
            irtmp.onNetStatus(info);
            final Object code = info.get("code");
            
            if (NetConnection.CONNECT_SUCCESS.equals(code))
            {
            }
            else
            {
                disconnected = true;
            }
        }
    }
    
    
    // fields
    private volatile boolean disconnected = false;
    
    /**
     * Constructor.
     */
    public RMTPConnection(String url,long port,IRTMP i)
    {
    	this.url=url;
    	this.port=port;
    	this.irtmp=i;
    }
    
    /**
     * Starts the example.
     */
    public void start()
    {
        final UltraNetConnection connection = new UltraNetConnection();
        
        connection.addEventListener(new NetConnectionListener());
			connection.client(this);
        
       // connection.connect("rtmp://192.168.1.247/chatapp", 81330);
        connection.connect(url, port);
        // wait till connected
        while (!connection.connected() && !disconnected)
        {
            try
            {
                Thread.sleep(100);
            }
            catch (Exception e) {e.printStackTrace();}
        }
        
        if (!disconnected)
        {
            UltraNetStream stream = new UltraNetStream(connection);
            
            stream.addEventListener(new NetStream.ListenerAdapter()
            {
                @Override
                public void onNetStatus(final INetStream source, final Map<String, Object> info)
                {
                    System.out.println("NetStream#onNetStatus: " + info);
                    irtmp.onNetStatus(info);
                }
            });
            
        }
        
        while (!disconnected)
        {
            try
            {
                Thread.sleep(100);
            }
            catch (Exception e) {/*ignore*/}
        }
        
        connection.close();
    }
    
    public void getSystemOfflineMsg(String str) {
    	irtmp.getSystemOfflineMsg(str);
    	System.out.println(str);
    }
    
    public void getSystemMSG(String str) {
    	irtmp.getSystemMSG(str);
    	System.out.println(str);
    }
    
    public void updatePublicInfo(String str) {
    	irtmp.updatePublicInfo(str);
    	System.out.println(str);
    }
    
    public void newMessageShow() {
    	irtmp.newMessageShow();
    	System.out.println("newMessage");
    }
    
    public void newUpdateSocialityInfo() {
    	irtmp.newUpdateSocialityInfo();
    	System.out.println("newUpdate");
    }
    
    public void updateAutoUndercityInfo(String str) {
    	irtmp.updateAutoUndercityInfo(str);
    	System.out.println("updateAuto " + str);
    }
    
    @SuppressWarnings("rawtypes")
    public interface IRTMP{
    	public void onError(String err,Exception e);
		public void onNetStatus(Map map);
		public void getSystemOfflineMsg(String str);
		public void getSystemMSG(String str);
		public void updatePublicInfo(String str);
		public void newMessageShow();
		public void newUpdateSocialityInfo();
		public void updateAutoUndercityInfo(String str);
    }
}