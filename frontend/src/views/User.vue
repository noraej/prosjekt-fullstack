<template>
  <div>
    <p>USER SIDE</p>
    <div id="userSearch" v-if="scene === 'search'">
      <h2 id="title">Find room</h2>
      <h3 id="lable">Choose buliding</h3>
      <select class="dropdown" v-model="selectedBuilding">
        <option value=" " hidden disabled>Choose building</option>
        <option
          class="dropdown-content"
          v-for="(building, index) in buildings"
          :value="building"
          :key="index"
        >
          {{ building }}
        </option>
      </select>
      <h3>Choose how many seats you need</h3>
      <input type="number" min="1" value="1" />
      <h3>Choose day</h3>
      <datepicker
        id="datepicker"
        v-model="date"
        :upperLimit="to"
        :lowerLimit="from"
      />
      <h3>Choose start time</h3>
      <input type="time" v-model="startTime" />
      <h3>Choose end time</h3>
      <input type="time" v-model="endTime" />
      <button @click="search">Search</button>
    </div>
    <div id="FindRoom" v-if="scene === 'find room'">
      <button id="button back" @click="back">Back</button>
      <h2>Ledige rom</h2>
      <div id="roomList">
        <RoomItem v-for="room in rooms" :key="room.roomId" :roomData="room" />
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { computed, defineComponent, ref } from "vue";
import Datepicker from "vue3-datepicker";

export default defineComponent({
  name: "User",
  components: {
    Datepicker,
  },
  setup() {
    //scene methods
    const scene = ref("search");
    const back = (): void => {
      scene.value = "search";
    };

    //User search methods
    const selectedBuilding = ref("");
    const buildings = ["Realfagsbygget", "Norge"]; //TODO:  Denne listen skal admin kunne legge til bygninger i
    const minSeats = ref(0);
    const date = ref(new Date());
    const startTime = ref("");
    const endTime = ref("");
    const avalibleSeats = 30; //hentes fra backend på et senere tidspunkt
    //date info
    const today = new Date();
    const oneYearFromNow = (): Date => {
      const newDate = new Date();
      newDate.setFullYear(newDate.getFullYear() + 1);
      return newDate;
    };

    //datepicker info
    const from = today;
    const to = oneYearFromNow();

    //button methods
    const disableButton = computed(() => {
      if (
        selectedBuilding.value === "" ||
        minSeats.value > 1 ||
        date.value === null ||
        startTime.value === null ||
        endTime.value === null
      ) {
        return true;
      } else return false;
    });

    const search = ref(() => {
      scene.value = "find room";
    });

    return {
      selectedBuilding,
      buildings,
      minSeats,
      date,
      endTime,
      startTime,
      avalibleSeats,
      from,
      to,
      disableButton,
      scene,
      search,
      back,
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
