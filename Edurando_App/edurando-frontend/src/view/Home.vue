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
        <img class="w-[400px] h-[600px] image-item" src="@/assets/images/TopTeacher/Krish.png" alt="Krish" />
        <ul class="image-points">
          <li>Hallo, Ich bin Krish Kalra und studiere Informatik.</li>
          <li>Ich studiere an der TH in Lübeck.</li>
          <li>Ich unterrichte programmieren in Java und Python</li>
        </ul>
      </div>
      <div class="Album">
        <img class="w-[400px] h-[600px] image-item" src="@/assets/images/TopTeacher/Bennet.png" alt="Bennet" />
        <ul class="image-points">
          <li>Ich bin Bennet Groklie und studiere Informatik.</li>
          <li>Ich studiere an der TH in Lübeck.</li>
          <li>Ich unterrichte programmieren in Java und Python</li>
        </ul>
      </div>
      <div class="Album">
        <img class="w-[400px] h-[600px] image-item" src="@/assets/images/TopTeacher/Ahmed.png" alt="Ahmed" />
        <ul class="image-points">
          <li>Ich bin Ahmed Radwan und studiere Informatik.</li>
          <li>Ich studiere an der TH in Lübeck.</li>
          <li>Ich unterrichte programmieren in Java und Mathe</li>
        </ul>
      </div>
      <div class="Album">
        <img class="w-[400px] h-[600px] image-item" src="@/assets/images/TopTeacher/Jamin.png" alt="Jamin" />
        <ul class="image-points">
          <li>Ich bin Jamin Karbasi und bin 21 Jahre alt.</li>
          <li>Ich studiere Wirtschaftsingeneurswesen an der TH</li>
          <li>in Lübeck. Ich unterrichte technische Mechanik</li>
        </ul>
      </div>
      <div class="Album">
        <img class="w-[400px] h-[600px] image-item" src="@/assets/images/TopTeacher/Marco.png" alt="Krish" />
        <ul class="image-points">
          <li>Ich bin Marco Phan und studiere Pflege.</li>
          <li>Ich studiere an der HAW in Hamburg.</li>
          <li>Ich unterrichte Anatomy</li>
        </ul>
      </div>
      <div class="Album">
        <img class="w-[400px] h-[600px] image-item" src="@/assets/images/TopTeacher/Kombi.png" alt="Krish" />
        <ul class="image-points">
          <li>Wir sind die drei Maschinen.</li>
          <li>Wir studieren an der TH in Lübeck.</li>
          <li>Und unterrichten programmieren Mathematik</li>
        </ul>
      </div>
    </div>
  </div>







</template>


  <style scoped>
    .image-list {
      display: flex;
      flex-wrap: wrap;
      justify-content: flex-start;
      padding: 0.2%;
      margin-left: 3%;
      margin-top: 60px;
    }

    .Album {
      width: 33.33%;
      padding-right: 1%;
      box-sizing: border-box;
      margin-bottom: 20px;
    }

    .image-points {
      list-style-type: none;
      padding: 0;
      margin-top: 10px;
    }

    .image-points li {
      margin-bottom: 5px;
      color: black;
      font-size: 18px;
    }

    .image-item {
      border: 7px solid;
      border-color: #483d8b;
    }

  </style>