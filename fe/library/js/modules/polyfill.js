/*
 * This file loads all polyfills from the `vendor` directory
 * and is loaded as a dependency inside `mediator/common.js`.
 */
'use strict';

import es6Promise from 'es6-promise';
import 'isomorphic-fetch';

export default function init() {

    es6Promise.polyfill();
}