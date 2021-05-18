import { RouteRecordRaw, createRouter, createWebHistory } from "vue-router";
import LogIn from "../views/LogIn.vue";

const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    name: "LogIn",
    component: LogIn,
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
