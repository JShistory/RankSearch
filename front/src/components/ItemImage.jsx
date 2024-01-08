import { height, width } from "@mui/system";
import Image from "next/image";
import React from "react";
import NonItem from "./NonItem";

const ItemImage = ({ item }) => {
  return (
    <>
      {item ? (
        <Image src={item} width={15} height={15} alt="item" />
      ) : (
        <NonItem />
      )}
    </>
  );
};

export default ItemImage;
