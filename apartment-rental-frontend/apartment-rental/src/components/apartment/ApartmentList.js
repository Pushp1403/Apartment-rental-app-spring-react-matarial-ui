import React, { useState } from "react";
import { makeStyles } from "@material-ui/core/styles";
import List from "@material-ui/core/List";
import ApartmentListItem from "./ApartmentListItem";
import { Button } from "@material-ui/core";
import ListItem from "@material-ui/core/ListItem";
import AddEditApartment from "./AddEditApartment";
import { connect } from "react-redux";
import * as apartmentAction from "./../../redux/actions/apartmentActions";
import { toast } from "react-toastify";

const useStyles = makeStyles(theme => ({
  demo: {
    backgroundColor: theme.palette.background.paper,
    maxHeight: "75vh",
    overflowY: "scroll"
  }
}));

function ApartmentList(props) {
  const classes = useStyles();
  const { apartments, saveApartment, user } = props;
  const [_apartment, setApartment] = useState({
    name: "",
    description: "",
    numberOfRooms: "",
    longitude: "",
    latitude: "",
    state: "",
    address: "",
    floorArea: "",
    price: ""
  });
  const [open, setOpen] = useState(false);
  const handleChange = event => {
    setApartment({ ..._apartment, [event.target.name]: event.target.value });
  };
  const handleSubmit = event => {
    event.preventDefault();
    saveApartment(_apartment)
      .then(() => {
        toast("Apartment Saved Successfully");
        handleClose();
      })
      .catch(error => {
        toast("Failed to save apartments");
      });
  };
  const deleteApartment = event => {
    const apt = { ..._apartment, enable: false };
    saveApartment(apt)
      .then(() => {
        toast("Apartment Deleted Successfully");
        handleClose();
      })
      .catch(error => {
        toast("Failed to save apartments");
      });
  };

  const editApartmentForThis = apt => {
    setApartment({ ...apt });
    setOpen(true);
  };
  const editApartments = event => {
    setOpen(true);
  };
  const handleClose = event => {
    setOpen(false);
  };

  const addressChangeHandler = changes => {
    setApartment({ ..._apartment, ...changes });
  };

  return (
    <>
      <div>
        <ListItem>
          <Button
            onClick={editApartments}
            color="primary"
            variant="contained"
            style={{ marginLeft: 40 }}
          >
            Add New Apartment
          </Button>
          <AddEditApartment
            open={open}
            apartment={_apartment}
            handleChange={handleChange}
            handleSubmit={handleSubmit}
            handleClose={handleClose}
            addressChangeHandler={addressChangeHandler}
            deleteApartment={deleteApartment}
            user={user}
          />
        </ListItem>
      </div>
      <div className={classes.demo}>
        <List>
          {apartments.map(apartment => {
            return (
              <ApartmentListItem
                apartment={apartment}
                key={apartment.apartmentId}
                editApartmentForThis={editApartmentForThis}
                user={user}
              ></ApartmentListItem>
            );
          })}
        </List>
      </div>
    </>
  );
}

function mapStateToProps(state) {
  return {
    apartments: state.apartmentReducer.apartments,
    user: state.userReducer.user
  };
}

const mapDispatchToProps = {
  saveApartment: apartmentAction.addOrUpdateApartment
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ApartmentList);
