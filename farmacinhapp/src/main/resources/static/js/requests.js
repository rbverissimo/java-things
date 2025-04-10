import { axios } from '/webjars/axios/1.8.4/dist/axios.min.js';
import { csrfHeader, csrfToken } from '/js/token.js';

export async function post(url, data){

    try {
        const response = await axios.post(url, data, {
            headers: {
                [csrfHeader]: csrfToken
            }
        });
        if(response.status >= 200 && response.status < 300) return response.data;
        throw new Error(`Erro na requisição: ${response.status}`);
    } catch (error) {
        throw error;
    }
}