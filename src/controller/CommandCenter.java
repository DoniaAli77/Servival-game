package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicColorChooserUI;

import exceptions.CannotTreatException;
import exceptions.IncompatibleTargetException;
import exceptions.SimulationException;
import model.disasters.Collapse;
import model.disasters.Fire;
import model.disasters.GasLeak;
import model.events.SOSListener;
import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;
import model.people.CitizenState;
import model.units.Ambulance;
import model.units.DiseaseControlUnit;
import model.units.Evacuator;
import model.units.FireTruck;
import model.units.GasControlUnit;
import model.units.Unit;
import model.units.UnitState;
import simulation.Rescuable;
import simulation.Simulator;
import view.GameView;
import view.NextCycleButton;
import view.TargetJButton;
import view.UnitJbutton;
import view.buildingJButton;



public class CommandCenter implements SOSListener,ActionListener{

	private GameView gameView;
	private Simulator engine;
	private ArrayList<ResidentialBuilding> visibleBuildings;
	private ArrayList<Citizen> visibleCitizens;
	private ArrayList<Citizen> deadCitizens;
	private ArrayList<JButton> AvailableUnitsbtns;
	private ArrayList<JButton> TreatingUnitsbtns;
	private ArrayList<JButton> RespondingUnitsbtns;
	private TargetJButton [][] targets;
	private ArrayList<Unit> respondingUnits;
	private ArrayList<Unit> treatingUnits;
	private boolean action=false;
	private NextCycleButton nextCycle;
	@SuppressWarnings("unused")
	private ArrayList<Unit> emergencyUnits;
	private int z=0 ;
	private	ArrayList<Citizen> Citizens;
	private int btn;
	private JButton btnbta3na;
	private JLabel jambulance;
	private JLabel jdisseasecontrolunit;
	private JLabel jfiretruck;
	private JLabel jgascontrolunit;
	private JLabel jevacuator;
	private ArrayList<JPanel>[][] because=new ArrayList[10][10] ;
	private ArrayList<JButton>[][] because2=new ArrayList[10][10] ;

