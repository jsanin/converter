<template>
  <div>
    <h1>Convert numbers to words</h1>
    <form @submit.prevent="onSubmit">
      <label>Enter an integer: </label>
      <input
              v-model="numberToConvert"
              type="text"
              placeholder="Enter an integer"
              class="field"
              @keyup.enter="convertNumber"
      >
      <button type="button" @click="convertNumber">Convert number</button>
    </form>
    <div v-if="!error">
      <div>{{ resultIntroText }}</div>
      <div class="bold">{{ numberInWords }}</div>
    </div>
    <div v-if="error" class="error">{{errorText}}</div>

  </div>
</template>

<script>
import GeneralService from '@/services/GeneralService.js'

export default {
  name: 'ConvertNumbersToWords',
  props: {
  },
  data() {
    return {
      numberToConvert: '',
      resultIntroText: '',
      numberInWords: '',
      error: false,
      errorText: ''
    }
  },
  methods: {
    convertNumber() {
      this.error = false;
      if(this.numberToConvert.trim().length > 0 && !isNaN(this.numberToConvert.trim())) {
        GeneralService.convertNumberToWords(this.numberToConvert)
                .then(response => {
                  this.resultIntroText = 'Number ' + this.numberToConvert;
                  this.numberInWords = response.data.words;
                })
                .catch(error => {
                  this.error = true;
                  this.errorText = 'An error occurred: ' + error;
                  if(error.response.data) {
                    this.errorText = error.response.data.error + ': ' + error.response.data.message;
                  }
                });

      } else {
        this.error = true;
        this.errorText = 'Please type a number';
      }
    }
  }

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .error {
    color: red;
  }
  .bold {
      font-weight: bold;
  }
</style>
