<template>
  <div class="logIn">
    <span>
      <button @click="user">User log in</button>
      <button @click="admin">Admin log in</button>
    </span>
    <div id="userLogIn" v-if="scene === 1">
      <h2>User log in</h2>
      <h3>Email</h3>
      <input type="text" placeholder="email" />
      <h3>Password</h3>
      <input type="password" placeholder="password" />
      <h4 id="feedback"></h4>
      <button @click="userLogIn">Log in</button>
    </div>
    <div id="adminLogIn" v-if="scene === 2">
      <h2>Admin log in</h2>
      <h3>Email</h3>
      <input type="text" placeholder="email" />
      <h3>Password</h3>
      <input type="password" placeholder="password" />
      <h4 id="feedback"></h4>
      <button @click="adminLogIn">Log in</button>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from "vue";
import { useRouter } from "vue-router";

export default defineComponent({
  setup() {
    const scene = ref(1);
    const router = useRouter();
    const feedback = ref("YOYO");
    const admin = ref(() => {
      scene.value = 2;
    });
    const user = ref(() => {
      scene.value = 1;
    });
    const userLogIn = ref(() => {
      router.replace("/user");
    });
    const adminLogIn = ref(() => {
      router.replace("/admin");
    });

    return {
      scene,
      feedback,
      user,
      admin,
      userLogIn,
      adminLogIn,
    };
  },
});
</script>
