<template>
  <div class="flex items-end justify-start left-0">
    <div :class="{'relative': isReceiver}" class="rounded-lg shadow-lg p-4 max-w-[50%]">
      <div class="flex items-start ">
        <div class="flex-shrink-0">
          <img class="h-8 w-8 rounded-full" src="../../../assets/p_placeholder.png" alt="Avatar" />
        </div>
        <div class="ml-4 ">
          <div class="mt-1 text-gray-700 ">{{ item.contents }}</div>
        </div>
      </div>
      <div v-if="isReceiver" class="absolute top-0 left-0 h-full w-2 bg-orange-500 rounded-l-lg"></div>
    </div>
  </div>
</template>


<script setup>
import {useRouter} from "vue-router";
import {defineProps} from "vue";

const isReceiver = true;
const router = useRouter()
const props = defineProps(["item"]);

</script>

<style>
</style>
