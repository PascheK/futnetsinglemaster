<script setup lang="ts">
import { ref, onMounted } from 'vue'

import TopBar from '../components/TopBar.vue'
import { useClassementStore } from '@/stores/classementStore'
import Classement from '@/utils/beans/Classement'

import DataTable from 'datatables.net-vue3'
import DataTablesCore from 'datatables.net-bs5'
import 'datatables.net-select'
import 'datatables.net-responsive'

DataTable.use(DataTablesCore)

const classementStore = useClassementStore()

onMounted(() => {
  classementStore.fetchClassement()
})
const columns = [
  { data: 'userID' },
  { data: 'nom' },
  { data: 'prenom' },
  { data: 'nomEquipe' },
  { data: 'score' }
]

const options = {
  responsive: true,
  select: true,
  language: {
    "lengthMenu": "Afficher _MENU_ entrées par page",
    "zeroRecords": "Aucun élément trouvé - Désolé",
    "info": "Affichage de la page _PAGE_ sur _PAGES_",
    "infoEmpty": "Aucune entrée valide",
    "infoFiltered": "(filtrer à partir de _MAX_ enregistrements totaux)",
    "search": "Recherche",
    "paginate": {
      "next": "Suivant",
      "previous": "Précédant"
    },
    "select": {
      "rows": "<br>%d lignes séléctionnées"
    }
  },
  columnDefs: [
    {
      target: 0,
      searchable: false,
      width: 50
    },
    {
      target: 4,
      searchable: false,
      width: 50
    }
  ],
  order: [[4, 'desc']],
}
</script>

<template>
  <TopBar section-title="Classement" />
  <div class="classement">
    <div class="tab">
      <h3>Classement</h3>
      <DataTable class="table table-hover table-striped" :data="classementStore.classement" :columns="columns"
        :options="options">
        <thead>
          <tr>
            <th>ID</th>
            <th>Nom</th>
            <th>Prénom</th>
            <th>Équipe</th>
            <th>Score</th>
          </tr>
        </thead>
      </DataTable>
    </div>
  </div>
</template>

<style lang="scss">
@import 'bootstrap';
@import 'datatables.net-bs5';

.classement {
  display: flex;
  justify-content: center;
  background-color: var(--white);
  margin: 1rem;
  border-radius: 15px;
  padding: 2rem;
  box-shadow: 0 0 16px 3px rgba(0, 0, 0, 0.16);

  .tab {
    width: 100%;

    .table {
      tr {
        padding: 0 100px;
      }
    }
  }

  .page-link.active,
  .active>.page-link {
    color: var(--pagination-active-color);
    background-color: var(--pagination-active-bg);
    border-color: var(--pagination-active-border-color);
  }

  .page-link,
  .page-link.disabled,
  .disabled>.page-link {
    color: var(--pagination-disabled-color);
    pointer-events: none;
    background-color: var(--pagination-disabled-bg);
    border-color: var(--pagination-disabled-border-color);
  }
  .page-item:hover .page-link{
    color: var(--pagination-active-color);
    background-color: var(--pagination-active-bg);
    border-color: var(--pagination-active-border-color);
  }

  .form-control {
    color: var(--body-color);
    background-color: var(--body-bg);
    border: var(--bs-border-width) solid var(--border-color);
  }

  table.dataTable.table-hover>tbody>tr.selected:hover>*,
  table.dataTable>tbody>tr.selected>*,
  table.dataTable.table-striped>tbody>tr.odd.selected>* {
    box-shadow: inset 0 0 0 9999px rgba(var(--red-rgb), 0.975);
  }
}
</style>
