<script setup lang="ts">
import { ref, onMounted } from 'vue';

import TopBar from '../components/TopBar.vue';
import { useClassementStore } from '@/stores/classementStore';
import Classement from '@/utils/beans/Classement';

const classementStore = useClassementStore()


onMounted(() => {
   classementStore.fetchClassement();
})


</script>

<template>
  <TopBar section-title="Classement"/>
<div class="classement">
  <div class="tab">
    <h3>Classement</h3>
    <div class="classement-tab" v-for="(c, index) in classementStore.classement" :key="index">
    {{ c.nom }}
  </div>
  </div>
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

