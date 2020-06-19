package model.units;

import exceptions.CannotTreatException;
import exceptions.IncompatibleTargetException;
import model.disasters.Collapse;
import model.disasters.Disaster;
import model.disasters.Fire;
import model.disasters.GasLeak;
import model.disasters.Infection;
import model.disasters.Injury;
import model.events.SOSResponder;
import model.events.WorldListener;
import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;
import model.people.CitizenState;
import simulation.Address;
import simulation.Rescuable;
import simulation.Simulatable;

public abstract class Unit implements Simulatable, SOSResponder {
	private String unitID;
	private UnitState state;
	private Address location;
	private Rescuable target;
	private int distanceToTarget;
	private int stepsPerCycle;
	private WorldListener worldListener;




	public Unit(String unitID, Address location, int stepsPerCycle,
			WorldListener worldListener) {
		this.unitID = unitID;
		this.location = location;
		this.stepsPerCycle = stepsPerCycle;
		this.state = UnitState.IDLE;
		this.worldListener = worldListener;
	}




	public void setWorldListener(WorldListener listener) {
		this.worldListener = listener;
	}

	public WorldListener getWorldListener() {
		return worldListener;
	}

	public UnitState getState() {
		return state;
	}

	public void setState(UnitState state) {
		this.state = state;
	}

	public Address getLocation() {
		return location;
	}

	public void setLocation(Address location) {
		this.location = location;
	}

	public String getUnitID() {
		return unitID;
	}

	public Rescuable getTarget() {
		return target;
	}

	public int getStepsPerCycle() {
		return stepsPerCycle;
	}

	public void setDistanceToTarget(int distanceToTarget) {
		this.distanceToTarget = distanceToTarget;
	}

	@Override
	public void respond(Rescuable r) throws IncompatibleTargetException, CannotTreatException {

		
		
		if((this instanceof Ambulance|| this instanceof DiseaseControlUnit) && !( r instanceof Citizen)) { 

			throw new IncompatibleTargetException(this, r, "medical unit can't respond to building");

		}	
		else	
			if((this instanceof FireTruck || this instanceof GasControlUnit || this instanceof Evacuator )&& !(r instanceof ResidentialBuilding))
			{
				throw new IncompatibleTargetException(this, r, "this unit can't respond to citizen ");
			}
        else if(r.getDisaster()==null) {


			throw  new CannotTreatException(this, r, "mfessh disaster");
			

		}


		else if(!canTreat(r)) {
		

			throw new CannotTreatException(this, r, "this target is safe");
			
		}

		if (target != null && state == UnitState.TREATING)
			reactivateDisaster();
		finishRespond(r);


	}



	public void reactivateDisaster() {
		Disaster curr = target.getDisaster();
		curr.setActive(true);
	}

	public void finishRespond(Rescuable r) {
		target = r;
		state = UnitState.RESPONDING;
		Address t = r.getLocation();
		distanceToTarget = Math.abs(t.getX() - location.getX())
				+ Math.abs(t.getY() - location.getY());

	}

	public abstract void treat();

	public void cycleStep() {
		if (state == UnitState.IDLE)
			return;
		if (distanceToTarget > 0) {
			distanceToTarget = distanceToTarget - stepsPerCycle;
			if (distanceToTarget <= 0) {
				distanceToTarget = 0;
				Address t = target.getLocation();
				worldListener.assignAddress(this, t.getX(), t.getY());
			}
		} else {
			state = UnitState.TREATING;
			treat();
		}
	}

	public void jobsDone() {
		target = null;
		state = UnitState.IDLE;

	}
	public boolean canTreat(Rescuable r) {
	
		if (r.getDisaster()==null)
			return false;
		

		if (this instanceof Ambulance &&!( (r.getDisaster()) instanceof Injury))
			return false;
		

		 if (this instanceof DiseaseControlUnit &&!( (r.getDisaster()) instanceof Infection)) {
		
			return false;}
		 

			if (this instanceof FireTruck &&!( (r.getDisaster()) instanceof Fire))
		{    
			return false;}
			
		 if (this instanceof GasControlUnit &&!( (r.getDisaster()) instanceof GasLeak))
			return false;
		

			if (this instanceof Evacuator &&!( (r.getDisaster()) instanceof Collapse))
			return false;
			




		 if(r instanceof ResidentialBuilding) {
			

			if(this instanceof FireTruck &&((ResidentialBuilding)r).getFireDamage()<=0)
				return false;
			

			if (this instanceof GasControlUnit &&((ResidentialBuilding)r).getGasLevel()<=0)
			return false;
			
			
			if (this instanceof Evacuator &&((ResidentialBuilding)r).getFoundationDamage()<=0) 
			{
				System.out.println(this +" "+r.getDisaster());
				return false;
			}
			

		}

		 else 
			 {		

		  if (this instanceof Ambulance && ((Citizen)r).getBloodLoss()<=0)
			return false;
			
		 if(this instanceof DiseaseControlUnit &&((Citizen)r).getToxicity()<=0) 	  


			return false;
			
			 }



		return true;
	}



