package vzap.losh.application.Panels;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import vzap.losh.enumerations.Media_Type;
import vzap.losh.styles.Styles;
import vzap.losh.user.User;

public class BorrowCDMaintPanel extends JPanel implements ActionListener, ChangeListener
{

	// JTabbed
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTabbedPane tabbedPane;
	private Container largeLabel;
	private CDMaintPanelCDEditPane panelCheck;
	

	// The media Catalog needed 
	public BorrowCDMaintPanel (JPanel jFramePanelWayBack, User currentUser) {
		{	
			this.setLayout(new BorderLayout());

			 JTabbedPane tabbedPane = new JTabbedPane();
			//tabbedPane.add(cdAddPanel ,  "Add");
			//tabbedPane.add(cdReadPanel , "Read/Update" );

			this.add(largeLabel =new JLabel("Borrow CD Screen"),BorderLayout.NORTH);
			tabbedPane.addChangeListener(this);
			panelCheck = new CDMaintPanelCDEditPane(this, currentUser);
			
			tabbedPane.add("Search  ", panelCheck);
			
			tabbedPane.add("Borrow  ", new BorrowMediaPanel(Media_Type.CD, currentUser) );  
			tabbedPane.add("Borrowed list ", new BorrowedMediaPanelList(Media_Type.CD, currentUser) );  
		
			
			//this.add(tabbedPane, );

			largeLabel.setFont(Styles.fontlabels);
			//largeContainerPanel.add(largeLabel);
			//largeContainerPanel.add(tabbedPane,BorderLayout.CENTER) ;
			this.add(tabbedPane, BorderLayout.CENTER);
		
		/*
			jFramePanel.removeAll();
			jFramePanel.validate();
			jFramePanel.repaint();

			adminPanel = new UserAdminPanel(this.jFramePanel, loggedIn);

			jFramePanel.add(adminPanel);// is a jPanel
			jFramePanel.validate();
			jFramePanel.repaint();
		*/	
		}


	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		Object source;
		source = e.getSource();
		
		panelCheck.searchBtn.doClick();
		
		System.out.println(source.toString());
		
		
	}



}

