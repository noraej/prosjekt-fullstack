<template>
<div>
  <UserHeader />
  
  <div id="userSearch" v-if="scene === 'search'">
    <h2 id="title">Find room</h2>
    <h3 id="lable">Choose buliding*</h3>
    <select class="dropdown input" v-model="selectedBuilding">
      <option value=" " hidden disabled>Choose building</option>
      <option value=" " v-if="noAvalibleBuildings" disabled>
        No avalible buildings
      </option>
      <option
        class="dropdown-content"
        v-for="(building, index) in buildings"
        :value="building.buildingId"
        :key="index"
      >
        {{ building.buildingName }}
      </option>
    </select>
    <div v-if="selectedBuilding">
      <h3 id="lable">Choose room*</h3>
      <select class="dropdown input" v-model="selectedRoom">
        <option value=" " hidden disabled>Choose room</option>
        <option value=" " v-if="!rooms.length" disabled>
          No avalible room
        </option>
        <option
          class="dropdown-content"
          v-for="(room, index) in rooms"
          :value="room.roomId"
          :key="index"
        >
          {{ room.roomName }}
        </option>
      </select>
      <div v-if="selectedRoom">
        <h3 id="lable">Choose section*</h3>
        <select class="dropdown input" v-model="selectedSection">
          <option value=" " hidden disabled>Choose section</option>
          <option value=" " v-if="!sections.length" disabled>
            No avalible section
          </option>
          <option
            class="dropdown-content"
            v-for="(section, index) in sections"
            :value="section.sectionId"
            :key="index"
          >
            {{ section.sectionName }}
          </option>
        </select>
        <div v-if="selectedSection">
          <h3>Choose how many seats you need*</h3>
          <input class="input" type="number" min="1" value="1" />
          <h3>Choose day*</h3>
          <datepicker
            class="input"
            id="datepicker"
            v-model="date"
            :upperLimit="to"
            :lowerLimit="from"
            inputFormat="dd/MM yyyy"
          />
          <h3>Choose start time*</h3>
          <input class="input" type="time" v-model="startTime" />
          <h3>Choose end time*</h3>
          <input class="input" type="time" v-model="endTime" />
          <div id="feedback">{{ feedback }}</div>
          <button v-if="!isFormNotValid" @click="reserve">Book</button>
        </div>
      </div>
    </div>
  </div>
  </div>
</template>

