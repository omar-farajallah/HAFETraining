/*
 * Grunt Mocha Configuration
 *
 * See all options: https://github.com/pghalliday/grunt-mocha-test
 */
module.exports = function ( grunt ) {

    'use strict';

    const babel = require( 'babel-core/register' );
    const pkg = require( '../../package.json' );
    const { argv } = require( 'yargs' );

    grunt.config( 'mochaTest', {
        test: {
            options: {
                compilers: {
                    js: babel
                },
                reporter: 'mochawesome',
                reporterOptions: {
                    inlineAssets: true,
                    reportName: 'index',
                    reportDir: 'reports/mocha',
                    reportTitle: `${pkg.name}@${pkg.version}`
                },
                require: [
                    './grunt/mocha/setup.js'
                ],
                ui: 'tdd'
            },
            src: [ argv.file || 'components/**/test/index.js' ]
        }
    });
};
