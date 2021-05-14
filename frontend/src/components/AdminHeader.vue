<template>
  <div id="admin-header">
    <div id="header-title">
      <h3>Yo mom a hoe</h3>
    </div>
    <div></div>
    <div id="header-options">
      <i class="fa fa-cog" aria-hidden="true"></i>
      Account
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";

export default defineComponent({});
</script>

<style scoped lang="scss">
#admin-header {
  width: 100%;
  height: 50px;
  background-color: #457b9d;
  color: #ffffff;
  display: grid;
  grid-template-columns: 2fr 3fr 1fr;
  align-content: center;
}

#header-title {
  padding-left: 20px;
  text-align: left;
}

#header-options {
  display: grid;
  justify-self: right;
  grid-template-columns: 1fr 3fr;
  padding-right: 20px;
}
</style>
