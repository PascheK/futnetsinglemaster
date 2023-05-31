<script setup lang="ts">
import TopBar from '../components/TopBar.vue';

</script>

<template>
  <TopBar section-title="Classement"/>
  <div class="classement">
    Classement

  </div>
</template>


<style lang="scss" scoped>
  .classement{
    display: flex;
    justify-content: center;
    background-color: var(--white);
    margin: 1rem;
    border-radius: 15px;
    padding: 2rem;
    box-shadow: 0 0 16px 3px rgba(0, 0, 0, 0.16);
  }
</style>

