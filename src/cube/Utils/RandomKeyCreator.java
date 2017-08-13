package cube.Utils;

public class RandomKeyCreator {
	public static String getKey32(){
		String key1=MD5.ToMD5(String.valueOf(RandomNumberCreator.getRandom()));
		String key2=MD5.ToMD5(String.valueOf(RandomNumberCreator.getRandom()));
		String key =MD5.ToMD5(key1+key2);
		return key;
	}
	public static String getKey64(){
		String key1=MD5.ToMD5(String.valueOf(RandomNumberCreator.getRandom()));
		String key2=MD5.ToMD5(String.valueOf(RandomNumberCreator.getRandom()));
		String key =MD5.ToMD5(key1+key2);
		key1=MD5.ToMD5(String.valueOf(RandomNumberCreator.getRandom()));
		key2=MD5.ToMD5(String.valueOf(RandomNumberCreator.getRandom()));
		key=key+MD5.ToMD5(key1+key2);
		return key;
	}
}
