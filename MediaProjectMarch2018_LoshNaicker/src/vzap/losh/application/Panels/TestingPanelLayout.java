package vzap.losh.application.Panels;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import vzap.losh.exceptions.UserClassException;
import vzap.losh.styles.Styles;
import vzap.losh.user.User;

//@SuppressWarnings("serial")
public class TestingPanelLayout extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel center;
	private  CDMaintPanelCDEditPane u;
	
	
	public TestingPanelLayout ()
	{
		
		try {
			u = new CDMaintPanelCDEditPane(null,new User("losh", "losh", "naicker", "Mr", true));
		} catch (UserClassException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.add(u);
		
	    this.setSize(400,400 ); // sets the opening size
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		//Dimension Dimension;
	      setVisible(true);

	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new TestingPanelLayout();

	}

}
