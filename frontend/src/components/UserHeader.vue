<template>
  <div id="admin-header">
    <div id="header-title">
      <h3>RoomBooking.com</h3>
    </div>
    <div></div>
    <div id="header-options">
      <div class="container" @click="openMenu">
        <div class="bar1"></div>
        <div class="bar2"></div>
        <div class="bar3"></div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from "vue";

export default defineComponent({
  name: "UserHeader",
  setup() {
    const isMenuOpen = ref(false);
    const openMenu = ref((): void => {
      isMenuOpen.value = true;
      //TODO: open menu here
    });
    return {
      openMenu,
    };
  },
});
</script>

<style scoped lang="scss">
#admin-header {
  width: 100%;
  height: 50px;
  background-color: #8d99ae;
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

.octicon {
  fill: #ffffff;
}
.container {
  display: inline-block;
  cursor: pointer;
}

.bar1,
.bar2,
.bar3 {
  width: 35px;
  height: 5px;
  background-color: #333;
  margin: 6px 0;
  transition: 0.4s;
}

.change .bar1 {
  -webkit-transform: rotate(-45deg) translate(-9px, 6px);
  transform: rotate(-45deg) translate(-9px, 6px);
}

.change .bar2 {opacity: 0;}

.change .bar3 {
  -webkit-transform: rotate(45deg) translate(-8px, -8px);
  transform: rotate(45deg) translate(-8px, -8px);
}
</style>
