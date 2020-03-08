const getChannel = () => {
    return document.querySelector('#combo-remotes').value;
};

const get = (event, btn) => {
    fetch(`/api/event/${event}/button/${btn}/channel/${getChannel()}`)    
        .then(response => {
            console.log(response.url);
            return response.json()
        })
        .then(({response}) => {
            console.log(response);
        });
};

const listener = (clsSelector) => {
    const el = document.querySelector(`.${clsSelector}`);
    const cb = () => {
        get("up", clsSelector);
    };
    
    el.addEventListener('mousedown', () => {
        get("down", clsSelector);
        el.addEventListener('mouseleave', cb);
    });

    el.addEventListener('mouseup', () => {
        el.removeEventListener('mouseleave', cb);
        get("up", clsSelector);
    });    
};

listener('up');
listener('down');
listener('middle');
listener('stop');