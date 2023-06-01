<script setup lang="ts">
import { ref } from 'vue'
import { useUserStore } from '@/stores/userStore'

const userStore = useUserStore()

const isExpended = ref(localStorage.getItem('isExpended') === 'true')
function toggleMenu() {
  isExpended.value = !isExpended.value
  localStorage.setItem('isExpended', '' + isExpended.value)
}

function logOut() {
  userStore.disconnect()
}
</script>

<template>
  <div class="sidebar" :class="{ expanded: isExpended }">
    <aside :class="`${isExpended && 'is-expanded'}`">
      <div class="logo">
        <img src="@/assets/logo.svg" alt="Vue" />
        <h3>
          FutNet Single<br />
          Master
        </h3>
      </div>
      <div class="menu-toggle-wrap">
        <div class="menu-toggle">
          <span class="material-icons" @click="toggleMenu()"> chevron_right </span>
        </div>
      </div>
      <h3>Menu</h3>
      <div class="menu">
        <router-link class="button" to="/">
          <span class="material-icons">dashboard</span>
          <span class="text">Classement</span>
        </router-link>
        <router-link class="button" to="/rencontre" active-class="active">
          <span class="material-icons">today</span>
          <span class="text">Rencontre</span>
        </router-link>
        <router-link
          v-if="userStore.isAdmin"
          class="button"
          to="/inscription"
          active-class="active"
        >
          <span class="material-icons">group</span>
          <span class="text">Inscriptions</span>
        </router-link>
        <div class="section-div">
          <router-link class="button" to="/settings" active-class="active">
            <span class="material-icons">settings</span>
            <span class="text">Options</span>
          </router-link>
        </div>
      </div>
      <div class="flex"></div>
      <div class="menu">
        <router-link class="link" to="/help" active-class="active">
          <span class="text">Help</span>
        </router-link>
        <router-link class="link" to="/contact" active-class="active">
          <span class="text">Contact us</span>
        </router-link>
        <router-link class="button" to="/login" @click.prevent="logOut()">
          <span class="material-icons">logout</span>
          <span class="text">Log out</span>
        </router-link>
      </div>
    </aside>
  </div>
</template>

<style lang="scss" scoped>
.sidebar {
  margin-right: var(--sidebar-width-close);
}
.expanded {
  margin-right: var(--sidebar-width);
  @media (max-width: 1000px) {
    margin-right: var(--sidebar-width-close);
  }
}
aside {
  position: fixed;
  display: flex;
  flex-direction: column;
  width: calc(2rem + 32px);
  overflow: hidden;
  min-height: 100vh;
  padding: 1rem;

  background-color: var(--dark);
  color: var(--white);

  transition: 0.2s ease-out;

  @media (max-width: 1000px) {
    position: fixed;
    z-index: 99;
  }

  .flex {
    flex: 1 0;
  }

  .menu-toggle-wrap {
    display: flex;
    justify-content: flex-end;
    margin-bottom: 1rem;

    position: relative;
    transition: 0.2s ease-out;

    .menu-toggle {
      position: fixed;
      bottom: 2.5rem;
      transition: 0.2s ease-out;
      transform: rotate(0deg) translateX(2rem);

      .material-icons {
        background-color: var(--dark);
        border-radius: 10px;
        font-size: 2rem;
        color: var(--white);
        transition: 0.2s ease-out;
      }

      &:hover {
        .material-icons {
          color: var(--red);
          transform: translateX(0.5rem);
        }
      }
    }
  }

  h3,
  .button .text {
    opacity: 0;
    transition: 0.3s ease-out;
  }

  .link {
    display: none !important;
  }

  h3 {
    color: var(--grey);
    font-size: 0.875rem;
    margin-bottom: 0.5rem;
    text-transform: uppercase;
  }

  .menu {
    margin: 0 -1rem;

    .section-div {
      padding-top: 4rem;

      .button {
        border-top: 1px solid var(--light-dark);
      }
    }

    .link {
      display: flex;
      align-items: center;
      text-decoration: none;

      padding: 0.5rem 1rem;
      transition: 0.2s ease-out;

      .text {
        color: var(--white);
        transition: 0.2s ease-out;
      }

      &:hover,
      &.router-link-exact-active {
        background-color: var(--light-dark);

        .text {
          color: var(--red);
        }
      }

      &.router-link-exact-active {
        border-right: 5px solid var(--red);
      }

      .active {
        background-color: var(--light-dark);
        border-right: 5px solid var(--red);

        .text {
          color: var(--red);
        }
      }
    }

    .button {
      display: flex;
      align-items: center;
      text-decoration: none;

      padding: 0.5rem 1rem;
      transition: 0.2s ease-out;

      .material-icons {
        font-size: 2rem;
        color: var(--white);
        transition: 0.2s ease-out;
      }

      .text {
        color: var(--white);
        transition: 0.2s ease-out;
      }

      &:hover,
      &.router-link-exact-active {
        background-color: var(--light-dark);

        .material-icons {
          color: var(--red);
        }
      }

      &.router-link-exact-active {
        border-right: 5px solid var(--red);
      }

      &.active {
        background-color: var(--light-dark);
        border-right: 5px solid var(--red);

        .material-icons {
          color: var(--red);
        }
      }
    }
  }

  &.is-expanded {
    width: var(--sidebar-width);

    .menu {
      padding: 0 16px;
    }

    .menu-toggle-wrap {
      .menu-toggle {
        transform: rotate(-180deg) translateX(-2rem);
      }
    }

    h3,
    .button .text {
      opacity: 1;
    }

    .link {
      display: flex !important;
    }
  }

  .logo {
    margin-bottom: 1rem;
    display: flex;

    h3 {
      padding-left: 2rem;
      margin: 0 !important;
      color: var(--white);
    }

    img {
      width: 2rem;
    }
  }

  .button {
    .material-icons {
      margin-right: 1rem;
    }
  }
}
</style>
