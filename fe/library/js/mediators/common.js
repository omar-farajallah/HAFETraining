'use strict';

import polyfills from '../modules/polyfill';

const mediator = {
    init() {

        polyfills();
        console.log( 'commons.js init' );
    }
};

export default mediator;
