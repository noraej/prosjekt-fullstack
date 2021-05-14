<template>
  <div>
    <h2 id="title">Find room</h2>
    <h3 id="label">Building</h3>
    <p>Choose buliding</p>
    <select class="dropdown" v-model="selectedBuilding">
      <option value=" " hidden disabled>Choose building</option>
      <option
        v-for="(building, index) in buildings"
        :value="building"
        :key="index"
      >
        {{ building }}
      </option>
    </select>
    <h3>Choose how many seats you need</h3>
    <select class="dropdown" v-model="minSeats">
      <option value=" " hidden disabled>Choose number</option>
      <option v-for="(day, index) in avalibleSeats" :key="index" :value="index">
        {{ day }}
      </option>
    </select>
    <h3>Choose day</h3>
    <datepicker v-model="date" :upperLimit="to" :lowerLimit="from" />
    <h3>Choose start time</h3>
    <input type="time" v-model="startTime" />
    <h3>Choose end time</h3>
    <input type="time" v-model="endTime" />
    <button @click="search">Search</button>
  </div>
</template>

<script lang="ts">
import { computed, defineComponent, ref } from "vue";
import Datepicker from "vue3-datepicker";

export default defineComponent({
  name: "UserSearch",
  components: {
    Datepicker,
  },
  setup() {
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

    //buttons
    const search = ref(() => {
      //endpont for search with right parameters
    })
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
      search,
    };
  },
});
</script>
