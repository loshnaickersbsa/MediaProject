package vzap.losh.media;
import java.io.Serializable;

import  vzap.losh.enumerations.*;
import  vzap.losh.exceptions.*;

/*
Other classes 

Class: Catalogue 
----------
Methods to find delete media


Class: Media Library
-----------------
-Test program
-Interact with the End user.
-easy in 
-name in  and password
-password alows access to the menu system
-list media
  dvd
Games
-add cd,game or dvd
-delete cd,dvd, or game

Arraylist for catalgoue - of media.
*/

public abstract class Media implements Serializable
{  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//inherited by CD, DVD and GAME

 // Attributes:
 // String mediaName
 // int duration minutes
 // String mediaType enumration
 // String Genre , movie etc cmbine
 
 private Media_Type mediaType; 
 private String mediaName;
 private int duration; //in minutes
 private Genre genre;
 private int copies; 
 public String mediaArtist;
 
 public Media(String mediaName, Media_Type mediaType, int duration, Genre genre,String mediaArtist, int copies)  throws MediaNameException 
 {
  if (mediaName  == null || mediaName.isEmpty())
	 throw new MediaNameException("Name must not be null or empty ");
  
   this.mediaName = mediaName;
   this.mediaType = mediaType;
   this.duration = duration;
   this.genre = genre;
   this.copies= copies;
   this.mediaArtist = mediaArtist;
   
 }
	/**
	getMediaName,
	setMediaName,
	getMediaType, //all DVD's
	setMediaType, //change to a horror movie
	getMediaGenre, //all Horror movies
	setMediaGenre  //change to a horror movie
	getDuration // list all movies < 2 hours in minutes
	setDuration // if incorrect
	*/
 
 //super(mediaName,mediaType,duration,genre, duration,copies);
 public Media(String mediaName, Media_Type mediaType,  Genre genre,String mediaArtist, int copies)  throws MediaNameException
 {
   this(mediaName,mediaType,0,genre,mediaArtist,copies);
 }

 public String getMediaName()
 {
   return mediaName;
 }

 public int getCopies() {
	return copies;
}
public void setCopies(int copies) {
	this.copies = copies;
}
public Media_Type getMedia_Type()
 {
   return this.mediaType;
 }

 public int getDuration()
 {
   return duration;
 }
 public Genre getGenre()
 {
   return genre;
 }
 
 public String toString()
 {
      return "\nMedia................................." +
              "\nType " + mediaType + 
             "\nmediaName: " + mediaName +
             "\nduration: " + duration +
             "\nGenre: " + genre +
             "nCopies" + this.copies +
             "\nMedia.end ............................"  ;
       
 }
 //public to string 
 
 //public abs


}//end of class

/**
class TestMedia extends Media
{
   public TestMedia(String media, Media_Type type, int duration, Genre genre)
   {
      super(media, type, duration, genre);
   }
   
   public static void main(String[] args)
   {
   
      TestMedia tm;
      tm = new TestMedia("Titanic" , Media_Type.DVD, 120, Genre.ACTION); 
      System.out.println("media name : " + tm.getMediaName());
      System.out.println("media name : " + tm);
       
       
   }
   
}
**/
