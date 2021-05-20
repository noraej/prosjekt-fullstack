<template>
  <div>
    <input
      class="sign-up-form-input"
      placeholder="Fornavn"
      v-model="user.firstname"
    />
    <input
      class="sign-up-form-input"
      placeholder="Etternavn"
      v-model="user.lastname"
    />
    <input
      class="sign-up-form-input"
      placeholder="E-postadresse"
      v-model="user.email"
    />
    <input
      class="sign-up-form-input"
      placeholder="Mobilnummer"
      v-model="user.phoneNumber"
    />
    <input
      class="sign-up-form-input"
      placeholder="Passord"
      type="password"
      v-model="user.password"
    />
    <input
      v-model="user.admin"
      type="checkbox"
      id="adminCheck"
      name="adminCheck"
    />
    <label for="adminCheck">Administratorbruker</label><br />
    <input
      v-model="user.valid"
      type="checkbox"
      id="validCheck"
      name="validCheck"
    />
    <label for="validCheck">Aktiv bruker (gyldig)</label><br />
    <button @click="saveUser">Opprett bruker</button>
    <p v-if="invalidEmail">E-postadresse trenger @ og .</p>
    <p v-if="invalidPassword">Passord trenger minst 8 tegn</p>
    <p v-if="userIsInvalid">Vennligst fyll inn alle feltene!</p>
    <p v-if="error">Noe gikk galt!</p>
  </div>
</template>

<script lang="ts">
import { computed, defineComponent, reactive, ref } from "vue";
import { useRouter } from "vue-router";
import { useStore } from "../store";
import CreateUser from "../interfaces/CreateUser.interface";
import axios from "@/axiosConfig";
import User from "@/interfaces/User.interface";
import { UserLevel } from "@/enums/UserLevel.enum";

export default defineComponent({
  name: "createUser",
  setup() {
    const router = useRouter();
    const store = useStore();
    const error = ref(false);

    const user = reactive({
      firstname: "",
      lastname: "",
      email: "",
      phoneNumber: "",
      valid: false,
      admin: false,
      password: "",
      role: "",
    } as CreateUser);

    const userIsInvalid = computed(() => {
      return (
        user.firstname.toString().trim() === "" ||
        user.lastname.toString().trim() === "" ||
        user.email.toString().trim() === "" ||
        user.phoneNumber.toString().trim() === "" ||
        user.password.toString().trim() === ""
      );
    });

    const saveUser = async (): Promise<void> => {
      if (user.admin) {
        user.role = UserLevel.ADMIN.toString();
      } else {
        user.role = UserLevel.NORMAL.toString();
      }
      if (!userIsInvalid.value) {
        try {
          // await axios.post("/users", user);
          if (await store.dispatch("register", user)) {
            if (user.admin) {
              router.replace("/admin");
            } else {
              router.replace("/user");
            }
          } else {
            router.push("/error");
          }
        } catch {
          error.value = true;
        }
      }
    };

    const invalidEmail = computed(() => {
      return !(user.email.includes("@") && user.email.includes("."));
    });

    const invalidPassword = computed(() => {
      return user.password.length !== 8;
    });

    return {
      saveUser,
      user,
      userIsInvalid,
      error,
      invalidEmail,
      invalidPassword,
    };
  },
});
</script>

<style lang="scss" scoped></style>
