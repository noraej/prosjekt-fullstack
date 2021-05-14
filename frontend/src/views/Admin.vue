<template>
  <AdminHeader />
</template>

<script lang="ts">
import { defineComponent } from "vue";
import AdminHeader from "../components/AdminHeader.vue";

export default defineComponent({
  name: "admin",
  components: {
    AdminHeader,
  },
});
</script>

<style lang="sass" scoped></style>
