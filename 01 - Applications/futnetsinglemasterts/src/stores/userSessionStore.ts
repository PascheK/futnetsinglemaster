/* eslint-disable prefer-const */
import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import User from '@/utils/beans/User'
import { useRouter, useRoute } from 'vue-router'
import { axios } from '@/utils/axios.js'
import { errorSwal } from '@/utils/swal'

export const useUserSessionStore = defineStore('user', () => {
  const user = ref({} as User)
  const connected = ref(false)
  const isAdmin = computed(() => {
    try {
      return connected.value && user.value.role === 'ADMIN'
    } catch {
      return false
    }
  })
  const idUser = computed(() => {
    try {
      return user.value.id
    } catch {
      return false
    }
  })
  const router = useRouter()
  const route = useRoute()
  function init() {
    try {
      let sessionString = localStorage.getItem('userStore')

      if (sessionString === null) throw new Error()

      let session = JSON.parse(sessionString)
      if (session.connected == undefined || session.user == undefined || session.connected == false)
        throw new Error()

      let sessionUser = session.user as User

      if (
        sessionUser.id == undefined ||
        sessionUser.nom == undefined ||
        sessionUser.prenom == undefined ||
        sessionUser.role == undefined ||
        sessionUser.nomEquipe == undefined ||
        sessionUser.mail == undefined ||
        sessionUser.username == undefined
      )
        throw new Error()

      user.value = new User(
        sessionUser.id,
        sessionUser.nom,
        sessionUser.prenom,
        sessionUser.role,
        sessionUser.nomEquipe,
        sessionUser.mail,
        session.username
      )
      connected.value = true
      if (route.fullPath === 'login') router.push('/')
    } catch {
      destroy()
    }
  }
  function save() {
    localStorage.setItem(
      'userStore',
      JSON.stringify({ user: user.value, connected: connected.value })
    )
    router.push('/')
  }
  function destroy() {
    connected.value = false
    user.value = {} as User
    localStorage.removeItem('userStore')
    errorSwal('Veuillez vous connecter !').fire()
    router.push('/login')
  }
  function login(username: string, password: string) {
    axios({
      method: 'POST',
      url: 'auth/login',
      data: {
        username: username,
        password: password
      }
    }).then(function (response: any) {
      if (response.status == 200) {
        if (response.data.responseObject != null) {
          let loggedUser = response.data.responseObject
          connected.value = true
          user.value = new User(
            loggedUser.id,
            loggedUser.nom,
            loggedUser.prenom,
            loggedUser.role,
            loggedUser.nomEquipe,
            loggedUser.mail,
            loggedUser.username
          )
          save()
        }
      }
    })
  }
  function disconnect() {
    axios({
      method: 'POST',
      url: 'auth/logout',
      data: {}
    }).then(function (response: any) {
      if (response.status == 200) {
        destroy()
      }
    })
  }
  return { user, connected, save, login, init, destroy, disconnect, isAdmin, idUser }
})
