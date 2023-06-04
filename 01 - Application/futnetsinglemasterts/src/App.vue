<script setup lang="ts">
import { RouterView } from 'vue-router'
import { useUserSessionStore } from '@/stores/userSessionStore'

const userStore = useUserSessionStore()

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
  --40dd4f07: var(--red);

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

body {
  height: 100vh;
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

.tab {
  width: 100%;
}

.action {
  display: flex;
  justify-content: space-between;
  padding: 20px 0;
  align-items: flex-end;

  .button {
    button {
      width: 140px;
      height: 45px;
      font-size: 12px;
      text-transform: uppercase;
      letter-spacing: 2px;
      font-weight: 600;
      color: var(--light-red);
      background-color: transparent;
      border: 1px solid var(--red);
      border-radius: 5px;
      box-shadow: 0px 8px 15px rgba(0, 0, 0, 0.1);
      transition: all 0.3s ease 0s;
      cursor: pointer;
      outline: none;
    }

    button:hover {
      background-color: var(--red);
      box-shadow: 0px 15px 20px var(--light-red);
      color: #fff;
      transform: translateY(-7px);
    }
  }

  .search {
    input {
      border: 1px solid var(--red);
      border-radius: 7px;
      padding: 5px;
      color: var(--light-red);
      background-color: transparent;

      &:focus {
        box-shadow: 0px 05px 20px var(--light-red);
        outline: none;
      }
    }
  }
}

span {
  &.edit {
    color: var(--orange);
  }

  &.delete {
    color: var(--red);
  }

  &.done {
    color: var(--green);
  }

  &.close {
    color: var(--red);
  }
}

.form-outline {
  padding: 0 20px;
}

@media (max-width: 1020px) {
  .section-bg {
    margin: 10px auto;
    width: 90%;
  }
}
@media (max-width: 550px) {
  .section-bg {

  }
  .action{
    flex-direction: column;
    align-items: flex-start;
    &>*{
      margin: 10px 0;
    }
  }
}
</style>
