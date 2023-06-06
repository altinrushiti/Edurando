<template>
  <header class="text-white font-font-family z-50">
    <div v-if="showSubmenu" class="fixed w-auto top-10 mt-[0.8%] h-auto right-0 bg-gray-100 dark:bg-[#181818] p-2 -z-50" @mouseleave="showSubmenu = false">
      <ul class="list-none mt-0">
        <li class="hover:bg-gray-200 dark:hover:bg-gray-800 p-2">
          <RouterLink to="/editProfile">
            <p class="dark:text-white text-black text-center">Profile</p>
          </RouterLink>
        </li>
        <li class="hover:bg-gray-200 dark:hover:bg-gray-800 p-2">
          <RouterLink to="/chat">
            <p class="dark:text-white text-black text-center">Chat</p>
          </RouterLink>
        </li>
        <li class="hover:bg-gray-200 dark:hover:bg-gray-800 p-2">
          <RouterLink to="/settings">
            <p class="dark:text-white text-black text-center">Settings</p>
          </RouterLink>
        </li>
        <li class="hover:bg-gray-200 dark:hover:bg-gray-800 p-2 border-t-gray-200 border-t-2 dark:border-t-gray-800">
          <button @click="logOut">
            <p class="dark:text-white text-black text-center">Logout</p>
          </button>
        </li>
      </ul>
    </div>
    <nav class="fixed top-0 left-0 right-0 z-50 flex justify-between items-center bg-[#483d8b]">
      <button class="logo flex items-center" @click="redirectToHome">
        <img class="w-[100px] h-[60px]" src="@/assets/logo/logo_image.png" alt="Logo"/>
        <h1 class="text-3xl font-medium text-align text-align: center">Edurando</h1>
      </button>
      <div class="box">
        <input type="checkbox" id="check">
        <div class="search-box">
          <input v-model="searchTerm" type="text" placeholder="type here">
          <label for="check" class="icon">
            <font-awesome-icon icon="fa-solid fa-magnifying-glass"></font-awesome-icon>
          </label>
        </div>
      </div>
      <div v-if="!user.getIsLoggedOut" class="absolute w-[2.5%] bg-white right-0 border-gray-200 rounded-full shadow dark:bg-gray-800 dark:border-gray-700 m-[1%] hover:cursor-pointer" @click="showSubmenu = !showSubmenu">
        <img class="rounded-full" src="@/assets/p_placeholder.png" alt="" />
      </div>
      <div v-else class="flex">
        <RouterLink to="/register">
          <div class="flex flex-col items-center hover:cursor-pointer mr-5">
            <font-awesome-icon icon="fa-solid fa-user"></font-awesome-icon>
            <span>Sign Up</span>
          </div>
        </RouterLink>
        <RouterLink to="/login">
          <div class="flex flex-col items-center hover:cursor-pointer mr-5">
            <font-awesome-icon icon="fa-solid fa-right-to-bracket"></font-awesome-icon>
            <span>Login</span>
          </div>
        </RouterLink>
      </div>


    </nav>
  </header>
</template>

<script setup>
import {onMounted, ref, watch} from "vue";
import {useUserStore} from "@/store/store";
import {useRouter} from "vue-router";

const showSubmenu = ref(false)
const user = useUserStore()
const router = useRouter()
const searchTerm = ref('')

onMounted(() => {
  console.log(user.getIsLoggedOut === true)
  console.log(user.getUser)
})

watch(() => searchTerm.value, (newValue) => {
  if (newValue.length > 2) {
    router.push('/search')
    user.fetchSearchResult(newValue)
  } else {
    router.push('/')
  }
})

async function logOut() {
  try {
    await user.logOut()
    await router.push('/')
    location.reload()
  } catch (error) {
    console.log(error)
  }
}

function redirectToHome() {
  router.push('/')
}

</script>

<style scoped>
.box {
  position: absolute;
  max-width: 400px;
  width: 100%;
  left: 40%;
}

.box .search-box {
  position: relative;
  height: 50px;
  max-width: 50px;
  margin: auto;
  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.25);
  border-radius: 25px;
  transition: all 0.2s ease;
}

#check:checked ~ .search-box {
  max-width: 380px;
}

.search-box input {
  height: 100%;
  width: 100%;
  border-radius: 25px;
  background: #fff;
  color: #1D1D1D;
  outline: none;
  border: none;
  padding-left: 20px;
  font-size: 18px;
}

.search-box .icon {
  position: absolute;

  right: 0;
  top: 0;
  width: 50px;
  background: #483d8b;
  height: 100%;
  color: #fff;
  border-radius: 25px;
  text-align: center;
  line-height: 50px;
  font-size: 20px;
}

#check:checked ~ .search-box .icon {
  background: #483d8b;
  color: #fff;
  width: 60px;
  border-radius: 0 25px 25px 0;
}

#check {
  display: none;
}

</style>
