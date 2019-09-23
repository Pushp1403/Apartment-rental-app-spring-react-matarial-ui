import * as constants from "./../constants";
import apartentService from "./../../api/apartmentService";

export function retrieveAllApartmentSuccess(apartments) {
  return { type: constants.APARTMENT_LOADED, apartments };
}

export function saveApartmentSuccess(apartments) {
  return { type: constants.APARTMENT_SAVED, apartments };
}

export function deleteApartmentSuccessfull(apartment) {
  return { type: constants.APARTMENT_DELETED, apartment };
}

export function filterApartmentSuccess(apartments) {
  return { type: constants.APARTMENT_LOADED, apartments };
}

export function retrieveAllApartments() {
  return async function(dispatch, getState) {
    try {
      const res = await apartentService.retrieveAllApartments();
      dispatch(retrieveAllApartmentSuccess(res.data));
    } catch (error) {
      throw error;
    }
  };
}

export function addOrUpdateApartment(apartment) {
  return async function(dispatch, getState) {
    try {
      const results = await apartentService.registerApartment(apartment);
      dispatch(saveApartmentSuccess(results.data));
    } catch (error) {
      throw error;
    }
  };
}

export function deleteApartment(apartment) {
  return async function(dispatch, getState) {
    try {
      const results = await apartentService.registerApartment(apartment);
      dispatch(deleteApartmentSuccessfull(results.data));
    } catch (error) {
      throw error;
    }
  };
}

export function filterApartments(criteria) {
  return async function(dispatch, getState) {
    try {
      const results = await apartentService.filterApartments(criteria);
      dispatch(filterApartmentSuccess(results.data));
    } catch (error) {
      throw error;
    }
  };
}
