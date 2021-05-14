<template>
  <div id="admin">
    <AdminHeader />
    <AdminMenu />
    <div id="admin-content">
      <Reservations />
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import AdminHeader from "../components/AdminHeader.vue";
import AdminMenu from "../components/AdminMenu.vue";
import Reservations from "../components/Reservations.vue";

export default defineComponent({
  name: "admin",
  components: {
    AdminHeader,
    AdminMenu,
    Reservations,
  },
});
</script>

<style lang="scss" scoped>
#admin {
  height: 100%;
}

#admin-content {
  margin-left: 250px;
}
</style>
