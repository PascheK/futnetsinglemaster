<template>
  <nav>
    <div class="title">
      <h3>{{ sectionTitle }}</h3>
    </div>
    <div class="Profile">
      <h4>Bienvenue Pasche Killian !</h4>
    </div>
  </nav>
</template>
<script setup>
import { defineProps } from 'vue'
defineProps({ sectionTitle: String });
</script>
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