package vzap.losh.application;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import vzap.losh.utils.ClockClassDisplay;
import vzap.losh.application.Panels.*;
import vzap.losh.catalogue.Catalogue;
import vzap.losh.styles.Styles;
import vzap.losh.user.User;
import vzap.losh.user.UserCatalog;


//import vzap.losh.TestingTextAreajPanel;

public class MediaApplicationGUI extends JFrame implements ActionListener {

	/*menu bar 
	menu items 
	Video
	DVD
	 */ 

	private boolean loggedIn;
	private boolean isAdmin;
	private boolean testMode=true;

	private JMenuBar menuBar;

	// user class
	private User currentUser;
	private UserCatalog uList;

	private JMenu adminMenu, cdMenu, dvdMenu, gameMenu;
	private JMenu searchMenuItem, myDetails;
	private JMenuItem userMaint, cdMaint, gameMaint, dvdMaint;
	private JMenuItem findACD,listAllCD, borrowCD;
	private JMenuItem findADVD,listAllDVD, borrowDVD;
	private JMenuItem findAGame,listAllGame, borrowGame;

	private JLabel timeLabel;
	private JLabel status;

	private JPanel jFramePanel; // all the magic happens

	private Font mainMenuFont;
	private Font itemMenuFont;
	private JMenuItem userDetailsItem;
	//private JFont fontlabels;
	private JMenuItem resetPasswordItem;

	private JPanel jSouthPanel;
	private ClockClassDisplay cCdisplay;
	private Image bImage;
	private Pattern pattern;
	private Matcher matcher;
	private CDMaintPanel cdMaintPanel;


	//	private JPanel pan1 = null, pan2 = null,pan3 = null;
	//	private JTabbedPane tabbedPane = null, tabbedInner=null;

	// Order of work 
	/*
	x1. Complete main menu  and look and exit.

	x2.a complete the login screen as an inner class and then all the other panels will be passed the login value for security 
       b user login checks complete

	3. Complete user admin screen with JFrames for 
	   user add / Delete  ,view  OR  delete(are you sure), button 
	   create 
	   edit.

	3. Search Media next
	   with a grid layout
	   and borrow

	4. CD 
		-borrow
		-list
		-find

	5. CD 
		-borrow
		-list
		-find

	6. CD 
		-borrow
		-list
		-find

	7. Get it all working 
		and then
		1)Add spacing to menus AND borders,
		2)Icons to menu items
		3)The screen layout to the right with a related help file

	8.image on the main frame. 

	 */


	//private JFileChooser jFC;


