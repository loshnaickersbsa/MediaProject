package vzap.losh.application;

import  vzap.losh.enumerations.*;
import  vzap.losh.exceptions.*;
import  vzap.losh.media.*;
import  java.util.ArrayList;
import java.util.Iterator;

import org.w3c.dom.CDATASection;

import  vzap.losh.utils.EasyIn;
import  vzap.losh.utils.StringChecks;
import vzap.losh.borrow.BorrowedMedia;
import vzap.losh.borrow.BorrowedMediaList;
import  vzap.losh.catalogue.*;
import  vzap.losh.user.*;

//import  vzap.losh.user.*;

// Media player is the view 

public class MediaLibrary 
{
	private EasyIn input;
	private Catalogue mainCatalogue;
	// private String userIdForTest;
	//private String passwordForTest;
	private User currentUser;
	private UserCatalog uList;
	private boolean loggedIn;
	private BorrowedMedia borrowedMedia;
	private BorrowedMediaList borrowedMediaList;


	public MediaLibrary()
	{
		this.input = new EasyIn(); //creates an object to assign to the reference variable
		//this.mainCatalogue = new Catalogue(this.prepopulatedArrayListFromDB());

		this.mainCatalogue = new Catalogue();
		this.uList = new UserCatalog(); 
		this.borrowedMediaList = new BorrowedMediaList();

		// login loop
		this.loggedIn=false;
		this.currentUser=this.login();

		this.printGeneralMenu();



		// process loop , till 
	}

	/**
      Returns the a pre-poulated medialist array   
	 */
	/*
	public ArrayList<Media> prepopulatedArrayListFromDB()
	{

		Track t1= new Track("name1" , 5, "ACDC");  
		Track t2= new Track("test2", 4 , "ACDC + Kid Rock");

		ArrayList<Track> listOftracks = new ArrayList<Track>();
		listOftracks.add(t1);
		listOftracks.add(t2);

		ArrayList<Media> mediaDB = new ArrayList<Media>();
		try 
		{
			CD cd1 = new CD(null, Media_Type.CD, 0, Genre.ROCK , listOftracks);
			mediaDB.add(cd1);
		}
		catch(MediaNameException mne)
		{
			mne.printStackTrace();
			System.out.println("test data error 1");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}

		try
		{
			CD cd2 = new CD("25",   Media_Type.CD, 0, Genre.POP , listOftracks);
			mediaDB.add(cd2);
		}
		catch(MediaNameException mne)
		{
			mne.printStackTrace();
			System.out.println("test data error 2");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		//System.out.println(cd1);
		//System.out.println("After cd1 printed  ");


		return mediaDB;

	}

	 */
	public User login ()
	{


		String userName = ""; 
		String password = "";

		while (true)
		{
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("..................VZAP Login.........................");
			System.out.print("Please enter the user Name : ");
			userName=input.readString();

			System.out.print("Please enter the Password : ");
			password=input.readString();
			System.out.println("..................Logged in complete................");
			System.out.println();
			System.out.println();
			System.out.println();

			//retrieve user that matches the string
			try
			{
				this.currentUser = this.uList.searchUserFromCatalog(userName);
			}
			catch(NullPointerException npe)
			{ 
				System.out.println("..................User  not found!..................");
			}
			//check if name and password match
			if ( this.currentUser != null && 
					userName.equals(this.currentUser.getUserID())   &&
					password.equals(this.currentUser.getPassword())   )
			{
				break;
			}

		}

		//System.out.println(" out");
		this.loggedIn =true;

		return currentUser;

	}


