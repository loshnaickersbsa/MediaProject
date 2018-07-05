package vzap.losh.utils;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class PatternMatching {

	public static boolean findMatch (String regex, String fullString)
	{

		Pattern p = Pattern.compile(regex);
		//  get a matcher object
		Matcher m = p.matcher(fullString);
		return m.find();
	}
	
	
	public static void main(String[]  args) {
		
		System.out.println("result : " + findMatch("".toUpperCase(), "mamaloko".toUpperCase()) );
		System.out.println("d");
	}



}