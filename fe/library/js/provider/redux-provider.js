import React from 'react';
import { Provider } from 'react-redux';
import configureStore from '../store/configureStore';

import rootReducer from '../reducers/boilerplate';
import rootSaga from '../sagas/boilerplate';
const store = configureStore( rootReducer, {}, rootSaga );

const ReduxProvider = (props) => {

    return (
        <Provider store={ store }>
            {props.children}
        </Provider>
    );
};

export default ReduxProvider;