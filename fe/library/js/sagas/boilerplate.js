'use strict';

import { fork } from 'redux-saga/effects';
import { searchFilterSaga } from '../../../components/searchBar/actions';

const rootSaga = function* () {

    yield [
        fork( searchFilterSaga )
    ];
};

export default rootSaga;