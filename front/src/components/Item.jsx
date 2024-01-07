import { ITEM_IMAGE_URL } from "@/const/api";
import Image from "next/image";
import React from "react";
import styled from "styled-components";
import NonItem from "./NonItem";

const Item = ({ itemIds, item6 }) => {
  return (
    <>
      <S.ItemContainer>
        {itemIds.map((item, index) => (
          <div key={index}>
            {item !== 0 ? (
              <Image
                src={ITEM_IMAGE_URL(item)}
                width={36}
                height={36}
                alt="item"
              />
            ) : (
              <NonItem />
            )}
          </div>
        ))}
      </S.ItemContainer>
      <S.Item>
        {item6 ? (
          <Image
            src={ITEM_IMAGE_URL(item6)}
            width={36}
            height={36}
            alt="item"
          />
        ) : (
          <NonItem />
        )}
      </S.Item>
    </>
  );
};

export default Item;

const S = {};

S.ItemContainer = styled.div`
  width: 120px;
  height: 100%;
  display: flex;
  gap: 5px;
  flex-wrap: wrap;
`;

S.Item = styled.div`
  padding-right: 10px;
  border-right: 1px solid #9e9eb1;
`;
