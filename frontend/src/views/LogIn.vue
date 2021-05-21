<template>
  <div class="logIn">
    <span>
      <button @click="user">User log in</button>
      <button @click="admin">Admin log in</button>
    </span>
    <div id="userLogIn" v-if="scene === 1">
      <h2>User log in</h2>
      <h3>Email</h3>
      <input v-model="email" type="text" placeholder="email" />
      <h3>Password</h3>
      <input v-model="password" type="password" placeholder="password" />
      <h4 id="feedback"></h4>
      <button @click="login">Log in</button>
    </div>
    <div id="adminLogIn" v-if="scene === 2">
      <h2>Admin log in</h2>
      <h3>Email</h3>
      <input v-model="email" type="text" placeholder="email" />
      <h3>Password</h3>
      <input v-model="password" type="password" placeholder="password" />
      <h4 id="feedback"></h4>
      <button @click="login">Log in</button>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from "vue";
import { useRouter } from "vue-router";
import LogInUser from "../interfaces/LogInUser.interface";
import { useStore } from "../store";

export default defineComponent({
  setup() {
    const store = useStore();
    const email = ref("");
    const password = ref("");
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
      router.replace("/book");
    });
    const adminLogIn = ref(() => {
      router.replace("/admin");
    });
    const login = async (): Promise<void> => {
      const user: LogInUser = { email: email.value, password: password.value };
      if (await store.dispatch("login", user)) router.push("/book");
    };

    return {
      scene,
      feedback,
      user,
      admin,
      userLogIn,
      adminLogIn,
      login,
      email,
      password,
    };
  },
});
</script>
