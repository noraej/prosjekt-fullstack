<template>
  <div id="romView" @click="roomClikced">
    <span>
      <p>Building:</p>
      <p>{{ building }}</p>
    </span>
    <span>
      <p>Room number:</p>
      <p>{{ name }}</p>
    </span>
    <span>
      <p>Seats:</p>
      <p>{{ seats }}</p>
    </span>
    <span>
      <p>Sections:</p>
      <p>{{ sections }}</p>
    </span>
  </div>
</template>

<script lang="ts">
import IRoomItem from "@/interfaces/IRoomItem.interface";
import { computed, defineComponent } from "vue";
import { useRouter } from "vue-router";

export default defineComponent({
  name: "RoomItem",

  props: {
    roomData: {
      type: Object as () => IRoomItem,
      required: true,
    },
  },
  setup(props) {
    const router = useRouter();
    //TODO: Koble på backend
    const building = computed((): string => {
      return props.roomData.building;
    });
    const name = computed((): string => {
      return props.roomData.roomName;
    });
    const seats = computed((): number => {
      return props.roomData.seats;
    });
    const sections = computed((): number => {
      return props.roomData.sections;
    });
    //TODO: trenger en liste med navnet på alle seksjoner
    const roomClikced = (): void => {
      router.push("/user/book-room" + props.roomData.roomId);
    };

    return {
      building,
      name,
      seats,
      sections,
      roomClikced,
    };
  },
});
</script>
