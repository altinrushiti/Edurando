<template>
  <edit-page></edit-page>
  <div
      class="mt-10 min-h-screen bg-gray-100 flex-col items-center justify-center py-12 px-4 sm:px-6 lg:px-8 font-font-family">
    <h2 class="mt-6 text-center text-3xl font-extrabold text-gray-900">Add Topic with Subject</h2>
    <div class="mt-8 space-y-6">
      <div v-if="showButton" class="flex items-center justify-center font-font-family">
        <button @click="showButton = false">
          <font-awesome-icon :icon="['fas', 'circle-plus']" class="mt-20 text-7xl text-[#483d8b]"></font-awesome-icon>
        </button>
      </div>
      <div v-else>
        <form class="flex flex-col items-center w-full">
          <div class="w-1/2">
            <label for="subject" class="text-black font-font-family p-2">Subject</label>
            <input id="subject" name="subject" type="text" v-model="subject" autocomplete="subject"
                   class="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-t-md focus:ring-purple-500 focus:border-purple-500 focus:z-10 sm:text-sm"
                   placeholder="Enter Subject">
          </div>
          <div class="mt-5 w-1/2">
            <label for="topic" class="text-black font-font-family p-1">Topic</label>
            <input id="topic" name="topic" type="text" v-model="topic"
                   class="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-t-md focus:ring-purple-500 focus:border-purple-500 focus:z-10 sm:text-sm"
                   placeholder="Enter Topic">
          </div>
          <div class="flex justify-center mt-7 space-x-4">
            <div class="mt-6 mr-8 text-center flex justify-center text-white">
              <button @click="showButton = true">
                <font-awesome-icon :icon="['fas', 'arrow-left']" class="text-4xl text-gray-900"/>
              </button>
            </div>
            <div class="mt-6 ml-8 text-center flex justify-center text-white">
              <button @click="showButton = true">
                <font-awesome-icon :icon="['fas', 'check']" class="text-4xl text-gray-900"/>
              </button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>


<script>
import {FontAwesomeIcon} from '@fortawesome/vue-fontawesome';
import EditPage from '@/modules/UserUpdate/EditPage.vue';

export default {
  components: {
    FontAwesomeIcon,
    'editPage': EditPage
  },
  data() {
    return {
      subject: '',
      topic: '',
      showButton: true
    }
  },
  methods: {}
}
</script>
