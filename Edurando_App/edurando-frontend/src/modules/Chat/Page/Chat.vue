<script setup xmlns="http://www.w3.org/1999/html">
import {onMounted, reactive, ref} from 'vue';
import {useUserStore} from "@/store/store";
import {FontAwesomeIcon} from "@fortawesome/vue-fontawesome";
import {Stomp} from "@stomp/stompjs";
import axios from "axios";

const isSidebarOpen = ref(false);
const user = useUserStore();
const senders = ref([]);

onMounted(() => {
  chatSenders()
})

async function chatSenders() {
  try {
    const response = await axios.get('/chatSenders/' + user.getUser.id)
    senders.value = response.data
  } catch (error) {
      console.error(error)
  }
}

async function loadChat() {

}
/*const socket = new SockJs('/ws');
const stompClient = Stomp.over(socket)

const message = 'Hallo';

onMounted(() => {
  console.log("Test")
  stompClient.send('/app/chat', {}, JSON.stringify(message))
})*/

</script>

<template>
  <div class="flex">
    <!-- Sidebar -->
    <aside id="default-sidebar" class="fixed top-0 left-0 z-40 w-[15%] h-full mt-16" aria-label="Sidebar">
      <div class="h-full px-3 py-4 overflow-y-auto "
           :class="isSidebarOpen ? 'bg-purple-50 border dark:bg-[#1f1c25] dark:border-[#17151a]' : 'bg-transparent dark:md:bg-[#1f1c25] dark:md:border-[#17151a] md:bg-purple-50 md:border'">
        <ul class="space-y-2 font-medium text-[#483d8b] dark:text-[#7f6dfc]">
          <li class="">
            <div v-for="sender in senders" :key="sender.id">
              <RouterLink :to="'/chat/' + sender.id" active-class="bg-[#e4e2ee]" @click="loadChat"
                          class="justify-center flex items-center p-2 rounded-lg hover:bg-[#e4e2ee] dark:hover:bg-[#3d3844]">
                <div>{{ sender.firstName + " " + sender.lastName }}</div>
              </RouterLink>
            </div>
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
          <div class="bg-gray-100 dark:bg-gray-800 p-2 rounded-lg">
            <p class="text-gray-800 dark:text-white">{{ message.text }}</p>
            <p class="text-black text-9xl dark:text-white">Test</p>

          </div>
          <p class="text-xs text-gray-500 dark:text-white mt-1">{{ message.sender }}</p>
        </div>
      </div>

      <!-- Input Field -->
      <div class="ml-[15%] flex items-center justify-center p-4 border-t w-[85%]">

        <input v-model="newMessage" @keyup.enter="sendMessage" type="text"
               class=" flex-1 border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:border-blue-500 dark:text-black"
               placeholder="Type your message..."/>
        <button @click="connection.value.send(JSON.stringify('Test'))" class="ml-3">
          <font-awesome-icon :icon="['fas', 'paper-plane']"/>
        </button>
      </div>
    </div>
  </div>


</template>

<style scoped>

</style>
