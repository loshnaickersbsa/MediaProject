package vzap.losh.application.Panels;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.im.spi.InputMethodDescriptor;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolTip;
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

public class CDMaintPanel extends JPanel implements ListSelectionListener , ActionListener
{

	// JTabbed
	private JTabbedPane tabbedPane;

	private JButton searchBtn = null;
	private JLabel searchLabel;

	// for search
	private JTextField cdTitle, artist;

	// or grid,

	// panels
	private JPanel southPanel;
	private JPanel centerMainPanel;
	private JPanel centerPanel2;
	private JPanel northPanel;
	private JPanel cdReadPanel;

	private JTextField logInDisplay;

	// values on the edit and update panel
	private JLabel lableInputCDTitle;
	private JTextField  inputCDTitle;

	private JLabel labelInputAlbumArtist;
	private JTextField  inputAlbumArtist;


	private JLabel lableInputMediaType;
	private JComboBox<String> inputLabelMediaType;
	private Media_Type inMediaType;



	private JPanel largeContainerPanel;

	private int copies=0;

	Genre[] genreArray = Genre.values();

	//number of tracks in a drop down
	private JLabel inputDurationLabel;
	private JTextField inputDurationJText;

	private JLabel inputGenreLabel;
	private JTextField inputGenreJTextField;

	private JButton inputAddNewCD;
	private JButton inputAddNewTrackToVector;

	private Vector<String> outVectorMedia;
	private Vector<String> editOutputTrackVector;
	private Vector<String> outVectorCDMedia;

	private JTextField cdSearchNameJtext;
	private JComponent cdSearchNameLabel;
	private JToolTip cdSearchToolTip;
	private JTextField cdSearchArtistJText;


	private JList<String> jListScroll;
	private JScrollPane scrollPane;

	private JList<String> inputTracks;
	private JScrollPane inputTracksScroll;




	private JLabel editCDNameJLabel;
	private JLabel editCDAlbumArtistLabel;

	private JLabel  editCDAlbumCopies;
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
	private JLabel addCDNameJLabel;
	private JLabel largeLabel;

	private JLabel addCDAlbumArtistLabel;
	private JTextField addCDNameJText;

	private JLabel addCDAlbumCopies;
	private JTextField addCDCopiesJText;

	private JTextField addCDAlbumArtistJText;
	private JLabel addCDGenreLabel;
	private JComboBox<String> addCDGenreCombo;
	private JLabel addTrackNameJLabel;
	private JLabel addTrackDuration;
	private JLabel addTrackArtist;
	private JTextField addTrackNameJText;
	private JTextField addTrackDurationJText;
	private Vector<String> addOutputTrackVector;
	private JButton addJListTrackDelBtn;
	private JButton addJListTrackAddBtn;
	private JList <String> addJListTrack;


	private JPanel cdAddPanel;

	private JScrollPane editJListTrackScrollPane;
	private JList<String> editJListTrack;

	private JPanel cdAddPanelInner;

	private JButton addJButtonAddCD;

	public int durationGlobal;

	private JTextField addTrackArtistJText;

	private int OptionSelected;

	private JLabel editTrackDuration;

	private CD CurrentCD;

	private String cdNameToFind;

	private String cdArtistToFind;

	//Panels



