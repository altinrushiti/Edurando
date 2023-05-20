<script setup>
import { useUserStore } from '@/store/store';
import {ref} from "vue"; // importieren Sie den User Store

const userStore = useUserStore();
const user = userStore// User Store verwenden

const restoreUser = () => {
    // Hier implementieren Sie den Code, um den Benutzer aus dem Speicher wiederherzustellen
    // Dies könnte der lokale Speicher (localStorage) oder ein API-Aufruf sein, um den Benutzer vom Server abzurufen

    // Beispiel für das Wiederherstellen aus dem lokalen Speicher
    const storedUser = localStorage.getItem('user');
    if (storedUser) {
        userStore.$state.user = JSON.parse(storedUser);
    }
};

</script>

<template>
    <div class="min-h-screen bg-gray-100 flex items-center justify-center py-12 px-4 sm:px-6 lg:px-8">
        <h1 class="text-4xl font-font-family text-slate-900">Welcome to Edurando</h1>
        <h1 class="text-4xl font-font-family text-slate-900">{{ user.getUser }}</h1>
    </div>
</template>

<style scoped>

</style>