import { defineStore } from "pinia"
import axios from "axios"

export const user = defineStore("store", {
    state: () => ({
        user: null,
    }),
    getters: {
        getUser(state) {
            return state.users
        }
    },
    actions: {
        async fetchUser() {
            try {
                const data = await axios.get('https://jsonplaceholder.typicode.com/users')
                this.users = data.data
            }
            catch (error) {
                console.log(error)
            }
        }
    }
})