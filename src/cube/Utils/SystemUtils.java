package cube.Utils;

import java.util.Date;

public class SystemUtils {
	public static Date getDate(){
		return(new Date());
	}
	public static Long getLongTime(){
		return (new Date()).getTime();
	}
}
