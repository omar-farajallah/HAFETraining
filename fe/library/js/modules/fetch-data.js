'use strict';

const defaults = {
    mode: 'cors',
    cache: 'default',
    method: 'GET'
};

// * Export a standard `fetch` method containing necessary global options
// *
// * @module FetchData
// * @param {String} url Path to supply to `fetch` method
// * @param {String} [opts] Optional parameters to supply or override default options. Default value is {}
export default function ( url, opts = {} ) {

    const options = Object.assign( {}, defaults, opts );

    return fetch( url, options )
    .then( response => response.json() )
    .catch( err => console.log( `[ERROR]: ${err}` ) );
}
