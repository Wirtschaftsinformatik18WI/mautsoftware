package controller;

import java.util.TimerTask;

public class Simulationszeit extends TimerTask {
	
	private String simulTime;
	private int hour;
	private int minute;
	private int second;

	@Override
	public void run() {
		
		if(second < 59) {
			addSec();
		} else {
			setSecond(0);
			if(minute < 59) {
				addMin();
			} else {
				setMinute(0);
				if(hour < 23) {
					addHour();
				} else
					setHour(0);
			}
		}
		
		simulTime = hour + ":" + minute + ":" + second; 
		System.out.println(simulTime);
		
	}
	
	
	
	public void addSec() {
		second = getSecond() + 1;
		
	}
	
	public void addMin() {
		minute = getMinute() + 1;
		
	}

	public void addHour() {
		hour = getHour() + 1;
	
	}
	
	
	
	
	public String getSimulTime() {
		return simulTime;
	}
	
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public int getMinute() {
		return minute;
	}
	public void setMinute(int minute) {
		this.minute = minute;
	}
	public int getSecond() {
		return second;
	}
	public void setSecond(int second) {
		this.second = second;
	}	
}