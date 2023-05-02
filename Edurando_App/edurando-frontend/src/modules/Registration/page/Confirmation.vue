<template>
  <div class="flex items-center justify-center h-screen bg-gray-100">
    <div class="max-w-md px-6 py-4 bg-white shadow-lg rounded-md">
      <h2 class="text-xl font-bold mb-4">E-Mail-Verifizierung</h2>
      <p class="mb-6">Vielen Dank für Ihre Registrierung! Wir haben eine E-Mail an <strong>{{ email }}</strong> gesendet. Bitte klicken Sie auf den Link in der E-Mail, um Ihre E-Mail-Adresse zu bestätigen.</p>
      <p class="mb-6">Wenn Sie die E-Mail nicht erhalten haben, überprüfen Sie bitte Ihren Spam-Ordner oder <a href="#">senden Sie die E-Mail erneut</a>.</p>
      <button class="bg-blue-500 hover:bg-blue-600 text-white py-2 px-4 rounded-md" @click="resendEmail">E-Mail erneut senden</button>
    </div>
  </div>
</template>
<script>


export default {
}
</script>
<style scoped>

</style>