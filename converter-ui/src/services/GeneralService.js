import axios from 'axios'

const apiClient = axios.create({
    baseURL: 'http://localhost:28080/converter',
    withCredentials: false,
    headers: {
        Accept: 'application/json',
        'Content-Type': 'application/json'
    }
})

export default {
    convertNumberToWords(numberToConvert) {
        return apiClient.get('/rest/api/v1/numberToWords/' + numberToConvert.trim())
    }

}
