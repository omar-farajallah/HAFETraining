/*
 * Grunt Shell Configuration
 *
 * See all options: https://github.com/sindresorhus/grunt-shell
 */
module.exports = function ( grunt ) {

    'use strict';

    grunt.config( 'shell', {
        coverage: {
            command: 'cross-env NODE_ENV=test ./node_modules/.bin/nyc --all --cache grunt mochaTest'
        }
    });
};
