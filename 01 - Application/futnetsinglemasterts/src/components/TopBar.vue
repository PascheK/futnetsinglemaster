<script setup lang="ts">
import { defineProps } from 'vue'

import { useUserSessionStore } from '@/stores/userSessionStore'
const userStore = useUserSessionStore()

const props = defineProps({ sectionTitle: String })
</script>

<template>
  <nav>
    <div class="title">
      <h3>{{ sectionTitle }}</h3>
    </div>
    <div class="Profile">
      <h4>Bienvenue {{ userStore.user.prenom + ' ' + userStore.user.nom }} !</h4>
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
