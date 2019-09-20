package com.toptal.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.lang.Nullable;

@Entity
@Table(name = "APARTMENT")
public class Apartment extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 3820800548095249239L;

	@Id
	@Column(name = "APARTMENTID")
	@GeneratedValue
	private long apartmentId;
	private String name;
	private String description;
	private int numberOfRooms;
	private double longitude;
	private double latitude;
	private boolean state;
	private double price;
	private String address;
	private double floorArea;
	@Column(columnDefinition = "boolean default true")
	private boolean enabled;

	@Nullable
	@ManyToOne(targetEntity = UserDetail.class, fetch = FetchType.LAZY)
	private UserDetail realtor;

	public long getApartmentId() {
		return apartmentId;
	}

	public void setApartmentId(long apartmentId) {
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

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public UserDetail getRealtor() {
		return realtor;
	}

	public void setRealtor(UserDetail realtor) {
		this.realtor = realtor;
	}

}
