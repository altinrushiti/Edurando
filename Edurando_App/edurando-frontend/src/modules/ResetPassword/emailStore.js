
//pinia store for storing the email
import {defineStore} from "pinia";

export const useEmailStore = defineStore('email', {
    state: () => ({ email: "" }), // initial state
},)