<script lang="ts" setup>
import { ref, onMounted } from 'vue'
import { swal } from '@/utils/swal'
import Swal from 'sweetalert2'
import type { Header, Item } from 'vue3-easy-data-table'
import { useUserSessionStore } from '@/stores/userSessionStore'
import { useUsersStore } from '@/stores/usersStore'
import { useRencontreStore } from '@/stores/rencontreStore'
import Rencontre from '@/utils/beans/Rencontre'

const rencontreStore = useRencontreStore()
const usersStore = useUsersStore()
const userStore = useUserSessionStore()
const items = ref([] as Item)
const loading = ref(true)
const searchField = ['joueur1', 'joueur2']
const searchValue = ref('')

onMounted(async () => {
  setTimeout(async () => {
    await usersStore.fetchUsers()
    await rencontreStore.fetchRencontre()
    loading.value = false
    items.value = rencontreStore.rencontre
  }, 2000)
})

const headers: Header[] = [
  { text: 'ID', value: 'idRencontre', sortable: true },
  { text: 'JOUEUR 1', value: 'joueur1', sortable: true },
  { text: 'SCORE', value: 'score1', sortable: true },
  { text: 'JOUEUR 2', value: 'joueur2', sortable: true },
  { text: 'SCORE', value: 'score2' },
  { text: 'DATE', value: 'date' },
  { text: 'VALIDE', value: 'valide' },
  { text: 'Operation', value: 'operation' }
]

const editItem = async (val: Item) => {
  let optionsUsers = `<option selected value="${val.joueur2}">${usersStore.users.find((u) => u.id == val.joueur2)?.nom +
    ' ' +
    usersStore.users.find((u) => u.id == val.joueur2)?.prenom
    }</option>`
  let currentUser = `<option selected value="${userStore.idUser}">${userStore.user.nom + ' ' + userStore.user.prenom
    }</option>`

  Swal.fire({
    title: 'Modification',
    html: `
    <div class="row mb-4">
   <div class="col">
      <div class="form-outline">
         <label class="form-label" for="registerJoueur1">Joueur 1</label>
         <select disabled class="form-select" id="registerJoueur1" aria-label="Default select example">
           ${currentUser}
         </select>
      </div>
   </div>
   <div class="col">
      <div class="form-outline">
         <label class="form-label" for="registerScore1">Score 1</label>
         <input type="number" min=0 max=15 id="registerScore1" class="form-control" value="${val.score1}"/>
      </div>
   </div>
</div>
<div class="row mb-4">
   <div class="col">
      <div class="form-outline">
         <label class="form-label" for="registerJoueur2">Joueur 2</label>
         <select disabled class="form-select" id="registerJoueur2" aria-label="Default select example">
            ${optionsUsers}
         </select>
      </div>
   </div>
   <div class="col">
      <div class="form-outline">
         <label class="form-label" for="registerScore2">Score 2</label>
         <input type="number" min=0 max=15 id="registerScore2" class="form-control" value="${val.score2}"/>
      </div>
   </div>
</div>
<div class="row mb-4">
   <div class="col">
      <div class="form-outline">
         <label class="form-label" for="registerDate">Date</label>
         <input type="date" id="registerDate" class="form-control" value="${val.date}"/>
      </div>
   </div>
   <div class="col">
    <div class="form-outline validation">
         <label class="form-label" for="registerRole">Validé</label>
         <span class="material-icons">
          close </span>
      </div>
   </div>
</div>
    `,
    confirmButtonText: 'Modifier',
    focusConfirm: false,
    preConfirm: () => {
      console.log(val)
      const score1 = (Swal.getPopup()?.querySelector('#registerScore1') as HTMLInputElement | null)
        ?.value
      const score2 = (Swal.getPopup()?.querySelector('#registerScore2') as HTMLInputElement | null)
        ?.value
      const date = (Swal.getPopup()?.querySelector('#registerDate') as HTMLInputElement | null)
        ?.value

      if ((score1 == 15 && score2 == 15) || (score1 != 15 && score2 != 15))
        Swal.showValidationMessage(`Il doit y avoir maximum un score équivalent à 15!`)
      if ((score1 < 0 && score1 > 15) || (score2 < 0 && score2 > 15) || date == null) {
        Swal.showValidationMessage(`Veuillez entrer des informations correctes!`)
      }
      return { rencontreID: val.idRencontre, score1: score1, score2: score2, date: date }
    }
  }).then(async (result) => {
    if (result.isConfirmed) {
      if (
        await rencontreStore.putRencontre(
          result.value?.rencontreID,
          result.value?.score1,
          result.value?.score2,
          result.value?.date
        )
      ) {
        items.value.find((u: Rencontre) => u.idRencontre == result.value?.rencontreID).score1 =
          result.value?.score1
        items.value.find((u: Rencontre) => u.idRencontre == result.value?.rencontreID).score2 =
          result.value?.score2
        items.value.find((u: Rencontre) => u.idRencontre == result.value?.rencontreID).date =
          result.value?.date
      }
    }
  })
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
      if (await rencontreStore.deleteRencontreById(val.idRencontre)) {
        swal.fire({
          title: 'Rencontre supprimé',
          text: '',
          icon: 'success'
        })
        items.value = items.value.filter((item: any) => item.idRencontre !== val.idRencontre)
      }
    }
  })
}

const valideItem = (val: Item) => {
  Swal.fire({
    title: 'Êtes-vous sûr?',
    text: 'Vous ne pourrez plus revenir en arrière!',
    icon: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#3085d6',
    cancelButtonColor: '#d33',
    confirmButtonText: 'Oui, valider!',
    cancelButtonText: 'Annuler'
  }).then(async (result) => {
    if (result.isConfirmed) {
      if (await rencontreStore.validateRencontre(val.idRencontre)) {
        swal.fire({
          title: 'Rencontre validé',
          text: '',
          icon: 'success'
        })
        items.value.find((item: Rencontre) => item.idRencontre == val.idRencontre).valide = true
      }
    }
  })
}

