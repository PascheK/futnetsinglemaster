<script lang="ts" setup>
import TopBar from '../components/TopBar.vue'
import ClassementTable from '@/components/ClassementTable.vue'
</script>
<template>
  <TopBar section-title="Classement" />
  <div class="section-bg classement">
    <div class="tab">
      <h3>Classement</h3>
      <ClassementTable />
    </div>
  </div>
</template>
