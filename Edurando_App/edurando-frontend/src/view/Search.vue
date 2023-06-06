<script setup>
import EdCard from "@/shared/ui/EdCard.vue";
import {useUserStore} from "@/store/store";

const user = useUserStore()
</script>

<template>
  <div class="flex flex-col justify-center items-center mt-[100px]">
    <h1 class="text-4xl font-bold text-center">Top 10 Bestbewertete Lehrer</h1>
    <div class="w-[15%] h-2 bg-[#483d8b] rounded-full mt-2 hover:w-[20%] hover:bg-purple-800 "></div>
  </div>
  <div class="flex flex-wrap justify-center items-center my-[50px] gap-4">
    <EdCard v-for="searchUser in user.getSearchResult"
            :key="searchUser.id" :item="searchUser"/>
  </div>
</template>

<style scoped>

</style>