const addRencontre = async () => {
  let optionsUsers = ''
  let currentUser = `<option selected value="${userStore.idUser}">${userStore.user.nom + ' ' + userStore.user.prenom
    }</option>`
  await usersStore.fetchUsers()
  usersStore.users.forEach((e) => {
    if (e.id != userStore.idUser) {
      optionsUsers += `<option value="${e.id}">${e.nom + ' ' + e.prenom}</option>`
    }
  })

  Swal.fire({
    title: 'Ajouter une rencontre',
    html: `
    <div class="row mb-4">
   <div class="col">
      <div class="form-outline">
         <label class="form-label" for="registerJoueur1">Joueur 1</label>
         <select disabled class="form-select" id="registerJoueur1" aria-label="Default select example">
           ${currentUser}
         </select>
      </div>
   </div>
   <div class="col">
      <div class="form-outline">
         <label class="form-label" for="registerScore1">Score 1</label>
         <input type="number" min=0 max=15 id="registerScore1" class="form-control" value=""/>
      </div>
   </div>
</div>
<div class="row mb-4">
   <div class="col">
      <div class="form-outline">
         <label class="form-label" for="registerJoueur2">Joueur 2</label>
         <select class="form-select" id="registerJoueur2" aria-label="Default select example">
            <option value="0" selected>Séléctionner un joueur 2</option>
            ${optionsUsers}
         </select>
      </div>
   </div>
   <div class="col">
      <div class="form-outline">
         <label class="form-label" for="registerScore2">Score 2</label>
         <input type="number" min=0 max=15 id="registerScore2" class="form-control" value=""/>
      </div>
   </div>
</div>
<div class="row mb-4">
   <div class="col">
      <div class="form-outline">
         <label class="form-label" for="registerDate">Date</label>
         <input type="date" id="registerDate" class="form-control" value=""/>
      </div>
   </div>
   <div class="col">
      <div class="form-outline validation">
         <label class="form-label" for="registerRole">Validé</label>
         <span class="material-icons">
          close </span>
      </div>
   </div>
</div>
    `,
    confirmButtonText: 'Ajouter',
    focusConfirm: false,
    preConfirm: () => {
      const joueur2 = (
        Swal.getPopup()?.querySelector('#registerJoueur2') as HTMLOptionElement | null
      )?.value
      const score1 = (Swal.getPopup()?.querySelector('#registerScore1') as HTMLInputElement | null)
        ?.value
      const score2 = (Swal.getPopup()?.querySelector('#registerScore2') as HTMLInputElement | null)
        ?.value
      const date = (Swal.getPopup()?.querySelector('#registerDate') as HTMLInputElement | null)
        ?.value

      if ((score1 == 15 && score2 == 15) || (score1 != 15 && score2 != 15))
        Swal.showValidationMessage(`Veuillez entrer des informations correctes!`)

      if (
        joueur2 == 0 ||
        (score1 < 0 && score1 > 15) ||
        (score2 < 0 && score2 > 15) ||
        date == ''
      ) {
        Swal.showValidationMessage(`Veuillez entrer des informations correctes!`)
      }
      return { idUser2: joueur2, score1: score1, score2: score2, date: date }
    }
  }).then(async (result) => {
    if (result.isConfirmed) {
      await rencontreStore.addRencontre(
        result.value?.idUser2,
        result.value?.score1,
        result.value?.score2,
        result.value?.date
      )
    }
  })
}
</script>
<template>
  <div class="action">
    <div class="button">
      <button @click="addRencontre()">Ajouter une rencontre</button>

    </div>
    <div class="search">
      <input type="text" v-model="searchValue" placeholder="Rechercher 🔎" />
    </div>
  </div>
  <EasyDataTable alternating buttons-pagination :headers="headers" :search-value="searchValue" :search-field="searchField"
    :items="items" :loading="loading" :rows-per-page="10">
    <template #loading>
      <img src="https://cdn.dribbble.com/users/3742211/screenshots/9195657/media/6796a544d6f9ef1293d8d8d9e60d38d5.gif"
        style="height: 100px" />
    </template>
    <template #item-joueur1="joueur">
      {{
        usersStore.users.find((u) => u.id == joueur.joueur1)?.nom +
        ' ' +
        usersStore.users.find((u) => u.id == joueur.joueur1)?.prenom
      }}
    </template>
    <template #item-joueur2="joueur">
      {{
        usersStore.users.find((u) => u.id == joueur.joueur2)?.nom +
        ' ' +
        usersStore.users.find((u) => u.id == joueur.joueur2)?.prenom
      }}
    </template>
    <template #item-valide="valide">
      <span class="material-icons" :class="valide.valide == true ? 'done' : 'close'">
        {{ valide.valide == true ? 'done' : 'close' }}
      </span>
    </template>
    <template #item-operation="item">
      <div class="operation-wrapper">
        <span class="material-icons edit" @click="editItem(item)" v-if="userStore.idUser == item.joueur1 && !item.valide">
          edit
        </span>
        <span class="material-icons delete" @click="deleteItem(item)"
          v-if="userStore.idUser == item.joueur1 && !item.valide">
          delete
        </span>
        <span class="material-icons done" @click="valideItem(item)"
          v-if="userStore.idUser == item.joueur2 && !item.valide">
          done
        </span>
      </div>
    </template>
  </EasyDataTable>
</template>

<style lang="scss">
.validation {
  display: flex;
  flex-direction: column;
  align-items: center;
  .material-icons{
    font-size: 40px;
    color: var(--red);
  }
}
</style>
