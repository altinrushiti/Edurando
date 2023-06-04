<template>
    <edit-page></edit-page>
    <div class="min-h-screen bg-gray-100 dark:bg-[#181818] flex-col items-center justify-center py-12 px-4 sm:px-6 lg:px-8 font-font-family">
        <h3 class="mt-8 text-center text-3xl font-extrabold text-gray-900 dark:text-[#b5a9fc]">Add Topic with Subject</h3>
        <div class="space-y-6">
            <div class="flex items-center justify-center font-font-family">
                <button @click="goSave">
                    <font-awesome-icon :icon="['fas', 'circle-plus']" class="mt-20 text-7xl text-[#483d8b]"></font-awesome-icon>
                </button>
            </div>
            <div>
                <h1 class="mt-8 text-center text-3xl font-extrabold text-gray-900 dark:text-[#b5a9fc]">Your Subjects with Topics</h1>
            </div>
            <div class="flex flex-col items-center text-center">
                <div v-for="subject in Object.keys(subjects)" class="items-start max-w-xs p-4 mb-4 border-b-2 hover:cursor-pointer" @click="toggleTopics(showTopics)">
                    <div class="font-bold text-black dark:text-[#b5a9fc]">{{ subject }}</div>
                    <ul v-if="showTopics" class="mt-2 text-black dark:text-[#b5a9fc]">
                        <li v-for="topic in subjects[subject]">{{topic.name}}</li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</template>

<!--<script>
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import EditPage from '@/modules/UserUpdate/EditPage.vue';

export default {
    components: {
        FontAwesomeIcon,
        'editPage': EditPage
    },
    data() {
        return {
            data: {
                user: {
                    subject: '',
                    topic: '',
                }
            },
            showButton: true,
            saved: true,
            people: [
                { id: 1, subject: 'Mathematik', topics: ['Algebra', 'Geometrie'], showTopics: false },
                { id: 2, subject: 'Informatik', topics: ['Programmierung', 'Datenbanken'], showTopics: false },
                { id: 3, subject: 'Geschichte', topics: ['Antike Zivilisationen', 'Mittelalter'], showTopics: false }
            ]
        };
    },
    methods: {
        async goSave() {
            try {
                this.$router.push({ path: '/SubjectsTopicSave' });
            } catch (error) {
                console.log(this.error);
            }
        },
        toggleTopics(person) {
            person.showTopics = !person.showTopics;
        }
    }
};
</script>-->

<script setup>
import { ref, reactive } from 'vue';
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import EditPage from '@/modules/UserUpdate/EditPage.vue';
import {useRouter} from "vue-router";
import {useUserStore} from "@/store/store";
import {transformData} from "@/functions/functions";

const data = reactive({
    user: {
        subject: '',
        topic: '',
    },
});

const showTopics = ref(false)
const showButton = ref(true);
const saved = ref(true);
const router = useRouter()
const user = useUserStore()

const subjects = reactive(transformData(user.getUser.topics))


function goSave() {
    try {
        router.push('/SubjectsTopicSave')
        // Füge hier die gewünschte Routing-Logik ein
    } catch (error) {
        console.log(error);
    }
}

function toggleTopics(show) {
    this.showTopics = !show;
}

// Exportiere die benötigten Daten
const components = {
    FontAwesomeIcon,
    'editPage': EditPage
};

// Du kannst hier weitere Exporte hinzufügen, falls benötigt

</script>


