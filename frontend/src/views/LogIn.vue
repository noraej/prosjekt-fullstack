<template>
    <div class = "logIn">
        <span>
            <button @click="logInAsUser">User log in</button>
            <button @click="logInAsAdmin">Admin log in</button>
        </span>
        <div id="userLogIn" v-if="scene === 1">
            <h3>Email</h3>
            <input type="text" placeholder="email">
            <h3>Password</h3>
            <input type="password">
            <h4 id="feedback"></h4>
            
        </div>
        <div id="adminLogIn" v-if="scene === 2">
            <h3>Email</h3>
            <input type="text" placeholder="email">
            <h3>Password</h3>
            <input type="password">
            <h4 id="feedback"></h4>
        </div>
        <button @click="logIn">Log in</button>
    </div>
</template>


<script lang="ts">
import { defineComponent, ref } from 'vue'

export default defineComponent({
    setup() {
        const scene = ref(1);
        const logInAsAdmin = ref(() => {
            scene.value = 2;
        });
        const logInAsUser = ref(() => {
            scene.value = 1;
        });
        const logIn = ref(() => {
            //method for logging inn
        });
        
        return{
            scene,
            logInAsAdmin, 
            logInAsUser,
            logIn,
        }
    },
})
</script>
