/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./index.html",
      "./src/**/*.{vue,js,ts,jsx,tsx}",],
  theme: {
    extend: {
      fontFamily: {
        'font-family':['Poppins', 'sans-serif'],
      },
      boxShadow: {
          '1.5xl': '0 10px 10px -5px rgba(0, 0, 0, 0.25)'
      }
    },
    /*screens: {
        'sm': {'max': '475px'}
    }*/
  },
  plugins: [],
    /*important: true,*/
}

