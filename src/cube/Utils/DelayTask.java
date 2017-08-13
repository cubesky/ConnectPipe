package cube.Utils;

public class DelayTask {
    public static void Delay(){
    	try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
    }
}
