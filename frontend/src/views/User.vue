<template>
  <div>
    <UserHeader />
    <div id="userSearch" v-if="scene === 'search'">
      <h2 id="title">Find room</h2>
      <h3 id="lable">Choose buliding*</h3>
      <select class="dropdown" v-model="selectedBuilding">
        <option value=" " hidden disabled>Choose building</option>
        <option value=" " v-if="noAvalibleBuildings" disabled>
          No avalible buildings
        </option>
        <option
          class="dropdown-content"
          v-for="(building, index) in buildings"
          :value="building"
          :key="index"
        >
          {{ building }}
        </option>
      </select>
      <h3>Choose how many seats you need*</h3>
      <input type="number" min="1" value="1" />
      <h3>Choose day*</h3>
      <datepicker
        id="datepicker"
        v-model="date"
        :upperLimit="to"
        :lowerLimit="from"
        inputFormat="dd/MM yyyy"
      />
      {{ date }}
      <h3>Choose start time*</h3>
      <input type="time" v-model="startTime" />
      <h3>Choose end time*</h3>
      <input type="time" v-model="endTime" />
      <div id="feedback">{{ feedback }}</div>
      <button @click="search">Search</button>
    </div>
    <div id="FindRoom" v-if="scene === 'find room'">
      <button id="button back" @click="back">Back</button>
      <h2>Ledige rom</h2>
      <div id="noRooms feedback" v-if="noAvalibleRooms">
        <h3>There is no avalible rooms</h3>
        <p>Try a different search</p>
      </div>
      <div id="roomList">
        <RoomItem v-for="room in rooms" :key="room.roomId" :roomData="room" />
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { computed, defineComponent, onBeforeMount, Ref, ref } from "vue";
import Datepicker from "vue3-datepicker";
import UserHeader from "../components/UserHeader.vue";
import axios from "@/axiosConfig";

export default defineComponent({
  name: "User",
  components: {
    Datepicker,
    UserHeader,
  },
  setup() {
  //generall methods
    const scene = ref("search");
  

    const back = (): void => {
      scene.value = "search";
    };

  //User search methods

    const selectedBuilding = ref(""); //DENNE SKAL TIL BACKEND 1/4
    const noAvalibleRooms = ref(false);
    const noAvalibleBuildings = ref(false);
    const buildings = ref([]); //TODO: Må dobbeltsjekke at denne funker når vi

    /**
     * Method for getting all buildings to choose from
     */
    onBeforeMount(async () => {
      try {
        const buildingsIn = await axios.get("/v1/buildings");
        buildings.value = buildingsIn.data;
      } catch (error) {
        console.log(error);
      }
      if (buildings.value.length === 0) {
        noAvalibleBuildings.value = true;
      }
    });

    /**
     * Minum seats in search
     * Every building with this number or more should be shown
     */
    const minSeats = ref(1); //TODO Denne skal til backend 2/4
    
    //date methods
    /**
     * The date the room should be booked for
    */
    const date = ref(new Date);

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
     * Formats date to format dd.mm.yyyy
     */
    const formatedDate = computed((): string => {
      const month = ref("");
      const y = new Date(date.value).getFullYear();
      const m = new Date(date.value).getMonth() + 1;
      const d = new Date(date.value).getDate();
      if (m < 10) {
        month.value = "0" + m;
      } else month.value = m + "";
      return d + "." + month.value + "." + y + " ";
    });

    /**
     * Formats start time of booking to dd.mm.yyyy hh:mm
     */
    //DENNE SKAL SENDES TIL BACKEND 3/4
    const start = computed((): string => {
      return formatedDate.value + " " + startTime.value;
    });

    /**
     * Formats end time of booking to dd.mm.yyyy hh:mm
     */
    //DENNE SKAL SENDES TIL BACKEND 4/4
    const end = computed((): string => {
      return formatedDate.value + " " + endTime.value;
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
    const disableButton = computed(() => {
      if (
        selectedBuilding.value === "" ||
        minSeats.value > 1 ||
        date.value === null ||
        startTime.value === "" ||
        endTime.value === ""
      ) {
        return true;
      } else return false;
    });

    const checkDate = computed((): boolean => {
      if (startTime.value > endTime.value) {
        return false;
      } else return true;
    })

    const search = ref(() => {
      if (disableButton.value) {
        feedback.value = "Fill in all fields";
      } else scene.value = "find room";
    });

    return {
      selectedBuilding,
      buildings,
      minSeats,
      date,
      endTime,
      startTime,
      from,
      to,
      disableButton,
      scene,
      search,
      back,
      noAvalibleBuildings,
      noAvalibleRooms,
      feedback,
    };
  },
});
</script>

<style lang="scss" scoped>
.dropdown {
  position: relative;
  display: inline-block;
  border-radius: 5px;
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

.dropdown:hover .dropdown-content {
  display: block;
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
}
</style>
