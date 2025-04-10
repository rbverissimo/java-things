import  axios  from '/webjars/axios/1.8.4/dist/esm/axios.min.js';
import { csrfheader, csrftoken } from '/js/token.js';

export async function post(url, data){

    try {
        const response = await axios.post(url, data, {
            headers: {
                'Content-Type': 'application/json',
                [csrfheader]: csrftoken
            }
        });
        if(response.status >= 200 && response.status < 300) return response.data;
    } catch (error) {
        throw error;
    }
}