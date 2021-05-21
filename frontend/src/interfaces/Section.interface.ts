import IRoomItem from 'src/interfaces/IRoomItem.interface'

export default interface Section {
  sectionId: number;
  sectionName: string;
  description: string;
  seats: number;
  size: number;
  room: IRoomItem;
}

export type SectionCreate = Omit<Section, "sectionId" | "room"> &  {
  roomId: number;
};
