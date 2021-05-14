<template>
  <div id="romView" @click="roomCliced">
    <span>
      <p>Building:</p>
      ¨
      <p>{{ building }}</p>
    </span>
    <span>
      <p>Room number:</p>
      <p>{{ roomNumber }}</p>
    </span>
    <span>
      <p>Seats:</p>
      <p>{{ seats }}</p>
    </span>
    <span>
      <p>Sections:</p>
      <p>{{ numberOfSections }}</p>
    </span>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from "vue";

export default defineComponent({
  setup() {
    //TODO: Koble på backend
    const building = ref("");
    const roomNumber = ref("");
    const seats = ref("");
    const numberOfSections = ref();
    const sections = ref(""); //list with name of sections
  },
});
</script>
