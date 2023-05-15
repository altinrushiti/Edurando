import { defineStore } from "pinia"
import axios from "axios"

export const users = defineStore("store", {
    state: () => ({
        users: [],
    }),
    getters: {
        getUsers(state) {
            return state.users
        }
    },
    actions: {
        async fetchUsers() {
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