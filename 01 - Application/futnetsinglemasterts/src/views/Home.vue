<script setup lang="ts">
import Sidebar from '@/components/SideBar.vue'
</script>
<template>
  <main class="home-page">
    <Sidebar />
    <div class="page">
      <RouterView></RouterView>
    </div>
  </main>
</template>

<style lang="scss">
.home-page {
  display: flex;
  flex-wrap: nowrap;
  width: 100%;
  .page {
    width: 100%;
  }
}
</style>
