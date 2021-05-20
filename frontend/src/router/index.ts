import { RouteRecordRaw, createRouter, createWebHistory } from "vue-router";
import LogIn from "../views/LogIn.vue";
import UserSearch from "../views/UserSearch.vue";
import CreateUserA from "../views/CreateUserA.vue";
import User from "../views/User.vue";

const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    name: "LogIn",
    component: LogIn,
  },
  {
    path: "/user",
    name: "User",
    component: User,
  },
  {
    path: "/create",
    name: "CreateUserA",
    component: CreateUserA,
  },
  {
    path: "/admin",
    name: "Admin",
    component: () => import("../views/Admin.vue"),
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
