import React from "react";
import ApartmentView from "./../../pages/ApartmentView";

function MainContent({ apartments }) {
  return <ApartmentView apartments={apartments}></ApartmentView>;
}

export default MainContent;
