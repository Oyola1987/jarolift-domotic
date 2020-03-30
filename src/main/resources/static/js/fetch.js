import { warn, error, showLoading, hideLoading } from "./info.js";

export const get = (url) => {
    showLoading();
    return fetch(url)
        .then(response => {
            if(response.status === 429) {
                warn('Espere un momento, una ejecución está en curso');
            } else {
                console.log(response.url);
                return response.json();
            }
        })
        .catch(error)
        .then((data) => {
            hideLoading();
            console.log(data);
        });
};
