<script setup>
import {ref} from "vue";
import { reactive, onMounted } from "vue";
import axios from "axios";
import EdCard from "@/shared/ui/EdCard.vue";
import EdModal from "@/shared/ui/EdModal.vue";
import {useUserStore} from "@/store/store";
import Footer from "@/modules/Footer/Footer.vue"


const responseData = ref("");
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


onMounted(async () => {
  console.log(showModal.value)
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
      <h1 class="text-4xl font-bold text-center">Our Top 10 Teachers</h1>
      <div class="w-[15%] h-2 bg-[#483d8b] rounded-full mt-2 hover:w-[20%] hover:bg-purple-800 "></div>
  </div>
  <div class="flex flex-wrap justify-center items-center my-[50px] gap-4">
    <EdCard v-for="topUser in responseData" :key="topUser.id" :item="topUser" @show-modal="setShowModal"/>
    <EdModal v-if="showModal" :item="user" @close-modal="closeModal"/>
  </div>
  <Footer />


</template>

<style scoped>

</style>