<template>
  <div>
    <p>YO</p>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from "vue";

export default defineComponent({
  name: "UserBookings",
  setup() {
    const bookings = ref();
    return {
      bookings,
    };
  },
});
</script>
