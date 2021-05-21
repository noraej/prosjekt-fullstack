const addLeadingZero = (number: number) =>
  number < 10 ? "0" + number : number;

export const formatTime = (date: Date): string => {
  return `${addLeadingZero(date.getHours())}:${addLeadingZero(
    date.getMinutes()
  )}`;
};

export const formatDate = (date: Date): string => {
  const isDifferentYear = date.getFullYear() !== new Date().getFullYear();
  return `${getDay(date.getDay())} ${date.getDate()} ${getMonth(
    date.getMonth()
  )} ${isDifferentYear ? date.getFullYear() : ""} - kl. ${formatTime(date)}`;
};

export const getDay = (day: number): string => {
  switch (day) {
    case 0:
      return "Søndag";
    case 1:
      return "Mandag";
    case 2:
      return "Tirsdag";
    case 3:
      return "Onsdag";
    case 4:
      return "Torsdag";
    case 5:
      return "Fredag";
    case 6:
      return "Lørdag";
    default:
      return String(day);
  }
};
export const getMonth = (month: number): string => {
  switch (month) {
    case 0:
      return "jan";
    case 1:
      return "feb";
    case 2:
      return "mars";
    case 3:
      return "april";
    case 4:
      return "mai";
    case 5:
      return "juni";
    case 6:
      return "juli";
    case 7:
      return "aug";
    case 8:
      return "sep";
    case 9:
      return "okt";
    case 10:
      return "nov";
    case 11:
      return "des";
    default:
      return String(month);
  }
};
