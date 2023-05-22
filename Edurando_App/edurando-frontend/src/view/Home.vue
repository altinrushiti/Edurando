<script setup>
import { useUserStore } from '@/store/store';

const userStore = useUserStore();


</script>

<template>
    <div class="min-h-screen bg-gray-100 flex items-center justify-center py-12 px-4 sm:px-6 lg:px-8">
        <h1 class="text-4xl font-font-family text-slate-900">Welcome to Edurando</h1>
        <h1 class="text-4xl font-font-family text-slate-900">{{ userStore.getUser }}</h1>
    </div>

</template>

<style scoped>

</style>