import { createRouter, createWebHistory } from 'vue-router'
import RegistrationPage from "@/modules/Registration/page/RegistrationPage.vue";
import Confirmation from "@/modules/Registration/page/Confirmation.vue";
import UpdatePage from "@/modules/UserUpdate/EditPage.vue";
import EditPage from "@/modules/UserUpdate/EditPage.vue";

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/register',
            name: 'register',
            component: RegistrationPage
        },
        {
            path: '/confirm',
            name: 'confirm',
            component: Confirmation
        },
        {
            path: '/edit',
            name: 'edit',
            component: EditPage
        }

    ]
})

export default router