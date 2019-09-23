import React from "react";
import ListItem from "@material-ui/core/ListItem";
import ListItemAvatar from "@material-ui/core/ListItemAvatar";
import ListItemSecondaryAction from "@material-ui/core/ListItemSecondaryAction";
import ListItemText from "@material-ui/core/ListItemText";
import Avatar from "@material-ui/core/Avatar";
import IconButton from "@material-ui/core/IconButton";
import Home from "@material-ui/icons/Home";
import EditIcon from "@material-ui/icons/Edit";
import Search from "@material-ui/icons/Search";

function ApartmentEditableListItem({ apartment, editApartmentForThis }) {
  return (
    <>
      <ListItem>
        <ListItemAvatar>
          <Avatar>
            <Home />
          </Avatar>
        </ListItemAvatar>
        <ListItemText
          primary={apartment.name}
          secondary={apartment.description}
        />
        <ListItemSecondaryAction>
          <IconButton
            edge="end"
            aria-label="delete"
            id={apartment.apartmentId}
            onClick={() => {
              editApartmentForThis(apartment);
            }}
          >
            <EditIcon />
          </IconButton>
        </ListItemSecondaryAction>
      </ListItem>
    </>
  );
}

function ApartmentNonEditableListItem({ apartment, editApartmentForThis }) {
  return (
    <>
      <ListItem>
        <ListItemAvatar>
          <Avatar>
            <Home />
          </Avatar>
        </ListItemAvatar>
        <ListItemText
          primary={apartment.name}
          secondary={apartment.description}
        />
        <ListItemSecondaryAction>
          <IconButton
            edge="end"
            aria-label="delete"
            id={apartment.apartmentId}
            onClick={() => {
              editApartmentForThis(apartment);
            }}
          >
            <Search />
          </IconButton>
        </ListItemSecondaryAction>
      </ListItem>
    </>
  );
}

function ApartmentListItem({ user, apartment, editApartmentForThis }) {
  if (
    user.authorities[0].role === "ROLE_ADMIN" ||
    user.authorities[0].role === "ROLE_REALTOR"
  ) {
    return (
      <ApartmentEditableListItem
        apartment={apartment}
        editApartmentForThis={editApartmentForThis}
      ></ApartmentEditableListItem>
    );
  } else {
    return (
      <ApartmentNonEditableListItem
        apartment={apartment}
        editApartmentForThis={editApartmentForThis}
      ></ApartmentNonEditableListItem>
    );
  }
}

export default ApartmentListItem;
