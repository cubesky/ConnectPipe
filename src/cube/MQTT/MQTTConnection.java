package cube.MQTT;

import java.util.UUID;

import org.fusesource.hawtbuf.Buffer;
import org.fusesource.hawtbuf.UTF8Buffer;
import org.fusesource.hawtdispatch.Dispatch;
import org.fusesource.mqtt.client.Callback;
import org.fusesource.mqtt.client.CallbackConnection;
import org.fusesource.mqtt.client.Listener;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.QoS;
import org.fusesource.mqtt.client.Topic;
import org.fusesource.mqtt.client.Tracer;
import org.fusesource.mqtt.codec.MQTTFrame;

public class MQTTConnection{
	CallbackConnection callbackConnection;
	String host="";
	String user="";
	String password="";
	String Queue="";
	int port=61613;
	IMQTT imqtt;
	MQTT mqtt;
	public MQTTConnection(String host,String user,String password,String Queue,IMQTT i){
		this.host=host;
		this.user=user;
		this.password=password;
		this.Queue=Queue;
		this.imqtt=i;
	}
	
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void connect(){
		try{
			mqtt=new MQTT();

			//MQTT����˵��
			mqtt.setHost(host,port);
			String s = UUID.randomUUID().toString();
			mqtt.setClientId(s);
			//mqtt.setClientId("876543210"); //�������ÿͻ��˻Ự��ID����setCleanSession(false);������ʱ��MQTT���������ø�ID�����Ӧ�ĻỰ����IDӦ����23���ַ���Ĭ�ϸ��ݱ�����ַ���˿ں�ʱ���Զ�����
			mqtt.setCleanSession(false); //����Ϊfalse��MQTT���������־û��ͻ��˻Ự�����嶩�ĺ�ACKλ�ã�Ĭ��Ϊtrue
			mqtt.setKeepAlive((short) 60);//����ͻ��˴�����Ϣ�����ʱ�������������������Ծݴ��ж���ͻ��˵������Ƿ��Ѿ��Ͽ����Ӷ�����TCP/IP��ʱ�ĳ�ʱ��ȴ�
			mqtt.setUserName(user);//��������֤�û���
			mqtt.setPassword(password);//��������֤����
			mqtt.setWillTopic("willTopic");//���á���������Ϣ�Ļ��⣬���ͻ����������֮������������жϣ��������������ͻ��˵ġ���������Ϣ
			//mqtt.setWillMessage("willMessage");//���á���������Ϣ�����ݣ�Ĭ���ǳ���Ϊ�����Ϣ
			mqtt.setWillQos(QoS.AT_MOST_ONCE);//���á���������Ϣ��QoS��Ĭ��ΪQoS.ATMOSTONCE
			mqtt.setWillRetain(true);//����Ҫ�ڷ�������������Ϣʱӵ��retainѡ���Ϊtrue
			mqtt.setVersion("3.1.1");

			//ʧ������������˵��
			//	mqtt.setConnectAttemptsMax(10L);//�ͻ����״����ӵ�������ʱ�����ӵ�������Դ����������ô����ͻ��˽����ش���-1��Ϊ���������ޣ�Ĭ��Ϊ-1
			//	mqtt.setReconnectAttemptsMax(3L);//�ͻ����Ѿ����ӵ�������������ĳ��ԭ�����ӶϿ�ʱ��������Դ����������ô����ͻ��˽����ش���-1��Ϊ���������ޣ�Ĭ��Ϊ-1
			mqtt.setReconnectDelay(10L);//�״������Ӽ����������Ĭ��Ϊ10ms
			mqtt.setReconnectDelayMax(30000L);//�����Ӽ����������Ĭ��Ϊ30000ms
			mqtt.setReconnectBackOffMultiplier(2);//����������ָ���ع顣����Ϊ1��ͣ��ָ���ع飬Ĭ��Ϊ2

			//Socket����˵��
			mqtt.setReceiveBufferSize(65536);//����socket���ջ�������С��Ĭ��Ϊ65536��64k��
			mqtt.setSendBufferSize(65536);//����socket���ͻ�������С��Ĭ��Ϊ65536��64k��
			mqtt.setTrafficClass(8);//���÷������ݰ�ͷ���������ͻ���������ֶΣ�Ĭ��Ϊ8����Ϊ��������󻯴���

			//������������˵��
			mqtt.setMaxReadRate(0);//�������ӵ����������ʣ���λΪbytes/s��Ĭ��Ϊ0����������
			mqtt.setMaxWriteRate(0);//�������ӵ���������ʣ���λΪbytes/s��Ĭ��Ϊ0����������

			//ѡ����Ϣ�ַ�����
			mqtt.setDispatchQueue(Dispatch.createQueue(Queue));//��û�е��÷���setDispatchQueue���ͻ��˽�Ϊ�����½�һ�����С������ʵ�ֶ������ʹ�ù��õĶ��У���ʽ��ָ��������һ���ǳ������ʵ�ַ���


			//���ø�����
			mqtt.setTracer(new Tracer(){
				@Override
				public void onReceive(MQTTFrame frame) {
					System.out.println("recv: "+frame);
				}
				@Override
				public void onSend(MQTTFrame frame) {
					System.out.println("send: "+frame);
				}
				@Override
				public void debug(String message, Object... args) {
					System.out.println(String.format("debug: "+message, args));
				}
			});



			//ʹ�ûص�ʽAPI
			callbackConnection=mqtt.callbackConnection();

			//���Ӽ���
			callbackConnection.listener(new Listener() {

				//���ն��Ļ��ⷢ������Ϣ
				@Override
				public void onPublish(UTF8Buffer topic, Buffer payload, Runnable onComplete) {
					System.out.println("=============receive msg================"+new String(payload.toByteArray()));
					imqtt.MQTTRecieve(new String(payload.toByteArray()));
					onComplete.run();
				}

				//����ʧ��
				@Override
				public void onFailure(Throwable value) {
					System.out.println("===========connect failure===========");
					imqtt.MQTTConnectFail();
					callbackConnection.disconnect(null);
				}

				//���ӶϿ�
				@Override
				public void onDisconnected() {
					imqtt.MQTTDisConnect();
					System.out.println("====mqtt disconnected=====");

				}

				//���ӳɹ�
				@Override
				public void onConnected() {
					imqtt.MQTTConnect();
					System.out.println("====mqtt connected=====");

				}
			});



			//����
			callbackConnection.connect(new Callback<Void>() {

				//����ʧ��
				public void onFailure(Throwable value) {
					System.out.println("============����ʧ�ܣ�"+value.getLocalizedMessage()+"============");
					System.out.println(value.getMessage());
					imqtt.MQTTConnectFail();
				}
				// ���ӳɹ�
				public void onSuccess(Void v) {
					//��������
					Topic[] topics = {new Topic(Queue, QoS.AT_MOST_ONCE)};
					callbackConnection.subscribe(topics, new Callback<byte[]>() {
						//��������ɹ�
						public void onSuccess(byte[] qoses) {
							System.out.println("========���ĳɹ�=======");
							imqtt.MQTTTopicPubSuccessful();
						}
						//��������ʧ��
						public void onFailure(Throwable value) {
							System.out.println("========����ʧ��=======");
							imqtt.MQTTTopicSubFail();
							callbackConnection.disconnect(null);
						}
					});



				}
			});



			while(true)
			{

			}


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void publish(String msg){
		//������Ϣ
		callbackConnection.publish(Queue, ("Hello ").getBytes(), QoS.AT_MOST_ONCE, true, new Callback<Void>() {
			public void onSuccess(Void v) {
				imqtt.MQTTTopicPubSuccessful();
				System.out.println("===========��Ϣ�����ɹ�============");
			}
			public void onFailure(Throwable value) {
				imqtt.MQTTTopicPubFail();
				System.out.println("========��Ϣ����ʧ��=======");
				callbackConnection.disconnect(null);
			}
		});

	}



	public interface IMQTT {
		public void MQTTRecieve(String msg);
		public void MQTTConnect();
		public void MQTTConnectFail();
		public void MQTTDisConnect();
		public void MQTTTopicSubSuccessful();
		public void MQTTTopicSubFail();
		public void MQTTTopicPubSuccessful();
		public void MQTTTopicPubFail();
	}
}
