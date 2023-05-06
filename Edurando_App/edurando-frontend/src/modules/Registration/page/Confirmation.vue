<template>
  <div class="flex flex-col items-center justify-center h-screen bg-gray-100">
    <div class="max-w-md px-6 py-4 bg-white shadow-lg rounded-md">
      <h2 class="text-xl font-bold mb-4 text-black">E-Mail-Verifizierung</h2>
      <p class="mb-6 text-black">Vielen Dank für Ihre Registrierung! Wir haben eine E-Mail an <strong>{{ email }}</strong> gesendet. Bitte klicken Sie auf den Link in der E-Mail, um Ihre E-Mail-Adresse zu bestätigen.</p>
      <p class="mb-6 text-black">Wenn Sie die E-Mail nicht erhalten haben, überprüfen Sie bitte Ihren Spam-Ordner oder <a href="#">senden Sie die E-Mail erneut</a>.</p>
      <div class="flex flex-row items-center justify-center text-center">
        <button class="bg-[#483d8b] hover:bg-[#483d8b] text-white py-2 px-4 rounded-md mr-4" @click="resendEmail">E-Mail erneut senden</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Confirmation',
  data() {
    return {
      email: ''
    }
  },
}
</script>

<style scoped>

</style>