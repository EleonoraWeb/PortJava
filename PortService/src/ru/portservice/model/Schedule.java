package ru.portservice.model;

import java.util.Date;

//����� ����������
public class Schedule {
	//������������ �������
	private String shipName;	
	
	//��� �����
	private CargoType cargoType;
	
	//����� �����
	private int cargoValue;
	
	//����������� ����/����� �������� � ����
	private Date plannedArrivalTime;
	
	//����������� ����/����� �������� � ����
	private Date realArrivalTime;
	
	//����������� ����� ������� � �����
	private int plannedUnloadingTimeDuration;
	
	//�������� ��������
	private int unloadingDelay;
	
	//����� ������ ���������
	private Date startUnloadingTime;
	
	//����� ��������� ���������
	private Date endUnloadingTime;
		
	///
	///������� � ������� ��� �����
	///
	public String getShipName() {
		return shipName;
	}
	
	public void setShipName(String value) {
		shipName = value;
	}
	
	public Date getPlannedArrivalTime() {
		return plannedArrivalTime;
	}
	
	public void setPlannedArrivalTime(Date value) {
		plannedArrivalTime = value;
	}
	
	public CargoType getCargoType() {
		return cargoType;
	}
	
	public void setCargoType(CargoType value) {
		cargoType = value;
	}
	
	public int getCargoValue() {
		return cargoValue;
	}
	
	public void setCargoValue(int value) {
		cargoValue = value;
	}

	public Date getRealArrivalTime() {
		return realArrivalTime;
	}

	public void setRealArrivalTime(Date realArrivalTime) {
		this.realArrivalTime = realArrivalTime;
	}

	public int getUnloadingDelay() {
		return unloadingDelay;
	}

	public void setUnloadingDelay(int unloadingDelay) {
		this.unloadingDelay = unloadingDelay;
	}

	public int getPlannedUnloadingTimeDuration() {
		return plannedUnloadingTimeDuration;
	}

	public void setPlannedUnloadingTimeDuration(int plannedUnloadingTimeDuration) {
		this.plannedUnloadingTimeDuration = plannedUnloadingTimeDuration;
	}

	public Date getStartUnloadingTime() {
		return startUnloadingTime;
	}

	public void setStartUnloadingTime(Date startUnloadingTime) {
		this.startUnloadingTime = startUnloadingTime;
	}

	public Date getEndUnloadingTime() {
		return endUnloadingTime;
	}

	public void setEndUnloadingTime(Date endUnloadingTime) {
		this.endUnloadingTime = endUnloadingTime;
	}
	
	public String waitingInQueue() {
		long duration = Helper.getDurationInMinutes(realArrivalTime, startUnloadingTime);
		return Helper.minutesToString(duration);
	}
	
	public String unloadingDuration() {
		long duration = Helper.getDurationInMinutes(startUnloadingTime, endUnloadingTime);
		return Helper.minutesToString(duration);
	}
	
	@Override
	public String toString() {
		return "������������: " + shipName + 
				", ��� �����: " + cargoType +
				", ���������� �����: " + cargoValue +
				", ���� ��������: " + realArrivalTime +
				", �������� � �������: " + waitingInQueue() +
				", ������ ���������: " + startUnloadingTime +
				", ������������ ���������: " + unloadingDuration();
	}
}