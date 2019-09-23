import React, { useState } from "react";
import ApartmentFilterBox from "./ApartmentFilterBox";
import * as apartmentActions from "./../../redux/actions/apartmentActions";
import { connect } from "react-redux";
import { toast } from "react-toastify";

function ApartmentFilter(props) {
  const { filterApartments } = props;
  const [criteria, setCriteria] = useState({
    minPrice: "",
    maxPrice: "",
    minSize: "",
    maxSize: "",
    numberOfRooms: ""
  });
  const handleChange = event => {
    setCriteria({ ...criteria, [event.target.name]: event.target.value });
  };
  const handleSubmit = event => {
    event.preventDefault();
    filterApartments(criteria).catch(error => {
      toast(error.message);
    });
  };
  return (
    <ApartmentFilterBox
      criteria={criteria}
      onChangeHandler={handleChange}
      onSubmitHandler={handleSubmit}
    />
  );
}
function mapStateToProps(state) {
  return {
    apartments: state.apartmentReducer.apartments
  };
}

const mapDispatchToProps = {
  filterApartments: apartmentActions.filterApartments
};
export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ApartmentFilter);
