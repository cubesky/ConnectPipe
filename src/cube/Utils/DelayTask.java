package cube.Utils;

public class DelayTask {
    public static void Delay(){
    	try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
    }
}