	public CommandCenter() throws Exception {
		gameView=new GameView();
		treatingUnits=new ArrayList<>();
		respondingUnits =new ArrayList<>();
		AvailableUnitsbtns=new ArrayList<>();
		TreatingUnitsbtns=new ArrayList<>();
		RespondingUnitsbtns=new ArrayList<>();
		deadCitizens = new ArrayList<>() ;
		targets=new TargetJButton[10][10];
		gameView=new GameView();
		engine = new Simulator(this);
		visibleBuildings = new ArrayList<ResidentialBuilding>();
		visibleCitizens = new ArrayList<Citizen>();
		emergencyUnits = engine.getEmergencyUnits();
		nextCycle= gameView.getNextCycle();
		nextCycle.addActionListener(this);
		Citizens=engine.getCitizens();
		//gameView.setIconImage(new ImageIcon("CreativeCourageousAphid-small1"));
		for(int i=0;i<because.length;i++) {
			for(int j=0;j<because.length;j++) {
				because[i][j]=new ArrayList<>();
				because2[i][j]=new ArrayList<>();		

			}
		}
		
		for(final Unit unit :emergencyUnits) {

			if(unit instanceof Ambulance) 
			{

				JButton ambbtn=new UnitJbutton("Ambulance",new ImageIcon("a.png"));
				ambbtn.setContentAreaFilled(false);
				ambbtn.setBorderPainted(true);
				ambbtn.setHorizontalTextPosition(JButton.CENTER);
				ambbtn.setVerticalTextPosition(JButton.NORTH);
				ambbtn.setVisible(true);
				AvailableUnitsbtns.add(ambbtn);
				ambbtn.addActionListener(this);
				ambbtn.setMinimumSize(new Dimension(20, 20));
				ambbtn.setMaximumSize(new Dimension(20, 20));
				ambbtn.setPreferredSize(new Dimension(20, 20));
				gameView.addbtns(ambbtn);
			}
			if(unit instanceof FireTruck) 
			{

				JButton FTKbtn=new UnitJbutton("Fire Truck",new ImageIcon("firetruck.png"));
				FTKbtn.setContentAreaFilled(false);
				FTKbtn.setBorderPainted(true);
				FTKbtn.setHorizontalTextPosition(JButton.CENTER);
				FTKbtn.setVerticalTextPosition(JButton.NORTH);
				AvailableUnitsbtns.add(FTKbtn);
				FTKbtn.addActionListener(this);
				FTKbtn.setMinimumSize(new Dimension(20, 20));
				FTKbtn.setMaximumSize(new Dimension(20, 20));

				FTKbtn.setPreferredSize(new Dimension(20, 20));
				gameView.addbtns(FTKbtn);

			}
			if(unit instanceof Evacuator) 
			{

				JButton	EVCbtn=new UnitJbutton("Evacuator",new ImageIcon("evpng.png"));
				EVCbtn.setContentAreaFilled(false);
				EVCbtn.setBorderPainted(true);
				EVCbtn.setHorizontalTextPosition(JButton.CENTER);
				EVCbtn.setVerticalTextPosition(JButton.NORTH);
				AvailableUnitsbtns.add(EVCbtn);
				EVCbtn.addActionListener(this);
				EVCbtn.setPreferredSize(new Dimension(20, 20));
				gameView.addbtns(EVCbtn);

			}
			if (unit instanceof DiseaseControlUnit) 
			{
				JButton DCUbtn =new UnitJbutton("Diseasecontrolunit",new ImageIcon("DiseaseControluni.png"));
				DCUbtn.setContentAreaFilled(false);
				DCUbtn.setBorderPainted(true);
				DCUbtn.setHorizontalTextPosition(JButton.CENTER);
				DCUbtn.setVerticalTextPosition(JButton.NORTH);
				AvailableUnitsbtns.add(DCUbtn);
				DCUbtn.addActionListener(this);
				DCUbtn.setPreferredSize(new Dimension(20, 20));
				gameView.addbtns(DCUbtn);

			}
			if (unit instanceof GasControlUnit) 
			{
				JButton	GASbtn=new UnitJbutton("Gascontrolunit",new ImageIcon("gascontrolunitt.png"));
				GASbtn.setContentAreaFilled(false);
				GASbtn.setBorderPainted(true);
				GASbtn.setHorizontalTextPosition(JButton.CENTER);
				GASbtn.setVerticalTextPosition(JButton.NORTH);
				AvailableUnitsbtns.add(GASbtn);
				GASbtn.addActionListener(this);
				GASbtn.setPreferredSize(new Dimension(20, 20));
				gameView.addbtns(GASbtn);
			}

		}





		for( int i =0 ; i<10 ; i++) {
			for(int j=0 ; j<10 ; j++) {
				TargetJButton a = new TargetJButton(("\n"+"\n"+i+ "," +j)) ;
				GridLayout b= new GridLayout(1,2);
				a.setLayout(b);
				JPanel x=new JPanel();
				JPanel y =new JPanel();

//				if (i%2==0&&j%2==0) {
//					
//					a.setBackground(Color.white);
//					x.setBackground(Color.white);
//					y.setBackground(Color.white);
//				}
//				else {
					
					a.setBackground(Color.black);		
					x.setBackground(Color.black);
					y.setBackground(Color.black);
				//}

				a.setPreferredSize(new Dimension(7, 7));
				a.add(x);
				a.add(y);
				a.setBorder(BorderFactory.createLineBorder(Color.magenta, 1));
				a.setIcon(new ImageIcon("citizengdeda.png"));
				y.setLayout(new GridLayout(5,1));
				JButton arwa =new JButton();
				because2[i][j].add(arwa);
				x.setLayout(new GridLayout(1,1));
				x.add(arwa);
				arwa.setContentAreaFilled(false);
				arwa.setBorderPainted(false);
				because[i][j].add(x);
				because[i][j].add(y);
				gameView.addtorescuepanel(a) ;
				targets[i][j] = a ; 
				a.addActionListener(this);


			}

		}
		for(final Unit unit :emergencyUnits) {
			if (unit instanceof Ambulance) {
				jambulance=new JLabel(new ImageIcon("amb.png"));
				because[0][0].get(1).add(jambulance);
			}
			else if(unit instanceof DiseaseControlUnit) {
				jdisseasecontrolunit=new JLabel(new ImageIcon("disbtn.png"));
				because[0][0].get(1).add(jdisseasecontrolunit);

			}
			else if(unit instanceof FireTruck) {
				jfiretruck=new JLabel(new ImageIcon("firebtn.png"));
				because[0][0].get(1).add(jfiretruck);
			}
			else if (unit instanceof Evacuator) {

				jevacuator=new JLabel(new ImageIcon("evnbtn.png"));
				because[0][0].get(1).add(jevacuator);
			}
			else {
				jgascontrolunit=new JLabel(new ImageIcon("gasbtn.png"));
				because[0][0].get(1).add(jgascontrolunit);
			}
		}




		for(int i=0;i<engine.getCitizens().size();i++)
		{   
			targets[engine.getCitizens().get(i).getLocation().getX()][engine.getCitizens().get(i).getLocation().getY()].setNo("citizen");
			because2[engine.getCitizens().get(i).getLocation().getX()][engine.getCitizens().get(i).getLocation().getY()].get(0).setIcon(new ImageIcon("citizengdeeda.png"));

		}

		for(int i=0;i<engine.getBuildings().size();i++)
		{   
			targets[engine.getBuildings().get(i).getLocation().getX()][engine.getBuildings().get(i).getLocation().getY()].setNo("building");
			because2[engine.getBuildings().get(i).getLocation().getX()][engine.getBuildings().get(i).getLocation().getY()].get(0).setIcon(new ImageIcon("building.png"));

		}




		gameView.setVisible(true);
	}

