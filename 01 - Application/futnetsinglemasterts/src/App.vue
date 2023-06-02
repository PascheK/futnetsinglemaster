<script setup lang="ts">
import { RouterView } from 'vue-router'
import { useUserStore } from '@/stores/userStore'

const userStore = useUserStore()

userStore.init()
</script>

<template>
  <RouterView />
</template>

<style lang="scss">
@import 'bootstrap';
@import 'datatables.net-bs5';

:root {
  --font-base: 'DM Sans', sans-serif;

  --dark: #212529;
  --light-dark: #343c40;
  --grey: #e9ecef;
  --light-grey: #f0eff4;
  --white: #f8f9fa;
  --red: #da1e37;
  --light-red: rgba(218, 30, 55, 0.75);
  --red-rgb: 218, 30, 55;
  --green: #55a630;
  --orange: #f77f00;

  --pagination-active-color: var(--white) !important;
  --pagination-active-bg: var(--light-red) !important;
  --pagination-active-border-color: var(--red) !important;
  --pagination-disabled-color: var(--red) !important;
  --pagination-disabled-bg: transparent;
  --pagination-disabled-border-color: var(--red) !important;
  --body-color: var(--red) !important;
  --body-bg: transparent !important;
  --border-color: var(--red) !important;

  --sidebar-width: 300px;
  --sidebar-width-close: 64px;
}

* {
  font-family: var(--font-base);
  box-sizing: border-box;
  padding: 0;
  margin: 0;
  scroll-behavior: smooth;
}

#app {
  display: flex;
  height: 100vh;
  background-color: var(--grey);
}

.app_greybg {
  background-color: var(--grey);
}

.section-bg {
  display: flex;
  justify-content: center;
  background-color: var(--white);
  margin: 1rem;
  border-radius: 15px;
  padding: 2rem;
  box-shadow: 0 0 16px 3px rgba(0, 0, 0, 0.16);
}
.buttons-pagination .item.button.active{
  background-color: var(--white);
    border-left: var(--red);
    border-color: var(--red)!important;
}
span{
  &.edit{
    color: var(--orange);
  }
  &.delete{
    color: var(--red);
  }
  &.done{
    color: var(--green);
  }
  &.close{
    color: var(--red);
  }
}
</style>
