<script setup>
import {useRouter} from "vue-router";
import axios from "axios";
import {onMounted, ref} from "vue";
import {useEmailStore} from "@/modules/ResetPassword/emailStore";

const router = useRouter();
const emailStore = useEmailStore();
const result = ref("")

const email = ref(emailStore.email)
const confirmationNumbers = ref(['', '', '', ''])
async function confirmNumber() {
  console.log(confirmationNumbers.value.join(''))
  let formData = new FormData();
  formData.append('email', email.value);
  formData.append('enteredCode', confirmationNumbers.value.join(''));
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

function handleKeyDown() {
  const otp = document.querySelectorAll('.input')
  otp.forEach((field, index) => {
    field.addEventListener('keydown', (e) => {
      if (e.key >= 0 && e.key <= 9) {
        otp[index].value = "";
        setTimeout(() => {
          otp[index + 1].focus();
        }, 4);
      } else if (e.key === 'Backspace') {
        setTimeout(() => {
          otp[index - 1].focus()
        }, 4)
      }
    })
  })

}

onMounted(() => {
  handleKeyDown()
})

/*const otp = document.querySelectorAll('.input')
otp[0].focus();
otp.forEach((field, index) => {
  field.addEventListener('keydown', (e) => {
    if (e.key >= 0 && e.key <= 9) {
      otp[index].value = "";
      setTimeout(() => {
        otp[index + 1].focus();
      }, 4);
    } else if (e.key === 'Backspace') {
      setTimeout(() => {
        otp[index - 1].focus()
      }, 4)
    }
  })
})*/
</script>

<template>
  <div class="min-h-screen bg-gray-100 dark:bg-[#181818] flex items-center justify-center py-12 px-4 sm:px-6 lg:px-8 mt-10">
    <div class="max-w-md w-full space-y-8">
      <div class="border border-white bg-white rounded-md p-8">
        <div class="bg-white rounded-md pt-8">
          <h2 class="mt-4 text-center text-3xl font-extrabold text-gray-900 dark:text-[#b5a9fc]">
            Confirm Number
          </h2>
          <p class="mt-2 text-center text-gray-600 dark:text-gray-400">
            Please enter the number you received via {{ emailStore.email }} to confirm.
          </p>
        </div>
        <form class="" @submit.prevent="confirmNumber" autocomplete="off">
          <div class="flex justify-center items-center my-[15px]">
            <input v-for="(_, index) in confirmationNumbers" :key="index" type="number" min="0" max="9" placeholder="0" v-model="confirmationNumbers[index]" required onpaste="false"
                   class="rounded-[5px] text-[60px] h-[100px] w-[100px] border-[3px] border-[#cacaca] m-[1%] text-center font-semibold outline-none valid:border-purple-500 valid:shadow-1.5xl input">
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
          <p v-if="result" class="text-red-500 text-xs">{{result}}</p>
        </form>
      </div>
    </div>
  </div>



</template>

<style scoped>
.input::-webkit-inner-spin-button,
.input::-webkit-outer-spin-button{
  -webkit-appearance: none;
  margin: 0;
}

@media screen and (max-width: 475px) {
  
}

</style>