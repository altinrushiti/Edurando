<template>
    <div class="min-h-screen bg-gray-100 dark:bg-[#181818] flex items-center justify-center py-12 px-4 sm:px-6 lg:px-8 mt-10">
        <div class="max-w-md w-full space-y-8">
            <div>
                <h2 class="mt-4 text-center text-3xl font-extrabold text-gray-900 dark:text-[#b5a9fc]">
                    Sign up for an account
                </h2>
            </div>
            <form class="mt-8 space-y-6" @submit.prevent="registerUser">
                <div class="rounded-md shadow-sm space-y-2">
                  <div>
                    <label for="firstname" class="text-black dark:text-[#b5a9fc] font-font-family p-2">First Name</label>
                    <input id="firstname" name="firstname" type="text" v-model="user.firstName"
                           autocomplete="firstname" required
                           class="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 dark:bg-[#c6c5d1] dark:border-[#9895ad] dark:text-black focus:outline-none rounded-t-md focus:ring-purple-500 focus:border-purple-500 focus:z-10 sm:text-sm"
                           placeholder="Firstname">
                  </div>

                  <div>
                        <label for="lastname" class="text-black dark:text-[#b5a9fc] font-font-family p-1">Last Name</label>
                        <input id="lastname" name="lastname" type="text" v-model="user.lastName" autocomplete="lastname"
                               required
                               class="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 dark:bg-[#c6c5d1] dark:border-[#9895ad] dark:text-black focus:outline-none rounded-t-md focus:ring-purple-500 focus:border-purple-500 focus:z-10 sm:text-sm"
                               placeholder="Lastname">
                    </div>

                    <div>
                        <label for="email-address" class="text-black dark:text-[#b5a9fc] font-font-family p-1">Email address</label>
                        <input autocomplete="off" id="email-address" name="email" type="email" v-model="user.email"
                               required
                               class="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 dark:bg-[#c6c5d1] dark:border-[#9895ad] dark:text-black focus:outline-none focus:ring-purple-500 focus:border-purple-500 focus:z-10 sm:text-sm"
                               placeholder="Email address">
                    </div>
                    <div>
                        <label for="password" class="text-black dark:text-[#b5a9fc] font-font-family p-1">Password</label>
                        <input id="password1" name="password1" type="password" v-model="user.password"
                               autocomplete="off" required
                               class="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 dark:bg-[#c6c5d1] dark:border-[#9895ad] dark:text-black rounded-b-md focus:outline-none focus:ring-purple-500 focus:border-purple-500 focus:z-10 sm:text-sm"
                               placeholder="Password">
                    </div>
                    <p v-if="showPasswordError(user.password)" class="text-red-500 text-xs">Please choose a more secure password, at
                        least 8 characters long, known only to you, and difficult for others to guess."</p>
                    <div>
                        <label for="password" class="text-black dark:text-[#b5a9fc] font-font-family p-1">Repeat your Password</label>
                        <input autocomplete="off" id="password2" name="password2" type="password"
                               v-model="user.passwordRepeat"
                               required
                               class="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 dark:bg-[#c6c5d1] dark:border-[#9895ad] dark:text-black rounded-b-md focus:outline-none focus:ring-purple-500 focus:border-purple-500 focus:z-10 sm:text-sm"
                               placeholder="Repeat Password">
                    </div>
                    <p v-if="showPasswordRepeatError(user.password, user.passwordRepeat)" class="text-red-500 text-xs">Passwords do not
                        match</p>
                    <div>
                        <label for="role" class="text-black dark:text-[#b5a9fc] font-font-family flex p-1 font-size=10px">Role</label>
                        <select class="bg-white text-gray-900 rounded-none relative block w-full px-3 py-2 border border-gray-300 rounded-b-md dark:bg-[#c6c5d1] dark:border-[#9895ad] dark:text-black focus:outline-none focus:ring-purple-500 focus:border-purple-500 focus:z-10 sm:text-sm"
                                id="role" v-model="user.role">
                            <option value="" disabled>Select role</option>
                            <option value="Student">Student</option>
                            <option value="Teacher">Teacher</option>
                        </select>
                    </div>
                    <div>
                        <label for="terms" class="text-black dark:text-[#b5a9fc] font-font-family flex p-1 font-size=10px">
                            <input id="terms" name="terms" type="checkbox" v-model="user.termsAgreed" required>
                            <span class="ml-2">Ich bin mit den Nutzungsbedingungen einverstanden.</span>
                        </label>
                    </div>
                    <div>
                        <label for="privacy" class="text-black dark:text-[#b5a9fc] font-font-family flex p-1 font-size=10px">
                            <input id="privacy" name="privacy" type="checkbox" v-model="user.privacyAgreed" required>
                            <span class="ml-2">Ich stimme der Datenschutzerklärung zu.</span>
                        </label>
                    </div>
                </div>
                <div>
                    <button type="submit"
                            class=" text-center mx-auto w-1/2 flex justify-center py-2 px-4 border border-transparent text-sm font-medium rounded-md text-white bg-[#483d8b] hover:bg-purple-950 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500">
                        Sign up
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>

<script setup>
import {reactive, ref} from "vue";
import axios from 'axios';
import {useRouter} from "vue-router";
import {showPasswordError, showPasswordRepeatError} from '@/functions/functions'

const result = ref("")
const error = ref([])
const router = useRouter()
const user = reactive({
    role: '',
    firstName: '',
    lastName: '',
    email: '',
    password: '',
    passwordRepeat: '',
    termsAgreed: false,
    privacyAgreed: false
})

async function registerUser() {
    try {
        const response = await axios.post('/register', user)
        response.statusText = response.data
        result.value = response.data
        await router.push('/confirm')
        console.log(result.value)
    } catch (error) {
        this.result = error.request.response
        this.error = this.result
        console.log(this.error)
    }
}

</script>

<style>
</style>