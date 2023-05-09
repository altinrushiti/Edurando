<template>
  <div class="min-h-screen bg-gray-100 flex items-center justify-center py-12 px-4 sm:px-6 lg:px-8">
    <div class="max-w-md w-full space-y-8">
      <div>
        <h2 class="mt-10 text-center text-3xl font-extrabold text-gray-900">
          User Bearbeiten
        </h2>
      </div>
      <form class="mt-8 space-y-6" @submit.prevent="edit">
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
            <label for="biography" class="text-black font-font-family p-1">Biography</label>
            <textarea id="biography" name="biography" v-model="user.biography" rows="5"
                      class="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-b-md focus:outline-none focus:ring-purple-500 focus:border-purple-500 focus:z-10 sm:text-sm"
                      placeholder="Write your biography here"></textarea>
          </div>
          <div>
            <label for="role" class="text-black font-font-family flex p-1 font-size=10px">Gender</label>
            <select
                class="bg-white text-gray-900 rounded-none relative block w-full px-3 py-2 border border-gray-300 rounded-b-md focus:outline-none focus:ring-purple-500 focus:border-purple-500 focus:z-10 sm:text-sm"
                id="role" v-model="user.role">
              <option value="" disabled>Select role</option>
              <option value="Student">Man</option>
              <option value="Teacher">Women</option>
            </select>
          </div>
          <div>
            <label for="role" class="text-black font-font-family flex p-1 font-size=10px">Role</label>
            <select
                class="bg-white text-gray-900 rounded-none relative block w-full px-3 py-2 border border-gray-300 rounded-b-md focus:outline-none focus:ring-purple-500 focus:border-purple-500 focus:z-10 sm:text-sm"
                id="role" v-model="user.role">
              <option value="" disabled>Select role</option>
              <option value="Student">Student</option>
              <option value="Teacher">Teacher</option>
            </select>
          </div>
          <div>
            <label for="Street" class="text-black font-font-family p-1">Street</label>
            <input id="Street" name="Street" type="Street" v-model="user.street"
                   autocomplete="new-password" required
                   class="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-b-md focus:outline-none focus:ring-purple-500 focus:border-purple-500 focus:z-10 sm:text-sm"
                   placeholder="Street">
          </div>
        </div>
        <div>
          <button type="submit"
                  class=" text-center mx-auto w-1/2 flex justify-center py-2 px-4 border border-transparent text-sm font-medium rounded-md text-white bg-[#483d8b] hover:bg-purple-950 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500">
            Save Change
          </button>
        </div>
      </form>
    </div>
  </div>

</template>


<script>
import {defineComponent} from "vue";

export default defineComponent({
  name: 'Register',
  data() {
    return {
      user: {
        firstName: '',
        lastName: '',
        email: '',
        password: '',
        passwordRepeat: '',
        street: '',
        biography: '',
        role: '',
        termsAgreed: false,
        privacyAgreed: false
      }
    }
  },
  methods: {
    register() {
      console.log(this.user)
    }
  }
})
</script>