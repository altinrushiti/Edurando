import { defineStore } from 'pinia';
import axios from 'axios';

export const useUserStore = defineStore('user', {
    state: () => ({
        user: null,
    }),
    persist: true,
    getters: {
        getUser() {
            return this.user;
        },
    },
    actions: {
        async fetchUser(email) {
            try {
                const response = await axios.get(`http://localhost:9001/api/v1/profileByEmail/${email}`);
                const user = response.data;

                this.user = user;

                // Benutzer im Local Storage speichern
                localStorage.setItem('user', JSON.stringify(user));

                // Weitere Aktionen ausführen
                // ...
            } catch (error) {
                console.error(error);
            }
        },
    },
});
