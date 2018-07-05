package vzap.losh.application.Panels;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import vzap.losh.catalogue.Catalogue;
import vzap.losh.enumerations.Genre;
import vzap.losh.enumerations.Media_Type;
import vzap.losh.styles.Styles;
import vzap.losh.user.User;
import vzap.losh.utils.PatternMatching;
import vzap.losh.media.*;

public class DVDMaintPanelEditPane extends JPanel implements ListSelectionListener , ActionListener ,FocusListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public JButton searchBtn = null;
	private JLabel searchLabel;

	// or grid,

	// panels
	private JPanel topRightBorder;
	private JPanel topRightcenterMainPanel; 

	// values on the edit and update panel


	Genre[] genreArray = Genre.values();

	private Vector<String> outVectorDVDMedia;

	private JTextField DVDSearchNameJtext;
	private JTextField DVDSearchArtistJText;


	private JList<String> jListScroll;
	private JScrollPane scrollPane;



	private JLabel editDVDNameJLabel;
	private JLabel editDVDAlbumArtistLabel;

	private JLabel  editDVDAlbumCopiesLabel;
	private JTextField editDVDCopiesJText;

	private JTextField editDVDAlbumArtistJText;
	private JTextField editDVDNameJText;
	private JLabel editDVDGenreLabel;
	private JComboBox<String> editDVDGenreCombo;

	private JLabel editDVDLabelDuration;
	private JTextField editDVDDrurationJText;

	

	private Catalogue mediaCatlog;
	private ArrayList<Media> mediaList;

	private JButton editJButtonDVDUpdate;
	private JButton editJButtonDelete;

	public int durationGlobal;


	private int OptionSelected;

	private DVD CurrentDVD;

	private String DVDNameToFind;

	private String DVDArtistToFind;


	private JPanel northPanel;

	private JPanel centerMainPanel;

	private Container centerPanel2;

	private Container topRight1;

	private JPanel topRight2;

	private JPanel topRightInsert;


	private JPanel bottomLeftInsert;


	private JPanel bottomLeftBorder;



	//Panels



	// The media Catalog needed 
	public DVDMaintPanelEditPane (JPanel borrowDVDMaintPanel, User currentUser) {
		{	
			//	this.setLayout(new GridLayout(2,2));

			mediaCatlog = new Catalogue();
			mediaList = mediaCatlog.getMediaList();

			// the read Panel is the this panel 
			//DVDReadPanel = new JPanel(new BorderLayout());

			// north
			this.addFocusListener(this);
			northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			searchLabel = new JLabel("Search ");
			searchLabel.setFont(Styles.fontlabels);

			DVDSearchNameJtext = new JTextField(15);
			DVDSearchNameJtext.setFont(Styles.fontlabels);
			DVDSearchNameJtext.setToolTipText("DVD Name");

			DVDSearchArtistJText= new JTextField(15);
			DVDSearchArtistJText.setFont(Styles.fontlabels);
			DVDSearchArtistJText.setToolTipText("Album Artist");

			searchBtn = new JButton("Search");
			searchBtn.setMnemonic('s');
			searchBtn.setFont(Styles.fontlabels);
			searchBtn.addActionListener(this);

			northPanel.add(DVDSearchNameJtext);
			northPanel.add(DVDSearchArtistJText);
			northPanel.add(searchBtn);

			centerMainPanel = new JPanel(new GridLayout(2, 2,0,10));
			outVectorDVDMedia = this.extractDVDVector();
			outVectorDVDMedia.addElement("");

			JPanel jPad = new JPanel(new FlowLayout()); 
			jListScroll = new JList<String>(outVectorDVDMedia);
			jListScroll.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jListScroll.setFont(Styles.listBoxes);
			jListScroll.addListSelectionListener(this);

			jListScroll.setVisibleRowCount(20);
			scrollPane = new JScrollPane(jListScroll,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

			//scrollPane.setLayout(new ScrollPaneLayout());
			scrollPane.setPreferredSize(new Dimension(400,200));

			jPad.add(scrollPane);
			centerMainPanel.add(jPad);

			topRightBorder = new JPanel(new BorderLayout());

			topRight1 = new JPanel(new FlowLayout(FlowLayout.LEFT));

			topRight2 = new JPanel(new GridLayout(3, 2, 0, 20 ));
			topRightInsert= new JPanel(new FlowLayout(FlowLayout.CENTER));


			topRightcenterMainPanel= new JPanel(new GridLayout(2,1));
			topRightcenterMainPanel.add(topRight1);
			topRightcenterMainPanel.add(topRight2);
			topRightBorder.add(topRightInsert, BorderLayout.SOUTH);
			topRightBorder.add(topRightcenterMainPanel, BorderLayout.CENTER);
			centerMainPanel.add(topRightBorder);
			// work on this second

			bottomLeftBorder = new JPanel();

			centerPanel2 = new JPanel(new GridLayout(6,1));

			//centerPanel2.setFont(Styles.fontlabels);
			editDVDNameJLabel = new JLabel("DVD Name");
			editDVDNameJText = new JTextField(15);
			editDVDAlbumArtistLabel = new JLabel("DVD Artist");
			editDVDAlbumArtistJText = new JTextField(15);
			editDVDGenreLabel = new JLabel("DVD Genre");
			editDVDGenreCombo  = new JComboBox<String>();
			editDVDAlbumCopiesLabel = new JLabel("Copies Owned");
			editDVDCopiesJText = new JTextField(15);
			editDVDLabelDuration= new JLabel("Duration");

			editDVDDrurationJText = new JTextField();
			loadComboValues(editDVDGenreCombo);

			//centerPanel2.setFont(new Font("arial", Font.BOLD , 6));
			centerPanel2.add(editDVDNameJLabel);
			centerPanel2.add(editDVDNameJText);
			centerPanel2.add(editDVDAlbumArtistLabel);
			centerPanel2.add(editDVDAlbumArtistJText);
			centerPanel2.add(editDVDLabelDuration);
			centerPanel2.add(editDVDDrurationJText);
			centerPanel2.add(editDVDGenreLabel);
			centerPanel2.add(editDVDGenreCombo);
			centerPanel2.add(editDVDAlbumCopiesLabel);
			centerPanel2.add(editDVDCopiesJText);

			editJButtonDVDUpdate = new JButton("Update ");
			editJButtonDVDUpdate.addActionListener(this);

			editJButtonDelete = new JButton("Delete");
			editJButtonDelete.addActionListener(this);


			bottomLeftInsert =new JPanel(new FlowLayout(FlowLayout.LEADING));


			centerPanel2.add(Styles.bufferButton(editJButtonDVDUpdate));
			centerPanel2.add(Styles.bufferButton(editJButtonDelete));

			bottomLeftInsert.add(centerPanel2);

			centerMainPanel.add(bottomLeftInsert);

			this.add(northPanel, BorderLayout.NORTH);
			this.add(centerMainPanel, BorderLayout.CENTER);
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			this.setPreferredSize(new Dimension(700, 700));	
			
			if (!currentUser.isAdmin())
			{
				centerPanel2.setVisible(false);
			}



		}


	}


	private void  loadComboValues(JComboBox<String> jC) {
		// TODO Auto-generated method stub

		for (Genre g: Genre.values())
		{
			if (g.genreMediaType() == Media_Type.DVD )
			{
				jC.addItem(g.toString());
			}
		}


	}


	private Vector<String> extractDVDVector() {
		// TODO Auto-generated method stub

		Vector<String> extract = new Vector<>();
		mediaCatlog = new Catalogue();
		mediaList = mediaCatlog.getMediaList();
//		Formatter f= new Formatter();

//		f.format("%15S %15S %15S %15S %5S",
//				"DVD Name,", 
//				"artist," ,
//				"duration,",
//				"Genre,",
//				"Copies");

		extract.addElement("DVD Name, artist, duration, Genre, Copies");
//		f.close();


		for (int pos = 0 ; pos < this.mediaList.size(); pos++)
		{

			Media_Type type = mediaList.get(pos).getMedia_Type();

			DVD DVDIN;

			switch (type) 
			{
			case DVD: DVDIN= (DVD) mediaList.get(pos);
			extract.addElement(
					DVDIN.getMediaName() + ", " +
							DVDIN.getDirector() + " , "  +
							DVDIN.getDuration() + " , "  +
							DVDIN.getGenre().toString() + "," +
							DVDIN.getCopies() 
					);

			//System.out.println(DVDIn); 
			break;
			default: System.out.println("Heuston I have a problem");
			}

		}
		return extract;
	}

	private Vector<String> extractDVDVectorForPattern(String DVDNameToFind , String DVDDirectorToFind) {
		// TODO Auto-generated method stub

		Vector<String> extract = new Vector<>();

//		Formatter f= new Formatter();

//		f.format("%15S %30S %30S %30S %5S",  " User ID" ,
//				"DVD Name,", 
//				"artist," ,
//				"duration,",
//				"Genre,",
//				"Copies");

//		extract.addElement(f.toString());
//		f.close();

		extract.addElement("DVD Name, artist, duration, Genre, Copies");

		for (int pos = 0 ; pos < this.mediaList.size(); pos++)
		{

			Media_Type type = mediaList.get(pos).getMedia_Type();

			DVD DVDIN;

			switch (type) 
			{

			case DVD: 

				DVDIN= (DVD) mediaList.get(pos);

				if ((PatternMatching.findMatch(DVDNameToFind.toUpperCase(), DVDIN.getMediaName().toUpperCase()))
						&& (PatternMatching.findMatch(DVDDirectorToFind.toUpperCase(), DVDIN.getDirector().toUpperCase()))) 
				{

					extract.addElement(	
							DVDIN.getMediaName() + ", " +
									DVDIN.getDirector() + " , "  +
									DVDIN.getDuration() + " , "  +
									DVDIN.getGenre().toString() + "," +
									DVDIN.getCopies() 
							);
				}
				break;
			default: System.out.println("Heuston I have a problem");
			}

		}
		return extract;
	}


	@Override
	public void actionPerformed(ActionEvent e) {

		// TODO Auto-generated method stub
		Object source = e.getSource();
		System.out.println("open ?");
		Object[][] listofDVDs = new Object[mediaList.size()][6];
		DVD DVDInMedia; 
		int duration;
		String DVDTrackText;
		StringTokenizer sTR;
		ArrayList<Track> tracks;
		Genre g;
		/*if I add this 
		 */



		if (source == editJButtonDVDUpdate)
		{
			tracks=null;

			Media inMedia=null; 
			try
			{
				//inMedia= new DVD(DVDTitle, Media_Type.DVD, 0, null,null,0);
				inMedia = this.mediaCatlog.findMediaByTitle(editDVDNameJText.getText());
				this.mediaCatlog.deleteFromMediaList(inMedia);
				durationGlobal = 0;
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(this, "delete part of Update failed");
			}

			try {


				DVDInMedia= new DVD(editDVDNameJText.getText(),
						Media_Type.DVD, 
						Integer.parseInt(editDVDDrurationJText.getText().trim()),
						Genre.valueOf((String)editDVDGenreCombo.getSelectedItem()),
						editDVDAlbumArtistJText.getText(),
						Integer.parseInt(editDVDCopiesJText.getText()));

				boolean success = this.mediaCatlog.addToMediaList(DVDInMedia);

				JOptionPane.showMessageDialog(this, "Update Operation complete" );

				outVectorDVDMedia = this.extractDVDVector();
				outVectorDVDMedia.addElement(" ");
				jListScroll.removeListSelectionListener(this);
				jListScroll.setListData(outVectorDVDMedia);
				jListScroll.addListSelectionListener(this);
				editDVDNameJText.setText("");
				editDVDAlbumArtistJText.setText("");
				editDVDCopiesJText.setText("");
		
				centerMainPanel.repaint();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(this,"Error with the data input");
			} 


		}	


		//  if you delete the DVD details
		// deleted selected items and all tracks on confirmation	
		if (source == editJButtonDelete)
		{
			OptionSelected = JOptionPane.showConfirmDialog(this,
					"Do you want to delete this user : " + editDVDNameJText.getText(), "Confirmation required:",
					JOptionPane.YES_NO_OPTION);

			if (OptionSelected == JOptionPane.NO_OPTION) 
			{	return;
			}
			Media inMedia=null; 
			try
			{
				//inMedia= new DVD(DVDTitle, Media_Type.DVD, 0, null,null,0);
				inMedia = this.mediaCatlog.findMediaByTitle(editDVDNameJText.getText());

				if (inMedia.getCopies() >1)
				{
					JOptionPane.showMessageDialog
					(this, "---delete cancelled more than one copy exists of--- " + inMedia);
					System.out.println("---delete cancelled more than one copy exists of--- " + inMedia);
					return;
				}
			}
			catch (Exception ex)
			{
				JOptionPane.showMessageDialog
				(this, "Deletion Error" + inMedia);
				System.out.println("Deletion error");
				ex.printStackTrace();
				return;
			}
			if  (this.mediaCatlog.deleteFromMediaList(inMedia))
			{ 
				JOptionPane.showMessageDialog
				(this, "Deletion Complete" + inMedia);

				outVectorDVDMedia = this.extractDVDVector();
				outVectorDVDMedia.addElement(" ");
				jListScroll.removeListSelectionListener(this);
				jListScroll.setListData(outVectorDVDMedia);
				centerMainPanel.repaint();
				jListScroll.addListSelectionListener(this);
				
				editDVDNameJText.setText("");
				editDVDAlbumArtistJText.setText("");
				editDVDCopiesJText.setText("");
				

				System.out.println("..................successful delete..................");

			}
			else
			{
				JOptionPane.showMessageDialog
				(this, "Deletion Process failed" + inMedia);

				System.out.println(".........delete cancelled - no record found..........");
			}
		}


		//search button is pressed
		if (source == searchBtn)
		{
			boolean found = true;
			DVDNameToFind = DVDSearchNameJtext.getText();
			DVDArtistToFind = DVDSearchArtistJText.getText();


			if (((DVDNameToFind.isEmpty()) && (DVDArtistToFind.isEmpty()))) 
			{
				outVectorDVDMedia = this.extractDVDVector();
			} 
			else 
			{
				outVectorDVDMedia = this.extractDVDVectorForPattern(DVDNameToFind , DVDArtistToFind);	
				if (outVectorDVDMedia.isEmpty())
				{
					found=false;
				}
			}

			jListScroll.removeListSelectionListener(this);
			jListScroll.setListData(outVectorDVDMedia);
			jListScroll.addListSelectionListener(this);


			editDVDNameJText.setText("");
			editDVDAlbumArtistJText.setText("");
			editDVDCopiesJText.setText("");
			

			//centerMainPanel.repaint();
			if (!found)
				JOptionPane.showMessageDialog(this, "Nothing matching the search criteria");
	
			this.repaint();


		}


	} //end action performed

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub

		Object source = e.getSource();
		StringTokenizer sTT;
		String selected;
		String findSelectedItemTracks;

//		System.out.println(" value changed source : " + source);

		// select from the read
		if (source == jListScroll)
		{
			if ((e.getValueIsAdjusting() == true) ) 
			{
				selected = (String) jListScroll.getSelectedValue();
				//unstring and populate the values
				sTT = new StringTokenizer(selected, ",", false);
				System.out.println("selected : "+ selected);
				int count = 0;
				String firstColumn="";
				while (sTT.hasMoreTokens()) {
					firstColumn=sTT.nextToken().trim();
					if (firstColumn.equalsIgnoreCase("DVD Name")) return;
					editDVDNameJText.setText(firstColumn);
					editDVDAlbumArtistJText.setText((sTT.nextToken().trim()));
					editDVDDrurationJText.setText((sTT.nextToken()).trim());
					editDVDGenreCombo.setSelectedItem((sTT.nextToken()).trim());
					editDVDCopiesJText.setText((sTT.nextToken()).trim());
				}
				System.out.println("genre : " + Genre.valueOf((String) editDVDGenreCombo.getSelectedItem() ));

				// empty out and fill the track list 
				CurrentDVD = (DVD) mediaCatlog.findMediaByTitleAndGenre(editDVDNameJText.getText(), 
						Genre.valueOf( (String) editDVDGenreCombo.getSelectedItem())) ; 



			}
		}



	}


	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		System.out.println("focus gained");
		searchBtn.doClick();
	}


	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

}

