const CACHE_KEY = 'previous-option';

const comboEl = document.querySelector('#combo-remotes');
const getChannel = () => comboEl.value;

const setCache = () => localStorage.setItem(CACHE_KEY, getChannel());

const getCache = () => localStorage.getItem(CACHE_KEY) || 0;

const selector = (sel) => document.querySelector(`.${sel}`);

const get = (url) => {
    return fetch(url)
        .then(response => {
            console.log(response.url);
            return response.json()
        })
        .then((data) => console.log(data));
};


const listener = (clsSelector) => {
    const el = selector(clsSelector);
    const mouseCall = (event) => {
        get(`/api/event/${event}/button/${clsSelector}/channel/${getChannel()}`);
    };

    const upCb = () => {
        el.removeEventListener('mouseup', upCb);
        el.removeEventListener('mouseleave', upCb);
        mouseCall("up");
    };
    
    el.addEventListener('mousedown', () => {
        el.addEventListener('mouseleave', upCb);
        el.addEventListener('mouseup', upCb);
        mouseCall("down");        
    });    
};

selector('middle').addEventListener('mousedown', () => {
    get(`/api/middle/channel/${getChannel()}`);
});

listener('up');
listener('down');
listener('stop');

comboEl.addEventListener('change', () => setCache());
comboEl.value = getCache();