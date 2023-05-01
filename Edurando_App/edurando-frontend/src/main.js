import { createApp } from 'vue';
import { library } from '@fortawesome/fontawesome-svg-core';
import { faFacebook, faTwitter, faInstagram, faTiktok } from '@fortawesome/free-brands-svg-icons';
import { faArrowUp } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import '@fortawesome/fontawesome-free/css/all.css';
import router from './router'

import App from './App.vue';

import './assets/main.css';

// Add the necessary icons to the fontawesome library
library.add(faFacebook, faTwitter, faInstagram, faTiktok, faArrowUp);

// Create the app and register the FontAwesomeIcon component globally
const app = createApp(App);
app.component('font-awesome-icon', FontAwesomeIcon);
app.mount('#app');
app.use(router);