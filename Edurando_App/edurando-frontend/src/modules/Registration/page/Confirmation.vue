<template>
  <div class="flex flex-col items-center justify-center h-screen bg-gray-100 dark:bg-[#181818]">
    <div class="max-w-md px-6 py-4 bg-white dark:bg-[#c6c5d1] shadow-lg rounded-md">
      <h2 class="text-xl font-bold mb-4 text-black">E-Mail-Verifizierung</h2>
      <p class="mb-6 text-black">Vielen Dank für Ihre Registrierung! Wir haben eine E-Mail an ihre E-Mail gesendet. Bitte klicken Sie auf den Link in der E-Mail, um Ihre E-Mail-Adresse zu
        bestätigen.</p>
      <button @click="goLogin()" type="submit"
              class=" text-center mx-auto w-1/2 flex justify-center py-2 px-4 border border-transparent text-sm font-medium rounded-md text-white bg-[#483d8b] hover:bg-purple-950 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500">
        Kehre zur Loginseite zurück
      </button>
    </div>
  </div>
</template>

<script setup>
import {useRouter} from "vue-router";

const router = useRouter();

function goLogin() {
  router.push('/login')
}

</script>

<style scoped>

</style>