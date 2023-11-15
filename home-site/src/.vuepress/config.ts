import { defineUserConfig } from "vuepress";
import theme from "./theme.js";

export default defineUserConfig({
  base: "/",

  locales: {
    "/": {
      lang: "en-US",
      title: "OpenGear",
      description: "OpenGear Projects Home Site",
    },
    "/zh/": {
      lang: "zh-CN",
      title: "OpenGear",
      description: "OpenGear Projects Home Site",
    },
  },

  theme,

  // Enable it with pwa
  // shouldPrefetch: false,
});
