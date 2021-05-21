export default interface IRoomItem {
  roomId: number;
  roomName: string;
  building: string;
  seats: number;
  sections: number;
}

export type IRoomItemCreate = Omit<IRoomItem, "roomId">;