<script setup xmlns="http://www.w3.org/1999/html">
import {reactive, ref} from 'vue';
import {useUserStore} from "@/store/store";
import {FontAwesomeIcon} from "@fortawesome/vue-fontawesome";

const isSidebarOpen = ref(false);
const user = useUserStore();


</script>

<template>
  <div class="flex">
    <!-- Sidebar -->
    <aside id="default-sidebar" class="fixed top-0 left-0 z-40 w-[15%] h-full mt-16" aria-label="Sidebar">
      <div class="h-full px-3 py-4 overflow-y-auto "
           :class="isSidebarOpen ? 'bg-purple-50 border' : 'bg-transparent md:bg-purple-50 md:border'">
        <ul class="space-y-2 font-medium text-[#483d8b]">
          <li class="">
            <RouterLink to="/editProfile" active-class="bg-[#e4e2ee]"
                        class="justify-center flex items-center p-2 rounded-lg hover:bg-[#e4e2ee]">
              <div>{{ user.getUser.firstName + " " + user.getUser.lastName }}
                <p>
                  Letzte Nachricht
                </p></div>
            </RouterLink>
          </li>
        </ul>
      </div>
    </aside>
  </div>

  <div>
    <div class="flex flex-col h-screen">
      <!-- Chat Messages -->
      <div class="flex-1 overflow-y-auto">
        <div v-for="message in chatMessages" :key="message.id" class="flex flex-col items-start mb-4">
          <div class="bg-gray-100 p-2 rounded-lg">
            <p class="text-gray-800">{{ message.text }}</p>
          </div>
          <p class="text-xs text-gray-500 mt-1">{{ message.sender }}</p>
        </div>
      </div>

      <!-- Input Field -->
      <div class="ml-[15%] flex items-center justify-center p-4 border-t w-[85%]">
        <input v-model="newMessage" @keyup.enter="sendMessage" type="text"
               class=" flex-1 border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:border-blue-500 "
               placeholder="Type your message..."/>
        <button @click="sendMessage" class="ml-3">
          <font-awesome-icon :icon="['fas', 'paper-plane']"/>
        </button>
      </div>
    </div>
  </div>


</template>

<style scoped>

</style>
