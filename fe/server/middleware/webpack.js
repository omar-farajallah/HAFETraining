/**
 * Webpack Dev Middleware
 *
 * Documentation: https://github.com/webpack/webpack-dev-middleware
 */

const webpack = require( 'webpack' );
const webpackConfig = require( '../../webpack.config.js' );
const webpackMiddleware = require( 'webpack-dev-middleware' );
const clearRequireCache = require( 'clear-require-cache' );

clearRequireCache( '**/*.js' );

webpackConfig.entry[ 'js/mediators/example' ] = [
    `${process.cwd()}/library/js/mediators/example.js`
];

module.exports = webpackMiddleware( webpack( webpackConfig ), {
    publicPath: webpackConfig.output.publicPath,
    stats: {
        colors: true,
        chunks: false
    }
});