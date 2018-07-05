package vzap.losh.media;

import vzap.losh.enumerations.*; // if Enums defined in the parameter list are in imported by the super class why do I need to import again
import vzap.losh.exceptions.MediaNameException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Formatter;

public class DVD extends Media implements Serializable
{
   //atributes:
   //main actor
   //supporting actor
   //
  
   public DVD (String mediaName, Media_Type mediaType, int duration, Genre genre) throws MediaNameException
   {
         super(mediaName,mediaType,duration,genre, duration);
   }
   
   public DVD (String mediaName, Media_Type mediaType, Genre genre) throws MediaNameException
   {
         this(mediaName,mediaType,0,genre);
   }

}