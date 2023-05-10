import { createRouter, createWebHistory } from 'vue-router'
import RegistrationPage from "@/modules/Registration/page/RegistrationPage.vue";
import Confirmation from "@/modules/Registration/page/Confirmation.vue";
import Home from "@/view/Home.vue";
import EditPage from "@/modules/UserUpdate/EditPage.vue";
import EditProfil from "@/modules/UserUpdate/components/EditProfil.vue";
import EditPassword from "@/modules/UserUpdate/components/EditPassword.vue";

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
            name: 'confirm',
            component: Confirmation
        },
        {
            path: '/edit',
            name: 'edit',
            component: EditPage
        },
        {
            path: '/editprofil',
            name: 'editprofil',
            component: EditProfil
        },
        {
            path: '/EditPassword',
            name: 'EditPassword',
            component: EditPassword
        },
    ]
})

export default router