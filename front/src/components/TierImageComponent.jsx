"use client";

import Image from "next/image";
import React from "react";

const TierImageComponent = ({ rank }) => {
  const rankImage = `${rank}.png`;

  return (
    <div>
      <Image src={`/img/${rankImage}`} width={50} height={50} alt="rank" />
    </div>
  );
};

export default TierImageComponent;
