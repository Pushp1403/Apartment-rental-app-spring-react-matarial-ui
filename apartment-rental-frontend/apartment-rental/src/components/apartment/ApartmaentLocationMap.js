import React from "react";
import GoogleMapReact from "google-map-react";

function ApartmentLocationMap(props) {
  const { longitude, lattitude, zoom, name } = props;
  const center = { longitude, lattitude };
  const MapHolder = ({ text }) => (
    <div
      style={{
        color: "white",
        background: "grey",
        padding: "15px 10px",
        display: "inline-flex",
        textAlign: "center",
        alignItems: "center",
        justifyContent: "center",
        borderRadius: "100%",
        transform: "translate(-50%, -50%)"
      }}
    >
      {text}
    </div>
  );
  return (
    <div style={{ height: "100vh", width: "100%" }}>
      <GoogleMapReact
        bootstrapURLKeys={{ key: "AIzaSyBSzbZA7nTuuFDE5arz3OIx8FYqT9IU_vE" }}
        defaultCenter={center}
        defaultZoom={zoom}
      >
        <MapHolder lat={lattitude} lng={longitude}>
          {name}>
        </MapHolder>
      </GoogleMapReact>
    </div>
  );
}

export default ApartmentLocationMap;
