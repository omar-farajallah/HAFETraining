'use strict';

// Modules
import common from './common';
import renderReact from '../modules/render-react';

// Components
import HalTitle from '../../../components/halTitle';

!function () {

    // Load Global Navigation / Footer functionality
    common.init();

    const mediator = {

        init() {
            this.initUI();
        },

        initUI() {
            this.initReact();
        },

        initReact() {
            renderReact( HalTitle, 'halTitle');
        }
    };

    document.addEventListener( 'DOMContentLoaded', () => {
        mediator.initUI();
    });
}();