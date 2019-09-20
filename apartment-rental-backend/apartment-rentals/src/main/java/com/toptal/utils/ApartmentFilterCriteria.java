package com.toptal.utils;

public class ApartmentFilterCriteria {

	private Double minSize;
	private Double maxSize;
	private Double minPrice;
	private Double maxPrice;
	private Integer numberOfRooms;

	public Double getMinSize() {
		return minSize;
	}

	public void setMinSize(Double minSize) {
		this.minSize = minSize;
	}

	public Double getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(Double maxSize) {
		this.maxSize = maxSize;
	}

	public Double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}

	public Double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}

	public Integer getNumberOfRooms() {
		return numberOfRooms;
	}

	public void setNumberOfRooms(Integer numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}

}
