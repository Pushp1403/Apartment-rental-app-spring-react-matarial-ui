import React from "react";
import ListItem from "@material-ui/core/ListItem";
import ListItemAvatar from "@material-ui/core/ListItemAvatar";
import ListItemSecondaryAction from "@material-ui/core/ListItemSecondaryAction";
import ListItemText from "@material-ui/core/ListItemText";
import Avatar from "@material-ui/core/Avatar";
import IconButton from "@material-ui/core/IconButton";
import FolderIcon from "@material-ui/icons/Folder";
import EditIcon from "@material-ui/icons/Edit";
import List from "@material-ui/core/List";
import { Button } from "@material-ui/core";

function UserGrid(props) {
  const { users, onEdit, resetForm } = props;
  return (
    <>
      <div>
        <ListItem>
          <Button
            onClick={resetForm}
            color="primary"
            variant="contained"
            style={{ marginLeft: 40 }}
          >
            Add New User
          </Button>
        </ListItem>
      </div>
      <List>
        {users.map(user => {
          return (
            <ListItem>
              <ListItemAvatar>
                <Avatar>
                  <FolderIcon />
                </Avatar>
              </ListItemAvatar>
              <ListItemText
                primary={user.firstName + " " + user.lastName}
                secondary={user.username}
              />
              <ListItemSecondaryAction>
                <IconButton
                  edge="end"
                  aria-label="delete"
                  onClick={() => {
                    onEdit(user);
                  }}
                >
                  <EditIcon />
                </IconButton>
              </ListItemSecondaryAction>
            </ListItem>
          );
        })}
      </List>
    </>
  );
}

export default UserGrid;
