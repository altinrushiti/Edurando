<script setup>

import NavBar from "./modules/NavBar/components/NavBar.vue";
import Footer from "@/modules/Footer/Footer.vue"
import '@fortawesome/fontawesome-free/css/all.css'

</script>

<template>
<NavBar />
    <RouterView />
<Footer />
</template>

<style scoped>

</style>
