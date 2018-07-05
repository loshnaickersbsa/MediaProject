package vzap.losh.media;
import java.io.Serializable;
import java.util.Formatter;

public class Track implements Serializable
{

   /**
	 * 
	 */
   private static final long serialVersionUID = 1L;
   private String trackName;
   private int trackDuration;
   private String artist;

   protected void setTrackName(String trackName) {
	this.trackName = trackName;
}

protected void setTrackDuration(int trackDuration) {
	this.trackDuration = trackDuration;
}

protected void setArtist(String artist) {
	this.artist = artist;
}

public Track (String trackName,int trackDuration, String artist)
   {
      this.trackName = trackName;
      this.trackDuration = trackDuration;
      this.artist = artist;
   }
   
   public String getTrackName()
   {
      return trackName;
   }
   public int getTrackDuration()
   {
      return trackDuration;
   }
   public String getArtist()
   {
      return artist;
   }   
      
   public String toString()
   {
      String rt ;

      
      rt = "\nName " + this. trackName +
           "\nDuration "   + this.trackDuration +
           "\nArtist "     + this.artist;
      
      return  rt;
   }
   
}
