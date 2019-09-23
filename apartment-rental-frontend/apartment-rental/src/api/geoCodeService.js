import Geocode from "react-geocode";

// set Google Maps Geocoding API for purposes of quota management. Its optional but recommended.
Geocode.setApiKey("AIzaSyBSzbZA7nTuuFDE5arz3OIx8FYqT9IU_vE");

// Enable or disable logs. Its optional.
Geocode.enableDebug();

// Get address from latidude & longitude.
export function getAddressesFromLatLong(lattitude, longitude) {
  return Geocode.fromLatLng(lattitude, longitude).then(
    response => {
      const addresses = response.results;
      return addresses;
    },
    error => {
      console.error(error);
    }
  );
}

// Get latidude & longitude from address.
export function getLatLongFromAddress(address) {
  return Geocode.fromAddress(address).then(
    response => {
      return response.results;
    },
    error => {
      console.error(error);
    }
  );
}
