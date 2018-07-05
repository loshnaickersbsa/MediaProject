package vzap.losh.catalogue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Properties;

import vzap.losh.media.Media;

public class CatalogueDAO { //extends Thread{
	
	private File file;
	private String fileTable;
	private ArrayList<Media> catalogueList;
	
	public CatalogueDAO() {
		this.file = null;
		this.fileTable = null;
		this.catalogueList = null;
	}
	
	public ArrayList<Media> loadCatalogue(){
		
		
		file = new File("resources/mediaproject.properties");
		Properties prop = new Properties();
		FileInputStream input = null;
		
		try 
		{
		   
		     input = new FileInputStream(file);
		     prop.load(input); //loads the stream to the property class
		}
		catch (FileNotFoundException fnf)
		{
			fnf.printStackTrace();
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
		
			
		this.fileTable = prop.getProperty("catalogueTable");
		this.file = new File(this.fileTable);
		
		try {
			input.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		if (!(this.file.exists()))
		{
			this.catalogueList = new ArrayList<Media>();
			ObjectOutputStream oos;
			try {
				
				oos = new ObjectOutputStream(new FileOutputStream(this.fileTable));
				oos.writeObject(this.catalogueList);
				oos.flush();
				oos.close();
				
			} 
			catch (FileNotFoundException fnf)
			{
				fnf.printStackTrace();
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(this.file));
			this.catalogueList = (ArrayList<Media>) ois.readObject();
			ois.close();
		} 
		catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return this.catalogueList;
		
		
	}
	
	public boolean save(ArrayList<Media> catalogueList)
	{
		try {			
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.fileTable));
			oos.writeObject(catalogueList);
			oos.flush();
			oos.close();
			
			return true;
			
		} 
		catch (FileNotFoundException fnf)
		{
			fnf.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return false;
		
	}

}
