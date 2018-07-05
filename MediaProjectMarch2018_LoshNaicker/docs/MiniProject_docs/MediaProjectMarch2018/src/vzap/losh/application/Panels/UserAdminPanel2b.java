package vzap.losh.application.Panels;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.regex.Matcher;

import javax.accessibility.Accessible;
import javax.accessibility.AccessibleContext;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Scrollable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import javax.swing.plaf.metal.MetalBorders.OptionDialogBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import vzap.losh.user.*;
import vzap.losh.styles.*;
import vzap.losh.utils.PatternMatching;

public class UserAdminPanel2b extends JPanel implements ActionListener  {

	// JTabbed
	private JPanel userReadPanel, userAddPanel;
	private JTabbedPane tabbedPane;

	private JButton searchBtn=null, deleteBtn=null, updateBtn=null, exitBtn;
	private JLabel searchLabel ;
	//for search
	private JTextField name, surname;

	private JScrollPane scrollPane;
	private JTable      table2scroll;
	// or grid,

	//panels
	private JPanel southPanel;
	private JPanel centerMainPanel;
	private JPanel centerPanel2;
	private JPanel northPanel;
	
	private JTextField logInDisplay;
	
	// values on the edit and update panel
	private JLabel userId;
	private JTextField firstNameJText;
	private JTextField surnameJText;
	private JCheckBox isAdmin;

	private JLabel firstNameLabel;
	private JLabel surnameLabel;
	private JLabel isAdminLabel;

	private JLabel userIdLabel;
	
	
	public  UserCatalog uList; 

	private ArrayList<User> allUsers;
	private Object[][] allUsersArray;
	private ComTableModelListener communicateWithTable;
	private String[] columnNames;
//	private UserModelClass userModel;
	//private TableModel
	private Object data;
	private UserModelClass userModelClass;
	private Object sorter;
	

