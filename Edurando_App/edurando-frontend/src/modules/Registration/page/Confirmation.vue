<template>
  <div class="flex flex-col items-center justify-center h-screen bg-gray-100 dark:bg-[#181818]">
    <div class="max-w-md px-6 py-4 bg-white dark:bg-[#c6c5d1] shadow-lg rounded-md">
      <h2 class="text-xl font-bold mb-4 text-black">E-Mail-Verifizierung</h2>
      <p class="mb-6">Vielen Dank für Ihre Registrierung! Wir haben eine E-Mail an <strong>{{ email }}</strong> gesendet. Bitte klicken Sie auf den Link in der E-Mail, um Ihre E-Mail-Adresse zu bestätigen.</p>
      <p class="mb-6">Wenn Sie die E-Mail nicht erhalten haben, überprüfen Sie bitte Ihren Spam-Ordner oder senden Sie die E-Mail erneut.</p>

      <button class="bg-blue-500 hover:bg-blue-600 text-white py-2 px-4 rounded-md" @click="resendEmail">E-Mail erneut senden</button>
      <p v-if="result" class="text-green-500 text-xs mt-2">{{result}}</p>
    </div>
  </div>
</template>

<script setup>
import {useRouter} from "vue-router";
import axios from "axios";
import {ref} from "vue";
import {useEmailStore} from "@/modules/Registration/emailStore";

const router = useRouter();
const emailStore = useEmailStore()
const result = ref("")
const email = ref(emailStore.email)

async function resendEmail() {
  const response = await axios.post(`/reconfirm/?email=${email.value}`)
  result.value = response.data
}

</script>

<style scoped>

</style>