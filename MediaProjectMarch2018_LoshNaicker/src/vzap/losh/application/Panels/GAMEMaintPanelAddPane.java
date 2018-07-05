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


public class GAMEMaintPanelAddPane extends JPanel implements  ActionListener
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
	private Vector<String> outVectorGAMEMedia;

	

	private JList<String> jListScroll;
	




	private Catalogue mediaCatlog;
	private ArrayList<Media> mediaList;
	private JLabel addGAMENameJLabel;
	
	private JLabel addGAMEAlbumArtistLabel;
	private JTextField addGAMENameJText;

	private JLabel addGAMEAlbumCopies;
	private JTextField addGAMECopiesJText;

	private JTextField addGAMEHouseJText;
	private JLabel addGAMEGenreLabel;
	private JComboBox<String> addGAMEGenreCombo;



	
	private JButton addJButtonAddGAME;

	public int durationGlobal;


	
	
	private GAME CurrentGAME;

	
	private JPanel bottomRight;

	private JPanel topLeftOuter;

	
	private JPanel topRight1;

	private JPanel topRight2;

	private JPanel topRightInsert;

	
	//Panels



	// The media Catalog needed 
	public GAMEMaintPanelAddPane () {
		{	this.setLayout(new GridLayout(2,2));

			mediaCatlog = new Catalogue();
			mediaList = mediaCatlog.getMediaList();

			//add first
			addGAMENameJLabel = new JLabel("GAME Name");
			addGAMENameJText = new JTextField(15);

			addGAMEAlbumArtistLabel = new JLabel("GAME Artist");
			addGAMEHouseJText = new JTextField(15);

			addGAMEGenreLabel = new JLabel("Genre");
			addGAMEGenreCombo  = new JComboBox<String>();
			loadComboValues(addGAMEGenreCombo);
			
			addGAMEAlbumCopies = new JLabel("Copies Owned");;
			addGAMECopiesJText = new JTextField(15);
	
			topLeftOuter = new JPanel(new GridLayout(1, 1,10,10));
			topLeftGrid  = new JPanel(new GridLayout(6, 1, 20, 20));
			
			topLeftGrid.add(addGAMENameJLabel);
			topLeftGrid.add(addGAMENameJText);
			topLeftGrid.add(addGAMEAlbumArtistLabel);
			topLeftGrid.add(addGAMEHouseJText);
			topLeftGrid.add(addGAMEGenreLabel);
			topLeftGrid.add(addGAMEGenreCombo);
			topLeftGrid.add(addGAMEAlbumCopies);
			topLeftGrid.add(addGAMECopiesJText);
			
			
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
			addJButtonAddGAME = new JButton("Add ");
			addJButtonAddGAME.addActionListener(this);

			bottomLeftBorder = new JPanel(new BorderLayout());
			bottomLeftBorder.add(Styles.bufferButton(addJButtonAddGAME), BorderLayout.NORTH);
			
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
			if (g.genreMediaType() == Media_Type.GAME )
			{
				jC.addItem(g.toString());
			}
		}


	}


	private Vector<String> extractGAMEVector() {
		// TODO Auto-generated method stub

		Vector<String> extract = new Vector<>();

		Formatter f= new Formatter();

		f.format("%15S %30S %30S %30S %5S",  " User ID" ,
				"GAME Name,", 
				"artist," ,
				"duration,",
				"Genre,",
				"Copies");

		extract.addElement(f.toString());
		f.close();


		for (int pos = 0 ; pos < this.mediaList.size(); pos++)
		{

			Media_Type type = mediaList.get(pos).getMedia_Type();

			GAME GAMEIN;

			switch (type) 
			{
			case GAME: GAMEIN= (GAME) mediaList.get(pos);
			extract.addElement(
					GAMEIN.getMediaName() + ", " +
							GAMEIN.getGamingHouse()+" , "  +
							GAMEIN.getDuration() + " , "  +
							GAMEIN.getGenre().toString() + "," +
							GAMEIN.getCopies() 
					);

			System.out.println(addGAMEHouseJText.getText()); 
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
		GAME GAMEInMedia; 
		int duration;
		String GAMETrackText;
		StringTokenizer sTR;
		ArrayList<Track> tracks;
		Genre g;
		/*if I add this 
		 */

	
		
		if (source == addJButtonAddGAME )
		{
			tracks=null;
			durationGlobal = 0;
			try {

				GAMEInMedia= new GAME(addGAMENameJText.getText(),
									Media_Type.GAME, 
									durationGlobal,
									Genre.valueOf((String)addGAMEGenreCombo.getSelectedItem()),
									addGAMEHouseJText.getText(),
									Integer.parseInt(addGAMECopiesJText.getText()));
				boolean success = this.mediaCatlog.addToMediaList(GAMEInMedia);
				JOptionPane.showMessageDialog(this, "Add Operation complete" );
				outVectorGAMEMedia = this.extractGAMEVector();
				outVectorGAMEMedia.addElement(" ");
				
				addGAMEHouseJText.setText("");
				addGAMECopiesJText.setText("");
				addGAMEGenreCombo.setSelectedIndex(0);
				addGAMENameJText.setText("");
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

