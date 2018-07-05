package vzap.losh.catalogue;
import  vzap.losh.enumerations.*;
//import  vzap.losh.exceptions.*;
import  vzap.losh.media.*;

import java.io.Serializable;
import  java.util.ArrayList;

import javax.swing.JTextField;

public class Catalogue implements Serializable
{
   /**
	 * 
	 */
   private static final long serialVersionUID = 1L;
   private ArrayList<Media> mediaCatalogueList; // has an array list of media and is responsible for maintaining this
   private CatalogueDAO catalogueDAO;
   
   
   public Catalogue()
   {
      //
      this.mediaCatalogueList = new ArrayList<Media>();
      catalogueDAO = new CatalogueDAO();
      this.mediaCatalogueList = catalogueDAO.loadCatalogue();
       
   }
 /*  
   public Catalogue()
   {
      this(null);
   }
 */
   
   public ArrayList<Media> getMediaList()
   {
      return this.mediaCatalogueList;    
   }
   // add to Media List
   public boolean addTomediaCatalogueList(Media media)
   {  
      if (this.findMediaByTitle(media.getMediaName()) == null)
      {
         this.mediaCatalogueList.add(media);
         this.catalogueDAO.save(this.mediaCatalogueList);
         return true;
      }
      
      return false;
   }
   
   // delete from Media List 
   public boolean deleteFromMediaList(Media media)
   {  
      
      if (this.findMediaByTitle(media.getMediaName()) != null)
      {  
         if (this.mediaCatalogueList.remove(this.findMediaByTitle(media.getMediaName())))
         {
                  this.catalogueDAO.save(this.mediaCatalogueList);
                  return true;
         }
      }
      
      return false;
   }
   
   
   public Media findMediaByTitle(String nameText )
   {
      /* call catalagoue, Get media using the string return null if not found */
      
      for (Media media : this.mediaCatalogueList)
      {
         if (media.getMediaName().equalsIgnoreCase(nameText))
         {
            return media;
         }
      }
      
      return null;
            
   }

   public Media findMediaByTitleAndGenre(String nameText, Genre g)
   {
      /* call catalagoue, Get media using the string return null if not found */
	  Genre g2;
	  
      for (Media media : this.mediaCatalogueList)
      {
         if (media.getMediaName().equalsIgnoreCase(nameText))
         {
        	switch (media.getMedia_Type())
        	{
        	case CD: return (CD)media; 
        	case DVD: return (DVD)media;
        	case GAME: return (Game)media; 
        	}
            
         }
      }
      
      return null;
   }
            
   /**
      Edit media list
   */
   // add to Media List
    public boolean addToMediaList(Media media)
    {  
       if (this.findMediaByTitle(media.getMediaName()) == null)
       {
          this.mediaCatalogueList.add(media);
          this.catalogueDAO.save(this.mediaCatalogueList);
          //this.catalogueDAO
          
          // if DAO thread exists and is complete then destroy it ? .
          // create a new reference.
          
          //Thread x = new Thread();
          
          //x.destroy();
          return true;
       }
       return false;
   }
   
     
   
  
   public String toString()
   {
      String rt = "Catologue..................................";
      for (int pos = 0 ; pos < this.mediaCatalogueList.size(); pos++)
      {
         
         Media_Type test = this.mediaCatalogueList.get(pos).getMedia_Type();
         
         switch (test) 
         {
            case CD: CD cdin = (CD) this.mediaCatalogueList.get(pos);
                               rt+=cdin.toString(); 
                               break;
            default: System.out.println("Heuston I have a problem");
         }
         
      }
      
      return rt;
       
   }

@SuppressWarnings("null")
public ArrayList<Media> listAllOfThisMediaThisGenre(int inMediaType, int inGenre) {
	
	ArrayList<Media> returnedExtract = new ArrayList<Media>();
	
	for (Media media:this.mediaCatalogueList)
	{
		if ((media.getGenre().getGenreNumber() ==inGenre) && (media.getMedia_Type().getMediaTypeNumber() == inMediaType))
		{
			returnedExtract.add(media);
		}
	}
	
	return returnedExtract;
	
}
   
   
   
  // public boolean delete , add
/*   
   public static void main (String[] args)
   {
        // ArrayList<Track> listOftracks = new (
        
         Track t1= new Track("name1" , 5, "ACDC");  
         Track t2= new Track("", 4 , "ACDC + Kid Rock");
         ArrayList<Track> listOftracks = new ArrayList<Track>();
         listOftracks.add(t1);
         listOftracks.add(t2);

         CD cd1 = new CD("ACDC", Media_Type.CD, 0, Genre.ROCK , listOftracks);
         CD cd2 = new CD("25",   Media_Type.CD, 0, Genre.POP , listOftracks);
         
         //System.out.println(cd1);
         System.out.println("After cd1 printed  ");
         
         
         Catalogue c1 = new Catalogue();
         c1.addTomediaCatalogueList(cd1);
         c1.addTomediaCatalogueList(cd2);
         
         System.out.println("Display find return " + c1.findMediaByTitle("ACDC"));
         System.out.println("Delete entry  "  + c1.deleteFrommediaCatalogueList(cd1) );
         System.out.println("Delete entry  "  + c1.deleteFrommediaCatalogueList(cd1) );
         System.out.println(c1.addTomediaCatalogueList(cd2));
        // System.out.println("Display find return " + deleteFrommediaCatalogueList );
          
        //this.mediaCatalogueList.addFrommediaCatalogueList(cd1);
        // System.out.println(c1);
        // c1.addTomediaCatalogueList(cd1);
        //   ( "25", Media_Type.CD, 0, "POP", null);
        //    );
         
   
   }*/
   
}// end of class


