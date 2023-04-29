<template>
  <footer class="bg-[#483d8b] text-white">
    <div class="flex justify-around items-center p-2">
      <div class="logo flex items-center">
        <img class="w-[100px] h-[60px]" src="@/assets/logo/logo_image.png" alt="Logo" />
        <h1 class="text-3xl font-medium">Edurando</h1>
      </div>
      <ul class="flex justify-around gap-2 m-2 text-2xl">
        <li>
          <router-link to="/">Home</router-link>
        </li>
        <li>
          <router-link to="/about">About</router-link>
        </li>
      </ul>
    </div>
  </footer>
</template>

<script>
export default {
  name: "Footer"
}
</script>

<style scoped>

</style>