<template>
  <div id="admin">
    <AdminHeader />
    <AdminMenu />
    <div id="admin-content">
      <h1>yo mom a hoe</h1>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import AdminHeader from "../components/AdminHeader.vue";
import AdminMenu from "../components/AdminMenu.vue";

export default defineComponent({
  name: "admin",
  components: {
    AdminHeader,
    AdminMenu,
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
