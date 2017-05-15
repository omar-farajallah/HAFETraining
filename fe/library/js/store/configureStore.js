'use strict';

import devStore from './configureStore.development';
import prodStore from './configureStore.production';

const store = ( process.env.NODE_ENV !== 'production' ) ? devStore : prodStore;

export default store;