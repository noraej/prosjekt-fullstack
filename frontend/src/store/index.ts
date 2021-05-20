import { createStore, Store, useStore as originalUseStore } from "vuex";
import axios from "../axiosConfig";
import User from "../interfaces/User.interface";
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
    async register({ commit }, user) {
      commit("authenticationRequest");
      try {
        //TODO create and add interface to user param
        const response = await axios.post("/users", user);
        const token = response.data.token;
        localStorage.setItem("token", token);
        axios.defaults.headers["Authorization"] = token;
        const currentUser = response.data.user;
        localStorage.setItem("user", JSON.stringify(currentUser));
        commit("authenticationSuccess", { user: currentUser, token });
        return true;
      } catch (error) {
        commit("authenticationError");
        localStorage.removeItem("token");
        localStorage.removeItem("user");
        delete axios.defaults.headers["Authorization"];
        return false;
      }
    },
  },
  getters: {
    authenticationStatus: (state) => state.status,
    user: (state): User => JSON.parse(state.user),
  },
  modules: {},
});

export function useStore(): Store<State> {
  return originalUseStore(key);
}
