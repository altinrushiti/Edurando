import { createApp } from 'vue';
import { library } from '@fortawesome/fontawesome-svg-core';
import { faFacebook, faTwitter, faInstagram, faTiktok,} from '@fortawesome/free-brands-svg-icons';
import {faArrowUp, faHome, faCircleInfo, faBars, faTimes, faArrowLeft, faUser, faKey, faTableList, faAnglesRight, faAnglesLeft, faCirclePlus, faCheck} from '@fortawesome/free-solid-svg-icons'
import { createPinia } from "pinia";
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import '@fortawesome/fontawesome-free/css/all.css';
import router from './router'
import App from './App.vue';
import './assets/main.css';
import "./axios"
import { Quasar } from 'quasar'
// Import icon libraries
import '@quasar/extras/roboto-font-latin-ext/roboto-font-latin-ext.css'
import '@quasar/extras/material-icons/material-icons.css'
import '@quasar/extras/fontawesome-v6/fontawesome-v6.css'
// Import Quasar css
import 'quasar/src/css/index.sass'
import piniaPluginPersistedState from "pinia-plugin-persistedstate"

import { Quasar } from 'quasar'

// Import icon libraries
import '@quasar/extras/roboto-font-latin-ext/roboto-font-latin-ext.css'
import '@quasar/extras/material-icons/material-icons.css'
import '@quasar/extras/material-icons-outlined/material-icons-outlined.css'
import '@quasar/extras/material-icons-round/material-icons-round.css'
import '@quasar/extras/material-icons-sharp/material-icons-sharp.css'
import '@quasar/extras/material-symbols-outlined/material-symbols-outlined.css'
import '@quasar/extras/material-symbols-rounded/material-symbols-rounded.css'
import '@quasar/extras/material-symbols-sharp/material-symbols-sharp.css'

// A few examples for animations from Animate.css:
// import @quasar/extras/animate/fadeIn.css
// import @quasar/extras/animate/fadeOut.css

// Import Quasar css
/*import 'quasar/src/css/index.sass'*/

library.add(faFacebook, faTwitter, faInstagram, faTiktok, faArrowUp, faHome, faCircleInfo, faBars, faTimes, faArrowLeft, faUser, faKey, faTableList, faAnglesRight, faAnglesLeft,faCirclePlus,faCheck)

const pinia = createPinia()
pinia.use(piniaPluginPersistedState)
const app = createApp(App);
app.component('font-awesome-icon', FontAwesomeIcon);
app.use(router);
app.use(pinia)
app.use(Quasar, {
    plugins: {}
})
app.mount('#app');