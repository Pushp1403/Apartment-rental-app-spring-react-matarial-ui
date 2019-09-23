import { combineReducers } from "redux";
import userReducer from "./userReducer";
import apartmentReducer from "./apartmentReducer";

const rootReducer = combineReducers({
  userReducer,
  apartmentReducer
});

export default rootReducer;
