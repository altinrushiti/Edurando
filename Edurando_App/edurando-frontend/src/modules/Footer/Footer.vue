<template>
    <footer class="bg-[#e4e2ee]">
        <div class="max-w-7xl mx-auto py-12 px-4 sm:px-6 lg:px-8">
            <div class="flex justify-between">
                <div class="justify-between items-center">
                    <h3 class="text-base leading-6 font-medium text-[#231e44]">Links</h3>
                    <ul class="mt-2 mr-6 text-base leading-6 text-[#7b73ac] max-w-7xl flex-col sm:flex-row py-4 mx-auto justify-between items-center">
                        <li><router-link to="/TermsOfService">Terms of Service</router-link></li>
                        <li><router-link to="/PrivacyPolicy">Privacy Policy</router-link></li>
                        <li><router-link to="/LegalNotice">Legal Notice</router-link></li>
                        <li><router-link to="/FAQ">FAQ</router-link></li>
                    </ul>
                </div>
                <div class="justify-between items-center">
                    <h3 class="text-base leading-6 font-medium text-[#231e44]">Socials</h3>
                    <ul class="mt-2 mr-6 text-[#7b73ac] leading-6 flex text-xl max-w-7xl flex-col sm:flex-row py-4 mx-auto justify-between items-center">
                        <li class="mr-4"><a href="https://www.instagram.com/"><font-awesome-icon icon="fab fa-instagram"/></a></li>
                        <li class="mr-4"><a href="https://www.twitter.com/"><font-awesome-icon icon="fab fa-twitter"/></a></li>
                        <li class="mr-4"><a href="https://www.facebook.com/"><font-awesome-icon icon="fab fa-facebook"/></a></li>
                        <li class="mr-4"><a href="https://www.tiktok.com/"><font-awesome-icon icon="fab fa-tiktok"/></a></li>
                    </ul>
                </div>
                <div class="justify-between items-center">
                    <h3 class="text-base leading-6 font-medium text-[#231e44]">Contact Us</h3>
                    <ul class="mt-2 mr-6 text-base leading-6 text-[#7b73ac] max-w-7xl flex-col sm:flex-row py-4 mx-auto justify-between items-center">
                        <li>info.edurando@gmail.com</li>
                        <li>+49 40 87654321</li>
                        <li><router-link to="/ContactForm">Contact Form</router-link></li>
                    </ul>
                </div>
            </div>
            <div class="text-center text-sm my-6 text-[#231e44]">
                <button @click="scrollToTop" class="w-max mx-auto justify-center py-2 px-4 border border-transparent font-medium rounded-md text-white bg-[#483d8b] hover:bg-purple-950 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500">
                    <font-awesome-icon icon="arrow-up"/>
                </button>
                <hr class="my-4 border-[#7b73ac]"/>
                © 2023 Edurando. All rights reserved.
            </div>
        </div>
    </footer>
</template>

<script>
export default {
    name: 'Footer',
    methods: {
        scrollToTop() {
            window.scrollTo({
                top: 0,
                behavior: "smooth"
            });
        }
    }
}

</script>

<style scoped>
</style>