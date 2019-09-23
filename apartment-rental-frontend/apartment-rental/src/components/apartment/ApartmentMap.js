import React from "react";
import GoogleMapReact from "google-map-react";
import ApartmentMarker from "./ApartmentMarker";

function ApartmentMap(props) {
  const { apartments } = props;
  const defaultProps = {
    center: {
      lat: 24.966760000000022,
      lng: 45.943161
    },
    zoom: 4
  };
  return (
    <div style={{ height: "78vh", width: "100%" }}>
      <GoogleMapReact
        bootstrapURLKeys={{ key: "AIzaSyBSzbZA7nTuuFDE5arz3OIx8FYqT9IU_vE" }}
        defaultCenter={defaultProps.center}
        defaultZoom={defaultProps.zoom}
      >
        {apartments.map(apartment => {
          return (
            <ApartmentMarker
              lat={apartment.latitude}
              lng={apartment.longitude}
              text={apartment.name}
              key={apartment.apartmentId}
            />
          );
        })}
      </GoogleMapReact>
    </div>
  );
}

export default ApartmentMap;
