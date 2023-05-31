<script setup>
import { RouterView } from 'vue-router'
</script>

<template>
  <RouterView />
</template>

<style lang="scss">
:root {
  --font-base: "DM Sans", sans-serif;

  --dark: #212529;
  --light-dark: #343C40;
  --grey: #e9ecef;
  --light-grey: #F0EFF4;
  --white: #f8f9fa;
  --red: #DA1E37;
  --light-red: rgba(218, 30, 55, .75);
  --green: #55A630;
  --orange: #F77F00;

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
</style>
