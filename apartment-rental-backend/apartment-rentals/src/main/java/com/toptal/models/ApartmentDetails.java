package com.toptal.models;

public class ApartmentDetails extends BaseModelDetail {

	private Long apartmentId;
	private String name;
	private String description;
	private int numberOfRooms;
	private double longitude;
	private double latitude;
	private boolean state;
	private String address;
	private double floorArea;
	private double price;
	private boolean enabled;
	public Long getApartmentId() {
		return apartmentId;
	}
	public void setApartmentId(Long apartmentId) {
		this.apartmentId = apartmentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getNumberOfRooms() {
		return numberOfRooms;
	}
	public void setNumberOfRooms(int numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public boolean getState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getFloorArea() {
		return floorArea;
	}
	public void setFloorArea(double floorArea) {
		this.floorArea = floorArea;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	
}
