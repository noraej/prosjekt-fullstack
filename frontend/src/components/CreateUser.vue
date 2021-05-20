<template>
  <div>
    <input
      class="sign-up-form-input"
      placeholder="Fornavn"
      v-model="user.firstname"
    />
    <input
      class="sign-up-form-input"
      placeholder="Fornavn"
      v-model="user.lastname"
    />
    <input
      class="sign-up-form-input"
      placeholder="Fornavn"
      v-model="user.email"
    />
    <input
      class="sign-up-form-input"
      placeholder="Fornavn"
      v-model="user.phoneNumber"
    />
    <input
      class="sign-up-form-input"
      placeholder="Fornavn"
      v-model="user.role"
    />
    <button @click="saveUser">Opprett bruker</button>
  </div>
</template>

<script lang="ts">
import { defineComponent, reactive } from "vue";
import { useRouter } from "vue-router";
import { useStore } from "../store";
import CreateUser from "../interfaces/CreateUser.interface";

export default defineComponent({
  name: "createUser",
  setup() {
    const router = useRouter(); 
    const store = useStore();

    const user = reactive({
      firstname: "sdf",
      lastname: "sdf",
      email: "asd",
      phoneNumber: "asd",
      valid: true,
      admin: false,
      password: "dssfd",
    } as CreateUser);

    const saveUser = async (): Promise<void> => {
      if (await store.dispatch("register", user)) {
        router.replace("/admin");
      } else {
        router.push("/");
      }
    };

    return {
      saveUser,
      user,
    };
  },
});
</script>

<style lang="scss" scoped></style>