	public void printGeneralMenu()
	{
		if (!this.loggedIn)
			System.exit(0);
		int selection=0; 

		while (true)
		{
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("....................................................."); 
			System.out.println(".  Main Menu                                        ."); 
			System.out.println("....................................................."); 


			if (this.currentUser.isAdmin())
			{
				System.out.println(". 1.  Add CD                                       .");
				System.out.println(". 2.  Delete CD                                    .");
				System.out.println(". 3.  Edit CD 					                   .");

			}

			System.out.println(". 4.  Find CD using Album Name                     .");
			System.out.println(". 5.  Find CD using Song title                     .");
			System.out.println(". 6.  List all CDs                                 .");
			System.out.println("...................................................."); 

			if (this.currentUser.isAdmin())
			{
				System.out.println(". 7.  Add DVD                                       .");
				System.out.println(". 8.  Delete DVD                                    .");
				System.out.println(". 9.  Edit DVD             	 	                    .");

			} 
			System.out.println(". 10.  Find DVDs using Name                        .");
			System.out.println(". 11.  Find DVDs using cast and/or year            .");
			System.out.println(". 12.  List all DVDs                               .");
			System.out.println("...................................................."); 
			if (this.currentUser.isAdmin())
			{	        
				System.out.println(". 13.  Add Games                                     .");
				System.out.println(". 14.  Delete GAME                                   .");
				System.out.println(". 15.  Edit DVD             	 	                 .");
			} 	 
			System.out.println(". 16.  Find GAME using Name                         .");
			System.out.println(". 17.  Find GAME using Gaming house                 .");
			System.out.println(". 18.  List all games                               .");
			System.out.println("....................................................."); 
			System.out.println("................Users................................"); 

			if (this.currentUser.isAdmin())
			{
				System.out.println(". 19.  Add User                                    .");
				System.out.println(". 20.  Delete User                                 .");
				System.out.println(". 21.  List Users                                  .");
				System.out.println(". 22.  Change Passwords                            .");
				System.out.println("...................................................."); 
			}      

			System.out.println("  23. Borrow Media                                  .");
			System.out.println("  24. Return Media                                  .");
			System.out.println("  25. List Borrowed Media                           .");
			System.out.println("                                                    .");
			System.out.println("  				                                    .");
			System.out.println(". 26. Exit System                                   .");
			System.out.println(".....................................................");

			System.out.println();
			System.out.println();
			System.out.println(); 

			System.out.println("...................................................."); 
			System.out.print(" Enter Selection :"); 

			selection = input.readInt();

			//.....................................................

			boolean processIncomplete=true;

			switch (selection)
			{
			case 1: 
				while (processIncomplete)
				{  
					try 
					{
						this.addCDScreen(loggedIn);
						processIncomplete=false;
					}
					catch(MediaNameException mne)
					{
						processIncomplete=true;
						System.out.println(".........Add error no record inserted................");
						mne.printStackTrace();
					}			       
					catch(Exception mne)
					{
						mne.printStackTrace();  
						System.out.println(".........Add error no record inserted................");
						processIncomplete=true;        	   		   
					}
				}	   
				break;
			case 2: this.deleteCDScreen(loggedIn);
			break;
			case 3: // edit this.deleteCDScreen(loggedIn);
				break;


			case 4: this.findCDUsingName(loggedIn);
			break;

			case 5: this.findCDUsingName(loggedIn);  
			break;

			case 6: this.listAllCDs(loggedIn);
			break;
			case 19:while (processIncomplete)
			{        	   
				try
				{
					this.addUserScreen();
					processIncomplete=false;
				}
				catch(UserClassException uce)
				{
					processIncomplete=true;
					System.out.println(".........Add error no record inserted................");
					//uce.printStackTrace();
				}
			}
			break;

			case 20:
				this.deleteUserScreen();
				break;

			case 21:
				this.listUserScreen();
				break;

			case 22:changeUserPassword();

			break;

			case 23:this.borrowMediaScreen();
			//lend 
			break;
			case 24:this.returnMediaScreen();
			break;
			case 25:this.listAllBorrowedMedia();
			break;

			case 26: System.exit(0);
			break;

			default: System.out.println(" Please enter correct option! ");  

			}//case 



		} //while


	}



