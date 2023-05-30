<template>
    <button data-drawer-target="default-sidebar" data-drawer-toggle="default-sidebar" aria-controls="default-sidebar" type="button" class="inline-flex items-center p-2 mt-2 ml-3 text-sm text-gray-500 rounded-lg hover:bg-gray-100 focus:outline-none focus:ring-2 focus:ring-gray-200">
        <span class="sr-only">Open sidebar</span>
        <svg class="w-6 h-6" aria-hidden="true" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg">
            <path clip-rule="evenodd" fill-rule="evenodd" d="M2 4.75A.75.75 0 012.75 4h14.5a.75.75 0 010 1.5H2.75A.75.75 0 012 4.75zm0 10.5a.75.75 0 01.75-.75h7.5a.75.75 0 010 1.5h-7.5a.75.75 0 01-.75-.75zM2 10a.75.75 0 01.75-.75h14.5a.75.75 0 010 1.5H2.75A.75.75 0 012 10z"></path>
        </svg>
    </button>
    <aside id="default-sidebar" class="fixed top-0 left-0 z-40 w-50 h-full mt-16" aria-label="Sidebar">
        <div class="h-full px-3 py-4 overflow-y-auto" :class="isSidebarOpen ? 'bg-purple-50 border dark:bg-[#1f1c25] dark:border-[#17151a]' : 'bg-transparent md:bg-purple-50 dark:md:bg-[#1f1c25] dark:md:border-[#17151a] md:border'">
            <ul class="space-y-2 font-medium text-[#483d8b] dark:text-[#7f6dfc]">
                <li :class="!isSidebarOpen && 'hidden md:block'">
                    <RouterLink to="/editProfile" active-class="bg-[#e4e2ee] dark:bg-[#3d3844]" class="flex items-center p-2 rounded-lg hover:bg-[#e4e2ee] dark:hover:bg-[#3d3844]">
                        <font-awesome-icon :icon="['fas', 'user']" />
                        <span :class="isSidebarOpen ? '' : 'hidden'" class="flex-1 ml-3 whitespace-nowrap">Personal Data</span>
                    </RouterLink>
                </li>
                <li :class="!isSidebarOpen && 'hidden md:block'">
                    <RouterLink to="/ChangePassword" active-class="bg-[#e4e2ee] dark:bg-[#3d3844]" class="flex items-center p-2 rounded-lg hover:bg-[#e4e2ee] dark:hover:bg-[#3d3844]">
                        <font-awesome-icon :icon="['fas', 'key']" />
                        <span :class="isSidebarOpen ? '' : 'hidden'" class="flex-1 ml-3 whitespace-nowrap">Change Password</span>
                    </RouterLink>
                </li>
                <li v-if="role === 'teacher'" :class="!isSidebarOpen && 'hidden md:block'">
                    <RouterLink to="/SubjectsTopics" active-class="bg-[#e4e2ee] dark:bg-[#3d3844]" class="flex items-center p-2 rounded-lg hover:bg-[#e4e2ee] dark:hover:bg-[#3d3844]">
                        <font-awesome-icon :icon="['fas', 'table-list']" />
                        <span :class="isSidebarOpen ? '' : 'hidden'" class="flex-1 ml-3 whitespace-nowrap">Subjects/Topics</span>
                    </RouterLink>
                </li>
                <li>
                    <button :class="isSidebarOpen ? '' : 'hidden'" @click="toggleSidebar" class="flex items-center p-2 rounded-lg hover:bg-[#e4e2ee] dark:hover:bg-[#3d3844]">
                        <font-awesome-icon :icon="['fas', 'angles-left']" />
                        <span class="flex-1 ml-3 whitespace-nowrap">Collapse Sidebar</span>
                    </button>
                    <button :class="isSidebarOpen ? 'hidden' : '' " @click="toggleSidebar" class="flex items-center p-2 rounded-lg hover:bg-[#e4e2ee] dark:hover:bg-[#3d3844]">
                        <font-awesome-icon :icon="['fas', 'angles-right']" />
                    </button>
                </li>
            </ul>
        </div>
    </aside>
</template>

<script setup>
import { ref } from 'vue';
import {useUserStore} from "@/store/store";

const isSidebarOpen = ref(false);
const user = useUserStore()
const role = user.getUser.role

function toggleSidebar() {
    isSidebarOpen.value = !isSidebarOpen.value;
}
</script>
