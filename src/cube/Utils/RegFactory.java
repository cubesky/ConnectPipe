package cube.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegFactory {
	public static void main(String[] args) {
		String tt = "8895+123+131+789+236/56+21*10";
		String reg = "\\D?(\\d+)(\\D?)";
		System.out.println(catchGroup(reg, tt));

	}
	/**
	 * 全角转半�?
	 * @param input
	 * @return
	 */
	public static String ToDBC(String input) {
		char c[] = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == '\u3000') {
				c[i] = ' ';
			} else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
				c[i] = (char) (c[i] - 65248);
			}
		}
		String ss = new String(c);
		return ss;
	}
	public static boolean isMatch(String reg, String content) {
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(content);
		return m.matches();
	}

	public static List<String> catchGroup(String reg, String source) {
		// System.out.println(reg+"\n"+source);
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(source);
		// System.out.println(m.lookingAt());
		List<String> re = new ArrayList<String>();
		if (m.find(0)) {
			for (int i = 1; i <= m.groupCount(); i++) {
				if(m.group(i).trim().length()>0)
				re.add(m.group(i));
			}
		}
		while (m.find()) {
			for (int i = 1; i <= m.groupCount(); i++) {
				if(m.group(i).trim().length()>0)
				re.add(m.group(i));
			}
		}
		return re;

	}
}
