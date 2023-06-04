/* eslint-disable prefer-const */
import { ref } from 'vue'
import { defineStore } from 'pinia'
import User from '@/utils/beans/User'
import { useRouter } from 'vue-router'
import { axios } from '@/utils/axios.js'
import { useUserSessionStore } from '@/stores/userSessionStore'
import { errorSwal, swal } from '@/utils/swal'

export const useUsersStore = defineStore('users', () => {
  const users = ref([] as User[])
  const userStore = useUserSessionStore()
  const router = useRouter()

  async function fetchUsers() {
    try {
      const response: any = await axios({
        method: 'GET',
        url: 'user/getAllUsers',
        data: {}
      })
      if (response.data.responseObject != null) {
        users.value = [] as User[]
        response.data.responseObject.forEach((u: any) => {
          users.value.push(new User(u.id, u.nom, u.prenom, u.role, u.nomEquipe, u.mail, u.username))
        })
      }
    } catch (e: any) {
      if (e.response.status == 401) userStore.destroy()
      errorSwal(e.response.data.responseText).fire()
    }
  }

  async function deleteUserById(id: number) {
    try {
      await axios({
        method: 'DELETE',
        url: 'user/deleteUserById',
        data: {
          id: id
        }
      })
      users.value = users.value.filter((item: any) => item.id !== id)
      return true
    } catch (e: any) {
      if (e.response.status == 401) userStore.destroy()
      errorSwal(e.response.data.responseText).fire()
      return false
    }
  }
  async function putUser(
    id: Number,
    nom: string,
    prenom: string,
    mail: string,
    role: Number,
    nomEquipe: Number
  ) {
    try {
      await axios({
        method: 'PUT',
        url: 'user/putUser',
        data: {
          id_user: id,
          nom: nom,
          prenom: prenom,
          mail: mail,
          role: role,
          nomEquipe: nomEquipe
        }
      })
      await swal.fire({
        title: 'Utilisateur modifiée',
        text: '',
        icon: 'success'
      })
      return true
    } catch (e: any) {
      if (e.response.status == 401) userStore.destroy()
      await errorSwal(e.response.data.responseText).fire()
      return false
    }
  }

  async function saveUser(
    nom: string,
    prenom: string,
    mail: string,
    role: Number,
    nomEquipe: Number
  ) {
    try {
      await axios({
        method: 'POST',
        url: 'user/saveUser',
        data: {
          nom: nom,
          prenom: prenom,
          mail: mail,
          role: role,
          nomEquipe: nomEquipe
        }
      })
      await swal.fire({
        title: 'Utilisateur ajoutée',
        text: '',
        icon: 'success'
      })
      router.go(0)
      return true
    } catch (e: any) {
      if (e.response.status == 401) userStore.destroy()
      await errorSwal(e.response.data.responseText).fire()
      return false
    }
  }

  return { users, fetchUsers, deleteUserById, putUser, saveUser }
})
