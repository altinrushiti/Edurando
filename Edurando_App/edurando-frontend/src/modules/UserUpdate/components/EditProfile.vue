<template>
  <div class="min-h-screen bg-gray-100 flex items-center justify-center py-12 px-4 sm:px-6 lg:px-8">
    <div class="max-w-md w-full space-y-8">
      <div>
        <h2 class="mt-10 text-center text-3xl font-extrabold text-gray-900">
          User Bearbeiten
        </h2>
      </div>
      <form class="mt-8 space-y-6" @submit.prevent="onEdit">
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
          <div class="flex flex-wrap -mx-3 mb-6">
            <div class="w-full md:w-1/2 px-3 mb-6 md:mb-0">
              <label for="street" class="text-black font-font-family p-1">Street</label>
              <input id="street" name="street" type="text" v-model="user.street"
                     autocomplete="new-password"
                     class="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-b-md focus:outline-none focus:ring-purple-500 focus:border-purple-500 focus:z-10 sm:text-sm"
                     placeholder="Street">
            </div>
            <div class="w-full md:w-1/2 px-3">
              <label for="houseNumber" class="text-black font-font-family p-1">House Number</label>
              <input id="houseNumber" name="houseNumber" type="text" v-model="user.houseNumber"
                     autocomplete="new-password"
                     class="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-b-md focus:outline-none focus:ring-purple-500 focus:border-purple-500 focus:z-10 sm:text-sm"
                     placeholder="House Number">
            </div>
          </div>
          <div class="flex flex-wrap -mx-3 mb-6">
            <div class="w-full md:w-1/2 px-3 mb-6 md:mb-0">
              <label for="city" class="text-black font-font-family p-1">City</label>
              <input id="city" name="city" type="text" v-model="user.city"
                     autocomplete="new-password"
                     class="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-b-md focus:outline-none focus:ring-purple-500 focus:border-purple-500 focus:z-10 sm:text-sm"
                     placeholder="City">
            </div>
            <div class="w-full md:w-1/2 px-3">
              <label for="state" class="text-black font-font-family p-1">State</label>
              <input id="state" name="state" type="text" v-model="user.state"
                     autocomplete="new-password"
                     class="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-b-md focus:outline-none focus:ring-purple-500 focus:border-purple-500 focus:z-10 sm:text-sm"
                     placeholder="State">
            </div>
          </div>
          <div>
            <label for="mobile" class="text-black font-font-family mb-2">Mobile</label>
            <input id="mobile" name="mobile" type="tel" v-model="user.mobile"
                   autocomplete="new-password"
                   class="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-b-md focus:outline-none focus:ring-purple-500 focus:border-purple-500 focus:z-10 sm:text-sm"
                   placeholder="Mobile number">
          </div>
        </div>
        <div>
          <button type="submit"
                  class=" text-center mx-auto w-1/2 flex justify-center py-2 px-4 border border-transparent text-sm font-medium rounded-md text-white bg-[#483d8b] hover:bg-purple-950 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500">
            Save Changes
          </button>
        </div>
      </form>
    </div>
  </div>

</template>


<script>
import {defineComponent} from "vue";
import axios from "axios";

export default defineComponent({
  name: 'edit',
  data() {
    return {
        user: {
            firstName: '',
            lastName: '',
            biography: '',
            gender:'',
            role: '',
            email: '',
            street: '',
            housenumber:'',
            city:'',
            state:'',
            mobilephone:'',
        },
    }
  },
    created() {
    },mounted() {
      this.getUser()
        //console.log('Component mounted.')
    },
    methods: {
        getUser() {
            axios.get('/profile/1')
                .then(response => {
                    console.log(response)
                    this.user = response.data
                })
                .catch(error => {
                    console.log(error)
                })
        },
        onEdit() {
            axios.put('/updatePersonalData', this.user)
                .then(response => {
                    console.log(response)
                    this.user = response.data
                })
                .catch(error => {
                    console.log(error)
                })

        }
    }
})
</script>