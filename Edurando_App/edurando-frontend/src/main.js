import { createApp } from 'vue';
import { library } from '@fortawesome/fontawesome-svg-core';
import { faFacebook, faTwitter, faInstagram, faTiktok,} from '@fortawesome/free-brands-svg-icons';
import {faArrowUp, faHome, faCircleInfo, faBars, faTimes, faArrowLeft, faUser, faKey, faTableList, faAnglesRight, faAnglesLeft, faCirclePlus, faCheck} from '@fortawesome/free-solid-svg-icons'
import { createPinia } from "pinia";
import { faFacebook, faTwitter, faInstagram, faTiktok } from '@fortawesome/free-brands-svg-icons';
import {faArrowUp, faHome, faCircleInfo, faBars, faTimes, faArrowLeft, faUser, faKey, faTableList, faAnglesRight, faAnglesLeft} from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import '@fortawesome/fontawesome-free/css/all.css';
import router from './router'
import App from './App.vue';
import './assets/main.css';
import "./axios"

library.add(faFacebook, faTwitter, faInstagram, faTiktok, faArrowUp, faHome, faCircleInfo, faBars, faTimes, faArrowLeft, faUser, faKey, faTableList, faAnglesRight, faAnglesLeft,faCirclePlus,faCheck)

const pinia = createPinia()
const app = createApp(App);
app.component('font-awesome-icon', FontAwesomeIcon);
app.use(router);
app.use(pinia)
app.mount('#app');