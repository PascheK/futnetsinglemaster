<script setup lang="ts">
import { defineProps } from 'vue'

import { useUserStore } from '@/stores/userStore';
const userStore = useUserStore()

const props = defineProps({ sectionTitle: String });
</script>

<template>
  <nav>
    <div class="title">
      <h3>{{ sectionTitle }}</h3>
    </div>
    <div class="Profile">
      <h4>Bienvenue {{ userStore.user.nom + " "+userStore.user.prenom }} !</h4>
    </div>
  </nav>
</template>

<style lang="scss">
nav {
  padding: 1.5rem 2rem;
  background-color: var(--white);
  display: flex;
  align-items: center;
  justify-content: space-between;
  flex-wrap: nowrap;
  width: 100%;

  h3 {
    text-transform: capitalize;
  }
}
</style>