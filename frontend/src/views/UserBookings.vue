<template>
  <div>
    <UserHeader />
    <h2 id="title">Bookings</h2>
    <div v-for="booking in bookings" :key="booking.bookingId">
      <BookingItem :bookedData="booking" />
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, onBeforeMount, ref } from "vue";
import UserHeader from "../components/UserHeader.vue";
import BookingItem from "../components/BookingItem.vue";
import axios from "@/axiosConfig";
import IBookedItem from "@/interfaces/IBookedItem.interface";

export default defineComponent({
  name: "UserBookings",
  components: {
    UserHeader,
    BookingItem,
  },
  setup() {
    const bookings = ref<Array<IBookedItem>>([]);
    onBeforeMount(async () => {
      try {
        const bookingResponse = await axios.get(`/reservations`);
        bookings.value = bookingResponse.data as IBookedItem[];
      } catch (error) {
        console.log(error);
      }
    });
    return {
      bookings,
    };
  },
});
</script>
