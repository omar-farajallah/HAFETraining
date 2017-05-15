'use strict';

import url from 'url';
import { createStore, applyMiddleware, compose } from 'redux';
import createSagaMiddleware from 'redux-saga';
import logger from 'redux-logger';

// Environment checks
const isNode =  !( typeof window !== 'undefined' && window.document );


// Redux dev tools check
const reduxDevTools = ( !isNode && window.devToolsExtension ) ? window.devToolsExtension() : f => f;


// Redux middleware
const sagaMiddleware = createSagaMiddleware();
let middleware = [ sagaMiddleware ];

if ( !isNode && location && 'debug' in url.parse( location.href, true, true ).query ) middleware.push( logger( { collapsed: true } ) );


// Compose Redux enhancers
const enhancer = compose(
    applyMiddleware( ...middleware ),
    reduxDevTools
);


export default function configureStore( rootReducer, initialState, rootSaga ) {

    // Note: only Redux >= 3.1.0 supports passing enhancer as third argument.
    // See https://github.com/rackt/redux/releases/tag/v3.1.0
    const store = createStore( rootReducer, initialState, enhancer );

    if ( rootSaga ) sagaMiddleware.run( rootSaga );

    if ( module.hot ) {

        // Enable Webpack hot module replacement for reducers
        module.hot.accept( '../reducers', () => {

            const nextRootReducer = rootReducer;

            store.replaceReducer( nextRootReducer );
        });
    }

    return store;
}