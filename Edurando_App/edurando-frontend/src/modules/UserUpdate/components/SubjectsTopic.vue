<template>
    <edit-page></edit-page>
    <div class="min-h-screen bg-gray-100 flex-col items-center justify-center py-12 px-4 sm:px-6 lg:px-8 font-font-family">
        <h3 class="mt-8 text-center text-3xl font-extrabold text-gray-900">Add Topic with Subject</h3>
        <div class="space-y-6">
            <div class="flex items-center justify-center font-font-family">
                <button @click="goSave">
                    <font-awesome-icon :icon="['fas', 'circle-plus']" class="mt-20 text-7xl text-[#483d8b]"></font-awesome-icon>
                </button>
            </div>
            <div>
                <h1 class="mt-8 text-center text-3xl font-extrabold text-gray-900">Your Subjects with Topics</h1>
            </div>
            <div class="flex flex-col items-center text-center">
                <div v-for="person in people" :key="person.id" class="items-start max-w-xs p-4 mb-4 border-b-2" @click="toggleTopics(person)">
                    <div class="font-bold text-black">{{ person.subject }}</div>
                    <ul v-if="person.showTopics" class="mt-2 text-black">
                        <li v-for="topic in person.topics" :key="topic.id">{{ topic }}</li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
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
</script>

