<script setup>
import { useUserStore } from '@/store/store';
import {ref, watch} from "vue";
import { reactive, onMounted } from "vue";
import axios from "axios";
import EdCard from "@/shared/ui/EdCard.vue";
import NavBar from "@/modules/NavBar/components/NavBar.vue";

const responseData = ref("");
const searchInput = ref("");
const searchData = ref('');
const user = useUserStore()
const tempData = ref("");


function handleSearch(input) {
  searchInput.value = input;
  watch(searchInput, (val) => {
    if (val.length > 0) {
      searchData.value = searchData.value.filter((user) => {
        return (user.role==="teacher" ) && user.firstName.toLowerCase().startsWith(val.toLowerCase() || user.topics[1].name.toLowerCase().startsWith(val.toLowerCase()));
      });
    } else {
      searchData.value = tempData.value;
    }
  });
}


onMounted(async () => {
    try {
        const response = await axios.get("/top-users");
        responseData.value = response.data;
        console.log(responseData.value)
    } catch (error) {
        console.log(error)
    }
  try {
    const response = await axios.get("/profiles");

    searchData.value = response.data;
    tempData.value = response.data;
    console.log(searchData.value)
  } catch (error) {
    console.log(error)
  }

  /*try {
    const response = await axios.get(`/profiles/search/${searchInput.value}`)
    searchData.value = response.data;
    console.log(searchData.value)
  } catch (error) {
    console.log(error)
  }*/
});

</script>

<template>
  <NavBar @search="handleSearch"/>
  <div class="flex flex-col justify-center items-center mt-[100px]">
      <h1 v-show="!searchInput" class="text-4xl font-bold text-center">Top 10 Bestbewertete Lehrer</h1>
      {{tempData.length}}
     <h1 v-show="searchInput.length"  class="text-4xl font-bold text-center">Search Result</h1>
      <div class="w-[15%] h-2 bg-[#483d8b] rounded-full mt-2 hover:w-[20%] hover:bg-purple-800 "></div>
    </div>
    <div class="flex flex-wrap justify-center items-center my-[50px] gap-4">
      <EdCard v-show="!searchInput" v-for="user in responseData"
              :key="user.id" :item="user"/>

      <EdCard v-show="searchInput" v-for="result in searchData"
              :key="result.id" :item="result"/>


      <h1 v-show="searchData.length===0" class="text-5xl font-bold text-center h-screen ">No Result for this Search</h1>
    </div>
</template>

<style scoped>

</style>