package vzap.losh.user;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Properties;
import vzap.losh.user.*;

public class UserCatalogDAO {


	private File file;
	private String userTable;
	private ArrayList<User> uList;
	
	public  UserCatalogDAO()
	{
		this.file=null;
		this.userTable =null;
		this.uList =null;
	}
	/*
	 * catalogueTable=resources/mediacatalogue.bin
userCatalogueTable=resources/usercatalogue.bin
borrowerTable=resources/borrowerDB.bin
mediaDB=....
	 */
	
	public ArrayList<User> loadUserCatalogue() 
	{ 
		
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
		
		try {
			input.close();
		} catch (IOException io) {
			io.printStackTrace();
		}
		
		this.userTable = prop.getProperty("userCatalogueTable");
		this.file = new File(this.userTable);
		
		if(!(this.file.exists()))
		{
			this.uList = new ArrayList<User>();
			ObjectOutputStream oos;
			try{
				oos = new ObjectOutputStream(new FileOutputStream(this.file));
				oos.writeObject(this.uList);
				oos.flush();
				oos.close();
			}
			catch (FileNotFoundException fnf)
			{
				fnf.printStackTrace();
			}
			catch(IOException ioe)
			{
				ioe.printStackTrace();
			}
			
		}
		
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(this.file));
			this.uList = (ArrayList<User>) ois.readObject();
			ois.close();
		}
		catch (ClassNotFoundException cnf)
		{
			cnf.printStackTrace();
		}
		catch (FileNotFoundException fnf)
		{
			fnf.printStackTrace();
		}
		catch (IOException io)
		{
			io.printStackTrace();
		}
		
		return this.uList;
		
	}
	
	public boolean save(ArrayList<User> uList)
	{
		try {			
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.userTable));
			oos.writeObject(uList);
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
