<script setup lang="ts">
import TopBar from '@/components/TopBar.vue'
import RencontresTable from '@/components/RencontresTable.vue'
</script>

<template>
  <TopBar section-title="Rencontre" />
  <div class="section-bg classement">
    <div class="tab">
      <h3>Rencontre</h3>
      <RencontresTable />
    </div>
  </div>
</template>