	private void listAllBorrowedMedia() {
		// TODO Auto-generated method stub

		ArrayList<BorrowedMedia>  bMEL = this.borrowedMediaList.listBorrowedMedia(this.currentUser);

		if (bMEL.isEmpty())
		{
			System.out.println(" No books to return");
			return;
		}
		for (int i = 0; i < bMEL.size(); i++) 
		{
			System.out.println( " [" + (i+1) + " ]"  + bMEL.get(i).getMedia()); 
		} 


	}

	private void returnMediaScreen() {
		// TODO Auto-generated method stub

		//BorrowedMedia[] x;
		ArrayList<BorrowedMedia>  bMEL = this.borrowedMediaList.listBorrowedMedia(this.currentUser);

		if (bMEL.isEmpty())
		{
			System.out.println(" No books to return");
			return;
		}
		for (int i = 0; i < bMEL.size(); i++) 
		{
			System.out.println( " [" + (i + 1) + " ]"  + bMEL.get(i).getMedia()); 
		} 

		int valueIn;

		while (true)
		{
			System.out.print("Enter the number of the media to return :");
			valueIn=input.readInt();
			valueIn--;
			if (valueIn <= bMEL.size())
			{
				break;
			}
		}

		this.borrowedMediaList.returnBorrowedMedia(bMEL.get(valueIn));
		System.out.println("Media return.");
		System.out.println("Media Outstanding : ");
		this.listAllBorrowedMedia();

	}

	public void changeUserPassword()
	{
		if (!this.loggedIn)
			System.exit(0);

		String newPassword;
		char accept='n';

		System.out.println();
		System.out.println();
		System.out.println(); 
		System.out.println("...............Change User Password Screen...........");
		System.out.print("Which user id do you want to change the password for? ");
		String userId = input.readString();

		User uFound = this.uList.searchUserFromCatalog(userId);

		if ( uFound != null)
		{	

			//display the user 
			listUserScreen (uFound);

			// change the password
			System.out.print("Please enter the new password: ");
			newPassword = input.readString();

			// confirm that you are happy with the password
			System.out.println("Are you happy with your new password  y/n ?");
			accept = Character.toLowerCase(input.readChar());

			if (accept == 'y') 
			{

				System.out.println("password changed! ");
			}



		}
		else 
		{
			System.out.println("This user is not found");
		}


	}
	public void deleteUserScreen()
	{
		if (!this.loggedIn)
			System.exit(0);

		System.out.println();
		System.out.println();
		System.out.println(); 
		System.out.println("..................Delete User Screen.................");

		System.out.println("Please enter the user id you wish to delete");
		String userId = input.readString();

		User uFound = this.uList.searchUserFromCatalog(userId);

		char deleteYN='n';

		if ( uFound != null)
		{	

			System.out.println("Do you want to delete the user :");
			listUserScreen (uFound);

			System.out.println(" y/n ?");
			deleteYN= Character.toLowerCase(input.readChar());
			if (deleteYN=='y')
			{
				this.uList.deleteUserFromCatalog(uFound);
				System.out.println("User deleted");
			}
		}
		else 
		{
			System.out.println("This user is not found");
		}


	}

	/** if catalog blank or read error boolean false */
	public void listUserScreen (User user)
	{
		if (!this.loggedIn)
			System.exit(0);

		System.out.printf("%-35S%-35S%-35S%-35S\n","User id","|First Name","| Surname ", "| Admin");
		System.out.printf("%-35S%-35S%-35S%-35S\n",user.getUserID(),user.getFirstName(),user.getSurName(),user.isAdmin());		

		//return true;
	}

	public void listUserScreen ()
	{
		if (!this.loggedIn)
			System.exit(0);

		ArrayList<User> uCat = this.uList.getUserList();

		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("......................User List......................");
		System.out.printf("%-35S%-35S%-35S%-35S\n","User id","|First Name","| Surname ", "| Admin");
		for (User u:uCat)
		{
			System.out.printf("%-35S%-35S%-35S%-35S\n",u.getUserID(),u.getFirstName(),u.getSurName(),u.isAdmin());		
		}
		System.out.println("......................End of List...................");

		//return true;
	}

