import * as constants from "./../../redux/constants";

export default function apartmentReducer(state = [], action) {
  switch (action.type) {
    case constants.APARTMENT_LOADED:
      return Object.assign({}, state, {
        apartments: action.apartments
      });
    case constants.APARTMENT_SAVED:
      return addOrUpdateStore(state, action);
    case constants.APARTMENT_DELETED:
      return Object.assign(
        {},
        {
          apartments: state.apartments.filter(apt => {
            return apt.apartmentId !== action.apartment.apartmentId;
          })
        }
      );
    default:
      return state;
  }
}

function addOrUpdateStore(state, action) {
  if (action.apartment.apartmentId) {
    let newState = state.apartments.filter(
      apartment => apartment.apartmentId !== action.apartment.apartmentId
    );
    newState.push(action.apartment);
    return Object.assign(
      {},
      {
        apartments: newState
      }
    );
  } else {
    return Object.assign(
      {},
      {
        apartments: [...state.apartments, action.apartments]
      }
    );
  }
}
