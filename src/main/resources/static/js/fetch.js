import { warn, error } from "./info.js";

export const get = (url) => {
    warn(url);
    return fetch(url)
        .then(response => {
            console.log(response.url);
            return response.json();
        })
        .catch(error)
        .then((data) => {
            console.log(data);
            if (data.status && data.status !== 200) {
                error(JSON.stringify(data, null, 4));
            }
        });
};
