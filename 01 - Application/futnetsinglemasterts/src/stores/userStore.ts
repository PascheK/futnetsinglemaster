/* eslint-disable prefer-const */
import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import User from '@/utils/beans/User';
import { useRouter } from 'vue-router'
import { axios } from '@/utils/axios.js'

export const useUserStore = defineStore('user', () => {
  const user = ref({} as User);
  const connected = ref(false);

  const isAdmin = computed(() => {
    try {
      return connected.value && user.value.role === "ADMIN";
    }catch{
      return false;
    }
  })

  const router = useRouter();

  function init() {
    try {
      let sessionString = localStorage.getItem('userStore');

      if(sessionString === null) throw new Error;

      let session = JSON.parse(sessionString);
      if(session.connected == undefined || session.user == undefined || session.connected == false) throw new Error;

      let sessionUser = session.user as User;

      if(sessionUser.idUser == undefined || sessionUser.nom == undefined ||sessionUser.prenom == undefined || sessionUser.role == undefined|| sessionUser.nomEquipe == undefined || sessionUser.mail == undefined) throw new Error;


      user.value = new User(sessionUser.idUser, sessionUser.nom, sessionUser.prenom, sessionUser.role, sessionUser.nomEquipe, sessionUser.mail);
      connected.value = true;
      router.push("/");
    }
    catch {
      destroy();
    }
    
  }

  function save() {
    localStorage.setItem('userStore', JSON.stringify({user: user.value, connected: connected.value})); 
    router.push("/");
  }

  function destroy(){
    connected.value = false;
    user.value = {} as User;
    localStorage.removeItem('userStore'); 
    router.push("/login");
  }

  function login(username:string, password:string){
    axios({
      method: 'POST',
      url: 'auth/login',
      data: {
        username: username,
        password: password
      }
    })
      .then(function (response: any) {
        if (response.status == 200) {
          if (response.data.responseObject != null) {
            let loggedUser = response.data.responseObject;
            connected.value = true;
            user.value = new User(loggedUser.id, loggedUser.nom, loggedUser.prenom, loggedUser.role, loggedUser.nomEquipe, loggedUser.mail);
            save();
            //router.push("/");
          }
        }
        console.log(response)
      });
  
  }

  function disconnect(){
    axios({
      method: 'POST',
      url: 'auth/logout',
      data: {}
    })
    .then(function (response: any) {
      if (response.status == 200) {         
          destroy();
      }
      console.log(response)
    });
  }

  return { user,connected, save, login, init, destroy, disconnect, isAdmin }
})
