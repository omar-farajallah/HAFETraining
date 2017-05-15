/**
 * PostCSS Middleware
 *
 * Documentation: https://github.com/jedmao/postcss-middleware
 */
const { processors } = require( '../../grunt/postcss/processors' );

const path = require( 'path' );
const postcssReporter = require( 'postcss-reporter' );
const postcssMiddleware = require( 'postcss-middleware' );
const brand = process.env.BRAND || 'library';
const brandFolder = brand === 'library' ? 'library' : 'brand';

module.exports = postcssMiddleware({
    inlineSourcemaps: true,
    src: ( req ) => {
        return path.join( `${brandFolder}/styles`, req.url );
    },
    plugins: [
        ...processors,
        postcssReporter( { clearReportedMessages: true } )
    ]
});