<script setup>
import { ref } from 'vue'
import {useRouter} from 'vue-router'
import {axios} from '@/utils/axios.js'

const router = useRouter();

const username = ref('');
const password = ref('');
function login(){
  axios({
  method: 'POST',
  url: 'auth/login',
  data: {
    username:  username.value,
    password: password.value
  }
})
  .then(function (response) {
    if(response.status == 200){
      response.data
      console.log(response.data)
    }
   console.log(response)
  });


  //router.push("/");
}
</script>

<template>
    <div class="login-container">
      <div class="login-wrapper">
        <div class="login">
          <div class="header">
            <img src="@/assets/logo.svg" ref="" />
            <h3>FutNet Single <br> Master</h3>
          </div>
          <div class="loginForm">
            <form>
              <div class="group">
                <input type="text" name="username" v-model="username" :class="{ 'used': username !== '' }" required><span
                  class="highlight"></span><span class="bar"></span>
                <label> <span class="material-symbols-outlined">person</span>
                  Nom d'utilisateur</label>
              </div>
              <div class="group">
                <input type="password" name="username" v-model="password" :class="{ 'used': password !== '' }"
                  required><span class="highlight"></span><span class="bar"></span>
                <label> <span class="material-symbols-outlined">password </span>
                  Mot de passe</label>
              </div>
              <div class="button">
                <button type="submit" class="login-submit" id="login_button" @click.prevent="login()">
                  Connexion
                </button>
              </div>

            </form>
          </div>
        </div>
      </div>
    </div>
</template>

<style lang="scss" scoped>
.login-container {
  display: flex;
  flex-direction: row;
  min-height: 100vh;
  width: 100%;

  .login-wrapper {
    flex: 1 1;
    flex-direction: column;
    padding: 4rem 2rem;
    width: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    @media (max-width: 550px) {
      padding: 0 1rem;
    }
  }
}

.login {
  width: 400px;
  display: flex;
  flex-direction: column;
  flex-wrap: nowrap;
  @media (max-width: 550px) {
    width: 100%;
  }
  .header {
    border-radius: 10px 10px 0px 0;
    display: flex;
    background-color: var(--light-dark);
    padding: 10px;

    img {
      width: 40px;
    }

    h3 {
      color: var(--white);
      padding-left: 30px;
      font-size: 26px;
    }
  }

  .loginForm {
    background-color: var(--white);
    padding: 2.5rem;
    border-radius: 0px 0 10px 10px;
    box-shadow: 0 0 16px 3px rgba(0, 0, 0, 0.16);

    h3 {
      padding-bottom: 2rem;

    }

    .group {
      position: relative;
      margin-bottom: 45px;

      input {
        font-size: 18px;
        padding: 10px 10px 10px 5px;
        -webkit-appearance: none;
        display: block;
        background: #fafafa;
        color: #636363;
        width: 100%;
        border: none;
        border-radius: 0;
        border-bottom: 1px solid #757575;

        &:focus {
          outline: none;
        }

      }

      input:focus~label,
      .used~label {
        top: -20px;
        transform: scale(.75);
        left: -2px;
        color: var(--red);
      }

      label {
        color: #999;
        font-size: 18px;
        font-weight: normal;
        position: absolute;
        pointer-events: none;
        left: 5px;
        top: 10px;
        transition: all 0.2s ease;
        display: flex;

        span {
          padding: 0 7px 0px 0px;
        }
      }



      /* Underline */

      .bar {
        position: relative;
        display: block;
        width: 100%;
      }

      .bar:before,
      .bar:after {
        content: '';
        height: 2px;
        width: 0;
        bottom: 1px;
        position: absolute;
        background: var(--red);
        transition: all 0.2s ease;
      }

      .bar:before {
        left: 50%;
      }

      .bar:after {
        right: 50%;
      }


      /* active */
      input:focus~.bar:before,
      input:focus~.bar:after {
        width: 50%;
      }


      /* Highlight */
      .highlight {
        position: absolute;
        height: 60%;
        width: 100px;
        top: 25%;
        left: 0;
        pointer-events: none;
        opacity: 0.5;
      }

      /* active */
      input:focus~.highlight {
        animation: inputHighlighter 0.3s ease;
      }

      /* Animations */

      @keyframes inputHighlighter {
        from {
          background: var(--red);
        }

        to {
          width: 0;
          background: transparent;
        }
      }

    }


    .button {
      display: flex;
      width: 100%;
      justify-content: center;

      button {
        width: 140px;
        height: 45px;
        font-size: 12px;
        text-transform: uppercase;
        letter-spacing: 2px;
        font-weight: 600;
        color: #fff;
        background-color: var(--dark);
        border: none;
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
  }
}
</style>