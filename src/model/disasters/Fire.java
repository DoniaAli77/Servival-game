package model.disasters;

import exceptions.BuildingAlreadyCollapsedException;
import exceptions.CitizenAlreadyDeadException;
import model.infrastructure.ResidentialBuilding;


public class Fire extends Disaster {

	public Fire(int startCycle, ResidentialBuilding target) {
		super(startCycle, target);

	}
	@Override
	public void strike() throws BuildingAlreadyCollapsedException, CitizenAlreadyDeadException
	{    if (this.getTarget() instanceof ResidentialBuilding && ((ResidentialBuilding) this.getTarget()).getStructuralIntegrity()==0 )
		throw new BuildingAlreadyCollapsedException(this ,"the building has already collapsed");
	else { 

		ResidentialBuilding target= (ResidentialBuilding)getTarget();
		target.setFireDamage(target.getFireDamage()+10);
		super.strike();
	}
	}
	@Override
	public void cycleStep() {
		ResidentialBuilding target= (ResidentialBuilding)getTarget();
		target.setFireDamage(target.getFireDamage()+10);

	}


}
