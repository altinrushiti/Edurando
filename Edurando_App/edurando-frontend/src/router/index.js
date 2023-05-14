import { createRouter, createWebHistory } from 'vue-router'
import RegistrationPage from "@/modules/Registration/page/RegistrationPage.vue";
import Confirmation from "@/modules/Registration/page/Confirmation.vue";
import Home from "@/view/Home.vue";
import EditPage from "@/modules/UserUpdate/EditPage.vue";
import EditProfile from "@/modules/UserUpdate/components/EditProfile.vue";
import ChangePassword from "@/modules/UserUpdate/components/ChangePassword.vue";
import SubjectsTopic from "@/modules/UserUpdate/components/SubjectsTopic.vue";

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
            path: '/editProfile',
            name: 'editProfile',
            component: EditProfile
        },
        {
            path: '/changePassword',
            name: 'changePassword',
            component: ChangePassword
        },
        {
            path: '/subjecttopics',
            name: 'subjecttopics',
            component: SubjectsTopic
        },
    ]
})

export default router