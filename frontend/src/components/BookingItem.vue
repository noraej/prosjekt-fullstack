<template>
  <div id="bookingView">
    <span>
      <p id="lable"><b>Section:</b> {{ reservation.section.sectionName }}</p>
    </span>
    <span>
      <p id="lable"><b>Description:</b> {{ reservation.description }}</p>
    </span>
    <span>
      <p id="lable"><b>Persons:</b> {{ reservation.numberOfUsers }}</p>
    </span>
    <span>
      <p id="lable"><b>From:</b> {{ from }}</p>
    </span>
    <span>
      <p id="lable"><b>To:</b> {{ to }}</p>
    </span>
  </div>
</template>

<script lang="ts">
import IBookedItem from "@/interfaces/IBookedItem.interface";
import { formatDate } from "../utils";
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
    console.log(props);
    console.log(props.bookedData.startTime);
    const from = computed((): string => {
      return formatDate(new Date(props.bookedData.startTime));
    });
    const to = computed((): string => {
      return formatDate(new Date(props.bookedData.endTime));
    });
    const reservation = computed(() => props.bookedData);

    return {
      reservation,
      from,
      to,
    };
  },
});
</script>

<style lang="scss" scoped>
#bookingView {
  background: #00000010;
  padding: 5px;
  width: fit-content;
  margin: 5px auto;
  min-width: 350px;
  border-radius: 10px;
  border: 1px solid #00000020;
}
</style>
