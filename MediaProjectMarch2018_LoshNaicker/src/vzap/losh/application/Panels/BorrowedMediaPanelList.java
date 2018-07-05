package vzap.losh.application.Panels;

import java.awt.Component;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.PageAttributes.MediaType;
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

import vzap.losh.borrow.BorrowedMedia;
import vzap.losh.borrow.BorrowedMediaList;
import vzap.losh.catalogue.Catalogue;
import vzap.losh.enumerations.Genre;
import vzap.losh.enumerations.Media_Type;
import vzap.losh.styles.Styles;
import vzap.losh.user.User;
import vzap.losh.utils.PatternMatching;
import vzap.losh.media.*;


public class BorrowedMediaPanelList  extends JPanel implements  ActionListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel topLeftGrid; // 1,6 , labels
	private JPanel topRightBorder;
	private JPanel topRightcenterMainPanel; 
	private JPanel bottomLeftBorder;
	private User currentUser;

	// values on the edit and update panel


	Genre[] genreArray = Genre.values();

	//number of tracks in a drop down
	private Vector<String> outVectorMediaMedia;
	private JList<String> jListScroll;

	private Catalogue mediaCatlog;
	private ArrayList<Media> mediaList;
	private JLabel addMediaNameJLabel;

	private JLabel addMediaAlbumArtistLabel;
	private JTextField addMediaNameJText;

	private JLabel addMediaAlbumCopies;
	private JTextField addMediaCopiesJText;

	private JTextField addMediaDirectorJText;
	private JLabel addMediaGenreLabel;

	private JTextField addMediaDrurationJText;
	private JLabel addMediaDurationLabel;

	private JComboBox<String> addMediaGenreCombo;
	private JButton addJButtonAddMedia;

	public int durationGlobal;




	private Media CurrentMedia;


	private JPanel bottomRight;

	private JPanel topLeftOuter;


	private JPanel topRight1;

	private JPanel topRight2;

	private JPanel topRightInsert;

	private JLabel borrowMediaNameTypeJLabel;

	private JTextField borrowMediaTypeJText;

	private JLabel borrowMediaNameTypeValueJLabel;

	private JButton borrowJButtonAddMedia;

	private JTextField borrowMediaNameJText;

	private BorrowedMediaList borrowedMediaList;

	private JLabel borrowMediaNameJLabel;

	private JList<String> borrowMediaListJlist;

	private JScrollPane borrowScrollPane;

	private JButton returnButton;

	private ArrayList<BorrowedMedia> bMEL;


	//Panels



	// The media Catalog needed 
	public BorrowedMediaPanelList (Media_Type mediaIn, User currentUser) 
		{	this.setLayout(new FlowLayout(FlowLayout.CENTER,400,200));

		this.currentUser=currentUser;
		mediaCatlog = new Catalogue();
		mediaList = mediaCatlog.getMediaList();



		borrowedMediaList = new BorrowedMediaList();

		borrowMediaListJlist = new JList<String>();

		borrowScrollPane =new JScrollPane(borrowMediaListJlist,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		borrowMediaListJlist.setVisibleRowCount(30);

		borrowScrollPane.setPreferredSize(new Dimension(800, 600));

		Styles.bufferButton(returnButton= new JButton("Return"));
		returnButton.addActionListener(this);

		populateValues();

		this.add(borrowScrollPane);
		this.add(returnButton);
		



		}





		private void populateValues() {
		// TODO Auto-generated method stub

			bMEL = this.borrowedMediaList.listBorrowedMedia(this.currentUser);

			if (bMEL.isEmpty())
			{
				borrowMediaListJlist.setListData(new String[]{"no books borrowed"});
			}
			else
			{
				Vector<String> display=new Vector<String>();
				for (int i = 0; i < bMEL.size(); i++) 
				{
					display.addElement( " [" + (i+1) + " ]"  + bMEL.get(i).getMedia()); 
				}
				borrowMediaListJlist.setListData(display);
			}

	}





		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object source = e.getSource();
			Media MediaInMedia; 
			int duration;
			String MediaTrackText;
			StringTokenizer sTR;
			ArrayList<Track> tracks;
			Genre g;
			/*if I add this 
			 */



			if (source == returnButton )
			{
				this.borrowedMediaList.returnBorrowedMedia(bMEL.get(borrowMediaListJlist.getSelectedIndex()));
				JOptionPane.showMessageDialog(this, "Media returned");
				 populateValues();
				 borrowMediaNameJText.setText("");
				 this.repaint();
			}
					
				/*
				 * 		while (true)
		{
			System.out.print("Enter the number of the media to return :");
			valueIn=input.readInt();
			valueIn--;
			if (valueIn <= bMEL.size())
			{
				break;
			}
		}

		
		System.out.println("Media return.");
		System.out.println("Media Outstanding : ");
		this.listAllBorrowedMedia();
				 */
				







			}
		
		
}