	// The media Catalog needed 
	public CDMaintPanel (JPanel jFramePanelWayBack, boolean logddDIn) {
		{	
			this.setLayout(new BorderLayout(200,20));
			mediaCatlog = new Catalogue();
			mediaList = mediaCatlog.getMediaList();

	
			tabbedPane = new JTabbedPane();
			cdReadPanel = new JPanel(new BorderLayout());

			cdAddPanelInner = new JPanel();
			cdAddPanelInner.setLayout(new GridLayout(20,2));
			

			cdAddPanel= new JPanel(new GridLayout(1, 1));
			cdAddPanel.add(cdAddPanelInner);
			cdAddPanel.add(new JLabel(""));
			// north
			northPanel = new JPanel();
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

			centerMainPanel = new JPanel(new GridLayout(1, 1));

			outVectorCDMedia = this.extractCDVector();
			outVectorCDMedia.addElement("");
			jListScroll = new JList<String>(outVectorCDMedia);
			jListScroll.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jListScroll.setFont(Styles.listBoxes);
			jListScroll.addListSelectionListener(this);

			scrollPane = new JScrollPane(jListScroll,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			scrollPane.setPreferredSize(new Dimension(400,200));

			// work on this second
			centerPanel2 = new JPanel(new GridLayout(15,2));
			centerPanel2.setFont(Styles.fontlabels);
			editCDNameJLabel = new JLabel("CD Name");
			editCDNameJText = new JTextField(15);

			editCDAlbumArtistLabel = new JLabel("CD Artist");
			editCDAlbumArtistJText = new JTextField(15);

			editCDGenreLabel = new JLabel("CD Genre");
			editCDGenreCombo  = new JComboBox<String>();
			loadComboValues(editCDGenreCombo);

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
			editJListTrackScrollPane.setPreferredSize(new Dimension(50, 50));
			editJListTrack.addListSelectionListener(this);

			editJListTrackDelBtn = new JButton("Delete Track");
			editJListTrackDelBtn.addActionListener(this);
			
			editJListTrackAddBtn = new JButton("Add Track");
			editJListTrackAddBtn.addActionListener(this);
			
			//
			//	editJListTrack.addListSelectionListener(listener);

			centerPanel2.add(editCDNameJLabel);
			centerPanel2.add(editCDNameJText);
			centerPanel2.add(editCDAlbumArtistLabel);
			centerPanel2.add(editCDAlbumArtistJText);
			centerPanel2.add(editCDGenreLabel);
			centerPanel2.add(editCDGenreCombo);
			centerPanel2.add(editJListTrackDelBtn);
			centerPanel2.add(editJListTrackAddBtn);
			centerPanel2.add(editJListTrackScrollPane);

			centerPanel2.add(editTrackNameJLabel);
			centerPanel2.add(editTrackNameJText);
			centerPanel2.add(editTrackDuration);
			centerPanel2.add(editTrackDurationJText);
			centerPanel2.add(editTrackArtist);
			centerPanel2.add(editTrackArtistJText);

			editJButtonCDUpdate = new JButton("Add ");
			editJButtonCDUpdate.addActionListener(this);
			
			editJButtonDelete = new JButton("Delete ");
			editJButtonDelete.addActionListener(this); 
			
			centerPanel2.add(editJButtonCDUpdate);
			
			centerPanel2.add(editJButtonDelete);

			// whole

			cdReadPanel.setLayout(new BorderLayout());
			cdReadPanel.add(northPanel, BorderLayout.NORTH);
			cdReadPanel.add(centerMainPanel);
			
			centerMainPanel.add(scrollPane,BorderLayout.CENTER);
			centerMainPanel.add(centerPanel2);

			//add first
			addCDNameJLabel = new JLabel("CD Name");
			addCDNameJText = new JTextField(15);

			addCDAlbumArtistLabel = new JLabel("CD Artist");
			addCDAlbumArtistJText = new JTextField(15);

			addCDGenreLabel = new JLabel("Genre");
			addCDGenreCombo  = new JComboBox<String>();
			loadComboValues(addCDGenreCombo);

			//Track add;
			addTrackNameJLabel = new JLabel("Tr. Name");
			addTrackDuration = new JLabel("Tr. Duration");
			addTrackArtist =new JLabel("Tr. Artist");
			addTrackNameJText = new JTextField(15);
			addTrackDurationJText = new JTextField(15);
			addTrackDurationJText.setToolTipText("In seconds only-Integer");

			addTrackArtistJText  =new JTextField(15);


			//will use  * = new loadListVectorForTheCD();
			//empty Jlist for empty tracks.

			addOutputTrackVector  = new Vector <String>();
			addOutputTrackVector.addElement("");
			addJListTrack = new JList(addOutputTrackVector);
			addJListTrack.addListSelectionListener(this);
			addJListTrack.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			inputTracksScroll = new JScrollPane(addJListTrack, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

			inputTracksScroll.setPreferredSize(new Dimension(50, 50));

			addJListTrackDelBtn = new JButton("Delete Track");
			addJListTrackDelBtn.addActionListener(this);
			
			addJListTrackAddBtn = new JButton("Add Track");
			addJListTrackAddBtn.addActionListener(this);
			
			//	addJListTrack.addListSelectionListener(listener);
			cdAddPanelInner.setFont(Styles.fontlabelsValue);
			cdAddPanelInner.add(addCDNameJLabel);
			cdAddPanelInner.add(addCDNameJText);
			cdAddPanelInner.add(addCDAlbumArtistLabel);
			cdAddPanelInner.add(addCDAlbumArtistJText);
			cdAddPanelInner.add(addCDGenreLabel);
			cdAddPanelInner.add(addCDGenreCombo);

			cdAddPanelInner.add(addJListTrackDelBtn);
			cdAddPanelInner.add(addJListTrackAddBtn);

			cdAddPanelInner.add(inputTracksScroll);

			cdAddPanelInner.add(addTrackNameJLabel);
			cdAddPanelInner.add(addTrackNameJText);

			cdAddPanelInner.add(addTrackDuration);
			cdAddPanelInner.add(addTrackDurationJText);

			cdAddPanelInner.add(addTrackArtist);
			cdAddPanelInner.add(addTrackArtistJText);


			addJButtonAddCD = new JButton("Add ");
			addJButtonAddCD.addActionListener(this);
			
			cdAddPanelInner.add(addJButtonAddCD);

			tabbedPane.add(cdAddPanel ,  "Add");
			tabbedPane.add(cdReadPanel , "Read/Update" );

			largeContainerPanel = new JPanel();

			this.add(largeLabel =new JLabel("CD Mainetenance Screen"),BorderLayout.NORTH);
			largeLabel.setFont(Styles.fontlabels);
			//largeContainerPanel.add(largeLabel);
			largeContainerPanel.add(tabbedPane,BorderLayout.CENTER) ;
			this.add(largeContainerPanel, BorderLayout.CENTER);
	

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

		Formatter f= new Formatter();

		f.format("%15S %30S %30S %30S %5S",  " User ID" ,
				"CD Name,", 
				"artist," ,
				"duration,",
				"Genre,",
				"Copies");

		extract.addElement(f.toString());
		f.close();


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

		Formatter f= new Formatter();

		f.format("%15S %30S %30S %30S %5S",  " User ID" ,
				"CD Name,", 
				"artist," ,
				"duration,",
				"Genre,",
				"Copies");

		extract.addElement(f.toString());
		f.close();


		for (int pos = 0 ; pos < this.mediaList.size(); pos++)
		{

			Media_Type type = mediaList.get(pos).getMedia_Type();

			CD cdIN;

			switch (type) 
			{

			case CD: 

				cdIN= (CD) mediaList.get(pos);

				if ((PatternMatching.findMatch(cdNameToFind, cdIN.getMediaName().toUpperCase()))
						&& (PatternMatching.findMatch(cdArtistToFind, cdIN.getCdArtist()))) 
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
					Integer.parseInt(editTrackDuration.getText().trim());
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

		
		if (source == addJButtonAddCD || source == editJButtonCDUpdate)
		{
			tracks=null;

			if (source == editJButtonCDUpdate)
			{
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
			}
			else
			{
				durationGlobal = 0;
				tracks=unStringForTracksFrom(addOutputTrackVector);
			}

			try {

				if (source == editJButtonCDUpdate)
				{
					cdInMedia= new CD(editCDNameJText.getText(),
							Media_Type.CD, 
							durationGlobal,
							Genre.valueOf((String)editCDGenreCombo.getSelectedItem()),
							tracks,
							editCDAlbumArtistJText.getText(),
							Integer.parseInt(editCDAlbumCopies.getText()));

				}
				else
				{
					cdInMedia= new CD(addCDNameJText.getText(),
							Media_Type.CD, 
							durationGlobal,
							Genre.valueOf((String)addCDGenreCombo.getSelectedItem()),
							tracks,
							addCDAlbumArtistJText.getText(),
							Integer.parseInt(addCDAlbumCopies.getText()));
				}

				boolean success = this.mediaCatlog.addToMediaList(cdInMedia);

				JOptionPane.showConfirmDialog(this, (source== editJButtonCDUpdate? "Edit-":"Add")
						+ "Operation complete" );
				
				outVectorCDMedia = this.extractCDVector();
				outVectorCDMedia.addElement(" ");
				jListScroll.removeListSelectionListener(this);
				jListScroll.setListData(outVectorCDMedia);
				centerMainPanel.repaint();
				jListScroll.addListSelectionListener(this);
						
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
			boolean found = false;
			cdNameToFind = cdSearchNameJtext.getText();
			cdArtistToFind = cdSearchArtistJText.getText();


			if (((cdNameToFind.isEmpty()) && (cdArtistToFind.isEmpty()))) 
			{
				outVectorCDMedia = this.extractCDVector();

			} 
			else 
			{
				outVectorCDMedia = this.extractCDVectorForPattern(cdNameToFind , cdArtistToFind);	
			}

			jListScroll.removeListSelectionListener(this);
			jListScroll.setListData(outVectorCDMedia);
			jListScroll.addListSelectionListener(this);
			centerMainPanel.repaint();

			editCDNameJText.setText("");
			editCDAlbumArtistJText.setText("");
			editCDCopiesJText.setText("");
			editTrackDuration.setText("");
			editTrackNameJText.setText("");
			editTrackArtist.setText("");

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
		
		System.out.println(" value changed source : " + source);
		
		// select from the read
		if (source == jListScroll)
		{
			if (e.getValueIsAdjusting() == true) 
			{

				selected = (String) jListScroll.getSelectedValue();

				//unstring and populate the values
				sTT = new StringTokenizer(selected, ",", false);

				int count = 0;
				while (sTT.hasMoreTokens()) {
					editCDNameJText.setText((sTT.nextToken()).trim());
					editCDAlbumArtistJText.setText((sTT.nextToken().trim()));
					editCDLabelDurationDisplay.setText((sTT.nextToken()).trim());
					editCDGenreCombo.setSelectedItem((sTT.nextToken()).trim());
					editCDCopiesJText.setText((sTT.nextToken()).trim());
				}
				// empty out and fill the track list 
				CurrentCD = (CD) mediaCatlog.findMediaByTitleAndGenre(editCDNameJText.getText(), 
						Genre.valueOf( (sTT.nextToken()).trim() ) ); 

				//
				editOutputTrackVector=loadVector(CurrentCD.getListOftracks());
				editJListTrack.setListData(editOutputTrackVector);

			}
		}

		if (source == editJListTrack)
		{	
			if (e.getValueIsAdjusting() == true) 
			{
				sTT = new StringTokenizer(editJListTrack.getSelectedValue(), ",", false);

				int count = 0;
				while (sTT.hasMoreTokens()) {

					editTrackNameJText.setText(sTT.nextToken().trim());
					editTrackArtistJText.setText(sTT.nextToken().trim());
					editTrackDurationJText.setText(sTT.nextToken().trim());
				}


			}
		}

		if (source == addJListTrack)
		{	
			if (e.getValueIsAdjusting() == true) 
			{
				sTT = new StringTokenizer(addJListTrack.getSelectedValue(), ",", false);

				int count = 0;
				while (sTT.hasMoreTokens()) {

					editTrackNameJText.setText(sTT.nextToken().trim());
					editTrackArtistJText.setText(sTT.nextToken().trim());
					editTrackDurationJText.setText(sTT.nextToken().trim());

				}
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

}

