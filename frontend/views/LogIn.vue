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

<script>
export default {
    setup() {
        const scene = 1;
        const logInAsAdmin = {
            scene = 2
        }
        const logInAsUser = {
            scene = 1
        }
        const logIn = {
            // TODO: methods for logging in
        }
        return{
            scene,
            logInAsAdmin,
            logInAsUser,
        }
    },
}
</script>