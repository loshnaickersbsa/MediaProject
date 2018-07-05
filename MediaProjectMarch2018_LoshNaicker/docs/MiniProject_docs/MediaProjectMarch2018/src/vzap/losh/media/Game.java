package vzap.losh.media;

import java.io.Serializable;

import vzap.losh.enumerations.Genre;
import vzap.losh.enumerations.Media_Type;
import vzap.losh.exceptions.MediaNameException;

public class Game extends Media implements Serializable
{

	public Game(String mediaName, Media_Type mediaType, Genre genre, int copies) throws MediaNameException {
		super(mediaName, mediaType, genre, copies);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

   //attrib
   //theme , string
   //

}