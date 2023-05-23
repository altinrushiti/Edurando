<template>
    <edit-page></edit-page>
    <div
            class="min-h-screen bg-gray-100 flex-col items-center justify-center py-12 px-4 sm:px-6 lg:px-8 font-font-family">
        <h2 class="mt-6 text-center text-3xl font-extrabold text-gray-900">Add Topic with Subject</h2>
        <div class="mt-8 space-y-6">
            <div>
                <form class="flex flex-col items-center w-full">
                    <div class="w-1/2">
                        <label for="subject" class="text-black font-font-family p-2">Subject</label>
                        <input id="subject" name="subject" type="text" v-model="data.subject" autocomplete="subject"
                               class="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-t-md focus:ring-purple-500 focus:border-purple-500 focus:z-10 sm:text-sm"
                               placeholder="Enter Subject">
                    </div>
                    <div class="mt-5 w-1/2">
                        <label for="topic" class="text-black font-font-family p-1">Topic</label>
                        <input id="topic" name="topic" type="text" v-model="data.topic"
                               class="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-t-md focus:ring-purple-500 focus:border-purple-500 focus:z-10 sm:text-sm"
                               placeholder="Enter Topic">
                    </div>
                    <div v-show="saved" class="mt-3 font-font-family text-green-400">
                        <p> Die Daten wurden erfolgreich gespeichert! </p>
                    </div>
                    <div class="flex justify-center mt-7 space-x-4">
                        <div class="mt-6 mr-8 text-center flex justify-center text-white">
                            <button @click="goBack">
                                <font-awesome-icon :icon="['fas', 'arrow-left']" class="text-4xl text-gray-900"/>
                            </button>
                        </div>
                        <div class="mt-6 ml-8 text-center flex justify-center text-white">
                            <button @click="addSubjectTopic">
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
import axios from "axios";

export default {
    components: {
        FontAwesomeIcon,
        'editPage': EditPage
    },
    data() {
        return {
            data: {
                subject: '',
                topic: '',
            },
            showButton: true,
            saved: true
        }


    },
    methods: {

        async addSubjectTopic() {
            try {
                const response = await axios.put('/', this.data)
                response.statusText = response.data
                this.result = response.data
                this.$router.push({path: '/'})
                console.log(response)
                this.saved = true
            } catch (error) {
                console.log(error)
            }
        },
        async goBack() {
            try {
                this.$router.push({path: '/SubjectsTopics'})
            } catch (error) {
                console.log(error)
            }
        }
    }
}
</script>

