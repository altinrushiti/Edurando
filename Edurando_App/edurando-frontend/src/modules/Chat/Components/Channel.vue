<template>
  <div class="flex items-center justify-center h-screen max-w-[50%]">
    <div :class="{'relative': isChannel}" class="rounded-lg shadow-lg p-4">
      <div class="flex items-start ">
        <div class="flex-shrink-0">
          <img class="h-8 w-8 rounded-full" src="avatar.png" alt="Avatar" />
        </div>
        <div class="ml-4 ">
          <div class="text-sm font-medium text-gray-900">Benutzername</div>
          <div class="mt-1 text-gray-700 ">Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.</div>
        </div>
      </div>
      <div v-if="isChannel" class="absolute top-0 left-0 h-full w-2 bg-green-500 rounded-l-lg"></div>
    </div>
  </div>
</template>


<script setup>
import {useRouter} from "vue-router";

const isChannel = true;
const router = useRouter()

</script>

<style>
</style>
