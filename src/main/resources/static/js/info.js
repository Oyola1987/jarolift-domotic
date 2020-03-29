import { selector } from "./utils.js";

let timeout;

const showMsg = (cls, msg) => {
    const infoCls = 'info';
    const el = selector(infoCls);
    el.innerHTML = msg;
    el.classList = `${infoCls} ${cls}`;
    
    if (timeout) {
        clearTimeout(timeout);
    }

    timeout = setTimeout(() => {
        el.classList = `${infoCls} ${cls} fadeout`;
    }, 2000);
};

export const showLoading = () => document.body.classList = 'loading';
export const hideLoading = () => document.body.classList = '';

export const warn = (msg) => showMsg('warn', msg);
export const error = (msg) => showMsg('error', msg);
