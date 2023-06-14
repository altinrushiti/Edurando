<script setup>
import EdCard from "@/shared/ui/EdCard.vue";
import {useUserStore} from "@/store/store";
import EdModal from "@/shared/ui/EdModal.vue";
import {ref} from "vue";
import Footer from "@/modules/Footer/Footer.vue";

const userStore = useUserStore()
const showModal = ref(false)

const user = ref(null)

function setShowModal(data) {
  user.value = data
  console.log("test")
  showModal.value = true;
}

function closeModal() {
  user.value = null
  showModal.value = false;
}
</script>

<template>
  <div class="flex flex-wrap justify-center items-center my-[50px] gap-4 mt-[200px]">
    <EdCard v-if="userStore.getSearchResult.length > 0" v-for="searchUser in userStore.getSearchResult" :key="searchUser.id" :item="searchUser" @show-modal="setShowModal"/>
    <div v-else class="flex flex-col justify-center items-center mt-[200px]">
      <h1 class="text-6xl font-bold text-center">No Matches</h1>
      <div class="w-[15%] h-2 bg-[#483d8b] rounded-full mt-2 hover:w-[20%] hover:bg-purple-800 "></div>
    </div>
    <EdModal v-if="showModal" :item="user" @close-modal="closeModal"/>
  </div>
  <Footer />
</template>

<style scoped>

</style>