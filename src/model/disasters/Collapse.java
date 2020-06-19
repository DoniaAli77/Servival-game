package model.disasters;

import exceptions.BuildingAlreadyCollapsedException;
import exceptions.CitizenAlreadyDeadException;
import model.infrastructure.ResidentialBuilding;


public class Collapse extends Disaster {

	public Collapse(int startCycle, ResidentialBuilding target) {
		super(startCycle, target);

	}
	public void strike() throws BuildingAlreadyCollapsedException, CitizenAlreadyDeadException 

	{   if (this.getTarget() instanceof ResidentialBuilding && ((ResidentialBuilding) this.getTarget()).getStructuralIntegrity()==0 )
		throw new BuildingAlreadyCollapsedException(this ,"the building has already collapsed");
	else 
	{ 

		ResidentialBuilding target= (ResidentialBuilding)getTarget();	
		target.setFoundationDamage(target.getFoundationDamage()+10);
		super.strike();
	}
	}
	public void cycleStep()
	{
		ResidentialBuilding target= (ResidentialBuilding)getTarget();
		target.setFoundationDamage(target.getFoundationDamage()+10);
	}

}
