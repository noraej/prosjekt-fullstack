import Section from "src/interfaces/Section.interface";

export type Reservation = {
  section: Section;
  numberOfUsers: number;
  startTime: string;
  endTime: string;
  description: string;
}

export type ReservationCreate = Omit<Reservation, "section"> & {
  sectionId: number;
};
