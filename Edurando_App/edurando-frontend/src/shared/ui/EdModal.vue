<script setup>
import axios from "axios";
import {reactive, ref} from "vue";
import {showPasswordError, transformData} from "@/functions/functions";
import {useUserStore} from "@/store/store";
import {useRouter} from "vue-router";

const props = defineProps(["item"])
const subjects = reactive(transformData(props.item.topics))
const emit = defineEmits(['close-modal'])
const userStorage = useUserStore()
const router = useRouter()
const result = ref('')

function close() {
  emit("close-modal")
}

const showError = ref(false)


async function redirectToChat() {
  try {
    const response1 = await axios.put('/editChatReceivers', {id: userStorage.getUser.id, chatReceiver: props.item.id})
    result.value = response1.data
    const response2 = await router.push('/chat/' + props.item.id)
    console.log(props.item.id)
  } catch (error) {
    showError.value = true
    result.value = error.response.data
  }
}

</script>

<template>
  <div class="fixed inset-0 flex items-center justify-center z-50 bg-opacity-100">
    <div class="bg-white w-full max-w-md mx-auto rounded-lg shadow-lg">
      <div class="flex justify-end relative cursor-pointer z-max right-1">
        <a @click="close">
          <font-awesome-icon :icon="['fas', 'xmark']" />
        </a>
      </div>
      <div class="p-4">
        <h2 class="text-lg font-semibold">More About Me</h2>
      </div>
      <div class="flex items-center p-4">
        <img class="w-12 h-12 rounded-full mr-4" src="../../assets/p_placeholder.png" alt="Profile Photo">
        <div>
          <h3 class="font-medium">{{item.firstName + " " + item.lastName}}</h3>
          <p class="text-gray-500">{{item.personalBiography}}</p>
        </div>
      </div>
      <!--My Subjects section-->
      <div class="w-[40%] h-1 bg-[#483d8b]  mt-2  mx-auto "></div>
      <div class="p-4">
        <h3 class="text-lg font-semibold">My Subjects</h3>
        <div class="overflow-x-auto">
          <table class="mt-4 w-full border" v-for="subject in Object.keys(subjects)">
            <thead>
              <tr class="bg-gray-200">
                <th class="py-2 px-4 border-b">{{ subject }}</th>
              </tr>
            </thead>
            <tbody v-for="topic in subjects[subject]" class="flex justify-center">
            <tr class="bg-white">
              <td class="py-2 px-4 border-b">{{topic.name}}</td>
            </tr>

            </tbody>
          </table>
        </div>
      </div>

      <div class="px-4 py-2 bg-gray-100">
        <!-- Modal content here -->
      </div>
      <div class="flex justify-end" :class="showError ? 'pt-4 pr-4' : 'p-4'">
        <button class="px-4 py-2 text-white bg-purple-800 rounded hover:bg-purple-600" @click="redirectToChat">Contact Me</button>
      </div>
      <p v-if="showError" class="text-red-500 text-xs flex justify-center p-4">{{ result }}</p>
    </div>
  </div>

</template>

<style scoped>

</style>