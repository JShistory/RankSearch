import { ITEM_IMAGE_URL } from "@/const/api";
import { height, width } from "@mui/system";
import Image from "next/image";
import React from "react";
import NonItem from "./NonItem";

const ItemImage = ({ item }) => {
  return (
    <>
      {item ? (
        <Image src={ITEM_IMAGE_URL(item)} width={15} height={15} alt="item" />
      ) : (
        <NonItem width={"15px"} height={"15px"} />
      )}
    </>
  );
};

export default ItemImage;
