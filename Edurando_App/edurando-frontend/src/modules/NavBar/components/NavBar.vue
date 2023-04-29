<template>
  <header class="text-white">
    <nav class="flex justify-between items-center bg-[#483d8b] p-2">
      <div class="logo flex items-center">
        <img class="w-[100px] h-[60px]" src="@/assets/logo/logo_image.png" alt="Logo" />
         <h1 class="text-3xl font-medium">Edurando</h1>

      </div>
      <ul class="flex justify-around gap-2 m-2 text-2xl">
        <li>
          <router-link to="/">Home</router-link>
        </li>
        <li>
          <router-link to="/about">About</router-link>
        </li>
      </ul>
    </nav>
  </header>
</template>

<script setup lang="ts">

</script>

<style scoped>

</style>