package vzap.losh.borrow;

import java.io.Serializable;
import java.util.ArrayList;

import vzap.losh.media.Media;
import vzap.losh.user.User;

public class BorrowedMediaList implements Serializable {

	/**
	 * 
	 */
	
	private ArrayList<BorrowedMedia> bMList;
	private static final long serialVersionUID = 1L;
	private BorrowedMediaDAO borrowMediaListDAO;
	
	/*
	public BorrowedMediaList(ArrayList<BorrowedMedia> bMList)
	{
		
		
		
	}
	*/
	public BorrowedMediaList()
	{
		this.bMList = new ArrayList<BorrowedMedia>();
		this.borrowMediaListDAO = new BorrowedMediaDAO();
		this.bMList = borrowMediaListDAO.loadBorrowedMediaList();
	}
	
	
	
	protected ArrayList<BorrowedMedia> getbMList() {
		return bMList;
	}

	


	public boolean checkIfMediaIsAvailble(Media media)
	{
		
		int count=0;
		// On media how many copies are there?
		
		//Retrieve from the 
		for (BorrowedMedia bM: bMList)
		{
			if (bM.getMedia().getMediaName().equals(media.getMediaName()))
			{
				count++;
			}
		}

		if (count < media.getCopies())
		{
			return true;
		}
		return false;
	}
	
	
	public ArrayList<BorrowedMedia> listBorrowedMedia (User u)
	{
		
		ArrayList<BorrowedMedia> myBMnew = new ArrayList<BorrowedMedia>();
		
		for (BorrowedMedia bM: bMList)
		{
			//System.out.println(" Inside search");
				myBMnew.add(bM);
		}

		return myBMnew;
		
		
	}
	
	public  boolean returnBorrowedMedia(BorrowedMedia bM)
	{
		this.bMList.remove(bM);
		this.borrowMediaListDAO.save(bMList);
		return true;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}



	public boolean addBorrowedMedia(User user , Media foundMedia) {
		bMList.add(new BorrowedMedia(user,foundMedia));
		this.borrowMediaListDAO.save(bMList);
		return true;
		// TODO Auto-generated method stub
		
	}

}
