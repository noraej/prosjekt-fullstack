<template>
   <div>
      <h2 id="title">Find room</h2>
      <h3 id="label">Building</h3>
      <button class="dropbtn"> Choose buliding</button>
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
         <option value="0" hidden disabled>Choose number</option>
         <option
            v-for="i in range(maxSeats)"
               :key="i"
               :value="i"
               >
            {{ i }}
         </option>
      </select>
      <!-- https://github.com/chronotruck/vue-ctk-date-time-picker -->
      <VueCtkDateTimePicker 
         v-model="startTime" 
         min-date="todaysDate" 
         end-date="maxDate" 
         minute-interval="30"/>
      <VueCtkDateTimePicker 
         v-model="endTime"
         min-date="todaysDate" 
         end-date="maxDate" 
         minute-interval="30" />

   </div>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue'
import 'vue-ctk-date-time-picker/dist/vue-ctk-date-time-picker.css';

export default defineComponent({
   setup() {
      const selectedBuilding = ref("");
      const buildings = ""; //TODO:  Denne listen skal admin kunne legge til bygninger i 
      const minSeats = ref();
      const startTime = ref(""); //Format: 


   },
})
</script>