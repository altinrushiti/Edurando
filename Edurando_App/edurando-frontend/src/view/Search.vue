<script setup>
import EdCard from "@/shared/ui/EdCard.vue";
import {useUserStore} from "@/store/store";

const user = useUserStore()
</script>

<template>
  <div class="flex flex-wrap justify-center items-center my-[50px] gap-4 mt-[200px]">
    <EdCard v-if="user.getSearchResult.length > 0" v-for="searchUser in user.getSearchResult"
            :key="searchUser.id" :item="searchUser"/>
    <div v-else class="flex flex-col justify-center items-center mt-[200px]">
      <h1 class="text-6xl font-bold text-center">No Matches</h1>
      <div class="w-[15%] h-2 bg-[#483d8b] rounded-full mt-2 hover:w-[20%] hover:bg-purple-800 "></div>
    </div>
  </div>
</template>

<style scoped>

</style>