/*
 * Copy Configuration
 *
 * See all options: https://github.com/webpack/grunt-webpack
 * See all options: http://webpack.github.io/docs/configuration.html
 */
module.exports = function ( grunt ) {

    const configDev = require( '../webpack.development' );
    const configDist = require( '../webpack.production' );
    const configServer = require( '../webpack.server' );

    grunt.config( 'webpack', {
        dev: configDev,
        dist: configDist,
        server: configServer
    });
};
