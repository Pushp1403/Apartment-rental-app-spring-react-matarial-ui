package com.toptal.utils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.toptal.entities.Apartment;
import com.toptal.models.ApartmentDetails;

@Component
@Primary
public class ApartmentDetailsConverterUtil {

	public Apartment convertApartmentModelToApartmentEntity(ApartmentDetails details) {
		Apartment apartment = new Apartment();
		if (null != details.getApartmentId()) {
			apartment.setApartmentId(details.getApartmentId());
			apartment.setEnabled(details.getEnabled());
		} else {
			apartment.setEnabled(true);
		}
		this.updateTimeStamps(details, apartment);
		apartment.setAddress(details.getAddress());
		apartment.setLongitude(details.getLongitude());
		apartment.setDescription(details.getDescription());
		apartment.setLatitude(details.getLatitude());
		apartment.setName(details.getName());
		apartment.setNumberOfRooms(details.getNumberOfRooms());
		apartment.setState(details.getState());
		apartment.setFloorArea(details.getFloorArea());
		apartment.setPrice(details.getPrice());
		return apartment;
	}

	public ApartmentDetails convertApartmentEntityToApartmentDetailsModel(Apartment details) {
		ApartmentDetails apartment = new ApartmentDetails();
		apartment.setApartmentId(details.getApartmentId());
		apartment.setCreatedAt(details.getCreatedAt());
		apartment.setCreatedBy(details.getCreatedBy());
		apartment.setUpdatedAt(details.getUpdatedAt());
		apartment.setUpdatedBy(details.getUpdatedBy());
		apartment.setAddress(details.getAddress());
		apartment.setLongitude(details.getLongitude());
		apartment.setDescription(details.getDescription());
		apartment.setLatitude(details.getLatitude());
		apartment.setName(details.getName());
		apartment.setNumberOfRooms(details.getNumberOfRooms());
		apartment.setState(details.isState());
		apartment.setFloorArea(details.getFloorArea());
		apartment.setPrice(details.getPrice());
		apartment.setEnabled(details.isEnabled());
		return apartment;
	}

	private void updateTimeStamps(ApartmentDetails details, Apartment apartment) {
		if (details.getApartmentId() == null) {
			apartment.setCreatedAt(new Timestamp(new Date().getTime()));
			apartment.setCreatedBy(details.getCreatedBy());
		} else {
			apartment.setCreatedAt(details.getCreatedAt());
			apartment.setCreatedBy(details.getCreatedBy());
			apartment.setUpdatedAt(new Timestamp(new Date().getTime()));
			apartment.setUpdatedBy(details.getUpdatedBy());
		}
	}

	public String validateApartmentDetails(ApartmentDetails details) {
		StringBuilder sb = new StringBuilder(Constants.BLANK_STRING);
		if (details.getAddress() == null || details.getAddress().trim().equals(Constants.BLANK_STRING)
				|| details.getDescription() == null || details.getDescription().trim().equals(Constants.BLANK_STRING)
				|| details.getFloorArea() == 0.0 || details.getLatitude() == 0.0 || details.getLongitude() == 0.0
				|| details.getName() == null || details.getName().trim().equals(Constants.BLANK_STRING)
				|| details.getNumberOfRooms() == 0 || details.getPrice() == 0.0) {
			sb.append(Constants.MISSING_INFORMATION);
			if (details.getAddress() == null || details.getAddress().trim().equals(Constants.BLANK_STRING))
				sb.append(Constants.ADDRESS+Constants.COMMA);
			if (details.getDescription() == null || details.getDescription().trim().equals(Constants.BLANK_STRING))
				sb.append(Constants.DESCRIPTION+Constants.COMMA);
			if (details.getFloorArea() == 0.0)
				sb.append(Constants.FLOOR_AREA+Constants.COMMA);
			if (details.getLatitude() == 0.0)
				sb.append(Constants.LATTITUDE+Constants.COMMA);
			if (details.getLongitude() == 0.0)
				sb.append(Constants.LONGITUDE+Constants.COMMA);
			if (details.getName() == null || details.getName().trim().equals(Constants.BLANK_STRING))
				sb.append(Constants.NAME+Constants.COMMA);
			if (details.getNumberOfRooms() == 0)
				sb.append(Constants.NUMBER_OF_ROOMS+Constants.COMMA);
			if (details.getPrice() == 0.0)
				sb.append(Constants.PRICE+Constants.COMMA);
		}
		return sb.toString();
	}

	public Specification<Apartment> createSpecification(ApartmentFilterCriteria criteria) {
		return new Specification<Apartment>() {

			private static final long serialVersionUID = -5633196062527616041L;

			@Override
			public Predicate toPredicate(Root<Apartment> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicate = new ArrayList<>();
				if (null != criteria.getNumberOfRooms()) {
					predicate.add(cb.equal(root.get("numberOfRooms"), criteria.getNumberOfRooms()));
				}

				if (null != criteria.getMinPrice()) {
					predicate.add(cb.greaterThanOrEqualTo(root.get("price"), criteria.getMinPrice()));
				}

				if (null != criteria.getMaxPrice()) {
					predicate.add(cb.lessThanOrEqualTo(root.get("price"), criteria.getMaxPrice()));
				}

				if (null != criteria.getMinSize()) {
					predicate.add(cb.greaterThanOrEqualTo(root.get("floorArea"), criteria.getMinSize()));
				}

				if (null != criteria.getMaxSize()) {
					predicate.add(cb.lessThanOrEqualTo(root.get("floorArea"), criteria.getMaxSize()));
				}

				return cb.and(predicate.toArray(new Predicate[] {}));
			}
		};
	}

}
