import { createStore, Store, useStore as originalUseStore } from "vuex";
import axios from "../axiosConfig";
import User from "../interfaces/User.interface";
import LogInUser from "../interfaces/LogInUser.interface";
import { InjectionKey } from "vue";
import CreateUser from "@/interfaces/CreateUser.interface";
import { BackendStatus } from "@/enums/BackendStatus.enum";
import { UserLevel } from "@/enums/UserLevel.enum";

export interface State {
  status: BackendStatus;
  userLevel: UserLevel;
  user: string;
  token: string;
}

export const key: InjectionKey<Store<State>> = Symbol();

export const store = createStore<State>({
  state: {
    status: BackendStatus.OK,
    userLevel: UserLevel.NORMAL || UserLevel.ADMIN,
    token: localStorage.getItem("token") || "",
    user: localStorage.getItem("user") || "",
  },
  //The mutators methods, to mutate the state
  mutations: {
    authenticationRequest(state) {
      state.status = BackendStatus.PENDING;
    },
    authenticationSuccess(state: State, { user, token }) {
      state.user = JSON.stringify(user);
      state.token = token;
      state.status = BackendStatus.OK;
    },
    authenticationError(state: State) {
      state.status = BackendStatus.ERROR;
    },
    authenticationLogout(state) {
      state.status = BackendStatus.OK;
      state.token = "";
      state.user = "";
    },
    updateUser(state, user: User) {
      state.user = JSON.stringify(user);
    },
  },
  actions: {
    async login({ commit }, user: LogInUser): Promise<boolean> {
      commit("authenticationRequest");
      try {
        const response = await axios.post("/login", user);
        const token = response.data.token;
        localStorage.setItem("token", token);
        axios.defaults.headers.common["Authorization"] = token;
        const userResponse = await axios.get(`/users/${response.data.userId}`);
        localStorage.setItem("user", JSON.stringify(userResponse.data));
        commit("authenticationSuccess", { user: userResponse.data, token });
        return true;
      } catch (error) {
        commit("authenticationError");
        localStorage.removeItem("token");
        localStorage.removeItem("user");
        delete axios.defaults.headers.common["Authorization"];
        return false;
      }
    },
    logout({ commit }) {
      commit("authenticationLogout");
      localStorage.removeItem("token");
      localStorage.removeItem("user");
      delete axios.defaults.headers["Authorization"];
    },

    async register({ commit }, user) {
      commit("authenticationRequest");
      try {
        //TODO create and add interface to user param
        await axios.post("/users", user);
        return true;
      } catch (error) {
        console.log(error.data);
        return false;
      }
    },
  },
  getters: {
    isLoggedIn: (state) => !!state.token,
    authenticationStatus: (state) => state.status,
    user: (state): User => JSON.parse(state.user),
    //TODO fix admin check
    //isAdmin: (state) => UserLevel.ADMIN,
  },
  modules: {},
});

export function useStore(): Store<State> {
  return originalUseStore(key);
}
