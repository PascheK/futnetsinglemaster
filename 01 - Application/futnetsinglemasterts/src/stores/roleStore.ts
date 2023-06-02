import { ref } from 'vue'
import { defineStore } from 'pinia'
import { axios } from '@/utils/axios.js'
import { useUserStore } from '@/stores/userStore'
import { errorSwal } from '@/utils/swal'
import { useRouter } from 'vue-router'

import Role from '../utils/beans/Role'

export const useRoleStore = defineStore('role', () => {
  const roles = ref([] as Role[])
  const router = useRouter()

  async function fetchRole() {
    try {
      const response = await axios({
        method: 'GET',
        url: 'utils/getAllRole',
        data: {}
      })
      if (response.data.responseObject != null) {
        roles.value = response.data.responseObject
      }
    } catch (e: any) {
      errorSwal(e.response.data.responseText).fire()
      return false
    }
  }

  return { roles, fetchRole }
})
