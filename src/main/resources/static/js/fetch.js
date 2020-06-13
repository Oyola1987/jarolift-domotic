import { warn, error, showLoading, hideLoading } from "./info.js";

export const get = (url) => {
    // showLoading();
    return fetch(url)
        .then(response => {
            console.log(response.url);
            return response.json().then((data) => {
                return response.status >= 400 ? Promise.reject(data) : Promise.resolve(data);
            });
        })
        .catch(error)
        .then((data) => {
            // hideLoading();
            console.log(data);
        });
};