	/**

       Enter the cd you want to find - string
       call the find media in catalogue class

      If the name is found return 0

	 */
	public void findCDUsingName(boolean loggedIn)
	{
		System.out.println();
		System.out.println();
		System.out.println(); 
		System.out.println("..................Search Screen......................");
		System.out.print("Enter the name of the CD you want to find : ");
		String cdTitle = input.readString();

		Media returnedMedia= this.mainCatalogue.findMediaByTitle(cdTitle);

		if  (returnedMedia != null)
		{ 
			System.out.println(" CD Found \n" + returnedMedia);
		}
		else
		{
			System.out.println(" CD not found ");
		}
		//return false;
	}

	/**
	 * 
	 * 
	 * 
	 */


	private void borrowMediaScreen() {
		// TODO Auto-generated method stub

		//if there are copies of the searched media available then lend it to the person 
		//
		String cdTitle=null;
		char accept='n';


		if (!this.loggedIn)
			System.exit(0);

		System.out.println();
		System.out.println();
		System.out.println(); 
		System.out.println("..................Borrow MediaScreen....................");		

		System.out.println("Please enter the media type you want to borrow: ");


		for (Media_Type ml:Media_Type.values())
		{
			if (ml.getMediaTypeNumber() < 4)
			{
				//System.out.println(ml);
				System.out.println(" ( " + ml.getMediaTypeNumber() + " )" + " for " +  ml);
			}
		}

		int inMediaType;
		while (true)
		{
			inMediaType = input.readInt();
			if (inMediaType < 4)
				break;
		}


		Media_Type x=Media_Type.CD;

		System.out.println("Please enter the genre type you want to borrow: ");

		int[] genreList= new int[6];
		int cGenreList=0;
		for (Genre gT:Genre.values())
		{
			if (gT.genreMediaType() == x.ReturnMedia_Type(inMediaType) )
			{
				System.out.println("( " + gT.getGenreNumber() + " ) " +  gT );
				genreList[cGenreList++]=gT.getGenreNumber();
			}
		}

		int inGenre;
		while (true)
		{
			boolean found=false;
			inGenre=input.readInt();
			for (int i = 0; i< genreList.length; i++) {
				if (genreList[i] == inGenre)
				{
					found=true;
				}
			}
			if (found) break;
		}

		// list all media  of the type and genre
		ArrayList<Media> extract = mainCatalogue.listAllOfThisMediaThisGenre(inMediaType,inGenre);

		for (Media media: extract)
		{
			System.out.println(media);
		}
		if (extract.isEmpty())
		{
			System.out.println(" empty list ");
			return;
		}

		System.out.println("Please enter the name of the media you want to borrow :");
		String mediaTitle = input.readString();

		Media foundMedia = mainCatalogue.findMediaByTitle(mediaTitle);

		boolean isAvailable=borrowedMediaList.checkIfMediaIsAvailble(foundMedia);


		if (isAvailable)
		{
			borrowedMediaList.addBorrowedMedia(this.currentUser, foundMedia);
			System.out.println("..................successful borrow....................");

		}
		else
		{
			System.out.println(".................This media is not available............");
		}

	}	




	/**
       Enter the cd you want to delete - string
       call the find media in catalogue class
      If the name is found return 0
	 */
	public void deleteCDScreen(boolean loggedIn)
	{

		this.listAllCDs(this.loggedIn);
		char accept='n';
		String cdTitle;

		System.out.println();
		System.out.println();
		System.out.println(); 
		System.out.println("..................Delete Screen......................");

		System.out.print("Enter the name of the CD you want to delete : ");
		cdTitle = input.readString();

		System.out.print("The CD you entered was  " + cdTitle + " are you sure you want to delete it y / n ?");
		accept = input.readChar();
		accept = Character.toLowerCase(accept);

		if (accept == 'y')
		{
			Media inMedia=null; 
			try
			{
				//inMedia= new CD(cdTitle, Media_Type.CD, 0, null,null,0);
				inMedia = this.mainCatalogue.findMediaByTitle(cdTitle);

				if (inMedia.getCopies() >1)
				{
					System.out.println("---delete cancelled more than one copy exists of--- " + inMedia);
					return;
				}
			}
			catch (Exception ex)
			{
				System.out.println("Deletion error");
				ex.printStackTrace();
			}
			if  (this.mainCatalogue.deleteFromMediaList(inMedia))
			{ 
				System.out.println("..................successful delete..................");
			}
			else
			{
				System.out.println(".........delete cancelled - no record found..........");
			}
		}
		else
		{
			System.out.println("..................delete cancelled...................");
		}
		//return false;
	}

