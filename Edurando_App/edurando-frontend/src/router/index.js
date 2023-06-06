import { createRouter, createWebHistory } from 'vue-router'
import RegistrationPage from "@/modules/Registration/page/RegistrationPage.vue";
import Confirmation from "@/modules/Registration/page/Confirmation.vue";
import Home from "@/view/Home.vue";
import EditProfile from "@/modules/UserUpdate/components/EditProfile.vue";
import ChangePassword from "@/modules/UserUpdate/components/ChangePassword.vue";
import SubjectsTopic from "@/modules/UserUpdate/components/SubjectsTopic.vue";
import Login from "@/modules/Login/components/Login.vue";
import SubjectsTopicSave from "@/modules/UserUpdate/components/SubjectsTopicSave.vue";
import {useUserStore} from "@/store/store";
import NotFound from "@/view/NotFound.vue";
import Chat from "@/modules/Chat/Page/Chat.vue";
import Imprint from "@/modules/Imprint/imprint.vue";
import Search from "@/view/Search.vue";

const entryList = ['entry1', 'entry2', 'entry3']

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'home',
            component: Home
        },
        {
            path: '/page-not-found',
            component: NotFound
        },
        {
            path: '/:catchAll(.*)',
            redirect: '/page-not-found'
        },
        {
            path: '/register',
            name: 'register',
            component: RegistrationPage
        },
        {
            path: '/confirm',
            name: 'confirm',
            component: Confirmation,
        },
        {
            path: '/editProfile',
            name: 'editProfile',
            component: EditProfile,
            meta: {
                needsAuth: true
            }
        },
        {
            path: '/changePassword',
            name: 'changePassword',
            component: ChangePassword,
            meta: {
                needsAuth: true
            }
        },
        {
            path: '/SubjectsTopics',
            name: 'SubjectsTopics',
            component: SubjectsTopic,
            meta: {
                needsAuth: true
            }
        },
        {
            path: '/login',
            name: 'login',
            component: Login
        },
        {
            path: '/SubjectsTopicSave',
            name: 'SubjectsTopicSave',
            component: SubjectsTopicSave,
            meta: {
                needsAuth: true
            }
        },
        {
            path: '/imprint',
            name: 'imprint',
            component: Imprint
        },
        {
            path: '/chat',
            name: 'chat',
            component: Chat,
            meta: {
                needsAuth: true
            }
        },
        {
            path: '/chat/:username',
            name: 'chat2',
            component: Chat,
            meta: {
                needsAuth: true
            }
        },
        {
            path: '/search',
            name: 'search',
            component: Search,
        },

    ]
})


router.beforeEach(async (to, from, next) => {
    const isLogged = await useUserStore().getUser !== null
    if (to.meta.needsAuth && !isLogged) {
        next('/login');

    } else {
        next();
    }

})

export default router

