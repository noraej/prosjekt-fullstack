import { RouteRecordRaw, createRouter, createWebHistory } from "vue-router";
import LogIn from "../views/LogIn.vue";
import CreateUserA from "../views/CreateUserA.vue";
import User from "../views/User.vue";
import UserBookings from "../views/UserBookings.vue";
import { store } from "../store/index";

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
    meta: {
      requiresAuth: true,
    },
    props: true,
  },
  {
    path: "/create",
    name: "CreateUserA",
    component: CreateUserA,
    meta: {
      requiresAuth: true,
    },
    props: true,
  },
  {
    path: "/admin",
    name: "Admin",
    component: () => import("../views/Admin.vue"),
    meta: {
      requiresAuth: true,
    },
    props: true,
  },
  {
    path: "/user/bookings",
    name: "UserBookings",
    component: UserBookings,
    props: true,
    meta: {
      requiresAuth: true,
    },
  },
  {
    path: "/create-building-room-section",
    name: "CreateBuildingRoomSection",
    component: () => import("../views/CreateSectionBuildingRoom.vue"),
    meta: {
      requiresAuth: true,
    },
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

router.beforeEach((to, from, next) => {
  if (to.matched.some((record) => record.meta.requiresAuth)) {
    if (store.getters.isLoggedIn) {
      next();
      return;
    }
    next("/");
    return;
  }
  next();
});

export default router;
