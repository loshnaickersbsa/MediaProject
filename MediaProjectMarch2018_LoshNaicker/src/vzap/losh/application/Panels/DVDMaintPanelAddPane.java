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


public class DVDMaintPanelAddPane extends JPanel implements  ActionListener
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
	private Vector<String> outVectorDVDMedia;

	

	private JList<String> jListScroll;
	




	private Catalogue mediaCatlog;
	private ArrayList<Media> mediaList;
	private JLabel addDVDNameJLabel;
	
	private JLabel addDVDAlbumArtistLabel;
	private JTextField addDVDNameJText;

	private JLabel addDVDAlbumCopies;
	private JTextField addDVDCopiesJText;

	private JTextField addDVDDirectorJText;
	private JLabel addDVDGenreLabel;
	
	private JTextField addDVDDrurationJText;
	private JLabel addDVDDurationLabel;
	
	private JComboBox<String> addDVDGenreCombo;



	
	private JButton addJButtonAddDVD;

	public int durationGlobal;


	
	
	private DVD CurrentDVD;

	
	private JPanel bottomRight;

	private JPanel topLeftOuter;

	
	private JPanel topRight1;

	private JPanel topRight2;

	private JPanel topRightInsert;

	
	//Panels



	// The media Catalog needed 
	public DVDMaintPanelAddPane () {
		{	this.setLayout(new GridLayout(2,2));

			mediaCatlog = new Catalogue();
			mediaList = mediaCatlog.getMediaList();

			//add first
			addDVDNameJLabel = new JLabel("DVD Name");
			addDVDNameJText = new JTextField(15);

			addDVDAlbumArtistLabel = new JLabel("DVD Artist");
			addDVDDirectorJText = new JTextField(15);

			addDVDGenreLabel = new JLabel("Genre");
			addDVDGenreCombo  = new JComboBox<String>();
			loadComboValues(addDVDGenreCombo);
			
			addDVDAlbumCopies = new JLabel("Copies Owned");;
			addDVDCopiesJText = new JTextField(15);
			
			addDVDDrurationJText= new JTextField(15);
			addDVDDurationLabel= new JLabel("Duration");
	
			topLeftOuter = new JPanel(new GridLayout(1, 1,10,10));
			topLeftGrid  = new JPanel(new GridLayout(6, 1, 20, 20));
			
			topLeftGrid.add(addDVDNameJLabel);
			topLeftGrid.add(addDVDNameJText);
			topLeftGrid.add(addDVDAlbumArtistLabel);
			topLeftGrid.add(addDVDDirectorJText);
			topLeftGrid.add(addDVDDurationLabel);
			topLeftGrid.add(addDVDDrurationJText);
			topLeftGrid.add(addDVDGenreLabel);
			topLeftGrid.add(addDVDGenreCombo);
			topLeftGrid.add(addDVDAlbumCopies);
			topLeftGrid.add(addDVDCopiesJText);
			
			
			topLeftOuter.add(topLeftGrid);
			topLeftOuter.add(new JLabel(" "));
			this.add(add(topLeftOuter));
			
			
			/*
			Border x;
			addCompForBorder
			*/
			topRightBorder = new JPanel(new BorderLayout());
			
			topRight1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
			
			
			topRight2 = new JPanel(new GridLayout(3, 2, 0, 20 ));
			topRightInsert= new JPanel(new FlowLayout(FlowLayout.CENTER));
			topRightcenterMainPanel= new JPanel(new GridLayout(2,1));
			topRightcenterMainPanel.add(topRight1);
			topRightcenterMainPanel.add(topRight2);
			topRightBorder.add(topRightInsert, BorderLayout.SOUTH);
			topRightBorder.add(topRightcenterMainPanel, BorderLayout.CENTER);
			//centerMainPanel.add(topRightBorder);
			this.add(topRightBorder);

			
			
			//	addJListTrack.addListSelectionListener(listener);
			addJButtonAddDVD = new JButton("Add ");
			addJButtonAddDVD.addActionListener(this);

			bottomLeftBorder = new JPanel(new BorderLayout());
			bottomLeftBorder.add(Styles.bufferButton(addJButtonAddDVD), BorderLayout.NORTH);
			
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
			if (g.genreMediaType() == Media_Type.DVD )
			{
				jC.addItem(g.toString());
			}
		}


	}


	private Vector<String> extractDVDVector() {
		// TODO Auto-generated method stub

		Vector<String> extract = new Vector<>();

		Formatter f= new Formatter();

		f.format("%15S %30S %30S %30S %5S",  " User ID" ,
				"DVD Name,", 
				"artist," ,
				"duration,",
				"Genre,",
				"Copies");

		extract.addElement(f.toString());
		f.close();


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

			System.out.println(addDVDDirectorJText.getText()); 
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
		DVD DVDInMedia; 
		int duration;
		String DVDTrackText;
		StringTokenizer sTR;
		ArrayList<Track> tracks;
		Genre g;
		/*if I add this 
		 */

	
		
		if (source == addJButtonAddDVD )
		{
			tracks=null;
			durationGlobal = 0;
			try {

				DVDInMedia= new DVD(addDVDNameJText.getText(),
									Media_Type.DVD, 
									Integer.parseInt(addDVDDrurationJText.getText().trim()),
									Genre.valueOf((String)addDVDGenreCombo.getSelectedItem()),
									addDVDDirectorJText.getText(),
									Integer.parseInt(addDVDCopiesJText.getText()));
				boolean success = this.mediaCatlog.addToMediaList(DVDInMedia);
				JOptionPane.showMessageDialog(this, "Add Operation complete" );
				outVectorDVDMedia = this.extractDVDVector();
				outVectorDVDMedia.addElement(" ");
				
				addDVDDirectorJText.setText("");
				addDVDCopiesJText.setText("");
				addDVDGenreCombo.setSelectedIndex(0);
				addDVDNameJText.setText("");
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

