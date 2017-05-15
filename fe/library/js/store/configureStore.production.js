'use strict';

import { createStore, applyMiddleware } from 'redux';
import createSagaMiddleware from 'redux-saga';

// Middleware you want to use in production:
const sagaMiddleware = createSagaMiddleware();
const enhancer = applyMiddleware( sagaMiddleware );

export default function configureStore( rootReducer, initialState, rootSaga ) {

    // Note: only Redux >= 3.1.0 supports passing enhancer as third argument.
    // See https://github.com/rackt/redux/releases/tag/v3.1.0
    const store = createStore( rootReducer, initialState, enhancer );

    if ( rootSaga ) sagaMiddleware.run( rootSaga );

    return store;
}
