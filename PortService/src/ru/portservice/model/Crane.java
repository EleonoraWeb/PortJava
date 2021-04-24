package ru.portservice.model;

import java.util.Date;

//����� �����
public class Crane {
	//������������������ �� ������
	private int productivity;
	//��� �����, ������� ����� ���������� ����
	private CargoType cargoType;
	
	private Date lastWorkFinished;
	
	//�����������
	public Crane(int productivity, CargoType cargoType) {
		this.productivity = productivity;
		this.cargoType = cargoType;
	}
	
	//������ ������������������
	public int getProductivity() {
		return productivity;
	}
	
	//������ ��� �����
	public CargoType getCargoType() {
		return cargoType;
	}

	public Date getLastWorkFinished() {
		return lastWorkFinished;
	}

	public void setLastWorkFinished(Date lastWorkFinished) {
		this.lastWorkFinished = lastWorkFinished;
	}
}
