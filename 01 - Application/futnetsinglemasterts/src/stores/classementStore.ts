/* eslint-disable prefer-const */
import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import Classement from '@/utils/beans/Classement'
import { useRouter } from 'vue-router'
import { axios } from '@/utils/axios.js'
import { useUserStore } from '@/stores/userStore'

export const useClassementStore = defineStore('classement', () => {
  const classement = ref([] as Classement[])
  const userStore = useUserStore()

  const classem = computed(() => {
    try {
      const formatClassement = []
      return connected.value && user.value.role === 'ADMIN'
    } catch {
      return false
    }
  })

  async function fetchClassement(): Promise<Classement[]> {
    try {
      const response = await axios({
        method: 'GET',
        url: 'rencontre/getClassement',
        data: {}
      })
      if (response.status == 200) {
        if (response.data.responseObject != null) {
          classement.value = response.data.responseObject
        }
      } else {
        userStore.destroy()
      }
    } catch {
      userStore.destroy()
    }

    return classement.value
  }

  return { classement, fetchClassement }
})
