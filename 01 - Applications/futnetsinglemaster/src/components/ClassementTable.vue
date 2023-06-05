<script setup lang="ts">
import { onMounted, ref } from 'vue'

import { useClassementStore } from '@/stores/classementStore'

import type { Header, Item, SortType } from 'vue3-easy-data-table'

const items = ref([] as Item)

const classementStore = useClassementStore()
const sortBy = 'score'
const sortType: SortType = 'desc'

onMounted(async () => {
  await classementStore.fetchClassement()
  items.value = classementStore.classement
})

const headers: Header[] = [
  { text: 'ID', value: 'userID', sortable: true },
  { text: 'NOM', value: 'nom', sortable: true },
  { text: 'PRENOM', value: 'prenom', sortable: true },
  { text: 'EQUIPE', value: 'nomEquipe', sortable: true },
  { text: 'SCORE', value: 'score', sortable: true }
]
const searchValue = ref('')
</script>
<template>
  <EasyDataTable
    alternating
    buttons-pagination
    :headers="headers"
    :search-value="searchValue"
    :items="items"
    :rows-per-page="10"
    :sort-by="sortBy"
    :sort-type="sortType"
  />
</template>

<style lang="scss"></style>
