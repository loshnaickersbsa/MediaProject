package vzap.losh.media;

import java.io.Serializable;

import vzap.losh.enumerations.Genre;
import vzap.losh.enumerations.Media_Type;
import vzap.losh.exceptions.MediaNameException;

public class GAME extends Media implements Serializable
{

	public GAME(String mediaName, Media_Type mediaType, int duration, Genre genre, String gamingHouse, int copies) throws MediaNameException {
		super(mediaName, mediaType, duration, genre, gamingHouse, copies);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

   //attrib
   //theme , string
   //

	   
   public String getGamingHouse() {
		return mediaArtist;
	}

	
}