<script lang="ts">
import {
  computed,
  defineComponent,
  onBeforeMount,
  watchEffect,
  ref,
} from "vue";
import Datepicker from "vue3-datepicker";
import UserHeader from "../components/UserHeader.vue";
import axios from "@/axiosConfig";
import Section from "../interfaces/Section.interface";
import Building from "../interfaces/Building.interface";
import {
  Reservation,
  ReservationCreate,
} from "../interfaces/Reservation.interface";
import IRoomItem from "../interfaces/IRoomItem.interface";
import { useRouter } from "vue-router";
export default defineComponent({
  name: "User",
  components: {
    Datepicker,
    UserHeader,
  },
  setup() {
    //generall methods
    const scene = ref<"search" | "find room">("search");
    const router = useRouter();
    const back = (): void => {
      scene.value = "search";
    };

    //User methods

    const selectedBuilding = ref<Building["buildingId"]>();
    const selectedRoom = ref<IRoomItem["roomId"]>();
    const selectedSection = ref<Section["sectionId"]>();
    const noAvalibleRooms = ref(false);
    const noAvalibleBuildings = ref(false);
    const buildings = ref<Array<Building>>([]);
    const rooms = ref<Array<IRoomItem>>([]);
    const sections = ref<Array<Section>>([]);

    /**
     * Method for getting all buildings to choose from
     */
    onBeforeMount(async () => {
      try {
        const buildingsIn = await axios.get<Array<Building>>("/buildings/");
        buildings.value = buildingsIn.data;
      } catch (error) {
        console.log(error);
      }
      if (buildings.value.length === 0) {
        noAvalibleBuildings.value = true;
      }
    });

    watchEffect(async () => {
      if (selectedBuilding.value) {
        try {
          const roomsIn = await axios.get<Array<IRoomItem>>(
            `/buildings/${selectedBuilding.value}/rooms/`
          );
          rooms.value = roomsIn.data;
        } catch (error) {
          console.log(error);
        }
      }
    });

    watchEffect(async () => {
      if (selectedRoom.value) {
        try {
          const sectionsIn = await axios.get<Array<Section>>(
            `/rooms/${selectedRoom.value}/sections/`
          );
          sections.value = sectionsIn.data;
        } catch (error) {
          console.log(error);
        }
      }
    });

    /**
     * Minum seats in search
     * Every building with this number or more should be shown
     */
    const minSeats = ref(1);
    //date methods
    /**
     * The date the room should be booked for
     */
    const date = ref(new Date());

    /**
     * Start time of booking
     */
    const startTime = ref("");

    /**
     * End time of booking
     */
    const endTime = ref("");
    //date info

    /**
     * Method for restricting which dates that are avalible for choosing
     */
    const oneYearFromNow = (): Date => {
      const newDate = new Date();
      newDate.setFullYear(newDate.getFullYear() + 1);
      return newDate;
    };

    /**
     * Formats start time of booking to dd.mm.yyyy hh:mm
     */
    const start = computed((): string => {
      const newDate = date.value;
      newDate.setHours(Number(startTime.value.substr(0, 2)));
      newDate.setMinutes(Number(startTime.value.substr(3, 2)));
      return newDate.toJSON();
    });

    /**
     * Formats end time of booking to dd.mm.yyyy hh:mm
     */
    const end = computed((): string => {
      const newDate = date.value;
      newDate.setHours(Number(endTime.value.substr(0, 2)));
      newDate.setMinutes(Number(endTime.value.substr(3, 2)));
      return newDate.toJSON();
    });

    /**
     * Gets todays date
     * Is the start date of avalible dates
     */
    const from = new Date();

    /**
     * Is one year from today
     * Is the end date of avalible dates
     */
    const to = oneYearFromNow();

    //button methods
    const feedback = ref("");
    const isFormNotValid = computed(() => {
      if (
        selectedBuilding.value === undefined ||
        minSeats.value > 1 ||
        date.value === null ||
        startTime.value === "" ||
        endTime.value === ""
      ) {
        return true;
      } else return false;
    });

    const reserve = async (): Promise<void> => {
      if (selectedSection.value !== undefined) {
        const newReservation: ReservationCreate = {
          sectionId: selectedSection.value,
          startTime: start.value,
          endTime: end.value,
          numberOfUsers: minSeats.value,
          description: "",
        };
        try {
          const createdReservation = await axios.post<Reservation>(
            "/reservations/",
            newReservation
          );
          console.log(createdReservation.data);
          router.push("/user/bookings");
        } catch (error) {
          console.log(error);
        }
        // if (await store.dispatch("login", search)) router.push("/admin");
      }
    };

    return {
      selectedBuilding,
      selectedRoom,
      selectedSection,
      buildings,
      rooms,
      sections,
      minSeats,
      date,
      endTime,
      startTime,
      reserve,
      from,
      to,
      isFormNotValid,
      scene,
      back,
      noAvalibleBuildings,
      noAvalibleRooms,
      feedback,
    };
  },
});
</script>

<style lang="scss" scoped>
input,
select {
  min-width: 350px;
  border-radius: 5px;
  padding: 10px;
  margin-bottom: 10px;
  border: 1px solid #00000080;
}
.dropdown {
  position: relative;
  display: inline-block;
  border-radius: 5px;
}

h2 {
  margin: 20px;
}

h3 {
  margin: 15px;
  margin-bottom: 3px;
}

.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f9f9f9;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
  padding: 12px 16px;
  z-index: 1;
}

#find-container {
  display: grid;
  grid-template-columns: 1fr 1fr;
  text-align: left;
}

.left {
  grid-column: 1;
}

.right {
  grid-column: 2;
  margin: 10px;
  border-radius: 7px;
  font-size: 15px;
  border: 1px solid #868acc;
  color: #868acc;
  text-decoration: none;
  display: block;
  cursor: pointer;
  padding: 5px;
  margin: 10px;
}

.dropdown:hover .dropdown-content {
  display: block;
}

#userSearch {
  width: 40%;
  margin: auto;
}

button {
  border-radius: 4px;
  border: 1px solid #868acc;
  color: #868acc;
  text-decoration: none;
  display: block;
  cursor: pointer;
  padding: 10px;
  font-size: 1rem;
  width: 120px;
  margin:auto;
  margin-top: 15px;
}
</style>
