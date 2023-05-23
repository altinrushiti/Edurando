import {describe, it, expect} from "vitest";
import Home from "../../view/Home.vue";
import {mount} from "@vue/test-utils";


describe("Home", () => {
    it("renders a message", () => {
        const wrapper = mount(Home)
        expect(wrapper.html()).toMatchSnapshot();
    });
})

