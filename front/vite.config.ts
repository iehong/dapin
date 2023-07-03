import { defineConfig } from "vite";
import react from "@vitejs/plugin-react-swc";
import legacy from "@vitejs/plugin-legacy";

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    react(),
    legacy({
      targets: ["Chrome > 72", "not dead", "not op_mini all"],
    }),
  ],
  build: {
    outDir: "../back/src/main/resources/static",
  },
  server: {
    proxy: {
      "^/api/": {
        target: "http://localhost:8089/api/",
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, ""),
      },
    },
  },
});
