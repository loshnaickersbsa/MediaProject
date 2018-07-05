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

public class CDMaintPanelCDEditPane extends JPanel implements ListSelectionListener , ActionListener ,FocusListener
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

	//number of tracks in a drop down


	private Vector<String> editOutputTrackVector;
	private Vector<String> outVectorCDMedia;

	private JTextField cdSearchNameJtext;
	private JTextField cdSearchArtistJText;


	private JList<String> jListScroll;
	private JScrollPane scrollPane;

	private JScrollPane inputTracksScroll;




	private JLabel editCDNameJLabel;
	private JLabel editCDAlbumArtistLabel;

	private JLabel  editCDAlbumCopiesLabel;
	private JTextField editCDCopiesJText;

	private JTextField editCDAlbumArtistJText;
	private JTextField editCDNameJText;
	private JLabel editCDGenreLabel;
	private JComboBox<String> editCDGenreCombo;
	private JLabel editTrackNameJLabel;

	private JLabel editCDLabelDuration;
	private JLabel editCDLabelDurationDisplay;

	private JLabel editTrackArtist;
	private JTextField editTrackNameJText;
	private JTextField editTrackDurationJText;
	private JTextField editTrackArtistJText;
	private Catalogue mediaCatlog;
	private ArrayList<Media> mediaList;
	private JButton editJListTrackAddBtn;
	private JButton editJListTrackDelBtn;
	private JButton editJButtonCDUpdate;
	private JButton editJButtonDelete;




	private JLabel addTrackDuration;
	private JTextField addTrackNameJText;
	private Vector<String> addOutputTrackVector;
	private JButton addJListTrackDelBtn;
	private JButton addJListTrackAddBtn;
	private JList <String> addJListTrack;



	private JScrollPane editJListTrackScrollPane;
	private JList<String> editJListTrack;

	public int durationGlobal;

	private JTextField addTrackArtistJText;

	private int OptionSelected;

	private JLabel editTrackDuration;

	private CD CurrentCD;

	private String cdNameToFind;

	private String cdArtistToFind;


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
	public CDMaintPanelCDEditPane (JPanel jFramePanelWayBack, User currentUser) {
		{	
			//	this.setLayout(new GridLayout(2,2));

			mediaCatlog = new Catalogue();
			mediaList = mediaCatlog.getMediaList();

			// the read Panel is the this panel 
			//cdReadPanel = new JPanel(new BorderLayout());

			// north
			this.addFocusListener(this);
			northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			searchLabel = new JLabel("Search ");
			searchLabel.setFont(Styles.fontlabels);

			cdSearchNameJtext = new JTextField(15);
			cdSearchNameJtext.setFont(Styles.fontlabels);
			cdSearchNameJtext.setToolTipText("CD Name");

			cdSearchArtistJText= new JTextField(15);
			cdSearchArtistJText.setFont(Styles.fontlabels);
			cdSearchArtistJText.setToolTipText("Album Artist");

			searchBtn = new JButton("Search");
			searchBtn.setMnemonic('s');
			searchBtn.setFont(Styles.fontlabels);
			searchBtn.addActionListener(this);

			northPanel.add(cdSearchNameJtext);
			northPanel.add(cdSearchArtistJText);
			northPanel.add(searchBtn);

			centerMainPanel = new JPanel(new GridLayout(2, 2,0,10));
			outVectorCDMedia = this.extractCDVector();
			outVectorCDMedia.addElement("");

			JPanel jPad = new JPanel(new FlowLayout()); 
			jListScroll = new JList<String>(outVectorCDMedia);
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

			//Track edit;
			editTrackNameJLabel = new JLabel("Tr. Name");
			editTrackDuration = new JLabel("Tr. Duration");
			editTrackArtist =new JLabel("Tr. Artist");

			editTrackNameJText = new JTextField(15);
			editTrackDurationJText = new JTextField(15);
			editTrackArtistJText  =new JTextField(15);


			editOutputTrackVector  = new Vector <String>();
			editOutputTrackVector.addElement("");
			editJListTrack = new JList(editOutputTrackVector);
			editJListTrack.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			editJListTrackScrollPane= new JScrollPane(editJListTrack,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

			editJListTrackScrollPane.setPreferredSize(new Dimension(300, 100));

			editJListTrack.addListSelectionListener(this);
			editJListTrack.setVisibleRowCount(10);



			editJListTrackDelBtn = new JButton("Delete Track");
			editJListTrackDelBtn.addActionListener(this);
			//editJListTrackDelBtn.setEnabled(false);

			editJListTrackAddBtn = new JButton("Add Track");
			editJListTrackAddBtn.addActionListener(this);
			//editJListTrackAddBtn.setEnabled(false);

			topRightBorder = new JPanel(new BorderLayout());

			topRight1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
			topRight1.add(editJListTrackScrollPane);

			topRight2 = new JPanel(new GridLayout(3, 2, 0, 20 ));
			topRightInsert= new JPanel(new FlowLayout(FlowLayout.CENTER));

			topRightInsert.add(Styles.bufferButton(editJListTrackDelBtn));
			topRightInsert.add(Styles.bufferButton(editJListTrackAddBtn));

			topRight2.add(editTrackNameJLabel);
			topRight2.add(editTrackNameJText);
			topRight2.add(editTrackArtist);
			topRight2.add(editTrackArtistJText);
			topRight2.add(editTrackDuration);
			topRight2.add(editTrackDurationJText);

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
			editCDNameJLabel = new JLabel("CD Name");
			editCDNameJText = new JTextField(15);
			editCDAlbumArtistLabel = new JLabel("CD Artist");
			editCDAlbumArtistJText = new JTextField(15);
			editCDGenreLabel = new JLabel("CD Genre");
			editCDGenreCombo  = new JComboBox<String>();
			editCDAlbumCopiesLabel = new JLabel("Copies Owned");
			editCDCopiesJText = new JTextField(15);
			editCDLabelDuration= new JLabel("Duration");

			editCDLabelDurationDisplay = new JLabel("0");
			loadComboValues(editCDGenreCombo);

			//centerPanel2.setFont(new Font("arial", Font.BOLD , 6));
			centerPanel2.add(editCDNameJLabel);
			centerPanel2.add(editCDNameJText);
			centerPanel2.add(editCDAlbumArtistLabel);
			centerPanel2.add(editCDAlbumArtistJText);
			centerPanel2.add(editCDLabelDuration);
			centerPanel2.add(editCDLabelDurationDisplay);
			centerPanel2.add(editCDGenreLabel);
			centerPanel2.add(editCDGenreCombo);
			centerPanel2.add(editCDAlbumCopiesLabel);
			centerPanel2.add(editCDCopiesJText);

			editJButtonCDUpdate = new JButton("Update ");
			editJButtonCDUpdate.addActionListener(this);

			editJButtonDelete = new JButton("Delete");
			editJButtonDelete.addActionListener(this);


			bottomLeftInsert =new JPanel(new FlowLayout(FlowLayout.LEADING));


			centerPanel2.add(Styles.bufferButton(editJButtonCDUpdate));
			centerPanel2.add(Styles.bufferButton(editJButtonDelete));

			bottomLeftInsert.add(centerPanel2);

			centerMainPanel.add(bottomLeftInsert);

			this.add(northPanel, BorderLayout.NORTH);
			this.add(centerMainPanel, BorderLayout.CENTER);
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			this.setPreferredSize(new Dimension(700, 700));	
			
			if (!currentUser.isAdmin())
			{
				bottomLeftInsert.setVisible(false);
				topRightcenterMainPanel.setVisible(false);
				topRightInsert.setVisible(false);
			}
				



		}


	}


	private void  loadComboValues(JComboBox<String> jC) {
		// TODO Auto-generated method stub

		for (Genre g: Genre.values())
		{
			if (g.genreMediaType() == Media_Type.CD )
			{
				jC.addItem(g.toString());
			}
		}


	}


	private Vector<String> extractCDVector() {
		// TODO Auto-generated method stub

		Vector<String> extract = new Vector<>();
		mediaCatlog = new Catalogue();
		mediaList = mediaCatlog.getMediaList();
//		Formatter f= new Formatter();

//		f.format("%15S %15S %15S %15S %5S",
//				"CD Name,", 
//				"artist," ,
//				"duration,",
//				"Genre,",
//				"Copies");

		extract.addElement("CD Name, artist, duration, Genre, Copies");
//		f.close();


		for (int pos = 0 ; pos < this.mediaList.size(); pos++)
		{

			Media_Type type = mediaList.get(pos).getMedia_Type();

			CD cdIN;

			switch (type) 
			{
			case CD: cdIN= (CD) mediaList.get(pos);
			extract.addElement(
					cdIN.getMediaName() + ", " +
							cdIN.getCdArtist() + " , "  +
							cdIN.getDuration() + " , "  +
							cdIN.getGenre().toString() + "," +
							cdIN.getCopies() 
					);

			//System.out.println(cdIn); 
			break;
			default: System.out.println("Heuston I have a problem");
			}

		}
		return extract;
	}

	private Vector<String> extractCDVectorForPattern(String cdNameToFind , String cdArtistToFind) {
		// TODO Auto-generated method stub

		Vector<String> extract = new Vector<>();

//		Formatter f= new Formatter();

//		f.format("%15S %30S %30S %30S %5S",  " User ID" ,
//				"CD Name,", 
//				"artist," ,
//				"duration,",
//				"Genre,",
//				"Copies");

//		extract.addElement(f.toString());
//		f.close();

		extract.addElement("CD Name, artist, duration, Genre, Copies");

		for (int pos = 0 ; pos < this.mediaList.size(); pos++)
		{

			Media_Type type = mediaList.get(pos).getMedia_Type();

			CD cdIN;

			switch (type) 
			{

			case CD: 

				cdIN= (CD) mediaList.get(pos);

				if ((PatternMatching.findMatch(cdNameToFind.toUpperCase(), cdIN.getMediaName().toUpperCase()))
						&& (PatternMatching.findMatch(cdArtistToFind.toUpperCase(), cdIN.getCdArtist().toUpperCase()))) 
				{

					extract.addElement(	
							cdIN.getMediaName() + ", " +
									cdIN.getCdArtist() + " , "  +
									cdIN.getDuration() + " , "  +
									cdIN.getGenre().toString() + "," +
									cdIN.getCopies() 
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
		Object[][] listofCDs = new Object[mediaList.size()][6];
		CD cdInMedia; 
		int duration;
		String cdTrackText;
		StringTokenizer sTR;
		ArrayList<Track> tracks;
		Genre g;
		/*if I add this 
		 */

		if (source == editJListTrackAddBtn)
		{

			String trackNameIn="", trackArtist=""; int trackDuration=0;

			if (editOutputTrackVector == null)
			{
				editOutputTrackVector = new Vector<String>();
			}
			if (editTrackNameJText.getText().trim().isEmpty())
			{
				JOptionPane.showMessageDialog(this, "The name of the track is missing");
				return;
			}

			if (editTrackArtistJText.getText().trim().isEmpty())
			{
				trackArtist="";
			}
			else
			{
				trackArtist=editTrackArtistJText.getText();
			}

			if (editTrackDuration.getText().trim().isEmpty())
			{
				trackDuration=0;
			}
			else
			{
				try {
					trackDuration=Integer.parseInt(editTrackDurationJText.getText().trim());
					editOutputTrackVector.addElement(new String (editTrackNameJText.getText() +
							"," + trackArtist +
							"," + trackDuration));

					editJListTrack.removeListSelectionListener(this);	
					editJListTrack.setListData(editOutputTrackVector);
					editJListTrack.addListSelectionListener(this);
					editJListTrackScrollPane.repaint();
					//unStringForTracks()


				} catch (Exception ex)
				{
					JOptionPane.showMessageDialog(this, "Duration must be a valid duration in seconds- Integer only");
				}
			}

		}

		if (source == editJListTrackDelBtn)
		{
			if (this.editJListTrack.isSelectionEmpty()) return;

			editOutputTrackVector.removeElementAt(this.editJListTrack.getSelectedIndex());
			editJListTrack.removeListSelectionListener(this);	
			editJListTrack.setListData(editOutputTrackVector);
			editJListTrack.addListSelectionListener(this);
			editJListTrackScrollPane.repaint();

			return;
		}


		if (source == addJListTrackAddBtn)
		{
			System.out.println("edit add");
			String trackNameIn="", trackArtist=""; int trackDuration=0;

			if (addOutputTrackVector == null)
			{
				addOutputTrackVector = new Vector<String>();
			}
			if (addTrackNameJText.getText().trim().isEmpty())
			{
				JOptionPane.showMessageDialog(this, "The name of the track is missing");
				return;
			}

			if (addTrackArtistJText.getText().trim().isEmpty())
			{
				trackArtist="";
			}
			else
			{
				trackArtist=addTrackArtistJText.getText();
			}

			if (addTrackDuration.getText().trim().isEmpty())
			{
				trackDuration=0;
			}
			else
			{
				try {
					Integer.parseInt(addTrackDuration.getText().trim());

					addOutputTrackVector.addElement(new String (addTrackNameJText.getText() +
							"," + trackArtist +
							"," + trackDuration));

					addJListTrack.removeListSelectionListener(this);	
					addJListTrack.setListData(addOutputTrackVector);
					addJListTrack.addListSelectionListener(this);
					inputTracksScroll.repaint();
					//unStringForTracks()


				} catch (Exception ex)
				{
					JOptionPane.showMessageDialog(this, "Duration must be a valid duration in seconds- Integer only");

				}
			}

		}


		if (source == addJListTrackDelBtn)
		{
			if (this.addJListTrack.isSelectionEmpty()) return;

			addOutputTrackVector.removeElementAt(this.addJListTrack.getSelectedIndex());
			addJListTrack.removeListSelectionListener(this);	
			addJListTrack.setListData(addOutputTrackVector);
			addJListTrack.addListSelectionListener(this);
			inputTracksScroll.repaint();

			return;
		}


		if (source == editJButtonCDUpdate)
		{
			tracks=null;

			Media inMedia=null; 
			try
			{
				//inMedia= new CD(cdTitle, Media_Type.CD, 0, null,null,0);
				inMedia = this.mediaCatlog.findMediaByTitle(editCDNameJText.getText());
				this.mediaCatlog.deleteFromMediaList(inMedia);
				durationGlobal = 0;
				tracks=unStringForTracksFrom(editOutputTrackVector);
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(this, "delete part of Update failed");
			}

			try {


				cdInMedia= new CD(editCDNameJText.getText(),
						Media_Type.CD, 
						durationGlobal,
						Genre.valueOf((String)editCDGenreCombo.getSelectedItem()),
						tracks,
						editCDAlbumArtistJText.getText(),
						Integer.parseInt(editCDCopiesJText.getText()));

				boolean success = this.mediaCatlog.addToMediaList(cdInMedia);

				JOptionPane.showMessageDialog(this, "Update Operation complete" );

				outVectorCDMedia = this.extractCDVector();
				outVectorCDMedia.addElement(" ");
				jListScroll.removeListSelectionListener(this);
				jListScroll.setListData(outVectorCDMedia);
				jListScroll.addListSelectionListener(this);
				editCDNameJText.setText("");
				editCDAlbumArtistJText.setText("");
				editCDCopiesJText.setText("");
				
				editTrackNameJText.setText("");
				editTrackDurationJText.setText("");
				editTrackArtistJText.setText("");
				
				centerMainPanel.repaint();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(this,"Error with the data input");
			} 


		}	


		//  if you delete the CD details
		// deleted selected items and all tracks on confirmation	
		if (source == editJButtonDelete)
		{
			OptionSelected = JOptionPane.showConfirmDialog(this,
					"Do you want to delete this user : " + editCDNameJText.getText(), "Confirmation required:",
					JOptionPane.YES_NO_OPTION);

			if (OptionSelected == JOptionPane.NO_OPTION) 
			{	return;
			}
			Media inMedia=null; 
			try
			{
				//inMedia= new CD(cdTitle, Media_Type.CD, 0, null,null,0);
				inMedia = this.mediaCatlog.findMediaByTitle(editCDNameJText.getText());

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

				outVectorCDMedia = this.extractCDVector();
				outVectorCDMedia.addElement(" ");
				jListScroll.removeListSelectionListener(this);
				jListScroll.setListData(outVectorCDMedia);
				centerMainPanel.repaint();
				jListScroll.addListSelectionListener(this);
				
				editCDNameJText.setText("");
				editCDAlbumArtistJText.setText("");
				editCDCopiesJText.setText("");
				
				editTrackNameJText.setText("");
				editTrackDurationJText.setText("");
				editTrackArtistJText.setText("");
				
				

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
			cdNameToFind = cdSearchNameJtext.getText();
			cdArtistToFind = cdSearchArtistJText.getText();


			if (((cdNameToFind.isEmpty()) && (cdArtistToFind.isEmpty()))) 
			{
				outVectorCDMedia = this.extractCDVector();
			} 
			else 
			{
				outVectorCDMedia = this.extractCDVectorForPattern(cdNameToFind , cdArtistToFind);	
				if (outVectorCDMedia.isEmpty())
				{
					found=false;
				}
			}

			jListScroll.removeListSelectionListener(this);
			jListScroll.setListData(outVectorCDMedia);
			jListScroll.addListSelectionListener(this);


			editCDNameJText.setText("");
			editCDAlbumArtistJText.setText("");
			editCDCopiesJText.setText("");
			
			editTrackNameJText.setText("");
			editTrackDurationJText.setText("");
			editTrackArtistJText.setText("");
			
			//centerMainPanel.repaint();
			if (!found)
				JOptionPane.showMessageDialog(this, "Nothing matching the search criteria");
	
			this.repaint();


		}


	} //end action performed

	private ArrayList<Track> unStringForTracksFrom(Vector<String> addOutputTrackVector)
	{

		String tempTrackName="";
		String tempTrackArtist="";
		int   tempTrackDuration=0;
		StringTokenizer sTT;
		ArrayList<Track> returnedTrks = new ArrayList<Track>();

		for (String selected:addOutputTrackVector)
		{
			sTT = new StringTokenizer(selected, ",", false);

			int count = 0;
			while (sTT.hasMoreTokens()) {

				tempTrackName = (sTT.nextToken()).trim();
				tempTrackArtist = (sTT.nextToken().trim());
				tempTrackDuration = Integer.parseInt((sTT.nextToken()).trim());


			}
			durationGlobal += tempTrackDuration;
			returnedTrks.add(new Track(tempTrackName, tempTrackDuration, tempTrackArtist));
		}
		return returnedTrks;
	}

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
					if (firstColumn.equalsIgnoreCase("CD Name")) return;
					editCDNameJText.setText(firstColumn);
					editCDAlbumArtistJText.setText((sTT.nextToken().trim()));
					editCDLabelDurationDisplay.setText((sTT.nextToken()).trim());
					editCDGenreCombo.setSelectedItem((sTT.nextToken()).trim());
					editCDCopiesJText.setText((sTT.nextToken()).trim());
				}
				System.out.println("genre : " + Genre.valueOf((String) editCDGenreCombo.getSelectedItem() ));

				// empty out and fill the track list 
				CurrentCD = (CD) mediaCatlog.findMediaByTitleAndGenre(editCDNameJText.getText(), 
						Genre.valueOf( (String) editCDGenreCombo.getSelectedItem())) ; 


				//
				editOutputTrackVector=loadVector(CurrentCD.getListOftracks());
				editJListTrack.setListData(editOutputTrackVector);

			}
		}



	}


	private Vector<String> loadVector(ArrayList<Track> listOftracks) {
		// TODO Auto-generated method stub

		Vector<String> out = new Vector<String>() ;

		for (Track t: listOftracks)
		{
			out.addElement(t.getTrackName() + " , " + t.getArtist() + ", " + String.valueOf(t.getTrackDuration()));
		}

		return out;
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

