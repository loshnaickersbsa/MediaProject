package vzap.losh.borrow;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Properties;

import vzap.losh.borrow.*;
import vzap.losh.user.User;

public class BorrowedMediaDAO {

	private File file;
	private String borrowedTable;
	private ArrayList<BorrowedMedia> bMList;

	public BorrowedMediaDAO()
	{
		this.file =null;
		this.borrowedTable=null;
		this.bMList=null;
	}

	public ArrayList<BorrowedMedia> loadBorrowedMediaList ()
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
		this.borrowedTable = prop.getProperty("borrowerTable");
		this.file = new File(this.borrowedTable );

		if(!(this.file.exists()))
		{
			this.bMList = new ArrayList<BorrowedMedia>();
			ObjectOutputStream oos;
			try{
				oos = new ObjectOutputStream(new FileOutputStream(this.file));
				oos.writeObject(this.bMList);
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
			this.bMList = (ArrayList<BorrowedMedia>) ois.readObject();
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

		return this.bMList;

	}

	public boolean save(ArrayList<BorrowedMedia> bMList)
	{
		try {			
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.file));
			oos.writeObject(bMList);
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


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


}
