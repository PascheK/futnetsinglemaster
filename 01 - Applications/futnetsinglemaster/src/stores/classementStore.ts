/* eslint-disable prefer-const */
import { ref } from 'vue'
import { defineStore } from 'pinia'
import Classement from '@/utils/beans/Classement'
import { axios } from '@/utils/axios.js'
import { useUserSessionStore } from '@/stores/userSessionStore'
import { errorSwal, swal } from '@/utils/swal'
export const useClassementStore = defineStore('classement', () => {
  const classement = ref([] as Classement[])
  const userStore = useUserSessionStore()

  async function fetchClassement() {
    try {
      const response = await axios({
        method: 'GET',
        url: 'rencontre/getClassement',
        data: {}
      })

      if (response.data.responseObject != null) {
        classement.value = response.data.responseObject
        swal.fire({
          title: 'Classement récupéré',
          text: '',
          icon: 'success'
        })
        return true
      } else {
        errorSwal(response.data.responseText).fire()
        return false
      }
    } catch (e: any) {
      if (e.response.status == 401) userStore.destroy()
      errorSwal(e.response.data.responseText).fire()
      return false
    }
  }

  return { classement, fetchClassement }
})
