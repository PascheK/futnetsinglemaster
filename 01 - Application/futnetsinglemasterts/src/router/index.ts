import { createRouter, createWebHistory } from 'vue-router'

import Login from '@/views/Login.vue'
import Home from '@/views/Home.vue'
import Classement from '@/views/Classement.vue'
import Rencontre from '@/views/Rencontre.vue'
import Inscription from '@/views/Inscription.vue'
import Options from '@/views/Options.vue';

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '',
      component: Home,
      children: [
        { path: '', component: Classement },
        { path: '/rencontre', component: Rencontre },
        { path: '/inscription', component: Inscription },
        { path: '/options', component: Options }
      ]
    },
    { path: '/login', component: Login }
  ]
})

export default router
