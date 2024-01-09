import { height, width } from "@mui/system";
import React from "react";
import styled from "styled-components";

const NonItem = ({ width, height }) => {
  return <S.Wrapper width={width} height={height}></S.Wrapper>;
};

export default NonItem;

const S = {};

S.Wrapper = styled.div`
  width: ${({ width }) => width};
  height: ${({ height }) => height};
  background-color: #ccc;
  display: flex;
`;
