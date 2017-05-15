'use strict';

// Modules
import common from '../common/index';
import renderReact from '../common/modules/render-react';

// Components
import TitleH1 from '../../../components/titleH1';

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
            renderReact( TitleH1, 'titleH1');
        }
    };

    document.addEventListener( 'DOMContentLoaded', () => {
        mediator.initUI();
    });
}();
