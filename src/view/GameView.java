package view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.PopupMenu;
import java.text.MessageFormat;
import java.util.ArrayList;

import javax.print.attribute.standard.PagesPerMinuteColor;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import controller.CommandCenter;
import model.disasters.Disaster;
import model.people.Citizen;
import model.people.CitizenState;

public class GameView  extends JFrame{

	private JPanel infopanel;
	private JPanel unitpanel;
	private JPanel AvailableUnits;
	private JPanel TreatingUnits;
	private JPanel RespondingUnits;
	private JPanel rescuepanel;
	private JPanel controlpanel;
	private JPanel infop ;


	private JPanel log ;
	private JPanel unitlog ;

	private JTextArea target;
	private JTextArea general ;
	private JTextArea casualties;
	private JTextArea cycles ;
	private JTextArea logTxt ;
	private JTextArea InfoText;
	private JTextArea unitlogtxt ;

	private JScrollPane logScroll ;
	private JScrollPane scrollPane;
	private JScrollPane unitscroll ;


	private UnitJbutton Ambulance;
	private UnitJbutton Firetruck;
	private UnitJbutton Evacuator;
	private UnitJbutton Diseasecontrolunit;
	private UnitJbutton Gascontrolunit;
	private NextCycleButton nextCycle;

	private JOptionPane pop ;

	private ArrayList<TargetJButton> targets;


