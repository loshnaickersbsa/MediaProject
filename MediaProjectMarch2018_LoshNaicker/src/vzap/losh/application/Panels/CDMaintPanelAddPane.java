package vzap.losh.application.Panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import vzap.losh.utils.PatternMatching;
import vzap.losh.media.*;


public class CDMaintPanelAddPane extends JPanel implements ListSelectionListener , ActionListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// JTabbed
	
	// for search
	
	// or grid,

	// panels
	private JPanel topLeftGrid; // 1,6 , labels
	private JPanel topRightBorder;
	private JPanel topRightcenterMainPanel; 
	private JPanel bottomLeftBorder;
		
	// values on the edit and update panel
	
	
	Genre[] genreArray = Genre.values();

	//number of tracks in a drop down
	private Vector<String> editOutputTrackVector;
	private Vector<String> outVectorCDMedia;

	

	private JList<String> jListScroll;
	
	private JScrollPane inputTracksScroll;




	private JTextField editCDCopiesJText;

	private JTextField editCDAlbumArtistJText;
	private JTextField editCDNameJText;
	private JComboBox<String> editCDGenreCombo;
	
	private JLabel editCDLabelDurationDisplay;

	private JTextField editTrackNameJText;
	private JTextField editTrackDurationJText;
	private JTextField editTrackArtistJText;
	private Catalogue mediaCatlog;
	private ArrayList<Media> mediaList;
	private JLabel addCDNameJLabel;
	
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


	private JList<String> editJListTrack;

	
	private JButton addJButtonAddCD;

	public int durationGlobal;

	private JTextField addTrackArtistJText;

	
	
	private CD CurrentCD;

	
	private JPanel bottomRight;

	private JPanel topLeftOuter;

	
	private JPanel topRight1;

	private JPanel topRight2;

	private JPanel topRightInsert;

	
	//Panels



	// The media Catalog needed 
	public CDMaintPanelAddPane () {
		{	this.setLayout(new GridLayout(2,2));

			mediaCatlog = new Catalogue();
			mediaList = mediaCatlog.getMediaList();

			//add first
			addCDNameJLabel = new JLabel("CD Name");
			addCDNameJText = new JTextField(15);

			addCDAlbumArtistLabel = new JLabel("CD Artist");
			addCDAlbumArtistJText = new JTextField(15);

			addCDGenreLabel = new JLabel("Genre");
			addCDGenreCombo  = new JComboBox<String>();
			loadComboValues(addCDGenreCombo);
			
			addCDAlbumCopies = new JLabel("Copies Owned");;
			addCDCopiesJText = new JTextField(15);
	
			topLeftOuter = new JPanel(new GridLayout(1, 1,10,10));
			topLeftGrid  = new JPanel(new GridLayout(6, 1, 20, 20));
			
			topLeftGrid.add(addCDNameJLabel);
			topLeftGrid.add(addCDNameJText);
			topLeftGrid.add(addCDAlbumArtistLabel);
			topLeftGrid.add(addCDAlbumArtistJText);
			topLeftGrid.add(addCDGenreLabel);
			topLeftGrid.add(addCDGenreCombo);
			topLeftGrid.add(addCDAlbumCopies);
			topLeftGrid.add(addCDCopiesJText);
			
			
			topLeftOuter.add(topLeftGrid);
			topLeftOuter.add(new JLabel(" "));
			this.add(add(topLeftOuter));
			
			//Track add;
			addTrackNameJLabel = new JLabel("Tr. Name");
			addTrackNameJText = new JTextField(15);
			
			addTrackDuration = new JLabel("Tr. Duration");
			addTrackDurationJText = new JTextField(15);
			addTrackDurationJText.setToolTipText("In seconds only-Integer");
	
			addTrackArtist =new JLabel("Tr. Artist");
			addTrackArtistJText  =new JTextField(15);
		
			addJListTrackDelBtn = new JButton("Delete Track");
			addJListTrackDelBtn.addActionListener(this);
			
			addJListTrackAddBtn = new JButton("Add Track");
			addJListTrackAddBtn.addActionListener(this);
			
			 
			addOutputTrackVector  = new Vector <String>();
			addOutputTrackVector.addElement("");
			addJListTrack = new JList(addOutputTrackVector);
			addJListTrack.addListSelectionListener(this);
			addJListTrack.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			inputTracksScroll = new JScrollPane(addJListTrack, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

			inputTracksScroll.setPreferredSize(new Dimension(250, 350));
			addJListTrack.setVisibleRowCount(5);
			
			/*
			Border x;
			addCompForBorder
			*/
			topRightBorder = new JPanel(new BorderLayout());
			
			topRight1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
			topRight1.add(inputTracksScroll);
			
			
			topRight2 = new JPanel(new GridLayout(3, 2, 0, 20 ));
			topRightInsert= new JPanel(new FlowLayout(FlowLayout.CENTER));
			
			topRightInsert.add(Styles.bufferButton(addJListTrackAddBtn));
			topRightInsert.add(Styles.bufferButton(addJListTrackDelBtn));

			topRight2.add(addTrackNameJLabel);
			topRight2.add(addTrackNameJText);
			topRight2.add(addTrackArtist);
			topRight2.add(addTrackArtistJText);
			topRight2.add(addTrackDuration);
			topRight2.add(addTrackDurationJText);

			topRightcenterMainPanel= new JPanel(new GridLayout(2,1));
			topRightcenterMainPanel.add(topRight1);
			topRightcenterMainPanel.add(topRight2);
			topRightBorder.add(topRightInsert, BorderLayout.SOUTH);
			topRightBorder.add(topRightcenterMainPanel, BorderLayout.CENTER);
			//centerMainPanel.add(topRightBorder);
			this.add(topRightBorder);

			
			
			//	addJListTrack.addListSelectionListener(listener);
			addJButtonAddCD = new JButton("Add ");
			addJButtonAddCD.addActionListener(this);

			bottomLeftBorder = new JPanel(new BorderLayout());
			bottomLeftBorder.add(Styles.bufferButton(addJButtonAddCD), BorderLayout.NORTH);
			
			this.add(bottomLeftBorder);
			
			bottomRight = new JPanel(new GridLayout(1, 1));
			//bottomRight.add();
		

			this.add(new JPanel(new FlowLayout()).add(bottomRight));
	
	

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

			System.out.println(addCDAlbumArtistJText.getText()); 
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
		CD cdInMedia; 
		int duration;
		String cdTrackText;
		StringTokenizer sTR;
		ArrayList<Track> tracks;
		Genre g;
		/*if I add this 
		 */

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
					trackDuration=Integer.parseInt(addTrackDurationJText.getText().trim());

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

		
		if (source == addJButtonAddCD )
		{
			tracks=null;
			durationGlobal = 0;
			tracks=unStringForTracksFrom(addOutputTrackVector);
			try {

				cdInMedia= new CD(addCDNameJText.getText(),
									Media_Type.CD, 
									durationGlobal,
									Genre.valueOf((String)addCDGenreCombo.getSelectedItem()),
									tracks,
									addCDAlbumArtistJText.getText(),
									Integer.parseInt(addCDCopiesJText.getText()));
				boolean success = this.mediaCatlog.addToMediaList(cdInMedia);
				JOptionPane.showMessageDialog(this, "Add Operation complete" );
				outVectorCDMedia = this.extractCDVector();
				outVectorCDMedia.addElement(" ");
				
				addCDAlbumArtistJText.setText("");
				addCDCopiesJText.setText("");
				addCDGenreCombo.setSelectedIndex(0);
				addCDNameJText.setText("");
				addTrackNameJText.setText("");
				addTrackDurationJText.setText("");
				addTrackArtist.setText("");
				addJListTrack.removeListSelectionListener(this);
				addJListTrackDelBtn.removeAll();
				addOutputTrackVector.removeAllElements();
				addJListTrack.addListSelectionListener(this);
				this.repaint();
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(this,"Error with the data input");
			} 


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

