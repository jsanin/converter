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
        // const dataToken = await this.getToken();
        // return apiClient.get('/resource', {headers: {"x-auth-token": dataToken.data.token}})
        return apiClient.get('/rest/api/v1/numberToWords/' + numberToConvert)
    }

}
