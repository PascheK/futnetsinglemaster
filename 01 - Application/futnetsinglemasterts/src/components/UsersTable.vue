<script lang="ts" setup>
import { ref, onMounted } from 'vue'
import { swal } from '@/utils/swal'
import Swal from 'sweetalert2'
import type { Header, Item } from 'vue3-easy-data-table'

import { useUsersStore } from '@/stores/usersStore'
import { useEquipeStore } from '@/stores/equipeStore'
import { useRoleStore } from '@/stores/roleStore'

const equipeStore = useEquipeStore()
const usersStore = useUsersStore()
const rolesStore = useRoleStore()
const items = ref([] as Item)
const loading = ref(true)
const searchValue = ref('')

onMounted(async () => {
  setTimeout(async () => {
    await usersStore.fetchUsers()
    loading.value = false
    items.value = usersStore.users
  }, 2000)
})

const headers: Header[] = [
  { text: 'ID', value: 'id', sortable: true },
  { text: 'NOM', value: 'nom', sortable: true },
  { text: 'PRENOM', value: 'prenom', sortable: true },
  { text: 'EQUIPE', value: 'nomEquipe', sortable: true },
  { text: 'EMAIL', value: 'mail' },
  { text: 'USERNAME', value: 'username' },
  { text: 'ROLE', value: 'role' },
  { text: 'Operation', value: 'operation' }
]

const editItem = async (val: Item) => {
  await equipeStore.fetchEquipe()
  await rolesStore.fetchRole()
  let optionsEquipes = ''
  let optionsRoles = ''

  equipeStore.equipes.forEach((e) => {
    if (e.nomEquipe.match(val.nomEquipe)) {
      optionsEquipes += `<option selected value="${e.id}">${e.nomEquipe}</option>`
    } else {
      optionsEquipes += `<option value="${e.id}">${e.nomEquipe}</option>`
    }
  })
  rolesStore.roles.forEach((e) => {
    if (e.role.match(val.role)) {
      optionsRoles += `<option selected value="${e.id}">${e.role}</option>`
    } else {
      optionsRoles += `<option value="${e.id}">${e.role}</option>`
    }
  })

  Swal.fire({
    title: 'Inscription',
    html: `<div class="row mb-4">
   <div class="col">
      <div class="form-outline">
         <label class="form-label" for="registerNom">Nom</label>
         <input type="text" id="registerNom" class="form-control" value="${val.nom}"/>
      </div>
   </div>
   <div class="col">
      <div class="form-outline">
         <label class="form-label" for="registerPrenom">Prénom</label>
         <input type="text" id="registerPrenom" class="form-control" value="${val.prenom}"/>
      </div>
   </div>
</div>
<div class="form-outline mb-4">
   <label class="form-label" for="registerMail">Email</label>
   <input type="email" id="registerMail" class="form-control" value="${val.mail}"/>
</div>
<div class="row mb-4">
   <div class="col">
      <div class="form-outline">
         <label class="form-label" for="registerEquipe">Équipe</label>
         <select class="form-select" id="registerEquipe" aria-label="Default select example">
            <option value="0" selected>Séléctionner une équipe</option>
          ${optionsEquipes}
         </select>
      </div>
   </div>
   <div class="col">
      <div class="form-outline">
         <label class="form-label" for="registerRole">Roles</label>
         <select class="form-select" id="registerRole"  aria-label="Default select example">
            <option value="0" selected>Séléctionner un rôle</option>
            ${optionsRoles}
         </select>
      </div>
   </div>
</div>`,
    confirmButtonText: 'Inscrire',
    focusConfirm: false,
    preConfirm: () => {
      const nom = (Swal.getPopup()?.querySelector('#registerNom') as HTMLInputElement | null)?.value
      const prenom = (Swal.getPopup()?.querySelector('#registerPrenom') as HTMLInputElement | null)
        ?.value
      const mail = (Swal.getPopup()?.querySelector('#registerMail') as HTMLInputElement | null)
        ?.value
      const equipe = (Swal.getPopup()?.querySelector('#registerEquipe') as HTMLOptionElement | null)
        ?.value
      const role = (Swal.getPopup()?.querySelector('#registerRole') as HTMLOptionElement | null)
        ?.value
      if (!validateEmail(mail)) Swal.showValidationMessage(`Veuillez entrer un mail correcte!`)
      if (nom == '' || prenom == '' || equipe == 0 || role == 0) {
        Swal.showValidationMessage(`Veuillez entrer des informations correctes!`)
      }
      return { idUser: val.id, nom: nom, prenom: prenom, mail: mail, equipe: equipe, role: role }
    }
  }).then(async (result) => {
    if (result.isConfirmed) {
      if (
        await usersStore.putUser(
          result.value?.idUser,
          result.value?.nom,
          result.value?.prenom,
          result.value?.mail,
          result.value?.role,
          result.value?.equipe
        )
      ) {
        items.value.find((u) => u.id == result.value?.idUser).nom = result.value?.nom
        items.value.find((u) => u.id == result.value?.idUser).prenom = result.value?.prenom
        items.value.find((u) => u.id == result.value?.idUser).mail = result.value?.mail
        items.value.find((u) => u.id == result.value?.idUser).equipe = equipeStore.equipes.find(
          (e) => e.id == result.value?.equipe
        )?.nomEquipe
        items.value.find((u) => u.id == result.value?.idUser).role = rolesStore.roles.find(
          (e) => e.id == result.value?.equipe
        )?.role
      }
    }
  })
}

