<template>
  <div id="admin-menu">
    <div id="menu-title">
      <h3>Admin</h3>
    </div>
    <div id="menu-options">
      <router-link
        v-for="(option, index) in options"
        :key="index"
        class="menu-options"
        :to="option.path"
      >
        {{ option.title }}
      </router-link>
    </div>
    <span class="menu-options logout" @click="logout"
      ><i class="fa fa-power-off" aria-hidden="true"></i> Logg ut</span
    >
  </div>
</template>

<script lang="ts">
import { defineComponent, ref, Ref } from "vue";
import { useRouter } from "vue-router";
import MenuOption from "../interfaces/MenuOption.interface";
import { store } from "@/store/index";

export default defineComponent({
  setup() {
    const router = useRouter();
    const options: Ref<MenuOption[]> = ref([
      { title: "Legg til bruker", path: "/create" },
      { title: "Brukeroversikt", path: "/admin" },
      {
        title: "Legg til bygning, rom eller seksjon",
        path: "/create-building-room-section",
      },
    ]);

    const logout = (): void => {
      try {
        store.dispatch("logout");
        router.replace("/");
      } catch (error) {
        console.log(error);
      }
    };

    return {
      options,
      logout,
    };
  },
});
</script>

<style lang="scss" scoped>
#admin-menu {
  width: 250px;
  height: 100%;
  background-color: #2b2d42;
  left: 0px;
  bottom: -50px;
  position: fixed;
  color: #ffffff;
}

#menu-title {
  margin: 70px;
}

h3 {
  font-weight: 400;
}

.menu-options {
  border-radius: 4px;
  border: 1px solid #868acc;
  color: #868acc;
  text-decoration: none;
  display: block;
  cursor: pointer;
  padding: 15px;
  margin: 20px;
}

.menu-options.logout {
  bottom: 0px;
  display: inline-block;
  border: none;
}

.menu-options:hover {
  background-color: #353752;
}
</style>
