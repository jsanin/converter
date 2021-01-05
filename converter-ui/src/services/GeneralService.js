import axios from 'axios'

const apiClient = axios.create({
    baseURL: 'http://localhost:9000',
    withCredentials: false,
    headers: {
        Accept: 'application/json',
        'Content-Type': 'application/json'
    }
})

const myServerClient = axios.create({
    baseURL: 'http://localhost:28080/converter',
    withCredentials: false,
    headers: {
        Accept: 'application/json',
        'Content-Type': 'application/json'
    }
})

export default {
    getEvents() {
        return apiClient.get('/events')
    },

    getToken() {
        return myServerClient.get("/token")
    },

    convertNumberToWords(numberToConvert) {
        // const dataToken = await this.getToken();
        // return apiClient.get('/resource', {headers: {"x-auth-token": dataToken.data.token}})
        return myServerClient.get('/rest/api/v1/numberToWords/' + numberToConvert)
    }

}