	public void receiveSOSCall(Rescuable r) {

		if (r instanceof ResidentialBuilding) {

			if (!visibleBuildings.contains(r))
				visibleBuildings.add((ResidentialBuilding) r);

		} else {

			if (!visibleCitizens.contains(r))
				visibleCitizens.add((Citizen) r);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton x=(JButton)e.getSource();


		if(x instanceof NextCycleButton ) {
			try {
				if(engine.checkGameOver())
				{

					gameView.ShowMessage(this);
				}
				else {
					engine.nextCycle();
					z++;
					gameView.NoOfCycles(z);
					gameView.addcasaulites(engine.calculateCasualties());

					for(int i=0;i<emergencyUnits.size();i++)
					{
						if(emergencyUnits.get(i).getState().equals(UnitState.TREATING)) 
						{
							gameView.remonvelbuttonfromresponding(AvailableUnitsbtns.get(i));
							if(emergencyUnits.get(i) instanceof Ambulance) {
								for(int l=0;l<targets.length;l++) {
									for(int m=0;m<targets.length;m++) {
										if(emergencyUnits.get(i).getTarget().getLocation().getX()==l&&emergencyUnits.get(i).getTarget().getLocation().getY()==m)
										{
											removeunitsfromcells(because[emergencyUnits.get(i).getLocation().getX()][emergencyUnits.get(i).getLocation().getY()].get(1), jambulance);

											because[l][m].get(1).add(jambulance);
											because[l][m].get(1).repaint();
											because[l][m].get(1).validate();

										}

									}

								}
							}
							else if(emergencyUnits.get(i) instanceof FireTruck) {
								for(int l=0;l<targets.length;l++) {
									for(int m=0;m<targets.length;m++) {
										if(emergencyUnits.get(i).getTarget().getLocation().getX()==l&&emergencyUnits.get(i).getTarget().getLocation().getY()==m)
										{
											removeunitsfromcells(because[emergencyUnits.get(i).getLocation().getX()][emergencyUnits.get(i).getLocation().getY()].get(1), jfiretruck);

											because[l][m].get(1).add(jfiretruck);
											because[l][m].get(1).repaint();
											because[l][m].get(1).validate();

										}

									}

								}
							}
							else if(emergencyUnits.get(i) instanceof DiseaseControlUnit) {
								for(int l=0;l<targets.length;l++) {
									for(int m=0;m<targets.length;m++) {
										if(emergencyUnits.get(i).getTarget().getLocation().getX()==l&&emergencyUnits.get(i).getTarget().getLocation().getY()==m)
										{
											removeunitsfromcells(because[emergencyUnits.get(i).getLocation().getX()][emergencyUnits.get(i).getLocation().getY()].get(1), jdisseasecontrolunit);

											because[l][m].get(1).add(jdisseasecontrolunit);
											because[l][m].get(1).repaint();
											because[l][m].get(1).validate();

										}

									}

								}
							}
							else if(emergencyUnits.get(i) instanceof GasControlUnit) {
								for(int l=0;l<targets.length;l++) {
									for(int m=0;m<targets.length;m++) {
										if(emergencyUnits.get(i).getTarget().getLocation().getX()==l&&emergencyUnits.get(i).getTarget().getLocation().getY()==m)
										{
											removeunitsfromcells(because[emergencyUnits.get(i).getLocation().getX()][emergencyUnits.get(i).getLocation().getY()].get(1), jgascontrolunit);

											because[l][m].get(1).add(jgascontrolunit);
											because[l][m].get(1).repaint();
											because[l][m].get(1).validate();

										}

									}

								}
							}
							else if(emergencyUnits.get(i) instanceof Evacuator) {
								for(int l=0;l<targets.length;l++) {
									for(int m=0;m<targets.length;m++) {
										if(emergencyUnits.get(i).getTarget().getLocation().getX()==l&&emergencyUnits.get(i).getTarget().getLocation().getY()==m)
										{
											removeunitsfromcells(because[emergencyUnits.get(i).getLocation().getX()][emergencyUnits.get(i).getLocation().getY()].get(1), jevacuator);

											because[l][m].get(1).add(jevacuator);
											because[l][m].get(1).repaint();
											because[l][m].get(1).validate();

										}

									}

								}
							}


							gameView.addtotreatingpanel(AvailableUnitsbtns.get(i));


						}
					     if (emergencyUnits.get(i).getState().equals(UnitState.IDLE))
						{
							gameView.removefromtreatingunitpanel(AvailableUnitsbtns.get(i));
							gameView.remonvelbuttonfromresponding(AvailableUnitsbtns.get(i));
							gameView.addbtns((AvailableUnitsbtns.get(i)));
						}
					    if (emergencyUnits.get(i).getState().equals(UnitState.RESPONDING)) {
							gameView.removefromtreatingunitpanel(AvailableUnitsbtns.get(i));
							gameView.remonvelbutton(AvailableUnitsbtns.get(i));
							gameView.addtorespobdingpanel((AvailableUnitsbtns.get(i)));
							
						}

					}




					gameView.addLog(engine.getExecutedDisasters(),engine.getCitizens(),deadCitizens ,z );
					for (int i=0;i<Citizens.size();i++) {
						if((Citizens.get(i).getBloodLoss()>0||Citizens.get(i).getToxicity()>0)&&Citizens.get(i).getState()!=CitizenState.DECEASED) {

							because2[Citizens.get(i).getLocation().getX()][Citizens.get(i).getLocation().getY()].get(0).setIcon(new ImageIcon("injured.png"));
						}
						else if((Citizens.get(i).getBloodLoss()<=0||Citizens.get(i).getToxicity()<=0)&&Citizens.get(i).getState()!=CitizenState.DECEASED)
							because2[Citizens.get(i).getLocation().getX()][Citizens.get(i).getLocation().getY()].get(0).setIcon(new ImageIcon("citizengdeeda.png"));

						if (Citizens.get(i).getState()==CitizenState.DECEASED )
						{
							//targets[Citizens.get(i).getLocation().getX()][Citizens.get(i).getLocation().getY()].setNo("citizen");
							for(int j=0;j<engine.getBuildings().size();j++) {
								if(!(engine.getBuildings().get(j).getLocation().getX()==Citizens.get(i).getLocation().getX())&&!(engine.getBuildings().get(j).getLocation().getY()==Citizens.get(i).getLocation().getY()))
								{	
									because2[Citizens.get(i).getLocation().getX()][Citizens.get(i).getLocation().getY()].get(0).setIcon(new ImageIcon("Dead_citizen2.png"));
								}
							}
							deadCitizens.add(Citizens.get(i));


						}
					}
					for(int i=0;i<engine.getBuildings().size();i++) {
						if(engine.getBuildings().get(i).getDisaster()==null) {
							because2[engine.getBuildings().get(i).getLocation().getX()][engine.getBuildings().get(i).getLocation().getY()].get(0).setIcon(new ImageIcon("building.png"));
						}

						
					   else if(engine.getBuildings().get(i).getDisaster() instanceof  Collapse) {
							because2[engine.getBuildings().get(i).getLocation().getX()][engine.getBuildings().get(i).getLocation().getY()].get(0).setIcon(new ImageIcon("collapsedbuildng2.png"));


						}

						else if(engine.getBuildings().get(i) .getDisaster() instanceof Fire) {

							because2[engine.getBuildings().get(i).getLocation().getX()][engine.getBuildings().get(i).getLocation().getY()].get(0).setIcon(new ImageIcon("fired2.gif"));
						}
						else if(engine.getBuildings().get(i) .getDisaster() instanceof GasLeak) {

							because2[engine.getBuildings().get(i).getLocation().getX()][engine.getBuildings().get(i).getLocation().getY()].get(0).setIcon(new ImageIcon("gass5.gif"));
						}
						if((engine.getBuildings().get(i).getStructuralIntegrity()<=0)) {


							because2[engine.getBuildings().get(i).getLocation().getX()][engine.getBuildings().get(i).getLocation().getY()].get(0).setIcon(new ImageIcon("SickQuarterlyKoodoo-small.gif"));

						}




					}

				}
			} catch (SimulationException e1) {

				e1.printStackTrace();
			}

		}

		else if (x instanceof UnitJbutton) 
		{
			for(int i=0;i<this.AvailableUnitsbtns.size();i++) {
				if (AvailableUnitsbtns.get(i).equals(x)) {
					btnbta3na=AvailableUnitsbtns.get(i);



					btn=i;
					action=true;
					gameView.addTouniltlog(engine.getEmergencyUnits().get(i).toString());
					break;
				}


			}

		}
		else if (x instanceof TargetJButton ) {
			if (x instanceof TargetJButton  && ((TargetJButton) x).getNo().equals("building"))
			{	
				for(int i=0;i<10;i++){
					for(int j=0;j<10;j++) {
						if(targets[i][j].equals(x)) {
							for(int k=0;k<engine.getBuildings().size();k++) {
								if(engine.getBuildings().get(k).getLocation().getX()==i&&engine.getBuildings().get(k).getLocation().getY()==j) {
									gameView.addinformation(engine.getBuildings().get(k).toString());
									if(action) {
										try {



											action=false;
											for(int btn=0;btn<AvailableUnitsbtns.size();btn++) {

												if(AvailableUnitsbtns.get(btn).equals(btnbta3na))
												{ 

													emergencyUnits.get(btn).respond(engine.getBuildings().get(k));
												}
											}
											//	respondingUnits.get(respondingUnits.size()-1).respond(engine.getBuildings().get(k));
										} catch (IncompatibleTargetException e1) {

											//returnn(btn);
											// TODO Auto-generated catch block
											JOptionPane.showMessageDialog( gameView," This unit goes to citizen only ");
											return;


										} catch (CannotTreatException e1) {
											//returnn(btn);
											// TODO Auto-generated catch block
											JOptionPane.showMessageDialog(gameView, " el building  sleem ");
											return;

										}
										gameView.removefromtreatingunitpanel(btnbta3na);
										gameView.remonvelbutton(btnbta3na);										
										gameView.addtorespobdingpanel(btnbta3na);
										RespondingUnitsbtns.add(btnbta3na);
										//respondingUnits.add(arg0)

										//getbutton(btn);
									}
									break;
								}

							}

						}
					}
				}

			}
			else  {

				for(int i=0;i<10;i++){
					for(int j=0;j<10;j++) {
						if(targets[i][j].equals(x)) {
							for(int k=0;k<engine.getCitizens().size();k++) {

								if(engine.getCitizens().get(k).getLocation().getX()==i&&engine.getCitizens().get(k).getLocation().getY()==j) {

									gameView.addinformation(engine.getCitizens().get(k).toString());
									if(action) {
										try {
											action=false;
											for(int btn=0;btn<AvailableUnitsbtns.size();btn++) {
												if(AvailableUnitsbtns.get(btn).equals(btnbta3na))
												{											

													emergencyUnits.get(btn).respond(engine.getCitizens().get(k));
												}
											}

										}
										catch (IncompatibleTargetException e1) {


											JOptionPane.showMessageDialog(gameView, "da byro7 L building ");
											return;

										} 	
										catch (CannotTreatException e1) {

											JOptionPane.showMessageDialog(gameView, " el citizen kwyes mat2l2sh 3leeh :) <3 ");
											return;

										}



										gameView.remonvelbutton(btnbta3na);
										gameView.addtorespobdingpanel(btnbta3na);




									}
									break;
								}

							}

						}
					}
				}
			}
		}
	}

	public GameView getGameView() {
		return gameView;
	}

	public Simulator getEngine() {
		return engine;
	}

	public ArrayList<ResidentialBuilding> getVisibleBuildings() {
		return visibleBuildings;
	}

	public ArrayList<Citizen> getVisibleCitizens() {
		return visibleCitizens;
	}

	public ArrayList<JButton> getAvailableUnitsbtns() {
		return AvailableUnitsbtns;
	}



	public ArrayList<JButton> getTreatingUnitsbtns() {
		return TreatingUnitsbtns;
	}

	public ArrayList<JButton> getRespondingUnitsbtns() {
		return RespondingUnitsbtns;
	}

	public JButton[][] getTargets() {
		return targets;
	}

	public ArrayList<Unit> getEmergencyUnits() {
		return emergencyUnits;
	}

	public static void main (String[]args) throws Exception {
		CommandCenter x=new CommandCenter();
	}
	public static void removeunitsfromcells(JPanel y,JLabel unit) {
		for(int i=0;i<y.getComponentCount();i++) {

			if(y.getComponent(i).equals(unit))
			{


				y.remove(i);
				y.validate();
				y.repaint();
				unit.repaint();
				unit.validate();
			}
		}
	}
}
