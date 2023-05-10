<template>
  <div class="min-h-screen bg-gray-100 flex items-center justify-center py-12 px-4 sm:px-6 lg:px-8">
    <div class="max-w-md w-full space-y-8">
      <div>
        <h2 class="mt-5 text-center text-3xl font-extrabold text-gray-900">Change Password</h2>
      </div>
      <form class="mt-8 space-y-6" @submit.prevent="editPassword">
        <div class="rounded-md shadow-sm space-y-2">
          <div>
            <label for="currentPassword" class="text-black font-font-family p-2">Current Password</label>
            <input id="currentPassword" name="currentPassword" type="password" v-model="password.currentPassword"
                   autocomplete="currentPassword" required
                   class="mb-4 appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-t-md focus:ring-purple-500 focus:border-purple-500 focus:z-10 sm:text-sm"
                   placeholder="Current Password">
          </div>

          <div>
            <label for="newPassword" class="text-black font-font-family p-2">New Password</label>
            <input id="newPassword" name="newPassword" type="password" v-model="password.newPassword"
                   autocomplete="newPassword" required
                   class="mb-4 appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-t-md focus:ring-purple-500 focus:border-purple-500 focus:z-10 sm:text-sm"
                   placeholder="New Password">
          </div>

          <div>
            <label for="newPasswordRepeat" class="text-black font-font-family p-2">Repeat New Password</label>
            <input id="newPasswordRepeat" name="newPasswordRepeat" type="password" v-model="password.newPasswordRepeat"
                   autocomplete="newPasswordRepeat" required
                   class="mb-4 appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-t-md focus:ring-purple-500 focus:border-purple-500 focus:z-10 sm:text-sm"
                   placeholder="Repeat New Password">
          </div>



          <div>
            <button type="submit"
                    class="mt-7 text-center mx-auto w-1/2 flex justify-center py-2 px-4 border border-transparent text-sm font-medium rounded-md text-white bg-[#483d8b] hover:bg-purple-950 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500">Save Changes</button>
          </div>

        </div>

      </form>

    </div>

  </div>
</template>

 <script>
 import {defineComponent} from "vue";
 import axios from "axios";
 export default defineComponent({
   data() {
    return {
        password: {
            id: 1,
            currentPassword: '',
            newPassword: '',
            newPasswordRepeat: '',
        }
    }
  },
  methods: {
    async editPassword() {
        try {
            const response = await axios.put('/editPassword', this.password)
            response.statusText = response.data
            this.result = response.data
            this.$router.push({ path: '/' })
            console.log(response)
        } catch (error) {
            console.log(error)
        }
    }
  }
})

</script>

<style scoped>
</style>

