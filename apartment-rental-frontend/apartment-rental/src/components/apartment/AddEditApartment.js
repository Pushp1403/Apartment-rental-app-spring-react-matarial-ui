import React from "react";
import Button from "@material-ui/core/Button";
import TextField from "@material-ui/core/TextField";
import Dialog from "@material-ui/core/Dialog";
import DialogActions from "@material-ui/core/DialogActions";
import DialogContent from "@material-ui/core/DialogContent";
import DialogTitle from "@material-ui/core/DialogTitle";
import Grid from "@material-ui/core/Grid";
import InputLabel from "@material-ui/core/InputLabel";
import FormControl from "@material-ui/core/FormControl";
import Select from "@material-ui/core/Select";
import AddressAutocomplete from "./AddressAutocomplete";

export default function AddEditApartment(props) {
  const {
    apartment,
    open,
    handleClose,
    handleChange,
    handleSubmit,
    addressChangeHandler,
    deleteApartment,
    user
  } = props;
  return (
    <Dialog
      open={open}
      onClose={handleClose}
      aria-labelledby="form-dialog-title"
    >
      <DialogTitle id="form-dialog-title">Add apartment</DialogTitle>
      <form onSubmit={handleSubmit}>
        <DialogContent>
          <Grid container spacing={2}>
            <Grid item xs={12} sm={12}>
              <TextField
                autoFocus
                variant="outlined"
                id="name"
                name="name"
                value={apartment.name}
                onChange={handleChange}
                label="Apartment name"
                type="text"
                disabled={user.authorities[0].role === "ROLE_CLIENT"}
                fullWidth
                required
              />
            </Grid>
            <Grid item xs={12} sm={12}>
              <TextField
                variant="outlined"
                id="description"
                name="description"
                value={apartment.description}
                label="A little description"
                type="text"
                onChange={handleChange}
                disabled={user.authorities[0].role === "ROLE_CLIENT"}
                fullWidth
                required
              />
            </Grid>
            <Grid item xs={12} sm={12}>
              <AddressAutocompleteElement
                user={user}
                apartment={apartment}
                addressChangeHandler={addressChangeHandler}
              ></AddressAutocompleteElement>
            </Grid>
            <Grid item xs={12} sm={6}>
              <TextField
                variant="outlined"
                id="longitude"
                name="longitude"
                value={apartment.longitude}
                label="Longitude"
                onChange={handleChange}
                fullWidth
                disabled={user.authorities[0].role === "ROLE_CLIENT"}
                required
              />
            </Grid>
            <Grid item xs={12} sm={6}>
              <TextField
                variant="outlined"
                id="latitude"
                name="latitude"
                value={apartment.latitude}
                label="Lattitude"
                onChange={handleChange}
                fullWidth
                disabled={user.authorities[0].role === "ROLE_CLIENT"}
                required
              />
            </Grid>
            <Grid item xs={12} sm={6}>
              <TextField
                variant="outlined"
                id="numberOfRooms"
                name="numberOfRooms"
                value={apartment.numberOfRooms}
                label="Number of rooms"
                type="number"
                onChange={handleChange}
                fullWidth
                disabled={user.authorities[0].role === "ROLE_CLIENT"}
                required
              />
            </Grid>
            <Grid item xs={12} sm={6}>
              <TextField
                variant="outlined"
                id="floorArea"
                name="floorArea"
                value={apartment.floorArea}
                label="Floor Area"
                onChange={handleChange}
                fullWidth
                disabled={user.authorities[0].role === "ROLE_CLIENT"}
                required
              />
            </Grid>
            <Grid item xs={12} sm={6}>
              <TextField
                variant="outlined"
                id="price"
                name="price"
                value={apartment.price}
                label="Price"
                onChange={handleChange}
                fullWidth
                disabled={user.authorities[0].role === "ROLE_CLIENT"}
                required
              />
            </Grid>
            <Grid item xs={12} sm={6}>
              <FormControl variant="outlined" style={{ width: "100%" }}>
                <InputLabel htmlFor="rental-status">Availability</InputLabel>
                <Select
                  native
                  disabled={user.authorities[0].role === "ROLE_CLIENT"}
                  value={apartment.state}
                  onChange={handleChange}
                  inputProps={{
                    name: "State",
                    id: "rental-status"
                  }}
                >
                  <option value="" />
                  <option value={true}>Available</option>
                  <option value={false}>Rented</option>
                </Select>
              </FormControl>
            </Grid>
          </Grid>
        </DialogContent>
        <DialogActions>
          <ActionPannel
            user={user}
            deleteApartment={deleteApartment}
          ></ActionPannel>
          <Button onClick={handleClose} color="default" variant="contained">
            Close
          </Button>
        </DialogActions>
      </form>
    </Dialog>
  );
}

function ActionPannel({ user, deleteApartment }) {
  if (user.authorities[0].role === "ROLE_CLIENT") {
    return null;
  } else {
    return (
      <>
        <Button type="Submit" color="primary" variant="contained">
          Submit
        </Button>
        <Button onClick={deleteApartment} color="secondary" variant="contained">
          Delete Apartment
        </Button>
      </>
    );
  }
}

function AddressAutocompleteElement({ user, apartment, addressChangeHandler }) {
  if (user.authorities[0].role === "ROLE_CLIENT") {
    return (
      <TextField
        variant="outlined"
        value={apartment.address}
        label="Address"
        fullWidth
        disabled
      />
    );
  } else {
    return (
      <AddressAutocomplete
        apartment={apartment}
        addressChangeHandler={addressChangeHandler}
      ></AddressAutocomplete>
    );
  }
}
