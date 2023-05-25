<!--<li class="flex items-center">
          <input type="text" placeholder="Search" class="p-2 rounded-md border border-gray-300 text-sm focus:outline-none focus:ring-2 focus:ring-blue-600 text-center" style="color: black;">
        </li> -->

<template>
  <header class="text-white font-font-family">
    <nav class="fixed top-0 left-0 right-0 z-50 flex justify-between items-center bg-[#483d8b] p-2">
      <div class="logo flex items-center">
        <img class="w-[100px] h-[60px]" src="@/assets/logo/logo_image.png" alt="Logo" />
        <h1 class="text-3xl font-medium text-align text-align: center">Edurando</h1>
      </div>
      <ul class="flex justify-end items-center gap-2 ml-3 mr-2 text-2xl">
        <li class="hidden md:block">
          <RouterLink to="/">
            <font-awesome-icon class="mr-2 fixed" icon="fa-solid fa-house"/>
            <span class="ml-8">Home</span>
          </RouterLink>
        </li>
        <li class="hidden md:block">
          <RouterLink to="/about">
            <font-awesome-icon class="mr-2 fixed" icon="fa-solid fa-circle-info"/>
            <span class="ml-7">About</span>
          </RouterLink>
        </li>
        <li class="flex items-center search-box fixed-left">
          <input type="text" placeholder="Search">
          <button> <i class="fas fa-search"></i></button>
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
.search-box {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  border-color: #1D1D1D;
  padding: 1%;
  border-radius: 32%;
  box-shadow: rgba(100, 100, 111, 0.2);
}

.search-box input {
  border: 0;
  background-color: transparent;
  font-size: 16px;
  width: 0;
  transition: 0.1s;
}

.search-box:hover input {
  width: 200px;

}

.search-box button {
  padding-left: 10px;
  border: 0;
  background-color: #483d8b;
  color: white;
  font-size: 20px;
  border-radius: 5px;
  cursor: pointer;
}


</style>