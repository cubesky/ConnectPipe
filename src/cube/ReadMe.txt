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
	 * ʵ��MQTThaveMessage�ӿڣ�����дMQTThaveMessageL\MQTTConnectSuccessful\MQTTDisconnect\MQTTLost����
	 * 
	 * ����MQTTConnectionʵ��
	 * MQTTConnection mqtt=new MQTTConnection(����,�˿�,�ͻ���Ψһ��ʶ);
	 * 
	 * ������Ϣ����ʱ��MQTT��ص�MQTThaveMessageL
	 * 
	 * 
	 * 
	 * ֧��JavaScript�ű�����
	 * 
	 * ʵ����JSEngine
	 * JSEngine js=new JSEngine(JS�ļ�·��);
	 *
	 * ����ͨ��Getter���ScriptEngine��Invocableʵ��
	 * 
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
	 */