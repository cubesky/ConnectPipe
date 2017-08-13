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
	 * 实现MQTThaveMessage接口，并重写MQTThaveMessageL\MQTTConnectSuccessful\MQTTDisconnect\MQTTLost方法
	 * 
	 * 创建MQTTConnection实例
	 * MQTTConnection mqtt=new MQTTConnection(主机,端口,客户端唯一标识);
	 * 
	 * 当有消息到达时，MQTT会回调MQTThaveMessageL
	 * 
	 * 
	 * 
	 * 支持JavaScript脚本运行
	 * 
	 * 实例化JSEngine
	 * JSEngine js=new JSEngine(JS文件路径);
	 *
	 * 可以通过Getter获得ScriptEngine和Invocable实例
	 * 
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
	 */