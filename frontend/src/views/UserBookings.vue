<template>
  <div>
    <UserHeader />
    <h2 id="title">Bookings</h2>
    <div></div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from "vue";
import UserHeader from "../components/UserHeader.vue";

export default defineComponent({
  name: "UserBookings",
  components: {
    UserHeader,
  },
  setup() {
    const bookings = ref();
    return {
      bookings,
    };
  },
});
</script>
