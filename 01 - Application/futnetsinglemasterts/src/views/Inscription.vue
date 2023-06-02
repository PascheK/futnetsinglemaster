<script setup lang="ts">
import TopBar from '@/components/TopBar.vue'
import UsersTable from '@/components/UsersTable.vue'
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
