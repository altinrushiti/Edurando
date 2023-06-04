import { defineStore } from 'pinia';
import axios from 'axios';
import {ref} from "vue";


export const useUserStore = defineStore('user', {
    state: () => ({
        user: null,
        isLoggedOut: true,
        chatReceiver: 0
    }),
    persist: true,
    getters: {
        getUser() {
            return this.user;
        },
        getIsLoggedOut() {
            return this.isLoggedOut;
        },
        getChatReceiver() {
            return this.chatReceiver;
        },
    },
    actions: {
        async fetchUser(email) {
            try {
                const response = await axios.get(`http://localhost:9001/api/v1/profileByEmail/${email}`);
                const user = response.data;
                const isLoggedOut = false

                this.user = user;
                this.isLoggedOut = isLoggedOut

                // Benutzer im Local Storage speichern
                localStorage.setItem('user', JSON.stringify(user));
                localStorage.setItem('isLoggedOut', JSON.stringify(isLoggedOut));

                // Weitere Aktionen ausführen
                // ...
            } catch (error) {
                console.error(error);
            }
        },
        async fetchUserById(id) {
            try {
                const response = await axios.get(`http://localhost:9001/api/v1/profile/${id}`);
                const user = response.data;
                const isLoggedOut = false

                this.user = user;
                this.isLoggedOut = isLoggedOut

                // Benutzer im Local Storage speichern
                localStorage.setItem('user', JSON.stringify(user));
                localStorage.setItem('isLoggedOut', JSON.stringify(isLoggedOut));

                // Weitere Aktionen ausführen
                // ...
            } catch (error) {
                console.error(error);
            }
        },
        async logOut() {
            const user = null;
            const isLoggedOut = true
            this.user = user
            this.isLoggedOut = isLoggedOut
            localStorage.setItem('user', JSON.stringify(user));
            localStorage.setItem('isLoggedOut', JSON.stringify(isLoggedOut));
        },
        async fetchChatReceiverById(id) {
            const chatReceiver = id
            this.chatReceiver = chatReceiver
            localStorage.setItem('chatReceiver', JSON.stringify(chatReceiver))
        }
    },
});
