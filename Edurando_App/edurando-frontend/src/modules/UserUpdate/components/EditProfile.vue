<template>
    <edit-page></edit-page>
    <div class="min-h-screen bg-gray-100 dark:bg-[#181818] flex items-center justify-center py-12 px-4 sm:px-6 lg:px-8">
        <div class="max-w-md w-full space-y-8">
            <div>
                <h2 class="mt-10 text-center text-3xl font-extrabold text-gray-900 dark:text-[#b5a9fc]">
                    Edit Profile
                </h2>
            </div>
            <form class="mt-8 space-y-6" @submit.prevent="onEdit">
                <div class="rounded-md shadow-sm space-y-2">
                  <label for="profilePicture" class="text-black dark:text-[#b5a9fc] font-font-family p-2 file-sel">Profile picture</label>
                  <div class="relative m-auto w-[100px]">
                    <img class="rounded-full w-[100px] h-[100px]" :src="image" alt="" />
                    <div class="absolute bottom-0 right-0 bg-[#483d8b] w-[30px] h-[30px] leading-[33px] text-center rounded-full overflow-hidden ">
                      <input id="profilePicture" name="profilePicture" type="file" @change="handleFileChange" class="absolute scale-[2] opacity-0" ref="fileInput">
                      <font-awesome-icon :icon="['fa', 'camera']" class="static text-white"/>
                    </div>
                  </div>
                  <div class="relative flex justify-center">
                    <button @click="removeImage" class="bg-red-500 rounded-md px-2 hover:bg-red-400" type="button">Remove image</button>
                  </div>
                  <div>
                        <label for="firstname" class="text-black dark:text-[#b5a9fc] font-font-family p-2">First Name</label>
                        <input id="firstname" name="firstname" type="text" v-model="user.firstName" required
                               autocomplete="firstname" placeholder="Firstname"
                               class="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 focus:outline-none dark:bg-[#c6c5d1] dark:border-[#9895ad] dark:text-black rounded-t-md focus:ring-purple-500 focus:border-purple-500 focus:z-10 sm:text-sm"
                               pattern="[A-Za-zäöüÄÖÜß\-]+" title="Please only enter letters">
                  </div>
                    <div>
                        <label for="lastname" class="text-black dark:text-[#b5a9fc] font-font-family p-1">Last Name</label>
                        <input id="lastname" name="lastname" type="text" v-model="user.lastName"
                               autocomplete="lastname" required placeholder="Lastname"
                               class="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 focus:outline-none dark:bg-[#c6c5d1] dark:border-[#9895ad] dark:text-black rounded-t-md focus:ring-purple-500 focus:border-purple-500 focus:z-10 sm:text-sm"
                               pattern="[A-Za-zäöüÄÖÜß\-]+" title="Please only enter letters">
                    </div>
                    <div>
                        <label for="biography" class="text-black dark:text-[#b5a9fc] font-font-family p-1">Biography</label>
                        <textarea id="biography" name="biography" v-model="user.personalBiography" rows="5"
                                  class="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 dark:bg-[#c6c5d1] dark:border-[#9895ad] dark:text-black rounded-b-md focus:outline-none focus:ring-purple-500 focus:border-purple-500 focus:z-10 sm:text-sm"
                                  placeholder="Write your biography here"
                                  minlength="10"></textarea>
                    </div>
                    <div>
                        <label for="role" class="text-black dark:text-[#b5a9fc] font-font-family flex p-1 font-size=10px">Gender</label>
                        <select
                                class="bg-white text-gray-900 rounded-none relative block w-full px-3 py-2 border border-gray-300 rounded-b-md focus:outline-none dark:bg-[#c6c5d1] dark:border-[#9895ad] dark:text-black focus:ring-purple-500 focus:border-purple-500 focus:z-10 sm:text-sm"
                                id="role" v-model="user.gender">
                            <option value="" disabled>Select gender</option>
                            <option class="2xl" value="Male">Male</option>
                            <option class="2xl" value="Female">Female</option>
                        </select>
                    </div>
                    <div>
                        <label for="role" class="text-black dark:text-[#b5a9fc] font-font-family flex p-1 font-size=10px">Role</label>
                        <select
                                class="bg-white text-gray-900 rounded-none relative block w-full px-3 py-2 border border-gray-300 dark:bg-[#c6c5d1] dark:border-[#9895ad] dark:text-black rounded-b-md focus:outline-none focus:ring-purple-500 focus:border-purple-500 focus:z-10 sm:text-sm"
                                id="role" v-model="user.role">
                            <option value="" disabled>Select role</option>
                            <option value="student">Student</option>
                            <option value="teacher">Teacher</option>
                        </select>
                    </div>
                    <div class="flex flex-wrap -mx-3 mb-6">
                        <div class="w-full md:w-1/2 px-3 mb-6 md:mb-0">
                            <label for="street" class="text-black dark:text-[#b5a9fc] font-font-family p-1">Street</label>
                            <input id="street" name="street" type="text" v-model="user.street"
                                   placeholder="Street"
                                   class="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 dark:bg-[#c6c5d1] dark:border-[#9895ad] dark:text-black placeholder-gray-500 text-gray-900 rounded-b-md focus:outline-none focus:ring-purple-500 focus:border-purple-500 focus:z-10 sm:text-sm"
                                   pattern="[A-Za-zäöüÄÖÜß\-]+" title="Please only enter your street here">
                        </div>
                        <div class="w-full md:w-1/2 px-3">
                            <label for="houseNumber" class="text-black dark:text-[#b5a9fc] font-font-family p-1">House Number</label>
                            <input id="houseNumber" name="houseNumber" type="text" v-model="user.houseNumber"

                                   class="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 dark:bg-[#c6c5d1] dark:border-[#9895ad] dark:text-black placeholder-gray-500 text-gray-900 rounded-b-md focus:outline-none focus:ring-purple-500 focus:border-purple-500 focus:z-10 sm:text-sm"
                                   placeholder="House Number">
                        </div>
                    </div>
                    <div class="flex flex-wrap -mx-3 mb-6">
                        <div class="w-full md:w-1/2 px-3 mb-6 md:mb-0">
                            <label for="postcode" class="text-black dark:text-[#b5a9fc] font-font-family p-1">Postcode</label>
                            <input id="postcode" name="postcode" type="tel" v-model="user.postCode" pattern="[0-9]{5}"

                                   class="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 dark:bg-[#c6c5d1] dark:border-[#9895ad] dark:text-black placeholder-gray-500 text-gray-900 rounded-b-md focus:outline-none focus:ring-purple-500 focus:border-purple-500 focus:z-10 sm:text-sm"
                                   placeholder="Postcode">
                        </div>
                        <div class="w-full md:w-1/2 px-3">
                            <label for="city" class="text-black dark:text-[#b5a9fc] font-font-family p-1">City</label>
                            <input id="city" name="city" type="text" v-model="user.city"

                                   class="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 dark:bg-[#c6c5d1] dark:border-[#9895ad] dark:text-black placeholder-gray-500 text-gray-900 rounded-b-md focus:outline-none focus:ring-purple-500 focus:border-purple-500 focus:z-10 sm:text-sm"
                                   placeholder="City">
                        </div>
                    </div>

                    <div>
                        <label for="state" class="text-black dark:text-[#b5a9fc] font-font-family p-1">State</label>
                        <input id="state" name="state" type="text" v-model="user.state"

                               class="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 dark:bg-[#c6c5d1] dark:border-[#9895ad] dark:text-black placeholder-gray-500 text-gray-900 rounded-b-md focus:outline-none focus:ring-purple-500 focus:border-purple-500 focus:z-10 sm:text-sm"
                               placeholder="State">
                    </div>

                    <div>
                        <label for="mobile" class="text-black dark:text-[#b5a9fc] font-font-family mb-2">Mobile</label>
                        <input id="mobile" name="mobile" type="tel" v-model="user.mobile"   pattern="^[+]?[0-9]+$"

                               class="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 dark:bg-[#c6c5d1] dark:border-[#9895ad] dark:text-black placeholder-gray-500 text-gray-900 rounded-b-md focus:outline-none focus:ring-purple-500 focus:border-purple-500 focus:z-10 sm:text-sm"
                               placeholder="Mobile number">
                    </div>
                </div>
                <div>
                    <button type="submit"
                            class=" text-center mx-auto w-1/2 flex justify-center py-2 px-4 border border-transparent text-sm font-medium rounded-md text-white bg-[#483d8b] hover:bg-purple-950 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500">
                        Save Changes
                    </button>
                </div>
            </form>
        </div>
    </div>
