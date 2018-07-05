package vzap.losh.styles;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
//import javax.swing;
import javax.swing.JPanel;

public final class Styles {
	
	public static final Font listBoxes=new Font("Lucida Console", Font.BOLD, 13);
	public static final Font fontlabels=new Font("Arial", Font.BOLD , 60);
	public static final Font fontlabelsValue=new Font("Arial", Font.PLAIN , 30);
	public static final Font subMenuFontlabels=new Font("Arial", Font.BOLD , 20);
	public static final Font headingFontlabels=new Font("Arial", Font.BOLD , 64);
	public static final Font menuFontlabels=new Font("Arial", Font.BOLD , 40);
	public static final Font statusFontlabels=new Font("Arial", Font.ITALIC , 10);
	public static final Dimension buttonDim= new Dimension(200, 30); 
	
	public static JButton setButtonSize(JButton button)
	{	button.setPreferredSize(Styles.buttonDim);
		button.setFont(Styles.subMenuFontlabels);
		return button;
	}

	public static  JPanel bufferButton(JButton addBtn, char mneumonic, String screenTip) {
		// TODO Auto-generated method stub
		JPanel bufferButton = new JPanel(new FlowLayout());
		bufferButton.add(setButtonSize(addBtn));
		addBtn.setMnemonic(mneumonic);
		addBtn.setToolTipText(screenTip);
		return bufferButton;
	}

	public static  JPanel bufferButton(JButton addBtn,  String screenTip) {
		// TODO Auto-generated method stub
		JPanel bufferButton = new JPanel(new FlowLayout());
		bufferButton.add(setButtonSize(addBtn));
		addBtn.setToolTipText(screenTip);
		return bufferButton;
	}

	public static  JPanel bufferButton(JButton addBtn, char mneumonic) {
		// TODO Auto-generated method stub
		JPanel bufferButton = new JPanel(new FlowLayout());
		bufferButton.add(setButtonSize(addBtn));
		addBtn.setMnemonic(mneumonic);
		return bufferButton;
	}

	public static  JPanel bufferButton(JButton addBtn) {
		// TODO Auto-generated method stub
		JPanel bufferButton = new JPanel(new FlowLayout());
		bufferButton.add(setButtonSize(addBtn));
		return bufferButton;
	}
}