<template>
  <header class="text-white font-font-family">
    <nav class="fixed top-0 left-0 right-0 z-50 flex justify-between items-center bg-[#483d8b] p-2">
      <div class="logo flex items-center">
        <img class="w-[100px] h-[60px]" src="@/assets/logo/logo_image.png" alt="Logo" />
        <h1 class="text-3xl font-medium text-align text-align: center">Edurando</h1>
      </div>
      <div class="box">
        <input type="checkbox" id="check">
        <div class="search-box">
          <input v-model="searchInput" @input="emitSearch" type="text" placeholder="type here">
          <label for="check" class="icon">
            <font-awesome-icon icon="fa-solid fa-magnifying-glass"/>
          </label>
        </div>
      </div>
      <ul class="flex justify-around gap-2 ml-3 mr-2 text-2xl">
        <li class="hidden md:block" @mouseover="showHome = true" @mouseleave="showHome = false">
          <RouterLink to="/">
            <font-awesome-icon class="p-0" icon="fa-solid fa-house"/>
            <span v-if="showHome">Home</span>
          </RouterLink>
        </li>
        <li class="hidden md:block" @mouseover="showAbout = true" @mouseleave="showAbout = false">
          <RouterLink to="/about">
            <font-awesome-icon class="" icon="fa-solid fa-circle-info"/>
            <span v-if="showAbout">About</span>
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
import { ref,defineEmits } from "vue";
const emit = defineEmits()

const showMenu = ref(false)
const showHome = ref(false)
const showAbout = ref(false)
const searchInput = ref('');
const emitSearch = () => {
 emit('search', searchInput.value);
};


function toggleMenu() {
  showMenu.value = !showMenu.value;
}
</script>

<style scoped>
.box {
  max-width: 400px;
  width: 100%;
  margin-right: 11%;

}

.box .search-box{
  position: relative;
  height: 50px;
  max-width: 50px;
  margin: auto;
  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.25);
  border-radius: 25px;
  transition: all 0.2s ease;
}

#check:checked ~ .search-box {
  max-width: 380px;

}
.search-box input {
  height: 100%;
  width: 100%;
  border-radius: 25px;
  background: #fff;
  color: #1D1D1D;
  outline: none;
  border: none;
  padding-left: 20px;
  font-size: 18px;
}

.search-box .icon {
  position: absolute;

  right: 0;
  top: 0;
  width: 50px;
  background: #483d8b;
  height: 100%;
  color: #fff;
  border-radius: 25px;
  text-align: center;
  line-height: 50px;
  font-size: 20px;
}

#check:checked ~ .search-box .icon{
  background: #483d8b;
  color: #fff;
  width: 60px;
  border-radius: 0 25px 25px 0;
}
#check {
  display: none;
}

</style>
