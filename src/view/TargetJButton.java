package view;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class TargetJButton extends JButton{
	
	private String No="null";
	
	
	public void setNo(String no3) {
		this.No = no3;
	}
	public String getNo() {
		return No;
	}
	public TargetJButton() {
		super();	
		}
	public  TargetJButton(String x) {
		super(x);
	}
	
	public TargetJButton(String a,ImageIcon b){
		super(a,b);	
	}
	 public void setPreferredSize(Dimension d) {
	    	super.setPreferredSize(d);
	}
	public void  setJbutton(String a,ImageIcon b){
		super.setIcon(b);	
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