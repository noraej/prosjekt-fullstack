<template>
  <div id="bookingView">
    <span>
      <p id="lable">Building:</p>
      <p>{{ building }}</p>
    </span>
    <span>
      <p id="lable">Room number:</p>
      <p>{{ name }}</p>
    </span>
    <span>
      <p id="lable">Date: </p>
      <p>{{ date }}</p>
    </span>
    <span>
      <p id="lable">From: </p>
      <p>{{ from }}</p>
    </span>
    <span>
      <p id="lable">To: </p>
      <p>{{ to }}</p>
    </span>
  </div>
</template>

<script lang="ts">
import IBookedItem from "@/interfaces/IBookedItem.interface";
import { computed, defineComponent } from "vue";

export default defineComponent({
  name: "BookingItem",
  props: {
    bookedData: {
      type: Object as () => IBookedItem,
      required: true,
    },
  },
  setup(props) {
    //TODO: Koble på backend
    const building = computed((): string => {
      return props.bookedData.building;
    });
    const name = computed((): string => {
      return props.bookedData.roomName;
    });
    const date = computed((): string => {
      return props.bookedData.date;
    });
     const from = computed((): string => {
      return props.bookedData.from;
    });
    const to = computed((): string => {
      return props.bookedData.to;
    });

    return {
      building,
      name,
      date,
      from,
      to,
    };
  },
});
</script>