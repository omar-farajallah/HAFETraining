/*
 * Git Hooks Configuration
 *
 * See all options: https://www.npmjs.org/package/grunt-githooks
 */
module.exports = function ( grunt ) {

    'use strict';

    grunt.config( 'githooks', {
        all: {
            options: {
                command: 'npm run grunt',
                dest: '../.git/hooks'
            },
            'pre-commit': 'eslint stylelint',
            'commit-msg': {
                template: 'grunt/hooks/commit-msg'
            }
        }
    });
};
