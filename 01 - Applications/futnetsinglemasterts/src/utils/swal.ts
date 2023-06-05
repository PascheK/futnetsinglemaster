import Swal from 'sweetalert2'
export const swal = Swal.mixin({
  position: 'top',
  toast: true,
  timer: 2000,
  timerProgressBar: true,
  showConfirmButton: false
})
export const errorSwal = (msg: string) =>
  Swal.mixin({
    position: 'top',
    toast: true,
    timer: 3000,
    timerProgressBar: true,
    showConfirmButton: false,
    title: msg,
    icon: 'error'
  })
