import { RouteRecordRaw, createRouter, createWebHistory } from "vue-router";
import LogIn from "../views/LogIn.vue";
import User from "../views/User.vue";
import UserBookings from "../views/UserBookings.vue"

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
    path: "/admin",
    name: "Admin",
    component: () => import("../views/Admin.vue"),
  },
  {
    path: "/user/bookings",
    name: "UserBookings", 
    component: UserBookings,
  }
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