function validateEmail(mail: string): boolean {
  var validRegex = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/g
  if (mail.match(validRegex)) return true
  return false
}
const deleteItem = (val: Item) => {
  Swal.fire({
    title: 'Êtes-vous sûr?',
    text: 'Vous ne pourrez plus revenir en arrière!',
    icon: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#3085d6',
    cancelButtonColor: '#d33',
    confirmButtonText: 'Oui, supprimer!',
    cancelButtonText: 'Annuler'
  }).then(async (result) => {
    if (result.isConfirmed) {
      if (await usersStore.deleteUserById(val.id)) {
        swal.fire({
          title: 'Joueur supprimé',
          text: '',
          icon: 'success'
        })
        items.value = items.value.filter((item: any) => item.id !== val.id)
      }
    }
  })
}

const addUser = async () => {
  await equipeStore.fetchEquipe()
  await rolesStore.fetchRole()
  let optionsEquipes = ''
  let optionsRoles = ''

  equipeStore.equipes.forEach((e) => {
    optionsEquipes += `<option value="${e.id}">${e.nomEquipe}</option>`
  })
  rolesStore.roles.forEach((e) => {
    optionsRoles += `<option value="${e.id}">${e.role}</option>`
  })

  Swal.fire({
    title: 'Inscription',
    html: `<div class="row mb-4">
   <div class="col">
      <div class="form-outline">
         <label class="form-label" for="registerNom">Nom</label>
         <input type="text" id="registerNom" class="form-control" value=""/>
      </div>
   </div>
   <div class="col">
      <div class="form-outline">
         <label class="form-label" for="registerPrenom">Prénom</label>
         <input type="text" id="registerPrenom" class="form-control" value=""/>
      </div>
   </div>
</div>
<div class="form-outline mb-4">
   <label class="form-label" for="registerMail">Email</label>
   <input type="email" id="registerMail" class="form-control" value=""/>
</div>
<div class="row mb-4">
   <div class="col">
      <div class="form-outline">
         <label class="form-label" for="registerEquipe">Équipe</label>
         <select class="form-select" id="registerEquipe" aria-label="Default select example">
            <option value="0" selected>Séléctionner une équipe</option>
          ${optionsEquipes}
         </select>
      </div>
   </div>
   <div class="col">
      <div class="form-outline">
         <label class="form-label" for="registerRole">Roles</label>
         <select class="form-select" id="registerRole"  aria-label="Default select example">
            <option value="0" selected>Séléctionner un rôle</option>
            ${optionsRoles}
         </select>
      </div>
   </div>
</div>`,
    confirmButtonText: 'Inscrire',
    focusConfirm: false,
    preConfirm: () => {
      const nom = (Swal.getPopup()?.querySelector('#registerNom') as HTMLInputElement | null)?.value
      const prenom = (Swal.getPopup()?.querySelector('#registerPrenom') as HTMLInputElement | null)
        ?.value
      const mail = (Swal.getPopup()?.querySelector('#registerMail') as HTMLInputElement | null)
        ?.value
      const equipe = (Swal.getPopup()?.querySelector('#registerEquipe') as HTMLInputElement | null)
        ?.value
      const role = (Swal.getPopup()?.querySelector('#registerRole') as HTMLInputElement | null)
        ?.value
      if (!validateEmail(mail)) Swal.showValidationMessage(`Veuillez entrer un mail correcte!`)
      if (nom == '' || prenom == '' || equipe == 0 || role == 0) {
        Swal.showValidationMessage(`Veuillez entrer des informations correctes!`)
      }
      return { nom: nom, prenom: prenom, mail: mail, equipe: equipe, role: role }
    }
  }).then(async (result) => {
    if (result.isConfirmed) {
      await usersStore.saveUser(
        result.value?.nom,
        result.value?.prenom,
        result.value?.mail,
        result.value?.role,
        result.value?.equipe
      )
    }
  })
}
</script>
<template>
  <button @click="addUser()">Ajouter un utilisateur</button>
  <span>search value: </span>
  <input type="text" v-model="searchValue" />
  <EasyDataTable
    alternating
    buttons-pagination
    :headers="headers"
    :search-value="searchValue"
    :items="items"
    :loading="loading"
    :rows-per-page="10"
  >
    <template #loading>
      <img
        src="https://cdn.dribbble.com/users/3742211/screenshots/9195657/media/6796a544d6f9ef1293d8d8d9e60d38d5.gif"
        style="height: 100px"
      />
    </template>
    <template #item-operation="item">
      <div class="operation-wrapper">
        <span class="material-icons edit" @click="editItem(item)"> edit </span>
        <span class="material-icons delete" @click="deleteItem(item)"> delete </span>
      </div>
    </template>
  </EasyDataTable>
</template>


<style lang="scss">
  
</style>