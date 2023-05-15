<script setup>
import { ref, onMounted, computed } from "vue";
import { users } from "@/store/store";
const store = users();
const msg = ref("Welcome to my Vuex Store");
const myUsers = computed(() => {
    return store.users;
});
onMounted(() => {
    store.fetchUsers();
});

</script>

<template>
    <div class="min-h-screen bg-gray-100 flex items-center justify-center py-12 px-4 sm:px-6 lg:px-8">
<!--        <h1 class="text-slate-900">{{ msg }}</h1>
        <h1 class="text-slate-900">Made By Actions</h1>
        <div class="text-slate-900">
            {{ myUsers[0].id }} {{ myUsers[0].name }} {{ myUsers[0].address }}
        </div>-->
        <h1 class="text-4xl font-font-family text-slate-900">Welcome to Edurando</h1>
    </div>

</template>

<style scoped>

</style>