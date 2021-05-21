export default interface Building {
  buildingId: number;
  buildingName: string;
  numberOfRooms: number;
}

export type BuildingCreate = Omit<Building, "buildingId">;
