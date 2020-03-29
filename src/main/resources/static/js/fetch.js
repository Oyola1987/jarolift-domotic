import { warn, error, showLoading, hideLoading } from "./info.js";

export const get = (url) => {
    warn(url);
    showLoading();
    return fetch(url)
        .then(response => {
            console.log(response.url);
            return response.json();
        })
        .catch(error)
        .then((data) => {
            hideLoading();
            console.log(data);
            if (data.status && data.status !== 200) {
                error(JSON.stringify(data, null, 4));
            }
        });
};
