<script setup>

</script>

<template>
  <div class="fixed inset-0 flex items-center justify-center z-50 bg-opacity-100 bg-gray-400 ">
    <div class="bg-white w-full max-w-md mx-auto rounded-lg shadow-lg">
      <div class="p-4">
        <h2 class="text-lg font-semibold">More About Me</h2>
      </div>
      <div class="flex items-center p-4">
        <img class="w-12 h-12 rounded-full mr-4" src="../../assets/p_placeholder.png" alt="Profile Photo">
        <div>
          <h3 class="font-medium">{{item.firstName + " " + item.lastName}}</h3>
          <p class="text-gray-500">{{item.personalBiography}}</p>
        </div>
      </div>
      <!--My Subjects section-->
      <div class="w-[40%] h-1 bg-[#483d8b]  mt-2  mx-auto "></div>
      <div class="p-4">
        <h3 class="text-lg font-semibold">My Subjects</h3>
        <div class="overflow-x-auto">
          <table class="mt-4 w-full border">
            <thead>
            <tr class="bg-gray-200">
              <th class="py-2 px-4 border-b">Subjects</th>
              <th class="py-2 px-4 border-b">Topics</th>
            </tr>
            </thead>
            <tbody>
            <tr class="bg-white">
              <td class="py-2 px-4 border-b">{{item.subjects[0].name}}</td>
              <td class="py-2 px-4 border-b">{{item.topics[0].name}}</td>
            </tr>
            <!-- <tr class="bg-gray-100">
               <td class="py-2 px-4 border-b">Subject 2</td>
               <td class="py-2 px-4 border-b">Topic 2</td>
             </tr>
             -->
            <!-- Add more rows as needed -->
            </tbody>
          </table>
        </div>
      </div>

      <div class="px-4 py-2 bg-gray-100">
        <!-- Modal content here -->
      </div>
      <div class="flex justify-end p-4">
        <button class="px-4 py-2 text-white bg-purple-800 rounded hover:bg-purple-600">Contact Me</button>
      </div>
    </div>
  </div>

</template>

<style scoped>

</style>