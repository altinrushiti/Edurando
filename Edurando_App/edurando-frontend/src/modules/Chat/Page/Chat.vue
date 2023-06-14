<script setup xmlns="http://www.w3.org/1999/html">
import {onMounted, onUpdated, reactive, ref} from 'vue';
import {useUserStore} from "@/store/store";
import {FontAwesomeIcon} from "@fortawesome/vue-fontawesome";
import axios from "axios";
import Channel from "../Components/Channel.vue"
import Receiver from "../Components/Receiver.vue"

const isSidebarOpen = ref(false);
const isSend = ref(false);
const userStore = useUserStore();
const receivers = ref([]);
const chatMessages = ref([]);
const chatMessage = reactive({
  sender: userStore.getUser.id,
  receiver: userStore.getChatReceiver,
  content: ''
})

onMounted(() => {
  chatSenders()
  loadChat(userStore.getChatReceiver)
  console.log(chatMessage.receiver)
})

onUpdated(async () => {
  await new Promise(resolve => setTimeout(resolve, 2000));
  await loadChat(userStore.getChatReceiver)
  const scroll = document.getElementById('scroll')
  scroll.scrollTop = scroll.scrollHeight
})

async function send() {
  try {
    if (chatMessage.content.length > 0) {
      const response = await axios.post('/send', chatMessage)
      console.log(response.data)
      console.log("Receiver: ", userStore.getChatReceiver)
      console.log("Sender: ", userStore.getUser.id)
      chatMessage.content = ''
      await loadChat(userStore.getChatReceiver)
      const scroll = document.getElementById('scroll')
      scroll.scrollTop = scroll.scrollHeight
    }
  } catch (error) {
    console.error(error)
  }
}

async function chatSenders() {
  try {
    const response = await axios.get('/chatReceivers/' + userStore.getUser.id)
    receivers.value = response.data
    userStore.chatReceivers = receivers.value
    console.log("test: " + userStore.getChatReceivers)
  } catch (error) {
    console.error(error)
  }
}

async function loadChat(receiver) {
  try {
    const response1 = await userStore.fetchChatReceiverById(receiver)
    chatMessage.receiver = receiver
    console.log("Receiver: ", userStore.getChatReceiver)
    console.log("Sender: ", userStore.getUser.id)
    const response2 = await axios.get('/chatHistory/' + userStore.getChatReceiver + "-" + userStore.getUser.id)
    chatMessages.value = response2.data
    console.log(userStore.getChatReceivers)
  } catch (error) {
    console.error(error)
  }
}

</script>

<template>
  <div class="flex">
    <!-- Sidebar -->
    <aside id="default-sidebar" class="fixed top-0 left-0 w-[15%] h-full mt-16 bg-transparent dark:md:bg-[#1f1c25] dark:md:border-[#17151a] md:bg-purple-50 md:border" aria-label="Sidebar">
      <div class="h-full px-3 py-4">
        <ul class="space-y-2 font-medium text-[#483d8b] dark:text-[#7f6dfc]">
          <li class="">
            <div v-for="sender in receivers" :key="sender.id">
              <RouterLink :to="'/chat/' + sender.id" active-class="bg-[#e4e2ee]" @click="loadChat(sender.id)"
                          class="justify-center flex items-center p-2 rounded-lg hover:bg-[#e4e2ee] dark:hover:bg-[#3d3844]">
                <div @click="loadChat(sender.id)">{{ sender.firstName + " " + sender.lastName }}</div>
              </RouterLink>
            </div>
          </li>
        </ul>
      </div>
    </aside>
  </div>

  <div class="ml-[17%] flex flex-col h-screen mt-16">
    <!-- Chat Messages -->

    <div class="flex-1 overflow-y-auto mb-20" id="scroll">
      <div v-for="chatMessage in chatMessages" :key="chatMessage.id">
        <channel v-if="chatMessage.sender === userStore.getUser.id" :item="chatMessage" class="m-1"/>
        <Receiver v-else class="m-1" :item="chatMessage"/>
      </div>

    </div>

    <!-- Input Field -->
    <div class="ml-[15%] fixed bottom-0 left-0 w-[85%] bg-[#e8e1fa] dark:bg-[#14131a]">
      <div class="container mx-auto flex items-center justify-center p-4 border-t dark:border-[#12101a]">
        <input v-model="chatMessage.content" @keyup.enter="send" type="text"
               class="flex-1 border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:border-purple-500 text-black dark:bg-[#c6c5d1] dark:placeholder:text-gray-500"
               placeholder="Type your message..."/>
        <button @click="send" class="ml-3 text-[#483d8b] dark:text-[#7f6dfc]">
          <font-awesome-icon :icon="['fas', 'paper-plane']"/>
        </button>
      </div>
    </div>
  </div>
</template>

<style scoped>

</style>