	//add Complete user admin screen with JFrames for user add 
	public UserAdminPanel2b(JPanel jFramePanelWayBack, boolean logddDIn) 
	{
		uList= new UserCatalog();
		
		allUsers= this.uList.getUserList();
	    
		userModelClass =new UserModelClass();
		userModelClass.loadlist(allUsers);
		userModelClass.DEBUG=true;
		// TODO Auto-generated constructor stub
		tabbedPane = new JTabbedPane(); 


		userReadPanel = new JPanel(new BorderLayout());


		//north
		northPanel = new JPanel();
		searchLabel = new JLabel("Search");
		searchLabel.setFont(Styles.fontlabels);
		name = new JTextField(15);
		name.setFont(Styles.fontlabels);
		surname = new JTextField(15);
		surname.setFont(Styles.fontlabels);
		
		searchBtn = new JButton("Search");
		searchBtn.setMnemonic('s');
		searchBtn.setFont(Styles.fontlabels);
		searchBtn.addActionListener(this);

		northPanel.add(searchLabel);
		northPanel.add(name);
		northPanel.add(surname);
		northPanel.add(searchBtn);


		//center1 -scroll

		centerMainPanel = new JPanel(new GridLayout(1,1));
	
		//sorter = new TableRowSorter<DefaultTableModel>(userTableModel);
	
		
		table2scroll =new JTable(userModelClass);
		//table2scroll.setAutoCreateRowSorter(true);

		//table2scroll.setModel(userModel);
		scrollPane = new JScrollPane(table2scroll, 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		communicateWithTable = new ComTableModelListener();
		
		table2scroll.getSelectionModel().addListSelectionListener(communicateWithTable);
		table2scroll.getModel().addTableModelListener(communicateWithTable);
		table2scroll.getColumnModel().addColumnModelListener(communicateWithTable);
		
		
		//table2scroll.paint(g);
		//System.out.println("table model out"  +  table2scroll.getModel());
		//System.out.println(table2scroll.getComponentListeners().toString());
		//scrollPane.add();



		//center2 
		centerPanel2 = new JPanel();
		userId = new JLabel("User Name");
		userIdLabel = new JLabel("Loshen");

		/*
		passwordLabel = new JLabel("Password");
		password =  new JTextField();
		 */

		firstNameLabel= new JLabel("First Name");
		firstNameJText = new JTextField(15);


		surnameLabel = new JLabel("Surname");
		surnameJText = new JTextField(15);

		isAdminLabel = new JLabel("Administrator");
		isAdmin =new JCheckBox();

		centerPanel2.add(userId);
		centerPanel2.add(userIdLabel);
		centerPanel2.add(firstNameLabel);
		centerPanel2.add(firstNameJText);
		centerPanel2.add(surnameLabel);
		centerPanel2.add(surnameJText);
		centerPanel2.add(isAdminLabel);
		centerPanel2.add(isAdmin);

		deleteBtn = new JButton("Delete");
		deleteBtn.addActionListener(this);

		updateBtn = new JButton("Update");
		updateBtn.addActionListener(this);

		centerPanel2.add(deleteBtn);
		centerPanel2.add(updateBtn);


		centerMainPanel.add(scrollPane);
		centerMainPanel.add(centerPanel2);


		//whole
		userReadPanel.setLayout(new BorderLayout());
		userReadPanel.add(northPanel, BorderLayout.NORTH);
		userReadPanel.add(centerMainPanel);
		userAddPanel = new  JPanel();

		//group layout 

		tabbedPane.add("User Search" ,  userReadPanel );

		//userUpdatePanel = new JPanel();
		//userDeletePanel = new JPanel();

		// each panel seperately, search and delete user is 1st

		/*
		 * search using pattern of users first name or last name regex  - comments on the side panel 
		 * if it's found then we will 
		 * populate a user list grid with matched users 
		 * We will also layout all the selected user fields 
		 */

		// 
		tabbedPane.add("Add User" ,  userAddPanel );

		// (firstName == null || (firstName.isEmpty())



		// main Panel 

		this.add(tabbedPane);



	}

	

	@Override
	public void actionPerformed(ActionEvent e) {
	
		// TODO Auto-generated method stub
		Object source = e.getSource();

		Object [] [] listOfUsers = new Object [allUsers.size()] [4];
		String nameToFind=null, surnameToFind=null;
		int OptionSelected=JOptionPane.NO_OPTION;
		//allUsersArray= 

		if (source == searchBtn)
		{
			int count=0;
			// repaint Jlist 
			
			table2scroll.getSelectionModel().removeListSelectionListener(communicateWithTable);
	
			Component[] c =  table2scroll.getComponents();
			for (Component o :c)
			{
				System.out.println(" component: " + o.toString() );
			}
			table2scroll.removeAll();
			
			for (Component o :c)
			{
				System.out.println("after  component: " + o.toString() );
			}
			
			nameToFind = name.getText().trim().toUpperCase();
			surnameToFind = surname.getText().trim().toUpperCase();

			if (( ( nameToFind.isEmpty() )  && ( surnameToFind.isEmpty() ) ))	
			{
				this.loadAllValues();
			}
			else
			{
				for (User user: allUsers)
				{
					System.out.println("mathchin" +   nameToFind + " " + user.getFirstName() );
					System.out.println("mathchin2" +   surnameToFind + " " + user.getSurName() );
					//list of objects
					if ( (PatternMatching.findMatch(nameToFind,user.getFirstName().toUpperCase())) && 
						 (PatternMatching.findMatch(surnameToFind,user.getSurName().toUpperCase()))
			           )	
					{
						System.out.println("mathch found ");
						allUsersArray[count][0]=user.getUserID();
						allUsersArray[count][1]=user.getFirstName();
						allUsersArray[count][2]=user.getSurName();
						allUsersArray[count][3]=Boolean.valueOf(user.isAdmin());
						count++;
					}
				}

			}
			
			
			for (int i = 0; i < allUsersArray.length; i++) {
				for (int j = 0; j < allUsersArray[i].length; j++) {
					System.out.println(allUsersArray[i][j]);
				}
			}
			
			//table2scroll;
			//table2scroll.revalidate();
			table2scroll.getSelectionModel().addListSelectionListener(communicateWithTable);
			
			//scrollPane.revalidate();
			//centerMainPanel.revalidate();
			this.repaint();


		}
		
		/*
		 * deleteBtn=null, 
		 * updateBtn=null
		 */
		
		if (source == deleteBtn)
		{
			
			String usedIDSearch = (String) table2scroll.getModel().getValueAt(table2scroll.getSelectedRow(),0);
			User uFound = this.uList.searchUserFromCatalog(usedIDSearch);
			char deleteYN='n';
			if ( uFound != null)
			{	

				OptionSelected = JOptionPane.showConfirmDialog(this, "Do you want to delete this user : " + userIdLabel.getText() , 
						"Confirmation required:",JOptionPane.YES_NO_OPTION);
				
				
				if (OptionSelected== JOptionPane.YES_OPTION)
				{
					this.uList.deleteUserFromCatalog(uFound);
					
					JOptionPane.showMessageDialog(this, "Deletion Complete");
					
					//userTableModel.removeRow(table2scroll.getSelectedRow());
					//scrollPane.repaint();
				}
			}
			else 
			{
				System.out.println("This user is not found");
			}
			
			
			
		}
	




	}

	private void loadAllValues() {
		// TODO  Auto-generated method stub

		allUsersArray = new Object [allUsers.size()][4];
		int count=0;
		for (User user: allUsers)
		{
			//list of objects
			//System.out.println(user);
			allUsersArray[count][0]=user.getUserID();
			allUsersArray[count][1]=user.getFirstName();
			//System.out.println(user.getFirstName());
			allUsersArray[count][2]=user.getSurName();
			allUsersArray[count][3]=Boolean.valueOf(user.isAdmin());
			count++;
		}

	
	} 

	class ComTableModelListener implements ListSelectionListener , TableColumnModelListener  ,TableModelListener 
	{



		@Override
		public void valueChanged(ListSelectionEvent e) {
			// TODO Auto-generated method stub
			// System.out.println("event :  "+ e);
			
			
			//retrieve the values for the edit and delete panel
			//String rUserId = userId;
			//String 
			
			if (e.getValueIsAdjusting() == true)
			{

				
				
				userIdLabel.setText((String) table2scroll.getModel().getValueAt(table2scroll.getSelectedRow(), 0));
				firstNameJText.setText((String)table2scroll.getModel().getValueAt(table2scroll.getSelectedRow(), 1)); 		
				surnameJText.setText((String)table2scroll.getModel().getValueAt(table2scroll.getSelectedRow(), 2));
				isAdmin.setSelected(new Boolean(table2scroll.getModel().getValueAt(table2scroll.getSelectedRow(), 3).toString()));
				
				/*
	private JLabel userId;
	private JTextField firstNameJText;
	private JTextField surnameJText;
	private JCheckBox isAdmin;
				 * 
				 */
				
				//System.out.println(selected);
				
				System.out.println("table sort1");
				
				return;
			}
			
			

		}

		@Override
		public void tableChanged(TableModelEvent e) {
			// TODO Auto-generated method stub
			System.out.println("table sort2");
			userModelClass.fireTableChanged(e);

			
		}

		@Override
		public void columnAdded(TableColumnModelEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void columnRemoved(TableColumnModelEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void columnMoved(TableColumnModelEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void columnMarginChanged(ChangeEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void columnSelectionChanged(ListSelectionEvent e) {
			// TODO Auto-generated method stub
			System.out.println("table sort3");

		}




	}

//	@Override
//	public void tableChanged(TableModelEvent e) {
//		// TODO Auto-generated method stub
//		
//		table2scroll.updateUI();
//		
//		
//		
//		
//	}

	/**
	 * Implement the 
	 * 
	 * 
	 * 
	 */





}
