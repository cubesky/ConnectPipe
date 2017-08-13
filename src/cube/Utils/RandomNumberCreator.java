package cube.Utils;

import java.util.Random;


public class RandomNumberCreator {
	static Random r;
	public static int getRandom(){
		r=new Random();
		return r.nextInt();
	}
}
