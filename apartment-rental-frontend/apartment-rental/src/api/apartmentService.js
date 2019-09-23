import API from "./baseService";
import * as constants from "./../redux/constants";

class ApartmentService {
  registerApartment(apartment) {
    return API.post(`${constants.APARTMENT_API_PATH}/saveApartment`, {
      ...apartment
    });
  }

  retrieveAllApartments() {
    return API.get(`${constants.APARTMENT_API_PATH}/listAllApartments`);
  }

  filterApartments(criteria) {
    return API.post(`${constants.APARTMENT_API_PATH}/filterApartments`, {
      ...criteria
    });
  }
}

export default new ApartmentService();
