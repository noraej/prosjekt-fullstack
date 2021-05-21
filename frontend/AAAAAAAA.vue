<template></template>

<script lang="ts">
import { defineComponent, ref } from "vue";
import router from "@/router/index";
import { store } from "@/store/index";

export default defineComponent({
  setup() {
    const logout = (): void => {
      try {
        store.dispatch("logout");
        router.replace("/");
      } catch {}
    };
  },
});
</script>
