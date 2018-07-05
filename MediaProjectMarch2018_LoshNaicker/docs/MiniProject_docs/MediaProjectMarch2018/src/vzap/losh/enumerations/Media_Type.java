package vzap.losh.enumerations;

public enum Media_Type
{
   CD(1),
   DVD(2),
   GAME(3),
   BOOK(4),
   PHOTOS(5);
	
	private final int mediaTypeNumber;
		
	Media_Type (int mediaTypeNumber)
	{
		this.mediaTypeNumber = mediaTypeNumber;
	}
	
	public int getMediaTypeNumber()
	{
		return mediaTypeNumber;
	}
	
	public static void main (String [] args)
	{
		
		
		Media_Type[] mDT = Media_Type.values();
		
		for (Media_Type ml:mDT)
		{
			if (ml.getMediaTypeNumber() < 4)
			{
				//System.out.println(ml);
				System.out.println(" ( " + ml.getMediaTypeNumber() + " )" + " for " +  ml);
			}
		}
		
		//System.out.println(Media_Type(2));
		
	}

	public  Media_Type ReturnMedia_Type(int i) {
		// TODO Auto-generated method stub
		for (Media_Type ml:Media_Type.values())
		{
			if (ml.getMediaTypeNumber() == i)
			{
				return ml;
			}
		}
		return null;
	}
	
}