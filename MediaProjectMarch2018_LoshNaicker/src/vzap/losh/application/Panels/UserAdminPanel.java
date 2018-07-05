package vzap.losh.application.Panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import vzap.losh.exceptions.UserClassException;
import vzap.losh.styles.Styles;
import vzap.losh.user.User;
import vzap.losh.user.UserCatalog;
import vzap.losh.utils.PatternMatching;
import vzap.losh.utils.StringChecks;

public class UserAdminPanel extends JPanel implements ListSelectionListener, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// JTabbed
	private JPanel userReadPanel, userAddPanel;
	private JTabbedPane tabbedPane;

	private JButton searchBtn = null, deleteBtn = null, updateBtn = null;
	private JLabel searchLabel;
	// for search
	private JTextField name, surname;

	private JScrollPane scrollPane;
	private JTable table2scroll;
	// or grid,

	// panels
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

	public UserCatalog uList;

	private ArrayList<User> allUsers;
	private Object[][] allUsersArray;
	//private String[] columnNames;
	// private UserModelClass userModel;
	// private TableModel
	//private Object data;
	//private UserModelClass userModelClass;
	//private Object sorter;
    //	private JList<String> jListNoScroll;

	private Vector<String> outVector;
	
	private JList<String> jListScroll;
	private JLabel userAddId;
	private JTextField userAddIdJText;
	private JLabel passwordAddLabel;
	private JTextField passwordAdd;
	private JLabel firstNameAddLabel;
	private JTextField firstNameAddJText;
	private JLabel surnameAddLabel;
	private JTextField surnameAddJText;
	private JLabel isAddAdminLabel;
	private JCheckBox isAddAdmin;
	private JButton addBtn;
	private JPanel largeContainerPanel;
	private JLabel largeLabel;
	private JScrollPane scrollPane1;
	private JPanel scrollContainer;
	private JPanel bufferButton;

	// add Complete user admin screen with JFrames for user add
	public UserAdminPanel(JPanel jFramePanelWayBack, User currentUSer) {
		this.setLayout(new BorderLayout(100,20));
		uList = new UserCatalog();

		allUsers = this.uList.getUserList();

		/*
		 * userModelClass =new UserModelClass();
		 * userModelClass.loadlist(allUsers); userModelClass.DEBUG=true;
		 */

		// TODO Auto-generated constructor stub
		tabbedPane = new JTabbedPane();
		userReadPanel = new JPanel(new BorderLayout());

		// north
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

		// northPanel.add(searchLabel);
		northPanel.add(name);
		northPanel.add(surname);
		northPanel.add(searchBtn);

		// center1 -scroll

		centerMainPanel = new JPanel(new GridLayout(1, 1));

		// sorter = new TableRowSorter<DefaultTableModel>(userTableModel);

		// table2scroll =new JTable(userModelClass);
		// table2scroll.setAutoCreateRowSorter(true);

    //jListNoScroll = new JList<String>(columnDummy);
		
	    outVector = this.uList.userListVector();
		jListScroll = new JList<String>(outVector);
		jListScroll.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jListScroll.setFont(Styles.listBoxes);
		jListScroll.addListSelectionListener(this);
		
		
		// table2scroll.setModel(userModel);
		scrollContainer = new  JPanel(new BorderLayout(50,50));
		scrollPane = new JScrollPane(jListScroll,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollContainer.add(scrollPane,BorderLayout.CENTER);
	//	scrollPane.setViewportView(new View());
	//?	scrollPane.setColumnHeaderView();
	//	jListNoScroll.setEnabled(false);
		//scrollPane.add(jListScroll);
		// table2scroll.paint(g);
		// System.out.println("table model out" + table2scroll.getModel());
		// System.out.println(table2scroll.getComponentListeners().toString());
		// scrollPane.add();

		// center2
		centerPanel2 = new JPanel(new GridLayout(10,2));
		centerPanel2.setFont(Styles.fontlabels);
		userId = new JLabel("User Name");
		userIdLabel = new JLabel("Loshen");

		/*
		 * passwordLabel = new JLabel("Password"); password = new JTextField();
		 */

		firstNameLabel = new JLabel("First Name");
		firstNameJText = new JTextField(15);

		surnameLabel = new JLabel("Surname");
		surnameJText = new JTextField(15);

		isAdminLabel = new JLabel("Administrator");
		isAdmin = new JCheckBox();

		centerPanel2.add(userId);
		centerPanel2.add(userIdLabel);
		userIdLabel.setFont(Styles.fontlabelsValue);
		userIdLabel.setForeground(Color.BLUE);
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
		updateBtn.setEnabled(false);
		//bufferButton.removeAll();
		JPanel twoButtons = new JPanel(new FlowLayout());
		twoButtons.add(Styles.bufferButton(deleteBtn));
		twoButtons.add(Styles.bufferButton(updateBtn));
		centerPanel2.add(twoButtons);
		//centerPanel2.add(Styles.bufferButton(updateBtn));

		centerMainPanel.add(scrollContainer);
		centerMainPanel.add(centerPanel2);

		// whole
		userReadPanel.setLayout(new BorderLayout(100,100));
		userReadPanel.add(northPanel, BorderLayout.NORTH);
		userReadPanel.add(centerMainPanel,BorderLayout.CENTER);

		// group layout

		tabbedPane.add("User Search", userReadPanel);

		// userUpdatePanel = new JPanel();
		// userDeletePanel = new JPanel();

		// each panel seperately, search and delete user is 1st

		/*
		 * search using pattern of users first name or last name regex -
		 * comments on the side panel if it's found then we will populate a user
		 * list grid with matched users We will also layout all the selected
		 * user fields
		 */

		//
		userAddPanel = new JPanel(new GridLayout(10, 2));
		tabbedPane.add("Add User", userAddPanel);

		userAddId = new JLabel("User Name");
		userAddIdJText = new JTextField(15);

		passwordAddLabel = new JLabel("Password");
		passwordAdd = new JPasswordField(15);

		firstNameAddLabel = new JLabel("First Name");
		firstNameAddJText = new JTextField(15);

		surnameAddLabel = new JLabel("Surname");
		surnameAddJText = new JTextField(15);

		isAddAdminLabel = new JLabel("Administrator");
		isAddAdmin = new JCheckBox();
		// (firstName == null || (firstName.isEmpty())

		Styles.setButtonSize(addBtn = new JButton("add"));
		//SaddBtn.setPreferredSize(new Dimension(100, 60));
		addBtn.addActionListener(this);
		
		userAddPanel.add(userAddId);
		userAddPanel.add(userAddIdJText);
		userAddPanel.add(passwordAddLabel);
		userAddPanel.add(passwordAdd);
		userAddPanel.add(firstNameAddLabel);
		userAddPanel.add(firstNameAddJText);

		userAddPanel.add(surnameAddLabel);
		userAddPanel.add(surnameAddJText);
		userAddPanel.add(isAddAdminLabel);
		userAddPanel.add(isAddAdmin);

	
		userAddPanel.add(Styles.bufferButton(addBtn));

		// main Panel

		largeContainerPanel = new JPanel(new BorderLayout());

		this.add(largeLabel =new JLabel("User Mainetenance Screen"),BorderLayout.NORTH);
		largeLabel.setFont(Styles.fontlabels);
		
		//largeContainerPanel.add(largeLabel);
		largeContainerPanel.add(tabbedPane) ;
		this.add(largeContainerPanel, BorderLayout.CENTER);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// TODO Auto-generated method stub
		Object source = e.getSource();

		Object[][] listOfUsers = new Object[allUsers.size()][4];
		String nameToFind = null, surnameToFind = null;
		int OptionSelected = JOptionPane.NO_OPTION;

		if (source == addBtn) {

			try {
				/*
				 * String userId, String password, String firstName, String
				 * surname, boolean isAdmin) throws UserClassException{ super();
				 */
				User userIn = new User(this.userAddIdJText.getText(), this.passwordAdd.getText(),
						this.firstNameAddJText.getText(), this.surnameAddJText.getText(), this.isAddAdmin.isSelected());

				this.uList.addUserToCatalog(userIn);

				if ((firstNameAddJText.getText().trim().isEmpty())
						|| (StringChecks.isNonAlpha(firstNameAddJText.getText()))) {
					throw new UserClassException("");
				}

				if ((surnameAddJText.getText().trim().isEmpty())
						|| (StringChecks.isNonAlpha(surnameAddJText.getText()))) {
					throw new UserClassException("");
				}

				JOptionPane.showMessageDialog(this, "User is added");

				jListScroll.removeListSelectionListener(this);
				outVector = this.uList.userListVector();
				jListScroll.setListData(outVector);
				jListScroll.addListSelectionListener(this);

				jListScroll.setSelectedIndex(outVector.size() - 1);
				jListScroll.ensureIndexIsVisible(outVector.size() - 1);
				this.userAddIdJText.setText("");
				this.passwordAdd.setText("");
				this.firstNameAddJText.setText("");
				this.surnameAddJText.setText("");
				this.isAddAdmin.setSelected(false);

				userReadPanel.setFocusable(true);

			}

			catch (UserClassException uce) {

				JOptionPane.showMessageDialog(this,
						"there is a problem with the add make sure that you use only numerics for names");

			}

		}

		if (source == searchBtn) {
			boolean found = true;
			// use the full vector and place it into a vector called partial
			// Vector

			nameToFind = name.getText().trim().toUpperCase();
			surnameToFind = surname.getText().trim().toUpperCase();

			if (((nameToFind.isEmpty()) && (surnameToFind.isEmpty()))) {
				outVector = this.uList.userListVector();
			} else {

				outVector.removeAllElements();
				Formatter f= new Formatter();
			    f.format("%15S   %30S %30S  %5S",  " User ID" ,
							 " First Name" , 
							 " Surname"  ,
							 " Admin");
			    outVector.addElement(f.toString());
			    f.close();
				for (User user : allUsers) {
					// System.out.println("mathchin" + nameToFind + " " +
					// user.getFirstName() );
					// System.out.println("mathchin2" + surnameToFind + " " +
					// user.getSurName() );
					// list of objects
	
					if ((PatternMatching.findMatch(nameToFind, user.getFirstName().toUpperCase()))
							&& (PatternMatching.findMatch(surnameToFind, user.getSurName().toUpperCase()))) {
						outVector.addElement(this.uList.convertUsertoString(user));
						
					 if (outVector.isEmpty()) found = false;
					}
				}

			}

			jListScroll.removeListSelectionListener(this);
			jListScroll.setListData(outVector);
			jListScroll.addListSelectionListener(this);
			centerMainPanel.repaint();
			userIdLabel.setText("");
			firstNameJText.setText("");
			surnameJText.setText("");
			isAdmin.setSelected(false);
			updateBtn.setEnabled(false);

			if (!found)
				JOptionPane.showMessageDialog(this, "Nothing matching the search criteria");

			this.repaint();
		}

		/*
		 * deleteBtn=null, updateBtn=null
		 */

		if (source == deleteBtn) {

			String usedIDSearch = userIdLabel.getText();

			User uFound = this.uList.searchUserFromCatalog(usedIDSearch);

			char deleteYN = 'n';

			if (uFound != null) {
				OptionSelected = JOptionPane.showConfirmDialog(this,
						"Do you want to delete this user : " + userIdLabel.getText(), "Confirmation required:",
						JOptionPane.YES_NO_OPTION);

				if (OptionSelected == JOptionPane.YES_OPTION) {
					int selection = jListScroll.getSelectedIndex();
					this.uList.deleteUserFromCatalog(uFound);
					jListScroll.removeListSelectionListener(this);
					outVector.removeElementAt(selection);
					jListScroll.setListData(outVector);
					jListScroll.addListSelectionListener(this);
					centerMainPanel.repaint();
					userIdLabel.setText("");
					firstNameJText.setText("");
					surnameJText.setText("");
					isAdmin.setSelected(false);
					JOptionPane.showMessageDialog(this, "Deletion Complete");
					updateBtn.setEnabled(false);
				}
			}

		}

		if (source == updateBtn) {
			// data from keys must be used to update the selected values
			// make sure the selected row is the correct one as the user could
			// have move the cursor

			int valueBack = -1;
			User uFound = this.uList.searchUserFromCatalog(userIdLabel.getText());
			System.out.println("This user iupdate pass");
			if (uFound != null) {
				if ((valueBack = uList.searchUserFromCatalogForIndex(userIdLabel.getText())) >= 0) {
					// user name already set
					this.allUsers.get(valueBack).setAdmin(isAdmin.isSelected());
					this.allUsers.get(valueBack).setFirstName(this.firstNameJText.getText());
					this.allUsers.get(valueBack).setSurName(this.surnameJText.getText());
					this.uList.saveTheUserList(allUsers);
					userIdLabel.setText("");
					firstNameJText.setText("");
					surnameJText.setText("");
					isAdmin.setSelected(false);
					JOptionPane.showMessageDialog(this, "Update complete for : " + uFound.getUserID());
					updateBtn.setEnabled(false);
					jListScroll.removeListSelectionListener(this);
					outVector = this.uList.userListVector();
					jListScroll.setListData(outVector);
					jListScroll.addListSelectionListener(this);

					jListScroll.setSelectedIndex(uList.searchUserFromCatalogForIndex(uFound.getUserID()));

				} else {

					JOptionPane.showMessageDialog(this, "User record not found for update in memory");

				}
			} else {
				JOptionPane.showMessageDialog(this, "User record not found for update");

			}

			name.setText("");
			surname.setText("");

		}

	}

	private void loadAllValues() {
		// TODO Auto-generated method stub

		allUsersArray = new Object[allUsers.size()][4];
		int count = 0;
		for (User user : allUsers) {
			// list of objects
			// System.out.println(user);
			allUsersArray[count][0] = user.getUserID();
			allUsersArray[count][1] = user.getFirstName();
			// System.out.println(user.getFirstName());
			allUsersArray[count][2] = user.getSurName();
			allUsersArray[count][3] = Boolean.valueOf(user.isAdmin());
			count++;
		}

	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub

		StringTokenizer sTT;

		if (e.getValueIsAdjusting() == true) {
			updateBtn.setEnabled(true);
			
			if (jListScroll.getSelectedIndex() == 0) 
			{
				userIdLabel.setText("");
				firstNameJText.setText("");
				surnameJText.setText("");
				isAdmin.setSelected(false);
				return;
			}
			String selected = (String) jListScroll.getSelectedValue();

			sTT = new StringTokenizer(selected, ",", false);
			int count = 0;
			while (sTT.hasMoreTokens()) {
				System.out.println();

				switch (count) {
				case 0:
					userIdLabel.setText((sTT.nextToken()).trim());
					
				case 1:
					firstNameJText.setText((sTT.nextToken().trim()));
				case 2:
					surnameJText.setText((sTT.nextToken()).trim());
					//System.out.println("out");
				case 3:
					boolean value = Boolean.parseBoolean(sTT.nextToken().trim());
					//System.out.println("Output: " +value);
					isAdmin.setSelected(value);
					
				}

			}

		}

	}

}
