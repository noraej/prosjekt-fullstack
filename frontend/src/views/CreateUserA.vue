<template>
  <div id="admin">
    <AdminHeader />
    <AdminMenu />
    <div id="admin-content">
      <CreateUser />
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import CreateUser from "../components/CreateUser.vue";
import AdminHeader from "../components/AdminHeader.vue";
import AdminMenu from "../components/AdminMenu.vue";

export default defineComponent({
  name: "create-user",
  components: {
    CreateUser,
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
