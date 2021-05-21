import Section from "src/interfaces/Section.interface"

export default interface IBookedItem {
  endTime: string;
  startTime: string;
  section: Section;
  description: string;
  numberOfUsers: number;
  reservationId: number;
  
}
