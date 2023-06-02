<script lang="ts" setup>
import TopBar from '../components/TopBar.vue'
import ClassementTable from '@/components/ClassementTable.vue'
</script>
<template>
  <TopBar section-title="Classement" />
  <div class="section-bg classement">
    <div class="tab">
      <h3>Classement</h3>
      <ClassementTable />
    </div>
  </div>
</template>

<style lang="scss">
.classement {
  .tab {
    width: 100%;
  }
}
.page-link.active,
.active > .page-link {
  color: var(--pagination-active-color);
  background-color: var(--pagination-active-bg);
  border-color: var(--pagination-active-border-color);
}

.page-link,
.page-link.disabled,
.disabled > .page-link {
  color: var(--pagination-disabled-color);
  pointer-events: none;
  background-color: var(--pagination-disabled-bg);
  border-color: var(--pagination-disabled-border-color);
}

.page-item:hover .page-link {
  color: var(--pagination-active-color);
  background-color: var(--pagination-active-bg);
  border-color: var(--pagination-active-border-color);
}

.form-control {
  color: var(--body-color);
  background-color: var(--body-bg);
  border: var(--bs-border-width) solid var(--border-color);
}

table.dataTable.table-hover > tbody > tr.selected:hover > *,
table.dataTable > tbody > tr.selected > *,
table.dataTable.table-striped > tbody > tr.odd.selected > * {
  box-shadow: inset 0 0 0 9999px rgba(var(--red-rgb), 0.975);
}
</style>
