<template>
  <!--Should only be visible for admin-->
  <div>Create a</div>
  <button @click="sectionChosen">Section</button>
  <button @click="roomChosen">Room</button>
  <button @click="buildingChosen">Building</button>

  <div v-if="isBuilding">
    <div>Building name:</div>
    <input type="text" v-model="building.buildingName" />
    <div>Address:</div>
    <input type="text" v-model="buildingAddress" />
  </div>
  <div v-if="isRoom">
    <div>Room name:</div>
    <input type="text" v-model="room.roomName" />
    <div>Building floor:</div>
    <input type="text" placeholder="" v-model="buildingFloor" />
  </div>
  <div v-if="isSection">
    <div>Section name:</div>
    <input type="text" v-model="section.sectionName" />
    <div>Description:</div>
    <input type="text" v-model="section.description" />
    <div>Number of seats:</div>
    <input type="text" placeholder="" v-model="section.numberOfSeats" />
    <div>Size of section (m^2):</div>
    <input type="text" placeholder="" v-model="section.sectionName" />
  </div>
  <button @click="cancel">Cancel</button>
  <button @click="save">Save</button>
</template>

<script lang="ts">
import { computed, defineComponent, reactive, Ref, ref } from "vue";
import { useRouter } from "vue-router";
import { BuildingCreate } from "@/interfaces/Building.interface";
import Room from "@/interfaces/Room.interface";
import { SectionCreate } from "@/interfaces/Section.interface";

export default defineComponent({
  setup() {
    const router = useRouter();

    const buildingAddress: Ref<string> = ref("");
    const buildingFloor: Ref<number> = ref(0);

    const isBuilding: Ref<boolean> = ref(false);
    const isRoom: Ref<boolean> = ref(false);
    const isSection: Ref<boolean> = ref(false);

    const building = reactive({
      buildingName: "",
      numberOfRooms: 0,
    } as BuildingCreate);

    const room = reactive({
      name: "",
      buildingId: 0,
    } as Room);

    const section = reactive({
      sectionName: "",
      description: "",
      seats: 0,
      size: 0,
      roomId: 1,
    } as SectionCreate);

    const buildingChosen = async (): Promise<void> => {
      isBuilding.value = true;
      isRoom.value = false;
      isSection.value = false;
    };

    const roomChosen = async (): Promise<void> => {
      isBuilding.value = true;
      isRoom.value = true;
      isSection.value = false;
    };

    const sectionChosen = async (): Promise<void> => {
      isBuilding.value = true;
      isRoom.value = true;
      isSection.value = true;
    };

    const save = async (): Promise<void> => {
      if (
        buildingValid.value ||
        (buildingValid.value && roomValid) ||
        (buildingValid.value && roomValid && sectionValid)
      ) {
        if (isBuilding.value && !isRoom.value && !isSection.value) {
          //TODO make and connect to backend to create buildings, rooms and sections
          // const response = await axios.post("/buildings", building);
        } else if (isBuilding.value && isRoom.value && !isSection.value) {
          //const response = await axios.post("/rooms", room);
        } else if (isBuilding.value && !isRoom.value && isSection.value) {
          //const response = await axios.post("/sections", section);
        }
      }
    };

    const cancel = async (): Promise<void> => {
      router.push("/admin");
    };

    const buildingValid = computed(() => {
      if (isBuilding.value) {
        return !(
          building.buildingName.trim() === "" || buildingAddress.value.trim() === ""
        );
      }
      return null;
    });

    const roomValid = computed(() => {
      if (isRoom.value) {
        return !(room.name.trim() === "" || buildingFloor.value <= -30);
      }
      return null;
    });

    const sectionValid = computed(() => {
      if (isSection.value) {
        return !(
          section.sectionName.trim() === "" ||
          section.description.trim() === "" ||
          section.seats <= 0 ||
          section.size <= 0
        );
      }
      return null;
    });

    return {
      buildingAddress,
      buildingFloor,
      isBuilding,
      isRoom,
      isSection,
      building,
      room,
      section,
      buildingChosen,
      roomChosen,
      sectionChosen,
      save,
      cancel,
      buildingValid,
      roomValid,
      sectionValid,
    };
  },
});
</script>