	public MediaApplicationGUI ()
	{

		this.uList = new UserCatalog(); 

		// Admin
		userMaint = new JMenuItem("User");
		userMaint.setFont(Styles.subMenuFontlabels);
		userMaint.addActionListener(this);


		cdMaint= new JMenuItem("CD");
		cdMaint.setFont(Styles.subMenuFontlabels);
		cdMaint.addActionListener(this);

		gameMaint= new JMenuItem("Game");
		gameMaint.setFont(Styles.subMenuFontlabels);

		dvdMaint= new JMenuItem("DVD");
		dvdMaint.setFont(Styles.subMenuFontlabels);

		//CD
		findACD = new JMenuItem("Find CD");
		findACD.setFont(Styles.subMenuFontlabels);
		listAllCD = new JMenuItem("List all CDs");
		listAllCD.setFont(Styles.subMenuFontlabels);
		borrowCD = new JMenuItem("Borrow CDs");
		borrowCD.setFont(Styles.subMenuFontlabels);

		//DVD
		findADVD = new JMenuItem("Find DVD");
		findADVD.setFont(Styles.subMenuFontlabels);
		listAllDVD = new JMenuItem("List all DVDs");
		listAllDVD.setFont(Styles.subMenuFontlabels);
		borrowDVD = new JMenuItem("Borrow DVDs");
		borrowDVD.setFont(Styles.subMenuFontlabels);

		//Game
		findAGame = new JMenuItem("Find DVD");
		findAGame.setFont(Styles.subMenuFontlabels);
		listAllGame = new JMenuItem("List all DVDs");
		listAllGame.setFont(Styles.subMenuFontlabels);
		borrowGame = new JMenuItem("Borrow DVDs");
		borrowGame.setFont(Styles.subMenuFontlabels);

		userDetailsItem = new JMenuItem("User details");
		userDetailsItem.setFont(Styles.subMenuFontlabels);
		resetPasswordItem = new JMenuItem("Reset Password");
		resetPasswordItem.setFont(Styles.subMenuFontlabels);

		adminMenu = new JMenu(" Admin");
		adminMenu.setBorder(BorderFactory.createDashedBorder(Color.BLACK));
		adminMenu.setMnemonic('A');
		adminMenu.setFont(Styles.menuFontlabels);
		adminMenu.add(userMaint);
		adminMenu.add(cdMaint);
		adminMenu.add(gameMaint);
		adminMenu.add(dvdMaint);

		cdMenu = new JMenu(" CD");
		cdMenu.setBorder(BorderFactory.createDashedBorder(Color.BLACK));
		cdMenu.setMnemonic('C');
		cdMenu.setFont(Styles.menuFontlabels);
		cdMenu.add(findACD);
		cdMenu.add(listAllCD);
		cdMenu.add(borrowCD);

		dvdMenu= new JMenu(" DVD");
		dvdMenu.setBorder(BorderFactory.createDashedBorder(Color.BLACK));
		dvdMenu.setMnemonic('D');
		dvdMenu.setFont(Styles.menuFontlabels);
		dvdMenu.add(findADVD);
		dvdMenu.add(listAllDVD);
		dvdMenu.add(borrowDVD);

		gameMenu= new JMenu(" Game");
		gameMenu.setBorder(BorderFactory.createDashedBorder(Color.BLACK));
		gameMenu.setMnemonic('G');
		gameMenu.setFont(Styles.menuFontlabels);
		gameMenu.add(findAGame);
		gameMenu.add(listAllGame);
		gameMenu.add(borrowDVD);
		gameMenu.setBorderPainted(true);


		searchMenuItem= new JMenu(" Search the Media and Borrow");
		searchMenuItem.setBorder(BorderFactory.createDashedBorder(Color.BLACK));
		searchMenuItem.setMnemonic('S');
		searchMenuItem.setFont(Styles.menuFontlabels);

		myDetails= new JMenu(" My Profile");
		myDetails.setBorder(BorderFactory.createDashedBorder(Color.BLACK));
		myDetails.setMnemonic('P');
		myDetails.setFont(Styles.menuFontlabels);
		myDetails.add(userDetailsItem);
		myDetails.add(resetPasswordItem);

		menuBar = new JMenuBar();
		menuBar.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5 ));
		menuBar.add(adminMenu);
		menuBar.add(cdMenu);
		menuBar.add(dvdMenu);
		menuBar.add(gameMenu);
		menuBar.add(searchMenuItem);
		menuBar.add(myDetails); 
		menuBar.setBorderPainted(true);

		//details plus reset password
		menuBar.setVisible(false);
		adminMenu.setVisible(false);
		//	menuBar.setBorder();
		jFramePanel=new JPanel();
		jFramePanel = new JPanel(new GridLayout(1, 1));


		this.add(jFramePanel, BorderLayout.CENTER);

		//jFramePanel.setBackground(Color.BLUE);
		this.setTitle(" Media Catalog Application System");
		this.setBounds(20, 20, 800, 400);
		this.setSize(400, 250);
		this.add(jFramePanel, BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		timeLabel = new JLabel("time here");
		cCdisplay = new ClockClassDisplay(timeLabel);
		cCdisplay.getT().start();
		status= new JLabel("status");

		jSouthPanel= new JPanel(new FlowLayout(FlowLayout.RIGHT,300,10));
		jSouthPanel.setFont(Styles.statusFontlabels);
		jSouthPanel.setBorder(BorderFactory.createDashedBorder(Color.BLACK));
		jSouthPanel.setBackground(Color.WHITE);
		jSouthPanel.add(status);
		jSouthPanel.add(timeLabel);
		this.add(jSouthPanel,BorderLayout.SOUTH);
		menuBar.setBorder(BorderFactory.createLineBorder(Color.BLACK,1, true));
		this.setJMenuBar(menuBar);
		// above the north area in the border layout	
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setPreferredSize(new Dimension(screenSize.height-200, screenSize.width-200));
		this.setVisible(true);

		// if not logged in display the login menu



		if (testMode==false)
		{
			jFramePanel.removeAll();
			jFramePanel.validate();
			jFramePanel.repaint();
			LoginPanel loginPanel = new  LoginPanel();
			jFramePanel.add(loginPanel);
			jFramePanel.validate();
			jFramePanel.repaint();

		}
		else
		{
			adminMenu.setVisible(true);
			menuBar.setVisible(true);
		}


	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Set this to the Windows look and feel
		try
		{

			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				System.out.println(info.getName());
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;

				}
			}
		}
		catch (UnsupportedLookAndFeelException e) {
			// handle exception
		}
		catch (ClassNotFoundException e) {
			// handle exception
		}
		catch (InstantiationException e) {
			// handle exception
		}
		catch (IllegalAccessException e) {
			// handle exception
		}

		new MediaApplicationGUI();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		UserAdminPanel adminPanel;
		Object source = e.getSource();
		System.out.println("into the action performed");
		// if its the admin menu
		if (source == userMaint)
		{
			System.out.println("into the usermain");
			jFramePanel.removeAll();
			jFramePanel.validate();
			jFramePanel.repaint();

			adminPanel = new UserAdminPanel(this.jFramePanel, loggedIn);

			jFramePanel.add(adminPanel);// is a jPanel
			jFramePanel.validate();
			jFramePanel.repaint();

		}
		if (source == cdMaint)
		{
			System.out.println("into the usermain");
			jFramePanel.removeAll();
			jFramePanel.validate();
			jFramePanel.repaint();
			cdMaintPanel = new CDMaintPanel(this.jFramePanel, loggedIn);
			jFramePanel.add(cdMaintPanel);// is a jPanel
			jFramePanel.validate();
			jFramePanel.repaint();			
		}


	}

	/*
	 * nested class of Panel has access of the Media Application
	 */
	class LoginPanel extends JPanel implements ActionListener {

		private static final long serialVersionUID = 1L;

		private JButton loginButton=null;

		//add 
		private JLabel usernameL, pswLabel,logInDisplay;
		private JTextField usernameJTF;
		private JPasswordField pswJT;

		private JPanel cCPanel;

		private JPanel innerPanel;

		private JPanel innerPanel2; 

		public LoginPanel()
		{

			this.setLayout(new BorderLayout()); //.MediaApplicationGUI.
			try
			{
				cCPanel= new JPanel();
				
				innerPanel= new JPanel(new GridLayout(1,1));
				innerPanel2=new JPanel(new GridLayout(2,1));
				innerPanel.add(innerPanel2);
				innerPanel.add(new Component() {});//dummy
				innerPanel2.add(cCPanel);
				innerPanel2.add(new Component() {});
				cCPanel.setBorder(BorderFactory.createTitledBorder("Login"));
				cCPanel.setLayout(new BoxLayout(cCPanel, BoxLayout.Y_AXIS));
				//cCPanel.size(200,300);
				//cCPanel.setPreferredSize(new Dimension(50, 50));
			}
			catch(Exception e)
			{

			}
			//

			usernameL = new JLabel("User Name: " );
			usernameL.setFont(Styles.fontlabels);
			/*       button2.setVerticalTextPosition(AbstractButton.BOTTOM);
	        button2.setHorizontalTextPosition(AbstractButton.CENTER);

			 */
			usernameJTF = new JTextField(15);
			usernameJTF.setFont(Styles.fontlabels);
			//usernameJTF.setHorizontalTextPosition(AbstractButton.CENTER);

			pswLabel = new JLabel("Password : ");
			pswLabel.setFont(Styles.fontlabels);
			pswJT = new JPasswordField(15);
			pswJT.setFont(Styles.fontlabels);

			logInDisplay = new JLabel("Login Screen");
			logInDisplay.setFont(Styles.headingFontlabels);

			loginButton=new JButton("Login");
			loginButton.setFont(Styles.fontlabels);
			loginButton.addActionListener(this);



			//this.setBounds(20, 20, 800, 400);
			this.setSize(400, 250);
			this.add(logInDisplay, BorderLayout.NORTH);
			cCPanel.add(usernameL);
			cCPanel.add(usernameJTF);
			cCPanel.add(pswLabel);
			cCPanel.add(pswJT);
			cCPanel.add(loginButton);


			this.add(innerPanel, BorderLayout.CENTER);
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			this.setPreferredSize(new Dimension(screenSize.width, screenSize.height)  );
		}



		@Override
		public void actionPerformed(ActionEvent e) 
		{

			// TODO Auto-generated method stub
			Object source = e.getSource();
			String userName;
			char[] passWord;
			String passWordS=null;


			while (true)
			{
				if (source == loginButton)
				{
					// loggedIn

					userName = usernameJTF.getText();
					passWord = pswJT.getPassword();
					passWordS = new String(passWord);

					currentUser =uList.searchUserFromCatalog(userName);

					//retrieve user that matches the string
					try
					{
						currentUser = uList.searchUserFromCatalog(userName); 
					}
					catch(NullPointerException npe)
					{ 
						System.out.println("..................User  not found!..................");
					}
					//check if name and password match
					if ( currentUser != null && 
							userName.equals(currentUser.getUserID())   &&
							passWordS.equals(currentUser.getPassword())   
							)
					{
						loggedIn=true;
						System.out.println("....found user................." + loggedIn);

						break;
					}

				}

			}

			if (loggedIn)
			{	menuBar.setVisible(true);						
			if (currentUser.isAdmin())
			{
				adminMenu.setVisible(true);
			}
			jFramePanel.removeAll();
			jFramePanel.validate();
			jFramePanel.repaint();
			}

		}

	}

}
