/*
 * Git Hooks Configuration
 *
 * See all options: https://www.npmjs.com/package/grunt-accessibility
 */
module.exports = function ( grunt ) {

    'use strict';

    grunt.config( 'accessibility', {
        options: {
            accessibilityLevel: 'WCAG2AA'
        },
        test: {
            options: {
                browser: true,
                reportLocation: 'reports/a11y',
                urls: [
                    'http://localhost:3000/pages/boilerplate'
                ]
            },
            src: [
                'boilerplate'
            ]
        }
    });
};