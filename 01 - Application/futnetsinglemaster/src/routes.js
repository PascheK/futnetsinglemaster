import { createRouter, createWebHistory } from "vue-router";

import Login from './views/Login.vue';
import Home from './views/Home.vue';
import Classement from './views/Classement.vue';

const router = createRouter({
  history: createWebHistory(),
  routes: [
    { path: '', component: Home, children: [
      { path: '', component: Classement },

    ]
    },
    { path: '/login', component: Login },
  ]
})

export default router;