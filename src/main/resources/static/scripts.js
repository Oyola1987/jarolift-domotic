const getChannel = () => {
    return document.querySelector('#combo-remotes').value;
};

const get = (url) => {
    return fetch(url)
        .then(response => {
            console.log(response.url);
            return response.json()
        })
        .then((data) => {
            console.log(data);
        });
};


const listener = (clsSelector) => {
    const el = document.querySelector(`.${clsSelector}`);
    const mouseCall = (event, btn) => {
        get(`/api/event/${event}/button/${btn}/channel/${getChannel()}`);
    };

    const cb = () => {
        mouseCall("up", clsSelector);
    };
    
    el.addEventListener('mousedown', () => {
        mouseCall("down", clsSelector);
        el.addEventListener('mouseleave', cb);
    });

    el.addEventListener('mouseup', () => {
        el.removeEventListener('mouseleave', cb);
        mouseCall("up", clsSelector);
    });

    // el.addEventListener('click', () => {
    //     get(`/api/button/${clsSelector}/channel/${getChannel()}`);
    // });
};

listener('up');
listener('down');
listener('middle');
listener('stop');