	public JPanel getInfopanel() {
		return infopanel;
	}
	public JPanel getUnitpanel() {
		return unitpanel;
	}
	public JPanel getAvailableUnits() {
		return AvailableUnits;
	}
	public JPanel getTreatingUnits() {
		return TreatingUnits;
	}
	public JPanel getRespondingUnits() {
		return RespondingUnits;
	}
	public JPanel getRescuepanel() {
		return rescuepanel;
	}
	public JPanel getControlpanel() {
		return controlpanel;
	}
	public JTextArea getTarget() {
		return target;
	}
	public JTextArea getGeneral() {
		return general;
	}
	public JButton getAmbulance() {
		return Ambulance;
	}
	public JButton getFiretruck() {
		return Firetruck;
	}
	public  NextCycleButton getNextCycle() {
		return nextCycle;
	}
	public JButton getEvacuator() {
		return Evacuator;
	}
	public JButton getDiseasecontrolunit() {
		return Diseasecontrolunit;
	}
	public JButton getGascontrolunit() {
		return Gascontrolunit;
	}
	public ArrayList<TargetJButton> getTargets() {
		return targets;
	}
	public GameView() {


		super();


		this.setBounds(-5, -5, 2000, 2000);
		this.setResizable(false);	
		controlpanel = new JPanel();
		controlpanel.setPreferredSize(new Dimension(100, 100));
		JPanel c = new JPanel() ;
		c.setPreferredSize(new Dimension(70, 300));
		FlowLayout f = new FlowLayout() ;
		c.setLayout(f);

		BorderLayout controlborder =new BorderLayout();
		controlpanel.setLayout(controlborder);
		c.setBackground(Color.WHITE);

		controlpanel.setBorder(BorderFactory.createTitledBorder( "Controlling"));
		nextCycle = new NextCycleButton("Next Cycle") ;
		nextCycle.setPreferredSize(new Dimension(120, 70));
		nextCycle.setBorderPainted(true);
		nextCycle.setHorizontalTextPosition(JButton.CENTER);
		nextCycle.setVerticalTextPosition(JButton.NORTH);
		nextCycle.setVisible(true);
		c.add(nextCycle) ;
		BorderLayout controlBorderLayout=new BorderLayout();
		controlpanel.setLayout(controlborder);

		cycles = new JTextArea("  Number of cycles ") ;
		cycles.setEditable(false);
		cycles.setPreferredSize(new Dimension(250, 70));
		cycles.setBorder(BorderFactory.createLineBorder(Color.black));

		casualties = new JTextArea("  Score ") ;
		casualties.setEditable(false);
		casualties.setBorder(BorderFactory.createLineBorder(Color.black));
		casualties.setPreferredSize(new Dimension(100, 70));


		Font myFont1 = new Font("Score", Font.BOLD, 24);
		cycles.setFont(myFont1);
		casualties.setFont(myFont1);
		c.add(cycles) ;
		c.add(casualties) ;
		controlpanel.add(c , controlborder.NORTH);

		unitpanel =new JPanel();
		unitpanel.setLayout(new GridLayout(4,1));
		//BorderLayout unitborder =new BorderLayout();
		//	unitpanel.setLayout(unitborder);
		unitpanel.setBackground(Color.BLACK);
		unitpanel.setPreferredSize(new Dimension(400, 2000));
		//	JPanel p1= new JPanel();
		//BorderLayout p1BorderLayout=new BorderLayout();
		//p1.setLayout(p1BorderLayout);
		//p1.setPreferredSize(new Dimension(300,800));
		//	this.setBounds(0,0,100000,100000);

		//panel satr el available

		AvailableUnits= new JPanel();
		JPanel AvailableUnitslabel=new JPanel();
		AvailableUnits.setPreferredSize(new Dimension(150, 150));	
		AvailableUnits.setBorder(BorderFactory.createTitledBorder( "Available Units"));
		BorderLayout  l = new BorderLayout() ;
		FlowLayout AvailableBorder=new FlowLayout();
		AvailableUnits.setLayout(AvailableBorder);
		AvailableUnits.setBackground(Color.WHITE);
		AvailableUnits.setLayout(new GridLayout(3,1));
		//	AvailableUnits.setPreferredSize(new Dimension(300,300));
		AvailableUnits.validate();

		//panel satr el responding

		RespondingUnits =new JPanel();

		RespondingUnits.setPreferredSize(new Dimension(300, 200));
		RespondingUnits.setBorder(BorderFactory.createLineBorder(Color.black));
		RespondingUnits.setBorder(BorderFactory.createBevelBorder(1, Color.BLUE,Color.cyan));
		RespondingUnits.setBorder(BorderFactory.createTitledBorder( "Respondig Units")) ;
		BorderLayout RespondingBorder=new BorderLayout();
		RespondingUnits.setLayout(RespondingBorder);
		RespondingUnits.setBackground(Color.WHITE);
		RespondingUnits.setLayout(new GridLayout(3,1));

		//panel satr el treating

		TreatingUnits =new JPanel();
		JPanel TreatingUnitslabel=new JPanel();
		BorderLayout TreatingBorder=new BorderLayout();
		TreatingUnits.setLayout(TreatingBorder);
		TreatingUnits.setBackground(Color.WHITE);
		TreatingUnits.setBorder(BorderFactory.createLineBorder(Color.black));
		TreatingUnits.setBorder(BorderFactory.createTitledBorder( "Treating Units"));
		TreatingUnits.setPreferredSize(new Dimension(300, 200));
		TreatingUnits.setBackground(Color.WHITE);
		TreatingUnits.setLayout(new GridLayout(3,1));

		unitlog = new JPanel() ;
		unitlogtxt = new JTextArea("   Unit Log  "+ "\n");
		Font font3 = new Font("Information",  Font.BOLD, 20) ;
		unitlogtxt.setFont(font3);
		unitlogtxt.setPreferredSize(new Dimension(1000, 10000));
		unitscroll = new JScrollPane(unitlogtxt) ;
		unitscroll.setPreferredSize(new Dimension(400, 200));
		unitlogtxt.setEditable(false);
		unitlog.add(unitscroll, BorderLayout.CENTER);

		JPanel zeyada2=new JPanel();

		//bagama3hom


		unitpanel.add(AvailableUnits);
		unitpanel.add(RespondingUnits);
		unitpanel.add(TreatingUnits);
		unitpanel.add(unitlog) ;
		//unitpanel.add(zeyada2,)

		this.validate();

		infopanel=new JPanel();
		infopanel.setPreferredSize(new Dimension(400, 1100)); 
		infopanel.setBackground(Color.WHITE);

		infop = new JPanel() ;
		InfoText=new JTextArea(" Information : "+ "\n" );
		Font y = new Font("Information",  Font.BOLD, 20) ;
		infop.setPreferredSize(new Dimension(400, 400));
		InfoText.setFont(y);
		InfoText.setEditable(false);
		infopanel.add(infop, BorderLayout.NORTH);
		InfoText.setPreferredSize(new Dimension(400, 19000));
		scrollPane=new JScrollPane(InfoText);
		scrollPane.setPreferredSize(new Dimension(400,400));
		infopanel.setBorder(BorderFactory.createLineBorder(Color.black));
		infop.add(scrollPane, BorderLayout.CENTER);


		log = new JPanel() ;
		logTxt = new JTextArea(" Log Disasters "+"\n") ;
		log.setPreferredSize(new Dimension(8000, 470));
		logTxt.setFont(y);
		logTxt.setEditable(false );
		infopanel.add(log, BorderLayout.SOUTH);
		logScroll = new JScrollPane(logTxt) ;
		logTxt.setVisible(true);
		log.setBorder(BorderFactory.createLineBorder(Color.black));
		logTxt.setPreferredSize(new Dimension(400, 19000));
		logScroll.setPreferredSize(new Dimension(400,450));
		log.add(logScroll , BorderLayout.CENTER) ;


		//el rescue panel bta3et elgrid
		rescuepanel =new JPanel();
		rescuepanel.setPreferredSize(new Dimension(400, 400));
		rescuepanel.setLayout(new GridLayout(10, 10));
		rescuepanel.setBackground(Color.WHITE);
		rescuepanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));



		Font myFont = new Font("The City", Font.BOLD, 28);
		BorderLayout rescueborder = new BorderLayout() ;

		this.add(rescuepanel,BorderLayout.CENTER);
		this.add(controlpanel,BorderLayout.NORTH);

		this.add(unitpanel,BorderLayout.EAST);
		this.add(infopanel, BorderLayout.WEST);
		this.validate();


		this.setTitle("GAME");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		


	}



	public void ShowMessage(CommandCenter c) {
		
	 //int test=JOptionPane.showMessageDialog(this,"Game Over "+"\n" + "You score is :"+c.getEngine().calculateCasualties());
		int test =JOptionPane. showConfirmDialog(this, "Game Over "+"\n" + "You score is :"+c.getEngine().calculateCasualties(),"GameOver",-1);
		if(test==0||test==1||test==2)
			this.dispose();
		
	}


	public void addinformation(String string) {
		InfoText.setText(this.InfoText.getText()+string+"\n"+"============================="+"\n");
		InfoText.setEditable(false);

	}
	public void addbtns(JButton units) 
	{ 
		AvailableUnits.add(units);
		AvailableUnits.repaint();
		AvailableUnits.validate();
		units.repaint();
		units.validate();
	}
	public void addtorespobdingpanel(JButton x) {
		RespondingUnits.add(x);
		RespondingUnits.repaint();
		RespondingUnits.validate();
		x.repaint();
		x.validate();

	}
	public void addtotreatingpanel(JButton x) {
		TreatingUnits.add(x);
		TreatingUnits.repaint();
		TreatingUnits.validate();
		x.validate();
		x.repaint();
	}

	public void addtorescuepanel(JButton x) {
		
		rescuepanel.add(x);

	}


	public void addcasaulites(int calculateCasualties) {
		casualties.setText("  Score "+"\n"+"      "+calculateCasualties+"");


	}
	public void addLog(ArrayList<Disaster> executedDisasters,ArrayList<Citizen> x,ArrayList<Citizen> y  ,int currentcycle) {
		String s ="" ;
		
		String past ="Currentcycle is "+currentcycle +"\n"+"Past disasters :  " ;
		String active ="Active disasters : ";
		for( int i=0 ; i<executedDisasters.size() ; i++) {
			//	 s+=executedDisasters.get(i).toString() +"\n"+ executedDisasters.get(i).getTarget().toString() + "\n";
			
			if( executedDisasters.get(i).getStartCycle()<currentcycle) {
				//System.out.println("d5l");
				past+="\n"+executedDisasters.get(i).toString() +"\n"+ executedDisasters.get(i).getTarget().toString() + "\n";
			}
			
			 if(executedDisasters.get(i).isActive()) {
				//System.out.println("d5lhna");
				active+="\n"+executedDisasters.get(i).toString() +"\n"+ executedDisasters.get(i).getTarget().toString() + "\n";
			}
			

		}
		s=s+past+"\n"+active+"\n";
		for(int i=0;i<x.size();i++)
		{
			if(x.get(i).getState().equals(CitizenState.DECEASED)) {
				int j=0;
				for(j=0;j<y.size();j++)
				{
					if (x.get(i).equals(y.get(j)))
						break;
				}
				if (j==y.size())
					s+="\n"+"The Citizen : " +x.get(i).getName()+"\n"+" in the location ("+x.get(i).getLocation().getX()+" , "+x.get(i).getLocation().getY()+") is dead";
			}
		}
		logTxt.setText(logTxt.getText()+"\n"+s);

	}

	public void NoOfCycles(int x) {
		String g = "Number Of Cycles "+"\n" +x ;
		cycles.setText(g);	
	}


	public void addTouniltlog (String info) {
		String h = "    Unit Log   "+"\n" + info+"\n";
		unitlogtxt.setText(h);
	}



	public void remonvelbutton(JButton x) {
		for(int i=0;i<AvailableUnits.getComponentCount();i++) {

			if(AvailableUnits.getComponent(i).equals(x))
			{
				//System.out.println("elilaelilaelilayasamrayasamaraellilayasamrah1"+"\n");

				AvailableUnits.remove(i);
				AvailableUnits.validate();
				AvailableUnits.repaint();
				x.repaint();
				x.validate();
			}
		
			
		}

	}
	public void remonvelbuttonfromresponding(JButton x) {
		for(int i=0;i<RespondingUnits.getComponentCount();i++) {

			if(RespondingUnits.getComponent(i).equals(x))
			{
				

				RespondingUnits.remove(i);
				RespondingUnits.validate();
				RespondingUnits.repaint();
				x.validate();
				x.repaint();
			}
		}

	}
	public void removefromtreatingunitpanel (JButton x) {
		for(int i=0;i<TreatingUnits.getComponentCount();i++) {

			if(TreatingUnits.getComponent(i).equals(x))
			{
				

				TreatingUnits.remove(i);
				TreatingUnits.validate();
				TreatingUnits.repaint();
				x.repaint();
				x.validate();
				
			}
			
		}

	}
	public void setIconImage(ImageIcon imageIcon) {
		// TODO Auto-generated method stub
		Image image=imageIcon.getImage(); 
		this.setIconImage(image);
	}




}
