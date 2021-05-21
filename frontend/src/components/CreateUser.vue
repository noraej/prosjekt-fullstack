<template>
  <div>
    <input
      class="sign-up-form-input"
      placeholder="Firstname"
      v-model="user.firstname"
    />
    <input
      class="sign-up-form-input"
      placeholder="Lastname"
      v-model="user.lastname"
    />
    <input
      class="sign-up-form-input"
      placeholder="Email"
      v-model="user.email"
    />
    <input
      class="sign-up-form-input"
      placeholder="Phone number"
      v-model="user.phoneNumber"
    />
    <input
      v-model="createPassword"
      type="checkbox"
      id="passwordCheck"
      name="passwordCheck"
      @click="autogeneratePassword"
    />
    <label for="passwordCheck">Autogenerate password</label><br />
    <input
      v-model="user.admin"
      type="checkbox"
      id="adminCheck"
      name="adminCheck"
    />
    <label for="adminCheck">Admin user</label>
    <input
      v-model="user.valid"
      type="checkbox"
      id="validCheck"
      name="validCheck"
    />
    <label for="validCheck">Valid user</label><br />
    <button @click="saveUser">Opprett bruker</button>
    <p v-if="invalidEmail">E-postadresse trenger @ og .</p>
    <p v-if="userIsInvalid">Vennligst fyll inn alle feltene!</p>
    <p v-if="error">Noe gikk galt!</p>
  </div>
</template>

<script lang="ts">
import {
  computed,
  defineComponent,
  onBeforeMount,
  reactive,
  Ref,
  ref,
} from "vue";
import { useRouter } from "vue-router";
import { useStore } from "../store";
import CreateUser from "../interfaces/CreateUser.interface";
import { UserLevel } from "@/enums/UserLevel.enum";
import axios from "@/axiosConfig";
import User from "@/interfaces/User.interface";
import { store } from "@/store/index";

export default defineComponent({
  name: "createUser",
  props: ["id"],
  setup(props) {
    const router = useRouter();
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

    const createPassword = ref(false);

    const userIsInvalid = computed(() => {
      return (
        user.firstname.toString().trim() === "" ||
        user.lastname.toString().trim() === "" ||
        user.email.toString().trim() === "" ||
        user.phoneNumber.toString().trim() === "" ||
        user.password.toString().trim() === "" ||
        createPassword.value === false
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
          const response = await axios.post("/users", user);
          const response2 = await axios.get(`/users/` + response.data.userId);
          const s = response.data;
          console.log(s.userId + " " + response2);
          //if (await store.dispatch("register", user)) {
          /*if (isAdmin.value) {
            router.replace("/admin");
          } else {
            router.replace("/user");
          }*/
          //} else {
          // router.push("/error");
          // }
        } catch {
          error.value = true;
        }
      }
    };

    const userAdminCheck = ref({}) as Ref<User>;
    const isAdmin = ref();
    //TODO finn ut hvorfor props ikke gir riktig id
    onBeforeMount(async () => {
      try {
        /*const response = await axios.get(`/users/${props.id}`);
        console.log(response.data);
        userAdminCheck.value = response.data;
        isAdmin.value = userAdminCheck.value.admin;*/
      } catch {
        router.push("/error");
      }
    });

    const invalidEmail = computed(() => {
      return !(user.email.includes("@") && user.email.includes("."));
    });

    const invalidPassword = computed(() => {
      return user.password.length !== 8;
    });

    const autogeneratePassword = async (): Promise<void> => {
      // eslint-disable-next-line vue/no-side-effects-in-computed-properties
      createPassword.value = !createPassword.value;
      if (createPassword.value) {
        let CharacterSet =
          "abcdefghijklmnopqrstuvwxyz" +
          "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
          "0123456789" +
          "![]{}()%&*$#^~@|";
        let password = "";
        const size = 30;

        for (let i = 0; i < size; i++) {
          password += CharacterSet.charAt(
            Math.floor(Math.random() * CharacterSet.length)
          );
        }
        //TODO slett
        console.log(password);
        //user.password = password;
        user.password = "test"; //Setter alle passord til test til mail funker
      }
    };

    return {
      saveUser,
      user,
      userIsInvalid,
      error,
      invalidEmail,
      invalidPassword,
      createPassword,
      autogeneratePassword,
    };
  },
});
</script>

<style lang="scss" scoped></style>
