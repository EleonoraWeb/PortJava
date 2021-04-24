package ru.portservice.model;

import java.util.ArrayList;

public class Port {
	//—писок всех кранов в порту
	private final ArrayList<Crane> cranes;
	
	private static final int looseCargoCraneProductivity = 10;
	private static final int liquidCargoCraneProductivity = 10;
	private static final int containerCargoCraneProductivity = 10;
	
	// онструктор
	public Port() {
		cranes = new ArrayList<Crane>();
		cranes.add(new Crane(looseCargoCraneProductivity, CargoType.сыпучий));
		cranes.add(new Crane(liquidCargoCraneProductivity, CargoType.жидкий));
		cranes.add(new Crane(containerCargoCraneProductivity, CargoType.контейнер));
	}
	
	//геттер дл€ списка кранов
	public ArrayList<Crane> getCranes() {
		return cranes;
	}
	
	//ƒабавление крана в список
	public void addCrane(CargoType type) {		
		switch(type) {
		case сыпучий:
			cranes.add(new Crane(looseCargoCraneProductivity, type));
			break;
		case жидкий:
			cranes.add(new Crane(liquidCargoCraneProductivity, type));
			break;
		case контейнер:
			cranes.add(new Crane(containerCargoCraneProductivity, type));
			break;
		}
	}
	
	// оличество кранов по типу разгружаемого груза
	public int cranesOfType(CargoType type) {
		int count = 0;
		for(int i=0; i < cranes.size(); i++) {
			if(cranes.get(i).getCargoType() == type)
				count++;
		}
		return count;
	}
	
	public ArrayList<Crane> getCranes(CargoType type) {
		Crane crane1 = null;
		switch(type) {
		case сыпучий:
			crane1 = cranes.get(0);
			break;
		case жидкий:
			crane1 = cranes.get(1);
			break;
		case контейнер:
			crane1 = cranes.get(2);
			break;
		}
		
		for(int i=3; i < cranes.size(); i++) {
			if(cranes.get(i).getCargoType() != type) {
				continue;
			}
			
			if(cranes.get(i).getLastWorkFinished() == null ||
			   cranes.get(i).getLastWorkFinished().before(crane1.getLastWorkFinished())) {
				crane1 = cranes.get(i);
				if(crane1.getLastWorkFinished() == null)
					break;
			}
		}
		
		ArrayList<Crane> selected = new ArrayList<Crane>();
		selected.add(crane1);
		
		for(int i=0; i < cranes.size(); i++) {
			if(cranes.get(i).getCargoType() != type) {
				continue;
			}
			if(!cranes.get(i).equals(crane1) &&
				   (cranes.get(i).getLastWorkFinished() == null ||
				   (crane1.getLastWorkFinished() != null && 
				    cranes.get(i).getLastWorkFinished().equals(crane1.getLastWorkFinished()))))
				selected.add(cranes.get(i));
			
			if(selected.size() >= 2)
				break;
		}
		
		return selected;
	}
	
	//ѕроизводительность крана по типу разгружаемого груза
	public int getCraneProductivity(CargoType type) {
		switch(type) {
		case сыпучий:
			return looseCargoCraneProductivity;
		case жидкий:
			return liquidCargoCraneProductivity;
		case контейнер:
			return containerCargoCraneProductivity;
		default:
			return 0;
		}
	}

	public void reset() {
		for(int i=0; i < cranes.size(); i++) {
			cranes.get(i).setLastWorkFinished(null);
		}
	}
}