	/** */
	public Genre convertNumberToGenre(int genreNumber)
	{
		//Genre gT;

		Genre[] genreArray = Genre.values();

		for (Genre gT:genreArray)
		{
			if (gT.getGenreNumber() == genreNumber)
			{
				return gT;
			}
		}

		return null;
	}

	/** if catalogue blank or read error boolean false */
	public boolean listAllCDs (boolean loggedIn)
	{
		if (!loggedIn)
			System.exit(0);

		CD cdIn;
		ArrayList<Media> mediaList =this.mainCatalogue.getMediaList();

		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("......................CD Catologue List..............");
		System.out.printf("%35S %35S %35S %35S","CD Name", "|artist" ,"|duration","|Genre","|Copies");
		System.out.println();
		for (int pos = 0 ; pos < mediaList.size(); pos++)
		{

			Media_Type test = mediaList.get(pos).getMedia_Type();

			switch (test) 
			{
			case CD: cdIn= (CD) mediaList.get(pos);
			System.out.println(cdIn); 
			break;
			default: System.out.println("Heuston I have a problem");
			}

		}
		System.out.println("......................End of List...................");

		return true;
	}

	//ArrayList<Track> listOftracks = new ArrayList<Track>();
	// already have a user list to add
	// use . 
	//
	// 		.input fields and check for valid values. Re-enter the values.
	//		.create user
	//		.add user to catalog
	//		


	public void addUserScreen() throws UserClassException
	{

		String userId="", password="", firstName="", surname="";  
		boolean isAdmin=false; //
		char  isAdminChar='n';

		//String inName;
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println(".....................User Add Screen.................");
		char accept = 'n';
		if (!this.loggedIn)
			System.exit(0);

		System.out.println();
		System.out.println();
		System.out.println(); 

		while (accept == 'n')
		{
			System.out.print("Please enter the User id : ");
			userId = input.readString();

			if (this.uList.searchUserFromCatalog(userId) == null)
			{
				System.out.println("The user id you entered is  " + userId + " are you happy with this y/n ? ");
				accept = Character.toLowerCase(input.readChar());
			}
			else
			{
				System.out.println("The user id exists already! Please use a new one.");
				accept='n';
			}
		}

		accept = 'n';
		while (accept == 'n')
		{
			System.out.print("Please enter the password : ");
			password = input.readString();

			System.out.println("The password entered is  " + password + " are you happy with this y/n ? ");
			accept = Character.toLowerCase(input.readChar());

		}


		while (true)
		{   
			System.out.print("Please enter the users first name : ");
			firstName = input.readString();

			if (firstName != null && (!firstName.isEmpty()) && (!StringChecks.isNonAlpha(firstName)) )
			{
				break;
			}
			System.out.println("invalid first name");

		}//end of genre while 


		accept = 'n'; //reset accept

		while (true)
		{  
			System.out.print("Please enter the users surname : ");
			surname = input.readString();

			if (surname != null && (!surname.isEmpty()) && (!StringChecks.isNonAlpha(surname)))
			{
				break;
			}
			System.out.println("invalid surname");     


		} //while

		while (true)
		{

			System.out.print("Is this user an admin (y/n) ");
			isAdminChar = Character.toLowerCase(input.readChar());


			if (isAdminChar=='y') 
			{
				isAdmin=true;
				break;
			}

			if (isAdminChar=='n')
			{
				isAdmin=false;
				break;
			}

			System.out.println("invalid choice"); 

		}

		//		.create user
		//		.add user to catalog
		//		
		User userIn = new User(userId,password, firstName, surname, isAdmin);


		boolean success = this.uList.addUserToCatalog(userIn);


		if (success)
		{
			System.out.println("Successful Addition - > " + this.mainCatalogue);

		}

		System.out.println(" --- Operation complete ---- ");

	}

