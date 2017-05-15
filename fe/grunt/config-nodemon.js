/*
 * Git Hooks Configuration
 *
 * See all options: https://www.npmjs.org/package/grunt-nodemon
 */
module.exports = function ( grunt ) {

    'use strict';

    const brand = grunt.option( 'brand' ) || 'library';

    grunt.config( 'nodemon', {
        default: {
            options: {
                callback: function ( nodemon ) {

                    nodemon.on( 'log', function ( event ) {

                        console.log( event.colour );
                    });
                },
                env: {
                    'NODE_ENV': 'development',
                    'BRAND': brand
                },
                ext: 'js',
                exec: 'babel-node server',
                watch: [
                    'grunt/config-nodemon.js',
                    'grunt/postcss/processors.js',
                    'pages/partials/react-component.hbs',
                    'server',
                    'webpack.*.js'
                ]
            }
        }
    });
};
