<template>
    <CreateUser />
</template>

<script lang="ts">
import { defineComponent } from "vue";
import CreateUser from "../components/CreateUser.vue";

export default defineComponent({
  name: "create-user",
  components: {
    CreateUser,
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
