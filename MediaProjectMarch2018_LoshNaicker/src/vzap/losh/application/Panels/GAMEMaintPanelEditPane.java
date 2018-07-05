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

public class GAMEMaintPanelEditPane extends JPanel implements ListSelectionListener , ActionListener ,FocusListener
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

	private Vector<String> outVectorGAMEMedia;

	private JTextField GAMESearchNameJtext;
	private JTextField GAMESearchArtistJText;


	private JList<String> jListScroll;
	private JScrollPane scrollPane;



	private JLabel editGAMENameJLabel;
	private JLabel editGAMEAlbumArtistLabel;

	private JLabel  editGAMEAlbumCopiesLabel;
	private JTextField editGAMECopiesJText;

	private JTextField editGAMEAlbumArtistJText;
	private JTextField editGAMENameJText;
	private JLabel editGAMEGenreLabel;
	private JComboBox<String> editGAMEGenreCombo;

	private JLabel editGAMELabelDuration;
	private JLabel editGAMELabelDurationDisplay;

	private Catalogue mediaCatlog;
	private ArrayList<Media> mediaList;

	private JButton editJButtonGAMEUpdate;
	private JButton editJButtonDelete;

	public int durationGlobal;


	private int OptionSelected;

	private GAME CurrentGAME;

	private String GAMENameToFind;

	private String GAMEArtistToFind;


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
	public GAMEMaintPanelEditPane (JPanel borrowGameMaintPanel, User currentUser) {
		{	
			//	this.setLayout(new GridLayout(2,2));

			mediaCatlog = new Catalogue();
			mediaList = mediaCatlog.getMediaList();

			// the read Panel is the this panel 
			//GAMEReadPanel = new JPanel(new BorderLayout());

			// north
			this.addFocusListener(this);
			northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			searchLabel = new JLabel("Search ");
			searchLabel.setFont(Styles.fontlabels);

			GAMESearchNameJtext = new JTextField(15);
			GAMESearchNameJtext.setFont(Styles.fontlabels);
			GAMESearchNameJtext.setToolTipText("GAME Name");

			GAMESearchArtistJText= new JTextField(15);
			GAMESearchArtistJText.setFont(Styles.fontlabels);
			GAMESearchArtistJText.setToolTipText("Album Artist");

			searchBtn = new JButton("Search");
			searchBtn.setMnemonic('s');
			searchBtn.setFont(Styles.fontlabels);
			searchBtn.addActionListener(this);

			northPanel.add(GAMESearchNameJtext);
			northPanel.add(GAMESearchArtistJText);
			northPanel.add(searchBtn);

			centerMainPanel = new JPanel(new GridLayout(2, 2,0,10));
			outVectorGAMEMedia = this.extractGAMEVector();
			outVectorGAMEMedia.addElement("");

			JPanel jPad = new JPanel(new FlowLayout()); 
			jListScroll = new JList<String>(outVectorGAMEMedia);
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
			editGAMENameJLabel = new JLabel("GAME Name");
			editGAMENameJText = new JTextField(15);
			editGAMEAlbumArtistLabel = new JLabel("GAME Artist");
			editGAMEAlbumArtistJText = new JTextField(15);
			editGAMEGenreLabel = new JLabel("GAME Genre");
			editGAMEGenreCombo  = new JComboBox<String>();
			editGAMEAlbumCopiesLabel = new JLabel("Copies Owned");
			editGAMECopiesJText = new JTextField(15);
			editGAMELabelDuration= new JLabel("Duration");

			editGAMELabelDurationDisplay = new JLabel("0");
			loadComboValues(editGAMEGenreCombo);

			//centerPanel2.setFont(new Font("arial", Font.BOLD , 6));
			centerPanel2.add(editGAMENameJLabel);
			centerPanel2.add(editGAMENameJText);
			centerPanel2.add(editGAMEAlbumArtistLabel);
			centerPanel2.add(editGAMEAlbumArtistJText);
			centerPanel2.add(editGAMELabelDuration);
			centerPanel2.add(editGAMELabelDurationDisplay);
			centerPanel2.add(editGAMEGenreLabel);
			centerPanel2.add(editGAMEGenreCombo);
			centerPanel2.add(editGAMEAlbumCopiesLabel);
			centerPanel2.add(editGAMECopiesJText);

			editJButtonGAMEUpdate = new JButton("Update ");
			editJButtonGAMEUpdate.addActionListener(this);

			editJButtonDelete = new JButton("Delete");
			editJButtonDelete.addActionListener(this);


			bottomLeftInsert =new JPanel(new FlowLayout(FlowLayout.LEADING));


			centerPanel2.add(Styles.bufferButton(editJButtonGAMEUpdate));
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
			if (g.genreMediaType() == Media_Type.GAME )
			{
				jC.addItem(g.toString());
			}
		}


	}


	private Vector<String> extractGAMEVector() {
		// TODO Auto-generated method stub

		Vector<String> extract = new Vector<>();
		mediaCatlog = new Catalogue();
		mediaList = mediaCatlog.getMediaList();
//		Formatter f= new Formatter();

//		f.format("%15S %15S %15S %15S %5S",
//				"GAME Name,", 
//				"artist," ,
//				"duration,",
//				"Genre,",
//				"Copies");

		extract.addElement("GAME Name, artist, duration, Genre, Copies");
//		f.close();


		for (int pos = 0 ; pos < this.mediaList.size(); pos++)
		{

			Media_Type type = mediaList.get(pos).getMedia_Type();

			GAME GAMEIN;

			switch (type) 
			{
			case GAME: GAMEIN= (GAME) mediaList.get(pos);
			extract.addElement(
					GAMEIN.getMediaName() + ", " +
							GAMEIN.getGamingHouse() + " , "  +
							GAMEIN.getDuration() + " , "  +
							GAMEIN.getGenre().toString() + "," +
							GAMEIN.getCopies() 
					);

			//System.out.println(GAMEIn); 
			break;
			default: System.out.println("Heuston I have a problem");
			}

		}
		return extract;
	}

	private Vector<String> extractGAMEVectorForPattern(String GAMENameToFind , String GAMEDirectorToFind) {
		// TODO Auto-generated method stub

		Vector<String> extract = new Vector<>();

//		Formatter f= new Formatter();

//		f.format("%15S %30S %30S %30S %5S",  " User ID" ,
//				"GAME Name,", 
//				"artist," ,
//				"duration,",
//				"Genre,",
//				"Copies");

//		extract.addElement(f.toString());
//		f.close();

		extract.addElement("GAME Name, artist, duration, Genre, Copies");

		for (int pos = 0 ; pos < this.mediaList.size(); pos++)
		{

			Media_Type type = mediaList.get(pos).getMedia_Type();

			GAME GAMEIN;

			switch (type) 
			{

			case GAME: 

				GAMEIN= (GAME) mediaList.get(pos);

				if ((PatternMatching.findMatch(GAMENameToFind.toUpperCase(), GAMEIN.getMediaName().toUpperCase()))
						&& (PatternMatching.findMatch(GAMEDirectorToFind.toUpperCase(), GAMEIN.getGamingHouse().toUpperCase()))) 
				{

					extract.addElement(	
							GAMEIN.getMediaName() + ", " +
									GAMEIN.getGamingHouse() + " , "  +
									GAMEIN.getDuration() + " , "  +
									GAMEIN.getGenre().toString() + "," +
									GAMEIN.getCopies() 
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
		Object[][] listofGAMEs = new Object[mediaList.size()][6];
		GAME GAMEInMedia; 
		int duration;
		String GAMETrackText;
		StringTokenizer sTR;
		ArrayList<Track> tracks;
		Genre g;
		/*if I add this 
		 */



		if (source == editJButtonGAMEUpdate)
		{
			tracks=null;

			Media inMedia=null; 
			try
			{
				//inMedia= new GAME(GAMETitle, Media_Type.GAME, 0, null,null,0);
				inMedia = this.mediaCatlog.findMediaByTitle(editGAMENameJText.getText());
				this.mediaCatlog.deleteFromMediaList(inMedia);
				durationGlobal = 0;
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(this, "delete part of Update failed");
			}

			try {


				GAMEInMedia= new GAME(editGAMENameJText.getText(),
						Media_Type.GAME, 
						durationGlobal,
						Genre.valueOf((String)editGAMEGenreCombo.getSelectedItem()),
						editGAMEAlbumArtistJText.getText(),
						Integer.parseInt(editGAMECopiesJText.getText()));

				boolean success = this.mediaCatlog.addToMediaList(GAMEInMedia);

				JOptionPane.showMessageDialog(this, "Update Operation complete" );

				outVectorGAMEMedia = this.extractGAMEVector();
				outVectorGAMEMedia.addElement(" ");
				jListScroll.removeListSelectionListener(this);
				jListScroll.setListData(outVectorGAMEMedia);
				jListScroll.addListSelectionListener(this);
				editGAMENameJText.setText("");
				editGAMEAlbumArtistJText.setText("");
				editGAMECopiesJText.setText("");
		
				centerMainPanel.repaint();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(this,"Error with the data input");
			} 


		}	


		//  if you delete the GAME details
		// deleted selected items and all tracks on confirmation	
		if (source == editJButtonDelete)
		{
			OptionSelected = JOptionPane.showConfirmDialog(this,
					"Do you want to delete this user : " + editGAMENameJText.getText(), "Confirmation required:",
					JOptionPane.YES_NO_OPTION);

			if (OptionSelected == JOptionPane.NO_OPTION) 
			{	return;
			}
			Media inMedia=null; 
			try
			{
				//inMedia= new GAME(GAMETitle, Media_Type.GAME, 0, null,null,0);
				inMedia = this.mediaCatlog.findMediaByTitle(editGAMENameJText.getText());

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

				outVectorGAMEMedia = this.extractGAMEVector();
				outVectorGAMEMedia.addElement(" ");
				jListScroll.removeListSelectionListener(this);
				jListScroll.setListData(outVectorGAMEMedia);
				centerMainPanel.repaint();
				jListScroll.addListSelectionListener(this);
				
				editGAMENameJText.setText("");
				editGAMEAlbumArtistJText.setText("");
				editGAMECopiesJText.setText("");
				

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
			GAMENameToFind = GAMESearchNameJtext.getText();
			GAMEArtistToFind = GAMESearchArtistJText.getText();


			if (((GAMENameToFind.isEmpty()) && (GAMEArtistToFind.isEmpty()))) 
			{
				outVectorGAMEMedia = this.extractGAMEVector();
			} 
			else 
			{
				outVectorGAMEMedia = this.extractGAMEVectorForPattern(GAMENameToFind , GAMEArtistToFind);	
				if (outVectorGAMEMedia.isEmpty())
				{
					found=false;
				}
			}

			jListScroll.removeListSelectionListener(this);
			jListScroll.setListData(outVectorGAMEMedia);
			jListScroll.addListSelectionListener(this);


			editGAMENameJText.setText("");
			editGAMEAlbumArtistJText.setText("");
			editGAMECopiesJText.setText("");
			

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
					if (firstColumn.equalsIgnoreCase("GAME Name")) return;
					editGAMENameJText.setText(firstColumn);
					editGAMEAlbumArtistJText.setText((sTT.nextToken().trim()));
					editGAMELabelDurationDisplay.setText((sTT.nextToken()).trim());
					editGAMEGenreCombo.setSelectedItem((sTT.nextToken()).trim());
					editGAMECopiesJText.setText((sTT.nextToken()).trim());
				}
				System.out.println("genre : " + Genre.valueOf((String) editGAMEGenreCombo.getSelectedItem() ));

				// empty out and fill the track list 
				CurrentGAME = (GAME) mediaCatlog.findMediaByTitleAndGenre(editGAMENameJText.getText(), 
						Genre.valueOf( (String) editGAMEGenreCombo.getSelectedItem())) ; 



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

