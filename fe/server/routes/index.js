// const api = require( './api' );
const base = require( './base' );
const pages = require( './pages' );
const services = require( './services' );

module.exports = function ( app ) {

    // const config = app.get( 'config' ) || {};

    // app.use( '/api', api( config ) );
    app.use( '/', base() );
    app.use( '/pages', pages() );
    app.use( '/services', services() );
};