package model;

import java.util.Calendar;

public class Zeit {
	
	private int day;
	private int month;
	private int year;
	private int hour;
	private int minute;
	private int second;
	
	private Calendar calendar;
	


	public String getAktualTime() {
		calendar = Calendar.getInstance();
		hour = calendar.get(Calendar.HOUR);
		minute = calendar.get(Calendar.MINUTE);
		second = calendar.get(Calendar.SECOND);
		String time = hour + ":" + minute + ":" + second;
		return time;
	}
	
	public String getAktualDate() {
		calendar = Calendar.getInstance();
		day = calendar.get(Calendar.DAY_OF_MONTH);
		month = calendar.get(Calendar.MONTH) + 1;
		year = calendar.get(Calendar.YEAR);
		String date = day + "." + month + "." + year;
		return date;
		
	}
	
	public void startSimulationTime(int hour, int minute, int second) {
		
		//Schleife
		
		
		
	}
	

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
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

	public Calendar getCalendar() {
		return calendar;
	}

	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}
	
	
	
	
	

}
