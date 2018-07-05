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

import vzap.losh.borrow.BorrowedMediaList;
import vzap.losh.catalogue.Catalogue;
import vzap.losh.enumerations.Genre;
import vzap.losh.enumerations.Media_Type;
import vzap.losh.styles.Styles;
import vzap.losh.user.User;
import vzap.losh.utils.PatternMatching;
import vzap.losh.media.*;


public class BorrowMediaPanel  extends JPanel implements  ActionListener
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


	 //Panels



	 // The media Catalog needed 
	 public BorrowMediaPanel (Media_Type mediaIn, User currentUser) {
		 {	this.setLayout(new GridLayout(3,2));

		 this.currentUser=currentUser;
		 mediaCatlog = new Catalogue();
		 mediaList = mediaCatlog.getMediaList();

		 borrowedMediaList = new BorrowedMediaList();

		 borrowMediaNameTypeJLabel = new JLabel("Media Type");
		 borrowMediaNameTypeValueJLabel = new JLabel("Media Type");

		 borrowMediaNameTypeValueJLabel.setText(mediaIn.toString());

		 borrowMediaNameJLabel = new JLabel("Media Name");
		 borrowMediaNameJText = new JTextField(30);


		 topLeftOuter = new JPanel(new GridLayout(1, 1,10,10));
		 topLeftGrid  = new JPanel(new GridLayout(3, 2, 20, 20));

		 topLeftGrid.add(borrowMediaNameTypeJLabel);
		 topLeftGrid.add(borrowMediaNameTypeValueJLabel);
		 topLeftGrid.add(borrowMediaNameJLabel);
		 topLeftGrid.add(borrowMediaNameJText);

		 topLeftOuter.add(topLeftGrid);
		 topLeftOuter.add(new JLabel(" "));
		 this.add(add(topLeftOuter));


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
		 borrowJButtonAddMedia = new JButton("Borrow ");
		 borrowJButtonAddMedia.addActionListener(this);

		 bottomLeftBorder = new JPanel(new BorderLayout());
		 bottomLeftBorder.add(Styles.bufferButton(borrowJButtonAddMedia), BorderLayout.NORTH);

		 this.add(bottomLeftBorder);

		 bottomRight = new JPanel(new GridLayout(1, 1));
		 //bottomRight.add();


		 this.add(new JPanel(new FlowLayout()).add(bottomRight));



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



		 if (source == borrowJButtonAddMedia )
		 {
			 tracks=null;
			 durationGlobal = 0;
			 try {


				 Media foundMedia = mediaCatlog.findMediaByTitle(borrowMediaNameJText.getText());

				 boolean isAvailable=borrowedMediaList.checkIfMediaIsAvailble(foundMedia);


				 if (isAvailable && borrowedMediaList.addBorrowedMedia(currentUser, foundMedia))
				 {
					 System.out.println("..................successful borrow....................");
					 JOptionPane.showMessageDialog(this, "Borrow successful complete" );
				 }
				 else
				 {
					 System.out.println(".................This media is not available............");
					 JOptionPane.showMessageDialog(this, "This media is not available");
				 }

				 borrowMediaNameJText.setText("");
				 this.repaint();

			 } catch (Exception e1) {
				 // TODO Auto-generated catch block
				 e1.printStackTrace();
				 JOptionPane.showMessageDialog(this,"Error with the data input");
			 } 


		 }	




	 } //end action performed


}



