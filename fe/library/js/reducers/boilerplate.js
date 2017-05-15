'use strict';

import { combineReducers } from 'redux';
import searchBarreducers from '../../../components/searchBar/reducers';

export default combineReducers({
    searchFilter: searchBarreducers
});
