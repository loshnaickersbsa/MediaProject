package vzap.losh.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Vector;

//import javax.swing.event.TableModelEvent;
//import javax.swing.table.AbstractTableModel;
//import javax.swing.table.AbstractTableModel;
//import javax.swing.table.DefaultTableModel;

public class UserCatalog  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ArrayList<User> userList; // has an array list of users and is responsible for maintaining this
	private UserCatalogDAO userCatalogDAO;
/*
    public UserCatalog(ArrayList<User> userList) {
		super();
		this.userList = userList;
	}
    */
    /*
     * Add to Jable 
     * fireTableDataChanged
     * add to the array list
     */
    public UserCatalog() {
    	
    	this.userList= new ArrayList<User>();
		userCatalogDAO = new UserCatalogDAO();
		this.userList= userCatalogDAO.loadUserCatalogue();
		/*
		 * 
		 */
    	try
		{
			this.userList.add(new User("losh", "test", "Loshen", "Naicker", true));
			this.userList.add(new User("kaidenA", "K4iden75", "Kaiden", "Naicker", false)); //not an admin
		}
		catch(Exception e)
		{
			System.out.println("error with test user add");
		}
	}

	
	public ArrayList<User> getUserList() 
    {
		return userList;
	}

	public Vector<String> userListVector()
	{
	
		Vector<String> outVector = new Vector<String>();
	
		Vector<String> columnDummy = new Vector<>();
		Formatter f= new Formatter();
	    f.format("%15S   %30S %30S  %5S",  " User ID" ,
					 " First Name" , 
					 " Surname"  ,
					 " Admin");
	    outVector.addElement(f.toString());
	    f.close();
	
		
		
		for (User user:userList)
		{	
			outVector.addElement(convertUsertoString(user));
		}
		
		return outVector;
	}
	
	public String convertUsertoString(User user) {
		// TODO Auto-generated method stub
		String rt= null;
		Formatter f= new Formatter();
	    f.format("%15S , %30S , %30S , %5S",  user.getUserID() ,
	    		   								 user.getFirstName() , 
	    		   								 user.getSurName()  ,
	    		   								 (new Boolean(user.isAdmin()).toString()));
	    rt=f.toString();
	    f.close();
		
		return rt;
	}



	
	public void setUserList(ArrayList<User> userList) 
	{
		this.userList = userList;
	}
		
	public	boolean addUserToCatalog(User user)
	{
		if (searchUserFromCatalog(user.getUserID()) ==null)
		{
			this.userList.add(user);
			this.userCatalogDAO.save(userList);
			return true; 
		}
		return false;
	}
	
	public boolean saveTheUserList(ArrayList<User> userList)
	{
		this.userCatalogDAO.save(userList);
		return true;
	}
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	public boolean deleteUserFromCatalog(User user)
	{
		if (searchUserFromCatalog(user.getUserID())  !=null)
		{
			this.userList.remove(user);
			this.userCatalogDAO.save(userList);
			return true;
		}
		return false;
	}
	
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	public User searchUserFromCatalog(String userId)
	{
		for (User u : userList)
		{
			
			if (u.getUserID().equalsIgnoreCase(userId))
			{
				return u;
			}
		}
		return null;
	}
	
	//allUsers.contains(uFound)	

	public int searchUserFromCatalogForIndex(String userId)
	{
		for (int i = 0; i < userList.size(); i++) 
		{
			if (userList.get(i).getUserID().equalsIgnoreCase(userId))
			{
				return i;
			}
		}
		

		return -1;

		
	}
	
}