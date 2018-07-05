package vzap.losh.media;

import vzap.losh.enumerations.*; // if Enums defined in the parameter list are in imported by the super class why do I need to import again
import vzap.losh.exceptions.MediaNameException;

import java.io.Serializable;


public class DVD extends Media implements Serializable
{
   //atributes:
   //main actor
   //supporting actor
   //
  
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


   



public DVD(String mediaName, Media_Type mediaType, int duration, Genre genre, String mediaArtist,int copies) throws MediaNameException {
	super(mediaName, mediaType, duration, genre, mediaArtist, copies);
	// TODO Auto-generated constructor stub
}

public DVD(String mediaName, Media_Type mediaType, Genre genre, String mediaArtist,int copies) throws MediaNameException {
	this(mediaName, mediaType, 0, genre, mediaArtist, copies);

	// TODO Auto-generated constructor stub
}



public String getDirector() {
	return mediaArtist;
}

}