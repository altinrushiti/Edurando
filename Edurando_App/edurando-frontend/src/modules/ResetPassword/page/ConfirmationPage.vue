<script setup>
import {useRouter} from "vue-router";
import axios from "axios";
import {onMounted, ref} from "vue";
import {useEmailStore} from "@/modules/ResetPassword/emailStore";

const router = useRouter();
const emailStore = useEmailStore();
const result = ref("")

const email = ref(emailStore.email)
const confirmationNumber = ref("")
async function confirmNumber() {
  let formData = new FormData();
  formData.append('email', email.value);
  formData.append('enteredCode', confirmationNumber.value);
  try {
  const response = await axios.post('/confirmCode', formData);
  result.value = response.data;
  if (result.value) {
    emailStore.email = email.value;}
  await router.push('/passwordform');
  console.log(result.value);
} catch (error) {
  result.value = error.request.response;
  console.log(result.value);
}

}
function goBack() {

  router.back()
}
</script>

<template>
  <div class="min-h-screen bg-gray-100 dark:bg-[#181818] flex items-center justify-center py-12 px-4 sm:px-6 lg:px-8 mt-10">
    <div class="max-w-md w-full space-y-8">
      <div class="border border-white bg-white rounded-md p-8">
        <div class="bg-white rounded-md p-8">
          <h2 class="mt-4 text-center text-3xl font-extrabold text-gray-900 dark:text-[#b5a9fc]">
            Confirm Number
          </h2>
          <p class="mt-2 text-center text-gray-600 dark:text-gray-400">
            Please enter the number you received via email to confirm.
            {{ emailStore.email }}
          </p>
        </div>
        <form class="mt-3 space-y-6 bg-white rounded-md p-8" @submit.prevent="confirmNumber">
          <div class="rounded-md shadow-sm space-y-2">
            <div>
              <label for="confirmation-number" class="text-black dark:text-[#b5a9fc] font-font-family p-2">Confirmation Number</label>
              <input id="confirmation-number" name="confirmation-number" type="number"
                     autocomplete="off" required
                     class="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 dark:bg-[#c6c5d1] dark:border-[#9895ad] dark:text-black focus:outline-none rounded-t-md focus:ring-purple-500 focus:border-purple-500 focus:z-10 sm:text-sm"
                     placeholder="Confirmation Number" v-model="confirmationNumber">
            </div>
            <div class="flex justify-between">
              <button type="submit"
                      class="w-1/2 flex justify-center py-2 px-4 border border-transparent text-sm font-medium rounded-md text-purple-500 bg-transparent hover:bg-purple-100 dark:hover:bg-[#26233b] focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-purple-500">
                Confirm
              </button>
              <button @click="goBack" type="button"
                      class="w-1/2 flex justify-center py-2 px-4 border border-transparent text-sm font-medium rounded-md text-gray-500 bg-transparent hover:bg-gray-100 dark:hover:bg-[#26233b] focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-gray-500">
                Back
              </button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>



</template>

<style scoped>

</style>