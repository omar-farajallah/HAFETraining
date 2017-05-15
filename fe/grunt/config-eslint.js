/*
 * Eslint Configuration
 *
 * See all rules: http://eslint.org/docs/rules/
 * See all options: http://eslint.org/docs/user-guide/configuring
 */
module.exports = function ( grunt ) {

    'use strict';

    grunt.config( 'eslint', {
        src: [
            'library/**/*.js',
            'components/**/*.js',
            'grunt/**/*.js',
            'Gruntfile.js',
            '!library/**/config/*.js',
            '!library/**/vendor/**/*.js'
        ]
    });
};
