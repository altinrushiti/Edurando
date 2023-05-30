<template>
    <footer class="bg-[#e4e2ee] z-50">
        <div class="max-w-7xl mx-auto py-12 px-4 sm:px-6 lg:px-8">
            <div class="flex flex-col justify-between sm:flex-row">
                <div>
                    <h3 class="text-base leading-6 font-medium text-[#231e44] py-4">Links</h3>
                    <ul class="text-base leading-6 text-[#7b73ac] max-w-7xl flex-col sm:flex-row mx-auto">
                        <li><router-link to="/about">About</router-link></li>
                        <li><router-link to="/TermsOfService">Terms of Service</router-link></li>
                        <li><router-link to="/PrivacyPolicy">Privacy Policy</router-link></li>
                        <li><router-link to="/LegalNotice">Legal Notice</router-link></li>
                        <li><router-link to="/FAQ">FAQ</router-link></li>
                    </ul>
                </div>
                <div>
                    <h3 class="text-base leading-6 font-medium text-[#231e44] py-4">Socials</h3>
                    <ul class="text-[#7b73ac] leading-6 flex flex-wrap text-xl max-w-7xl mx-auto ">
                        <li class="pr-2"><a href="https://www.instagram.com/"><font-awesome-icon icon="fab fa-instagram"/></a></li>
                        <li class="px-2"><a href="https://www.twitter.com/"><font-awesome-icon icon="fab fa-twitter"/></a></li>
                        <li class="px-2"><a href="https://www.facebook.com/"><font-awesome-icon icon="fab fa-facebook"/></a></li>
                        <li class="px-2"><a href="https://www.tiktok.com/"><font-awesome-icon icon="fab fa-tiktok"/></a></li>
                    </ul>
                </div>
                <div>
                    <h3 class="text-base leading-6 font-medium text-[#231e44] py-4">Contact Us</h3>
                    <ul class=" text-base leading-6 text-[#7b73ac] max-w-7xl flex-col  mx-auto">
                        <li><a href="mailto:info.edurando@gmail.com">info.edurando@gmail.com</a></li>
                        <li><a href="tel:+49 40 87654321">+49 40 87654321</a></li>
                        <li><router-link to="/ContactForm">Contact Form</router-link></li>
                    </ul>
                </div>
            </div>
            <div class="text-center text-sm my-6 text-[#231e44]">
                <button @click="scrollToTop" class="w-max mx-auto justify-center py-2 px-4 border border-transparent font-medium rounded-md text-white bg-[#483d8b] hover:bg-purple-950 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500">
                    <font-awesome-icon icon="arrow-up"/>
                </button>
                <hr class="my-4 border-[#7b73ac]"/>
                Â© 2023 Edurando. All rights reserved.
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