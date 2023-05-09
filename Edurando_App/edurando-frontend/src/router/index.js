import { createRouter, createWebHistory } from 'vue-router'
import RegistrationPage from "@/modules/Registration/page/RegistrationPage.vue";
import Confirmation from "@/modules/Registration/page/Confirmation.vue";
import Home from "@/view/Home.vue";

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'home',
            component: Home,
        },
        {
            path: '/register',
            name: 'register',
            component: RegistrationPage
        },
        {
            path: '/confirm',
            props: true,
            name: 'confirm',
            component: Confirmation
        },
    ]
})

export default router