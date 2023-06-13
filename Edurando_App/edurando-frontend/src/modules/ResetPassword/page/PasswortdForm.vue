<script setup>
import {reactive, ref} from "vue";
import {showPasswordRepeatError} from "@/functions/functions";
import axios from "axios";
import {useRouter} from "vue-router";
import {useEmailStore} from "@/modules/ResetPassword/emailStore";
const router = useRouter();
const emailStore = useEmailStore();
const password = reactive({
  email: emailStore.email,
  newPassword: '',
  newPasswordRepeat: '',
});
async function onSetPassword() {
  if (password.password!== password.confirmPassword && !password.password.email) {
    alert('Passwords do not match')
    return
  }
  try {
    const response = await axios.post('/resetPassword', password)
    console.log(response)
    await router.push('/login')
  } catch (error) {
    console.log(error)
  }
}
function goBack() {
  router.back();
}
</script>

<template>
  <div class="min-h-screen bg-gray-100 dark:bg-[#181818] flex items-center justify-center py-12 px-4 sm:px-6 lg:px-8 mt-10">
    <div class="max-w-md w-full space-y-8">
      <div class="border border-white bg-white rounded-md p-8">
        <div class="bg-white rounded-md p-8">
          <h2 class="mt-4 text-center text-3xl font-extrabold text-gray-900 dark:text-[#b5a9fc]">
            Set Password
          </h2>
        </div>
        <form class="mt-3 space-y-6 bg-white rounded-md p-8" @submit.prevent="onSetPassword">
          <div class="rounded-md shadow-sm space-y-2">
            <div>
              <label for="password" class="text-black dark:text-[#b5a9fc] font-font-family p-2">New Password</label>
              <input id="password" name="password" type="password"
                     autocomplete="off" required
                     class="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 dark:bg-[#c6c5d1] dark:border-[#9895ad] dark:text-black focus:outline-none rounded-t-md focus:ring-purple-500 focus:border-purple-500 focus:z-10 sm:text-sm"
                     placeholder="New Password" v-model="password.newPassword">
            </div>
            <div>
              <label for="confirmPassword" class="text-black dark:text-[#b5a9fc] font-font-family p-2">Confirm Password</label>
              <input id="confirmPassword" name="confirmPassword" type="password"
                     autocomplete="off" required
                     class="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 dark:bg-[#c6c5d1] dark:border-[#9895ad] dark:text-black focus:outline-none focus:ring-purple-500 focus:border-purple-500 focus:z-10 sm:text-sm"
                     placeholder="Confirm Password" v-model="password.newPasswordRepeat">
            </div>
            <div class="flex justify-between">
              <button type="submit"
                      class="w-1/2 flex justify-center py-2 px-4 border border-transparent text-sm font-medium rounded-md text-purple-500 bg-transparent hover:bg-purple-100 dark:hover:bg-[#26233b] focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-purple-500">
                Set Password
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
