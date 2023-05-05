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
                        <label for="role" class="text-black font-font-family p-2 font-size=10px">Role:  </label>
                        <select class="text-black" id="role" v-model="user.role">
                            <option value="Student">Student</option>
                            <option value="Teacher">Teacher</option>
                        </select>
                    </div>

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
            result: {},
            user: {
                role: '',
                firstName: '',
                lastName: '',
                email: '',
                password: '',
                passwordRepeat: ''

            }
        }
    },
    created() {
    },mounted() {

    },
    methods: {
        registerUser() {
            axios.post('http://localhost:9001/api/v1/register', this.user)
                .then(response => {
                    console.log(response.data);
                    this.result = response.data
                })
                .catch(error => {
                    console.log(error)
                })
        },
    }
}
)
</script>
<style>
</style>