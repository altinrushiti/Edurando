<template>
  <header class="text-white font-font-family">
    <nav class="fixed top-0 left-0 right-0 z-50 flex justify-between items-center bg-[#483d8b] p-2">
      <div class="logo flex items-center">
        <img class="w-[100px] h-[60px]" src="@/assets/logo/logo_image.png" alt="Logo" />
         <h1 class="text-3xl font-medium text-align text-align: center">Edurando</h1>
      </div>
        <ul class="flex justify-around gap-2 ml-3 mr-2 text-2xl">
            <li class="hidden md:block">
                <RouterLink to="/">
                    <font-awesome-icon class="mr-2" icon="fa-solid fa-house"/>
                    <span>Home</span>
                </RouterLink>
            </li>
            <li class="hidden md:block">
                <RouterLink to="/about">
                    <font-awesome-icon class="mr-2" icon="fa-solid fa-circle-info"/>
                    <span>About</span>
                </RouterLink>
            </li>
            <li class="flex-col md:hidden flex">
                <button @click="toggleMenu">
                    <font-awesome-icon :icon="showMenu ? 'fa-solid fa-times' : 'fa-solid fa-bars'"></font-awesome-icon>
                </button>
                <ul :class="showMenu ? 'flex-col' : 'hidden' ">
                    <li class="py-4">
                        <RouterLink to="/">
                            <font-awesome-icon icon="fa-solid fa-house"/>
                        </RouterLink>
                    </li>
                    <li class="py-4">
                        <RouterLink to="/">
                            <font-awesome-icon icon="fa-solid fa-circle-info"/>
                        </RouterLink>
                    </li>
                </ul>
            </li>
        </ul>
    </nav>
  </header>
</template>

<script setup lang="ts">
import { ref } from "vue";
const showMenu = ref(false)
function toggleMenu() {
    showMenu.value = !showMenu.value;
}
</script>

<style scoped>

</style>