package ru.portservice.model;

import java.util.ArrayList;

public class Port {
	//������ ���� ������ � �����
	private final ArrayList<Crane> cranes;
	
	private static final int looseCargoCraneProductivity = 10;
	private static final int liquidCargoCraneProductivity = 10;
	private static final int containerCargoCraneProductivity = 10;
	
	//�����������
	public Port() {
		cranes = new ArrayList<Crane>();
		cranes.add(new Crane(looseCargoCraneProductivity, CargoType.�������));
		cranes.add(new Crane(liquidCargoCraneProductivity, CargoType.������));
		cranes.add(new Crane(containerCargoCraneProductivity, CargoType.���������));
	}
	
	//������ ��� ������ ������
	public ArrayList<Crane> getCranes() {
		return cranes;
	}
	
	//���������� ����� � ������
	public void addCrane(CargoType type) {		
		switch(type) {
		case �������:
			cranes.add(new Crane(looseCargoCraneProductivity, type));
			break;
		case ������:
			cranes.add(new Crane(liquidCargoCraneProductivity, type));
			break;
		case ���������:
			cranes.add(new Crane(containerCargoCraneProductivity, type));
			break;
		}
	}
	
	//���������� ������ �� ���� ������������� �����
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
		case �������:
			crane1 = cranes.get(0);
			break;
		case ������:
			crane1 = cranes.get(1);
			break;
		case ���������:
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
	
	//������������������ ����� �� ���� ������������� �����
	public int getCraneProductivity(CargoType type) {
		switch(type) {
		case �������:
			return looseCargoCraneProductivity;
		case ������:
			return liquidCargoCraneProductivity;
		case ���������:
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