	public void addCDScreen(boolean loggedIn) throws Exception
	{
		//String inName;
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("......................CD Add Screen.................");
		Media_Type inMediaType = Media_Type.CD;
		String cdTitle="";
		int duration = 0;
		int inGenre=0;
		int numberOfTracks =0;
		int copies=0;
		Track trackN;
		Genre g=null;
		Genre[] genreArray = Genre.values();

		ArrayList<Track> listOftracks = new ArrayList<Track>();

		String trackName = "";
		int trackDuration = 0; 
		String artist = "";  
		char accept = 'n';

		// CD cd2 = new CD("25",   Media_Type.CD, 0, Genre.POP , null); 


		if (!loggedIn)
			System.exit(0);

		System.out.println();
		System.out.println();
		System.out.println(); 

		while (accept == 'n')
		{
			System.out.print("Please enter CD title : ");
			cdTitle = input.readString();

			System.out.println("The CD title you entered is  " + cdTitle + " are you happy with this y/n ? ");
			accept = input.readChar();



		}


		accept = 'n';
		while (accept == 'n')
		{
			System.out.print("Please enter Artist Album : ");
			artist = input.readString();

			System.out.println("The CD artist as " + artist + " are you happy with this y/n ? ");
			accept = input.readChar();

		}



		accept = 'n'; //reset genre
		while (accept == 'n')
		{   
			System.out.println("Please enter the associated values in () for the genres below : ");

			for (Genre gT:genreArray)
			{
				if (gT.genreMediaType() == Media_Type.CD )
				{
					System.out.println("( " + gT.getGenreNumber() + " ) " +  gT );
				}

			}

			inGenre = input.readInt();
			g=convertNumberToGenre(inGenre);

			if (g!= null)
			{
				System.out.print("The value you entered was  (" +inGenre + ") for " + g + " are you happy with this y/n ? ");
				accept = input.readChar();
			}
			else
			{
				System.out.println("invalid entry " + inGenre);
			}



		}//end of genre while 


		accept = 'n'; //reset accept




		System.out.println("How Many tracks are on the CD - enter 0 if you want to leave them blank  ");
		numberOfTracks = input.readInt();

		if (numberOfTracks != 0)
		{
			duration=0;
			for (int n=0; n  < numberOfTracks ; n++)
			{

				/*                     String trackName = "";
	                        int trackDuration = 0; 
	                        String artist = ""; 
				 */
				System.out.println("For track " + (
						n+1) + " : "  );

				System.out.print("Enter the trackName - ");
				trackName = input.readString();

				System.out.print("Enter the track duration - ");
				trackDuration = input.readInt();
				duration +=trackDuration;

				System.out.print("Enter the Artist on the track -");
				artist = input.readString();

				listOftracks.add(new Track(trackName , trackDuration, artist)); 

			}

		}

		else
		{
			listOftracks=null;

		}
		System.out.println("How Many copies of the CD - do you have ?");
		copies = input.readInt();

		accept='y';

		//CD cd1 = new CD("ACDC", Media_Type.CD, 0, Genre.ROCK , listOftracks);
		CD inMedia=null;

		inMedia= new  CD(cdTitle ,  Media_Type.CD, duration,  g , listOftracks, artist , copies);

		boolean success = this.mainCatalogue.addToMediaList(inMedia);

		if (success)
		{
			System.out.println("Successful Addition - > " + this.mainCatalogue);

		}

		System.out.println(" --- Operation complete ---- ");

	}

	public static void main (String[] args)
	{
		//creates new media library 

		MediaLibrary mL = new MediaLibrary();


	}   

}