package vzap.losh.enumerations;

public enum Genre
{

   HORROR(1, Media_Type.DVD),
   SCIFI(2,Media_Type.DVD),
   ACTION(3,Media_Type.DVD),
   INDIE(4,Media_Type.GAME),
   CLASSIC(5,Media_Type.GAME),
   POP(6,Media_Type.CD),
   ROCK(7,Media_Type.CD),
   METAL(8,Media_Type.CD);
   
   private final int genreNumber;
   private final Media_Type genreMediaType;
  
   /** Constructor definition for media type */ 
   Genre (int genreNumber , Media_Type genreMediaType) 
   {
      this.genreNumber = genreNumber;
      this.genreMediaType = genreMediaType;
      
   }
   
  /** */
  public int getGenreNumber()
  {
      return this.genreNumber;
  }
  
  /** */
  public Media_Type  genreMediaType()
  {
      return this.genreMediaType;
  }  
  
  /**
  */
  public static void main(String[] args)
  {
      //Genre g;
      //print cd items
      Genre[] genreArray = Genre.values();
      
      for (Genre gT:genreArray)
      {
         
         if (gT.genreMediaType() == Media_Type.CD )
         {
           System.out.println(" Type  " + gT.genreMediaType() + 
           " Number  " + gT.getGenreNumber() + " for :  " + gT  + gT.toString());
         }
         
      }
      
      int x=7;
      
      
      
      
      
      
      
      
      //print dvd items 
      
      
      //print game items
  
  }
  
}