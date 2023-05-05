import { createApp } from 'vue';
import { library } from '@fortawesome/fontawesome-svg-core';
import { faFacebook, faTwitter, faInstagram, faTiktok } from '@fortawesome/free-brands-svg-icons';
import { faArrowUp, faHome, faCircleInfo, faBars, faTimes } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import '@fortawesome/fontawesome-free/css/all.css';
import router from './router'
import App from './App.vue';
import './assets/main.css';

library.add(faFacebook, faTwitter, faInstagram, faTiktok, faArrowUp, faHome, faCircleInfo, faBars, faTimes);

const app = createApp(App);
app.component('font-awesome-icon', FontAwesomeIcon);
app.use(router);
app.mount('#app');