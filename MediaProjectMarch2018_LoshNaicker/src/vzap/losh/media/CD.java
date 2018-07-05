package vzap.losh.media;

import vzap.losh.enumerations.*; // if Enums defined in the parameter list are in imported by the super class why do I need to import again
import vzap.losh.exceptions.MediaNameException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Formatter;
//import vzap.losh.exceptions.*;

public class CD extends Media implements Serializable
{  
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Atributes
   //For a specific CD we have 
   // 
   //  - each track has a duration
   // Artist
   // lyrics
   //
   // getMediaName will get CD name
   // getTrackName
   // getTrackList
   // getTrackName
   // 
   // 

   private ArrayList<Track> listOftracks;  // has an array list of tracks so it is responsible for maintaining this
   
   public CD (String mediaName, Media_Type mediaType, int duration, Genre genre ,  ArrayList<Track> listOfTracks, String cdArtist , int copies) throws MediaNameException 
   {
         super(mediaName,mediaType,duration,genre,cdArtist, copies);
         this.listOftracks =  listOfTracks;
      //   this.mediaArtist = mediaArtist;
   }
   
   public CD (String mediaName, Media_Type mediaType, int duration, Genre genre,String cdArtist, int copies) throws Exception
   {
         this(mediaName,mediaType,0,genre,null, cdArtist, copies);
   }
   
   
   
   public String getCdArtist() {
	return mediaArtist;
}

public ArrayList<Track> getListOftracks() {
	return listOftracks;
}

protected void setListOftracks(ArrayList<Track> listOftracks) {
	this.listOftracks = listOftracks;
}

public void setCdArtist(String cdArtist) {
	this.mediaArtist = cdArtist;
}

public String toString()
   {
      Formatter f = new Formatter();
      String rt ;

      f.format("\n%35S %35S %35S %35S %35S",  this.getMediaName(),
    		  							      this.getCdArtist(),
                                              this.getDuration(),
                                              this.getGenre(),
                                              this.getCopies());
      rt=f.toString();
      f.close();
      if (listOftracks != null)
      {
         int x=0;             
         for (Track track : listOftracks)
         {
            rt+="\nTrack number : " + ++x;
            rt+= track.toString();
         }
      }          
      return rt;   
    }
    
    /** Add a track*/
    
    /** Delete a track*/ 
    
    /** Edit track duration */
    
/*    public static void main(String[] args)
    {
          //CD newCd ;
          //CD cd1 = new CD("ACDC", Media_Type.CD, 0, Genre.ROCK , null);
          //CD cd2 = new CD("25",   Media_Type.CD, 0, Genre.POP , null);
         
          Track t1= new Track("name1" , 5, "ACDC");  
          Track t2= new Track("", 4 , "ACDC + Kid Rock");
          
          ArrayList<Track> listOftracks = new ArrayList<Track>();
          listOftracks.add(t1);
          listOftracks.add(t2);
          
          CD cd1 = new CD("ACDC", Media_Type.CD, 0, Genre.ROCK , listOftracks);
          System.out.println(cd1);
          
          //mediaName "" Media_Type mediaType, int duration, Genre genre 
          
    }*/
    
}