	public String toString() {

		String s="";
		if(target!=null) {
			if (this instanceof Ambulance)
				s=	"UnitID: "+ unitID+"\n"+"Type: "+"Ambulance"+"\n"+"UnitState: "+ state+"\n"
						+"Location:"+location+"\n"+"Target: "+target.toString()+"\n"+"StepsPercycle: "+ stepsPerCycle+"\n";
			else if (this instanceof GasControlUnit)
				s=	"UnitID: "+ unitID+"\n"+"Type: "+"GasControlUnit"+"\n"+"UnitState: "+ state+"\n"
						+"Location:"+location+"\n"+"Target: "+target.toString()+"\n"+"StepsPercycle: "+ stepsPerCycle+"\n";
			else if(this instanceof DiseaseControlUnit)
				s=	"UnitID: "+ unitID+"\n"+"Type: "+"DiseaseControlUnit"+"\n"+"UnitState: "+ state+"\n"
						+"Location:"+location+"\n"+"Target: "+target.toString()+"\n"+"StepsPercycle: "+ stepsPerCycle+"\n";
			else if (this instanceof FireTruck)
				s=	"UnitID: "+ unitID+"\n"+"Type: "+"FireTruck"+"\n"+"UnitState: "+ state+"\n"
						+"Location:"+location+"\n"+"Target: "+target.toString()+"\n"+"StepsPercycle: "+ stepsPerCycle+"\n";
			else if(this instanceof Evacuator) {
				s=	"UnitID: "+ unitID+"\n"+"Type: "+"Evacuator"+"\n"+"UnitState: "+ state+"\n"
						+"Location:"+location+"\n"+"Target: "+target.toString()+"\n"+"StepsPercycle: "+ stepsPerCycle+"\n"+"Passengers: "+"\n"+"Size"+((Evacuator)this).getPassengers().size()+"\n";
				for(int i=0;i<((Evacuator)this).getPassengers().size();i++) {
					s=s+((Evacuator)this).getPassengers().get(i).toString()+"\n";

				}
			}}
		else
		{
			if (this instanceof Ambulance)
				s=	"UnitID: "+ unitID+"\n"+"Type: "+"Ambulance"+"\n"+"UnitState: "+ state+"\n"
						+"Location:"+location+"\n"+"Target: "+target+"\n"+"StepsPercycle: "+ stepsPerCycle+"\n";
			else if (this instanceof GasControlUnit)
				s=	"UnitID: "+ unitID+"\n"+"Type: "+"GasControlUnit"+"\n"+"UnitState: "+ state+"\n"
						+"Location:"+location+"\n"+"Target: "+target+"\n"+"StepsPercycle: "+ stepsPerCycle+"\n";
			else if(this instanceof DiseaseControlUnit)
				s=	"UnitID: "+ unitID+"\n"+"Type: "+"DiseaseControlUnit"+"\n"+"UnitState: "+ state+"\n"
						+"Location:"+location+"\n"+"Target: "+target+"\n"+"StepsPercycle: "+ stepsPerCycle+"\n";
			else if (this instanceof FireTruck)
				s=	"UnitID: "+ unitID+"\n"+"Type: "+"FireTruck"+"\n"+"UnitState: "+ state+"\n"
						+"Location:"+location+"\n"+"Target: "+target+"\n"+"StepsPercycle: "+ stepsPerCycle+"\n";
			else if(this instanceof Evacuator) {
				s=	"UnitID: "+ unitID+"\n"+"Type: "+"Evacuator"+"\n"+"UnitState: "+ state+"\n"
						+"Location:"+location+"\n"+"Target: "+target+"\n"+"StepsPercycle: "+ stepsPerCycle+"\n"+"Passengers: "+"\n"+"Size"+((Evacuator)this).getPassengers().size()+"\n";
				for(int i=0;i<((Evacuator)this).getPassengers().size();i++) {
					s=s+((Evacuator)this).getPassengers().get(i).toString()+"\n";

				}
			}

		}return s;
	}
}

