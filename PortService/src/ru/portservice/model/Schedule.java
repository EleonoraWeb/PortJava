package ru.portservice.model;

import java.util.Date;

//Класс расписания
public class Schedule {
	//Наименование корабля
	private String shipName;	
	
	//Тип груза
	private CargoType cargoType;
	
	//Объем груза
	private int cargoValue;
	
	//Планируемая дата/время прибытия в порт
	private Date plannedArrivalTime;
	
	//Планируемая дата/время прибытия в порт
	private Date realArrivalTime;
	
	//Планируемое время стоянки в порту
	private int plannedUnloadingTimeDuration;
	
	//Задержка разгруки
	private int unloadingDelay;
	
	//Время начала разгрузки
	private Date startUnloadingTime;
	
	//Время окончания разгрузки
	private Date endUnloadingTime;
		
	///
	///геттеры и сеттеры для полей
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
		return "Наименование: " + shipName + 
				", Тип груза: " + cargoType +
				", Количество груза: " + cargoValue +
				", Дата прибытия: " + realArrivalTime +
				", Ожидание в очереди: " + waitingInQueue() +
				", Начало разгрузки: " + startUnloadingTime +
				", Длительность разгрузки: " + unloadingDuration();
	}
}