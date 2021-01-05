<template>
  <div id="app">
    <ConvertNumbersToWords />
  </div>
</template>

<script>
import ConvertNumbersToWords from './components/ConvertNumbersToWords.vue'

export default {
  name: 'App',
  components: {
    ConvertNumbersToWords
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
