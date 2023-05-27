<script setup>
import { useUserStore } from '@/store/store';
import {ref} from "vue";
import { reactive, onMounted } from "vue";

import axios from "axios";
const rating =ref(5)


const responseData = ref(null);

const user = useUserStore()

onMounted(async () => {
    try {
        const response = await axios.get("/top-users");
        responseData.value = response.data;
        console.log(responseData.value)
    } catch (error) {
        console.log(error)
    }
});

</script>

<template>
    <div class="flex flex-col justify-center items-center mt-[100px]">
      <h1 class="text-4xl font-bold text-center">Top 10 Bestbewertete Lehrer</h1>
      <div class="w-[15%] h-2 bg-[#483d8b] rounded-full mt-2 hover:w-[20%] hover:bg-purple-800 "></div>
    </div>
    <div class="flex flex-wrap justify-center items-center my-[50px] gap-4">

    <div class="max-w-sm bg-white border border-gray-200 rounded-lg shadow-1 dark:bg-gray-800 dark:border-gray-700" v-for="item in responseData" :key="item.id">
      <a href="#">
        <img class="rounded-t-lg" src="../assets/p_placeholder.png" alt="" />
      </a>
      <div class="p-5">
        <a href="#">
          <h5 class="mb-2 text-2xl font-bold tracking-tight text-gray-900 dark:text-white">{{item.firstName + " " + item.lastName}}</h5>
        </a>
        <p class="mb-3 font-normal text-gray-700 dark:text-gray-400">{{item.personalBiography}}</p>
        <div class="flex items-center mt-2.5 mb-5">
          <template v-for="star in item.rating">
            <svg aria-hidden="true" class="w-5 h-5 text-yellow-300" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><title>Star</title><path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"></path></svg>
          </template>
          <template v-for="star in 5-item.rating">
            <svg aria-hidden="true" class="w-5 h-5 text-gray-300" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><title>Star</title><path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"></path></svg>
          </template>
          <span class="bg-blue-100 text-blue-800 text-xs font-semibold mr-2 px-2.5 py-0.5 rounded dark:bg-blue-200 dark:text-blue-800 ml-3">{{ item.rating }}</span>
        </div>
        <a href="#" class="inline-flex items-center px-3 py-2 text-sm font-medium text-center text-white bg-[#483d8b] rounded-lg hover:bg-purple-800 focus:ring-4 focus:outline-none focus:ring-blue-300 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
          Read more
          <svg aria-hidden="true" class="w-4 h-4 ml-2 -mr-1" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M10.293 3.293a1 1 0 011.414 0l6 6a1 1 0 010 1.414l-6 6a1 1 0 01-1.414-1.414L14.586 11H3a1 1 0 110-2h11.586l-4.293-4.293a1 1 0 010-1.414z" clip-rule="evenodd"></path></svg>
        </a>
      </div>
    </div>
    </div>
</template>

<style scoped>

</style>