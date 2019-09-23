import React from "react";
import { makeStyles } from "@material-ui/core/styles";
import Card from "@material-ui/core/Card";
import CardActionArea from "@material-ui/core/CardActionArea";
import CardActions from "@material-ui/core/CardActions";
import CardContent from "@material-ui/core/CardContent";
import CardMedia from "@material-ui/core/CardMedia";
import Button from "@material-ui/core/Button";
import Typography from "@material-ui/core/Typography";
import ApartmentLocationMap from "./ApartmaentLocationMap";

const useStyles = makeStyles({
  card: {
    maxWidth: 345
  }
});

export default function Apartment(props) {
  const classes = useStyles();
  const { apartment } = props;

  function MapSnap() {
    return <ApartmentLocationMap zoom={11} {...props}></ApartmentLocationMap>;
  }

  return (
    <Card className={classes.card}>
      <CardActionArea>
        <CardMedia component={MapSnap} alt="Location" height="140" />
        <CardContent>
          <Typography gutterBottom variant="h5" component="h2">
            {apartment.name}
          </Typography>
          <Typography variant="body2" color="textSecondary" component="p">
            {apartment.description}
          </Typography>
        </CardContent>
      </CardActionArea>
      <CardActions>
        <Button size="small" color="primary">
          Edit Apartment Details
        </Button>
      </CardActions>
    </Card>
  );
}
