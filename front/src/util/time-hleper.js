const timeHelper = (date) => {
  const thisTime = new Date();
  const createTime = new Date(date);
  const timeDifference = thisTime - createTime; // 차이를 밀리초 단위로 얻음

  // 밀리초를 분, 시간, 일, 달로 변환
  const minutes = Math.floor(timeDifference / (1000 * 60));
  const hours = Math.floor(timeDifference / (1000 * 60 * 60));
  const days = Math.floor(timeDifference / (1000 * 60 * 60 * 24));
  const months = Math.floor(timeDifference / (1000 * 60 * 60 * 24 * 30));

  if (months > 0) {
    return `${months} 달 전`;
  } else if (days > 0) {
    return `${days} 일 전`;
  } else if (hours > 0) {
    return `${hours} 시간 전`;
  } else if (minutes > 0) {
    return `${minutes} 분 전`;
  } else {
    return "방금";
  }
};

export default timeHelper;
