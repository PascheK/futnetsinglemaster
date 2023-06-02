import { ref } from 'vue'
import { defineStore } from 'pinia'
import { axios } from '@/utils/axios.js'
import { useUserStore } from '@/stores/userStore'
import { errorSwal } from '@/utils/swal'
import Equipe from '../utils/beans/Equipe'

export const useEquipeStore = defineStore('equipe', () => {
  const equipes = ref([] as Equipe[])

  async function fetchEquipe() {
    try {
      const response = await axios({
        method: 'GET',
        url: 'utils/getAllEquipe',
        data: {}
      })
      if (response.data.responseObject != null) {
        equipes.value = response.data.responseObject
      }
    } catch (e: any) {
      errorSwal(e.response.data.responseText).fire()
      return false
    }
  }

  return { equipes, fetchEquipe }
})
