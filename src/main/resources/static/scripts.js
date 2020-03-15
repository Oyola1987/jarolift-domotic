
const setVH = () => {
    const vh = window.innerHeight * 0.01;
    document.documentElement.style.setProperty('--vh', `${vh}px`);
};

const CACHE_KEY = 'previous-option';

const comboEl = document.querySelector('#combo-remotes');
const getChannel = () => comboEl.value;

const setCache = () => localStorage.setItem(CACHE_KEY, getChannel());

const getCache = () => localStorage.getItem(CACHE_KEY) || 0;

const touchSupported = 'ontouchstart' in window;

const upEvent = touchSupported ? 'touchend' : 'mouseup';
const downEvent = touchSupported ? 'touchstart' : 'mousedown';

const selector = (sel) => document.querySelector(`.${sel}`);

const get = (url) => {
    selector('dev').innerHTML = url;
    return fetch(url)
        .then(response => {
            console.log(response.url);
            return response.json()
        })
        .then((data) => console.log(data));
};

const vibrate = () => window.navigator.vibrate && window.navigator.vibrate(10);

const listener = (clsSelector) => {
    const el = selector(clsSelector);
    const btnUrl = clsSelector === 'middle' ? 'middle' : `button/${clsSelector}`;

    const upCb = () => {   
        el.className = el.className.replace(/active/g, '').trim();
        vibrate();
        el.removeEventListener(upEvent, upCb);
        el.removeEventListener('mouseleave', upCb);        
    };
    
    el.addEventListener(downEvent, () => {  
        el.className = `${el.className} active`;
        vibrate();
        el.addEventListener(upEvent, upCb);
        el.addEventListener('mouseleave', upCb);
        get(`/api/${btnUrl}/channel/${getChannel()}`);
    });    
};

listener('up');
listener('down');
listener('stop');
listener('middle');

comboEl.addEventListener('change', () => setCache());
comboEl.value = getCache();

window.addEventListener("resize", setVH);
setVH();
