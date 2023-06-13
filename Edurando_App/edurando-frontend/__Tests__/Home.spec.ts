import {describe, it, expect} from "vitest";


import {mount} from "@vue/test-utils";
import Home from "../src/view/Home.vue";


describe("Home", () => {
    it("renders a message", () => {
        const wrapper = mount(Home)
        expect(wrapper.html()).toMatchSnapshot();
    });
})

