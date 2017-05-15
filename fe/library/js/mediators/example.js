'use strict';

import React from 'react';
import { render } from 'react-dom';
// Redux
import { Provider } from 'react-redux';
import configureStore from '../store/configureStore';

// Modules
import common from './common';
import vp from '../modules/global-vps';
import fetchData from '../modules/fetch-data';
import sample from '../modules/sample-module';
import renderReact from '../modules/render-react';

// Components
import SimpleReact from '../../../components/example-simple-react';

// import GridComponent from '../../../components/grid';
// import ExampleComponent from '../../../components/example';
import ModalComponent from '../../../components/example-modal';
import CarouselExample from '../../../components/example-carousel';
import ImageExample from '../../../components/example-image';


// React/Redux container
import GithubContainer from '../containers/example-github';
// Combined reducers
import rootReducer from '../reducers/boilerplate';
// Sagas
import rootSaga from '../sagas/boilerplate';

!function () {

    // Load Global Navigation / Footer functionality
    common.init();

    let store;
    const githubWrapper = document.querySelector( '[data-type="example-github"]' );

    const mediator = {

        init() {

            this.counter();

            console.log( 'current viewport:', vp.current().name );

            this.initUI();
        },

        initUI() {

            this.getData();
            this.initReact();
        },

        initReact() {
            // Set up Redux
            const githubProps = SR.components.data.find( cmp => cmp.type === 'example-github' );
            // Create structured preloadedState for Redux
            const preloadedState = {
                github: {
                    ...githubProps.attributes
                }
            };

            // Create store from combined reducer and preloaded state object
            store = configureStore( rootReducer, preloadedState, rootSaga );
            store.subscribe( this.renderRedux );
            this.renderRedux();

            // Init standard React components
            this.renderReact();
        },

        renderReact() {

            renderReact( SimpleReact, 'example-simple-react' );
            renderReact( ModalComponent, 'example-modal' );
            renderReact( CarouselExample, 'example-carousel' );
            renderReact( ImageExample, 'example-image' );
        },

        renderRedux() {

            // Redux implementation
            render(
                <Provider store={store}>
                    <GithubContainer />
                </Provider>,
                githubWrapper
            );
        },

        getData() {

            const service = SR.services.test;

            fetchData( service.url, { method: service.method } ).then( response => console.log( 'response:', response ) );
        },

        counter() {

            var sampleCounter = sample();

            sampleCounter.addValue( 3 );
            console.log( 'sampleCounter:', sampleCounter.counter );
            sampleCounter.addValue( 2 );
            console.log( 'sampleCounter:', sampleCounter.counter );
            sampleCounter.subValue( 4 );
            console.log( 'sampleCounter:', sampleCounter.counter );
        }
    };

    document.addEventListener( 'DOMContentLoaded', () => {

        console.log( 'DOM is ready.' );
        mediator.init();
    });
}();