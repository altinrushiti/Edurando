<template>
  <div class="min-h-screen bg-gray-100 flex items-center justify-center py-12 px-4 sm:px-6 lg:px-8">
    <div class="max-w-md w-full space-y-8">
      <div>
        <h2 class="mt-5 text-center text-3xl font-extrabold text-gray-900">Login</h2>
      </div>
      <form class="mt-8 space-y-6" @submit.prevent="submit">
        <div>
          <div>
            <label for="mail" class="text-black font-font-family p-2">Email</label>
            <input id="mail" name="mail" type="text"
                   v-model="loginRequest.email"
                   autocomplete="mail" required
                   class="mb-4 appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-t-md focus:ring-purple-500 focus:border-purple-500 focus:z-10 sm:text-sm"
                   placeholder="Email">
          </div>

          <div>
            <label for="password" class="text-black font-font-family p-2">Password</label>
            <input id="password" name="password" type="password" v-model="loginRequest.password"
                   autocomplete="password" required
                   class="mb-4 appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-t-md focus:ring-purple-500 focus:border-purple-500 focus:z-10 sm:text-sm"
                   placeholder="Password">
          </div>

        </div>

          <div>
              <button type="submit"
                      class="mt-7 text-center mx-auto w-1/2 flex justify-center py-2 px-4 border border-transparent text-sm font-medium rounded-md text-white bg-[#483d8b] hover:bg-purple-950 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500">
                  Login
              </button>
          </div>

      </form>

    </div>

  </div>
</template>

<script>
import {defineComponent} from "vue";
import axios from "axios";
import EditPage from "@/modules/UserUpdate/EditPage.vue";
import {useUserStore} from "@/store/store";

export default defineComponent({
    name: "changePassword",
    components: {
        'editPage': EditPage
    },
    data() {
        return {
            loginRequest: {
                email: '',
                password: ''
            }
        }
    },
    methods: {
        async submit() {
            try {
                const { email } = this.loginRequest;

                // User Store verwenden
                const userStore = useUserStore();

                // Benutzerdaten abrufen und speichern
                await userStore.fetchUser(email);

                // Weiterleiten oder andere Aktionen ausführen
                this.$router.push({ path: '/' });
            } catch (error) {
                console.log(error)
            }
        }
    }
})

</script>


<style scoped>

</style>