<Footer></Footer>
</template>

<script setup>
import {onMounted, reactive, ref, watch} from "vue";
import axios from "axios";
import {useUserStore} from "@/store/store";
import router from "@/router";
import EditPage from "@/modules/UserUpdate/EditPage.vue";
import Footer from "@/modules/Footer/Footer.vue";

const userStore = useUserStore();

const user = reactive({
    id: userStore.getUser.id,
    firstName: userStore.getUser.firstName,
    lastName: userStore.getUser.lastName,
    gender: userStore.getUser.gender,
    role: userStore.getUser.role,
    personalBiography: userStore.getUser.personalBiography,
    mobile: userStore.getUser.mobile,
    street: userStore.getUser.address.street,
    houseNumber: userStore.getUser.address.houseNumber,
    city: userStore.getUser.address.city,
    state: userStore.getUser.address.state,
    postCode: userStore.getUser.address.postCode !== -1 ? userStore.getUser.address.postCode : ''
})
const image = ref(null)
const file = ref(null)

onMounted(async () => {
  // Dynamisches Importieren des Bildes
  const imageModule = await import(userStore.getUser.profilePictureReference);
  image.value = imageModule.default
});

async function onEdit() {
    try {
      const response1 = await axios.put('/updatePersonalData', user)
      if (file.value !== null) {
        let formData = new FormData()
        formData.append('id', user.id)
        formData.append('file', file.value)
        const response2 = await axios.post('/upload', formData, {
          headers: {
            'Content-Type': 'multipart/form-data'
          }
        })
      }
      await userStore.fetchUserById(user.id)
      await router.push('/')
    } catch (error) {
        console.log(error.response.data)
    }
}

function handleFileChange(event) {
  file.value = event.target.files[0];

  image.value = URL.createObjectURL(file.value)
}

async function removeImage() {
  const imageModule = await import("../../../assets/p_placeholder.png");
  image.value = imageModule.default
  try {
    const response1 = await axios.delete(`/removeImage/?id=${user.id}`)
    const response2 = await userStore.fetchUserById(user.id)
    console.log(response1.data)

  } catch (error) {
    console.error(error)
  }
}
</script>

<style scoped>
  input[type="file"]::-webkit-file-upload-button {
    cursor: pointer;
  }
</style>
