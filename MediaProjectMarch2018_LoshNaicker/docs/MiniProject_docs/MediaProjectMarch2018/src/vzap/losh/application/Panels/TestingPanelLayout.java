package vzap.losh.application.Panels;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import vzap.losh.styles.Styles;

//@SuppressWarnings("serial")
public class TestingPanelLayout extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel center;
	private  CDMaintPanel u;
	
	
	public TestingPanelLayout ()
	{
		
		u = new CDMaintPanel(center, true);
		this.add(u,BorderLayout.CENTER);
		
	    this.setSize(800,400 ); // sets the opening size
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
