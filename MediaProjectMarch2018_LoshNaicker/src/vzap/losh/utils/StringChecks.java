package vzap.losh.utils;

public class StringChecks {
	
	public static boolean isNonAlpha(String inputString)
	{
		for (int x=0; x<inputString.length(); x++)
		{
			if (!Character.isAlphabetic(inputString.charAt(x)))
			{
				return true;
			}
		}
		return false;
	}
	
	
	
	//password checks
	
	
	//password itself
	

}
