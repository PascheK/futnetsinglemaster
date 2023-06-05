<script setup lang="ts">
import { onMounted } from 'vue'
import TopBar from '@/components/TopBar.vue'
import UsersTable from '@/components/UsersTable.vue'
import { useUserSessionStore } from '@/stores/userSessionStore'
import { useRouter } from 'vue-router'

const router = useRouter()
const userStore = useUserSessionStore()

onMounted(async () => {
  if (!userStore.isAdmin) router.push('/')
})
</script>

<template>
  <TopBar section-title="Inscription" />
  <div class="section-bg users">
    <div class="tab">
      <h3>Utilisateur</h3>
      <UsersTable />
    </div>
  </div>
</template>

<style lang="scss">
.users {
  .tab {
    width: 100%;
  }
}
</style>
