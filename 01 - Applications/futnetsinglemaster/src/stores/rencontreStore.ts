import { ref } from 'vue'
import { defineStore } from 'pinia'
import { axios } from '@/utils/axios.js'
import { useUserSessionStore } from '@/stores/userSessionStore'
import { errorSwal, swal } from '@/utils/swal'
import { useRouter } from 'vue-router'

import Rencontre from '../utils/beans/Rencontre'

export const useRencontreStore = defineStore('rencontre', () => {
  const userStore = useUserSessionStore()
  const rencontre = ref([] as Rencontre[])
  const router = useRouter()

  async function fetchRencontre() {
    try {
      const response = await axios({
        method: 'GET',
        url: 'rencontre/getRencontres',
        data: {}
      })
      if (response.data.responseObject != null) {
        rencontre.value = response.data.responseObject
      }
    } catch (e: any) {
      if (e.response.status == 401) userStore.destroy()
      errorSwal(e.response.data.responseText).fire()
      return false
    }
  }

  async function deleteRencontreById(id: Number) {
    try {
      await axios({
        method: 'DELETE',
        url: 'rencontre/deleteRencontreById',
        data: {
          id: id
        }
      })
      rencontre.value = rencontre.value.filter((item: any) => item.idRencontre !== item.idRencontre)
      return true
    } catch (e: any) {
      if (e.response.status == 401) userStore.destroy()
      errorSwal(e.response.data.responseText).fire()
      return false
    }
  }
  async function validateRencontre(id: Number) {
    try {
      await axios({
        method: 'PUT',
        url: 'rencontre/putValideRencontre',
        data: {
          id: id
        }
      })
      rencontre.value = rencontre.value.filter((item: any) => item.idRencontre !== item.idRencontre)
      return true
    } catch (e: any) {
      if (e.response.status == 401) userStore.destroy()
      errorSwal(e.response.data.responseText).fire()
      return false
    }
  }
  async function addRencontre(idUser2: Number, score1: Number, score2: Number, date: string) {
    try {
      await axios({
        method: 'POST',
        url: 'rencontre/saveRencontre',
        data: {
          joueur2: idUser2,
          score1: score1,
          score2: score2,
          date: date
        }
      })
      await swal.fire({
        title: 'Rencontre ajoutée',
        text: '',
        icon: 'success'
      })
      router.go(0)
      return true
    } catch (e: any) {
      if (e.response.status == 401) userStore.destroy()
      errorSwal(e.response.data.responseText).fire()
      return false
    }
  }
  async function putRencontre(idRencontre: Number, score1: Number, score2: Number, date: string) {
    try {
      await axios({
        method: 'PUT',
        url: 'rencontre/putRencontre',
        data: {
          rencontreId: idRencontre,
          score1: score1,
          score2: score2,
          date: date
        }
      })
      await swal.fire({
        title: 'Rencontre modifié',
        text: '',
        icon: 'success'
      })
      return true
    } catch (e: any) {
      if (e.response.status == 401) userStore.destroy()
      errorSwal(e.response.data.responseText).fire()
      return false
    }
  }

  return {
    rencontre,
    putRencontre,
    fetchRencontre,
    deleteRencontreById,
    validateRencontre,
    addRencontre
  }
})
