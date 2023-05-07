<template>
    <div class="min-h-screen bg-gray-100 flex items-center justify-center py-12 px-4 sm:px-6 lg:px-8">
        <div class="max-w-md w-full space-y-8">
            <div>
                <h2 class="mt-4 text-center text-3xl font-extrabold text-gray-900">
                    Sign up for an account
                </h2>
            </div>
            <form class="mt-8 space-y-6" @submit.prevent="registerUser">
                <div class="rounded-md shadow-sm space-y-2">
                    <div>
                        <label for="firstname" class="text-black font-font-family p-2">First Name</label>
                        <input id="firstname" name="firstname" type="text" v-model="user.firstName"
                               autocomplete="firstname" required
                               class="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-t-md focus:ring-purple-500 focus:border-purple-500 focus:z-10 sm:text-sm"
                               placeholder="Firstname">
                    </div>
                    <div>
                        <label for="lastname" class="text-black font-font-family p-1">Last Name</label>
                        <input id="lastname" name="lastname" type="text" v-model="user.lastName" autocomplete="lastname"
                               required
                               class="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-t-md focus:ring-purple-500 focus:border-purple-500 focus:z-10 sm:text-sm"
                               placeholder="Lastname">
                    </div>

                    <div>
                        <label for="email-address" class="text-black font-font-family p-1">Email address</label>
                        <input id="email-address" name="email" type="email" v-model="user.email" autocomplete="email"
                               required
                               class="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 focus:outline-none focus:ring-purple-500 focus:border-purple-500 focus:z-10 sm:text-sm"
                               placeholder="Email address">
                    </div>
                    <div>
                        <label for="password" class="text-black font-font-family p-1">Password</label>
                        <input id="password1" name="password1" type="password" v-model="user.password"
                               autocomplete="new-password" required
                               class="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-b-md focus:outline-none focus:ring-purple-500 focus:border-purple-500 focus:z-10 sm:text-sm"
                               placeholder="Password">
                    </div>
                    <div>
                        <label for="password" class="text-black font-font-family p-1">Repeat your Password</label>
                        <input id="password2" name="password2" type="password" v-model="user.passwordRepeat"
                               autocomplete="new-password" required
                               class="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-b-md focus:outline-none focus:ring-purple-500 focus:border-purple-500 focus:z-10 sm:text-sm"
                               placeholder="Repeat Password">
                    </div>
                    <p v-if="user.password !== user.passwordRepeat" class="text-red-500">Passwords do not match</p>
                    <div>
                        <label for="role" class="text-black font-font-family flex p-1 font-size=10px">Role</label>
                        <select class="bg-white text-gray-900 rounded-none relative block w-full px-3 py-2 border border-gray-300 rounded-b-md focus:outline-none focus:ring-purple-500 focus:border-purple-500 focus:z-10 sm:text-sm" id="role" v-model="user.role">
                            <option value="" disabled>Select role</option>
                            <option value="Student">Student</option>
                            <option value="Teacher">Teacher</option>
                        </select>
                    </div>
                    <div>
                        <label for="terms" class="text-black font-font-family flex p-1 font-size=10px">
                            <input id="terms" name="terms" type="checkbox" v-model="user.termsAgreed" required>
                            <span class="ml-2">Ich bin mit den Nutzungsbedingungen einverstanden.</span>
                        </label>
                    </div>
                    <div>
                        <label for="privacy" class="text-black font-font-family flex p-1 font-size=10px">
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

<script>
import {defineComponent} from "vue";
import axios from 'axios';
import { useRouter } from 'vue-router'

export default defineComponent({
      name: "Register",
      data() {

        return {
            result: "",
            user: {
                role: '',
                firstName: '',
                lastName: '',
                email: '',
                password: '',
                passwordRepeat: '',
                termsAgreed: false,
                privacyAgreed: false
            }
        }
      },
      methods: {
          registerUser() {
            axios.post('http://localhost:9001/api/v1/register', this.user)
                .then(response => {
                    response.statusText = response.data
                    this.result = response.data
                    this.$router.push({path: '/confirm'})
                    console.log(response)
                })
                .catch(error => {
                  console.log(error.request.response)
                })
          },
      },
    }
)
</script>
<style>
</style>