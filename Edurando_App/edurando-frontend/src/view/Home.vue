<script setup>
import { useUserStore } from '@/store/store';

const userStore = useUserStore();


</script>

<template>
    <div class="min-h-screen bg-gray-100 flex items-center justify-center py-12 px-4 sm:px-6 lg:px-8">
        <q-card class="my-card" flat bordered>
            <q-card-section>
                <div class="text-h6 q-mb-xs">Our Changing Planet</div>
                <div class="row no-wrap items-center">
                    <q-rating size="18px" v-model="stars" :max="5" color="primary" />
                    <span class="text-caption text-grey q-ml-sm">4.2 (551)</span>
                </div>
            </q-card-section>

            <img src="https://cdn.quasar.dev/img/mountains.jpg">
        </q-card>
    </div>

</template>

<style scoped>

</style>