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
  <div class=" bg-gray-100 items-center justify-center py-12 px-4 sm:px-6 lg:px-8">

    <div class="h-[20em] bg-gray-100 flex items-center justify-center">
      <h1 class="text-4xl font-font-family text-slate-900">Welcome to Edurando</h1>
      <h1 class="text-4xl font-font-family text-slate-900">{{ user.getUser }}</h1>
    </div>

    <div class="h-full bg-gray-100 flex items-center justify-center">
      <!-- <div id="part2"><br /><br /><br /> </div> -->
      <h2 class="text-4xl font-font-family text-slate-900">Top Teacher</h2>
    </div>

    <div class="image-list">
      <div class="Album">
        <img class="w-[400px] h-[600px]" src="@/assets/images/TopTeacher/Krish.png" alt="Krish" />
      </div>

      <div class="Album">
        <img class="w-[400px] h-[600px]" src="@/assets/images/TopTeacher/Bennet.png" alt="Bennet" />
      </div>

      <div class="Album">
        <img class="w-[400px] h-[600px]" src="@/assets/images/TopTeacher/Ahmed.png" alt="Ahmed" />
      </div>

      <div class="Album">
        <img class="w-[400px] h-[600px]" src="@/assets/images/TopTeacher/Jamin.png" alt="Jamin" />
      </div>

      <div class="Album">
        <img class="w-[400px] h-[600px]" src="@/assets/images/TopTeacher/Marco.png" alt="Marco" />
      </div>

      <div class="Album">
        <img class="w-[400px] h-[600px]" src="@/assets/images/TopTeacher/Kombi.png" alt="Kombi" />
      </div>

    </div>


  </div>







</template>

<style scoped>

.image-list {
  display: grid;
  grid-template-columns: repeat(3, 1fr); /* Drei Spalten */
  gap: 20px; /* Abstand zwischen den Bildern */
  padding: 0.2%;
  margin-left: 3%;
  margin-top: 60px;
}

.Album {
  margin-right: 3%; /* Optional: Anpassung des Abstands zwischen den Bildern */
}

</style>