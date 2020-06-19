package view;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class NextCycleButton extends JButton {

	public NextCycleButton(String a){
		super(a);	
	} 
	public NextCycleButton(String a,ImageIcon b){
		super(a,b);	
	}
    public void setPreferredSize(Dimension d) {
    	super.setPreferredSize(d);
    }

	public void setContentAreaFilled(boolean b) {

		super.setContentAreaFilled(b);
	}
	public void setBorderPainted(boolean b) {

		super.setBorderPainted(b);
	}
	public void setHorizontalTextPosition(int horizontalAlignment) {

		super.setHorizontalTextPosition(horizontalAlignment);
	}

	public void setVerticalTextPosition(int horizontalAlignment) {
		super.setVerticalTextPosition(horizontalAlignment);

	}


}
