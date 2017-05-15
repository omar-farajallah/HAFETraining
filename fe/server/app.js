/* eslint-disable new-cap */
'use strict';

const fs = require( 'fs' );
const path = require( 'path' );
const morgan = require( 'morgan' );
const express = require( 'express' );
const favicon = require( 'serve-favicon' );
const Handlebars = require( 'handlebars' );
const bodyParser = require( 'body-parser' );
const compression = require( 'compression' );
const exphbs = require( 'express-handlebars' );
const reactHelper = require( './helpers/react' );
const compareHelper = require( './helpers/compare' );

const routes = require( './routes' );
const postcssMiddleware = require( './middleware/postcss' );
const webpackMiddleware = require( './middleware/webpack' );

const app = express();
const cwd = process.cwd();

const brandFolder = process.env.BRAND === 'library' ? 'library' : 'brand';
const configFolder = `${brandFolder}/js/config`;
const faviconPath = `${brandFolder}/images/favicon.jpg`;
const brand = `/${brandFolder}`;

/*
 * HANDLEBARS IMPLEMENTATION
 */
const hbsConfig = {
    extname: '.hbs',
    handlebars: Handlebars,
    partialsDir: [
        'pages',
        'pages/partials',
        configFolder
    ],
    helpers: {
        compare: compareHelper,
        stringify( string ) {
            return string;
        },
        include( path ) {
            return fs.readFileSync( path, 'utf-8' );
        },
        local( partial ) {
            return `${ this.template }/${ partial }`;
        },
        react: reactHelper
    }
};

module.exports = () => {

    /*
     * MIDDLEWARE
     */
    app.use( compression() );
    app.use( bodyParser.json() );
    app.use( bodyParser.urlencoded( { extended: false } ) );

    // Use Morgan logging tool
    app.use( morgan( 'dev' ) );

    // Use Webpack to serve build files from memory
    app.use( webpackMiddleware );

    // Use PostCSS to serve built css directly
    app.use( `/${brandFolder}/css`, postcssMiddleware );

    // Set static paths
    app.use( favicon( path.resolve( cwd, faviconPath ) ) );
    app.use( '/docs', express.static( path.resolve( cwd, 'docs' ) ) );
    app.use( brand, express.static( path.resolve( cwd, brandFolder ) ) );
    app.use( '/services', express.static( path.resolve( cwd, 'services' ) ) );

    /*
     * ROUTING
     */
    routes( app );

    /*
     * VIEWS
     */
    app.set( 'views', path.resolve( cwd, 'pages' ) );
    app.engine( '.hbs', exphbs( hbsConfig ) );
    app.set( 'view engine', '.hbs' );

    /*
     * ERROR HANDLERS
     */

    return app;
};