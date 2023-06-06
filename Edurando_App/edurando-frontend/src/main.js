import { createApp } from 'vue';
import { library } from '@fortawesome/fontawesome-svg-core';
import { faFacebook, faTwitter, faInstagram, faTiktok,} from '@fortawesome/free-brands-svg-icons';
import {faArrowUp, faHome, faCircleInfo, faBars, faTimes, faArrowLeft, faUser, faKey, faTableList, faAnglesRight, faAnglesLeft, faCirclePlus, faCheck, faPaperPlane, faMagnifyingGlass, faRightToBracket, } from '@fortawesome/free-solid-svg-icons'
import { createPinia } from "pinia";
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import '@fortawesome/fontawesome-free/css/all.css';
import router from './router'
import App from './App.vue';
import './assets/main.css';
import "./axios"
import piniaPluginPersistedState from "pinia-plugin-persistedstate"

library.add(faFacebook, faTwitter, faInstagram, faTiktok, faArrowUp, faHome, faCircleInfo, faBars, faTimes, faArrowLeft, faUser, faKey, faTableList, faAnglesRight, faAnglesLeft,faCirclePlus,faCheck,faPaperPlane, faMagnifyingGlass, faRightToBracket)

const pinia = createPinia()
pinia.use(piniaPluginPersistedState)
const app = createApp(App);
app.component('font-awesome-icon', FontAwesomeIcon);
app.use(pinia)
app.use(router);
app.mount('#app');