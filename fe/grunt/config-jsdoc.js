/*
 * JSDoc Configuration
 *
 * See all options: https://github.com/krampstudio/grunt-jsdoc
 */
module.exports = function ( grunt ) {

    'use strict';

    var source = [
        'library/js/**/*.js',
        'components/**/*.js',
        '!**/test/*.js',
        '!**/test.js',
        '!library/**/vendor/**/*.js',
        '!library/js/modules/pubsub.js'
    ];

    grunt.config( 'jsdoc', {
        default: {
            src: source,
            options: {
                css: {
                    dest: 'docs/generated/jsdoc/styles',
                    src: 'grunt/jsdoc/assets'
                },
                destination: 'docs/generated/jsdoc'
            }
        },
        dev: {
            src: source,
            options: {
                css: {
                    dest: '<%= buildPath.dev %>docs/generated/jsdoc/styles',
                    src: 'grunt/jsdoc/assets'
                },
                destination: '<%= buildPath.dev %>docs/generated/jsdoc'
            }
        },
        dist: {
            src: source,
            options: {
                css: {
                    dest: '../<%= pkg.name %>-docs@<%= pkg.version %>/docs/jsdoc/styles',
                    src: 'grunt/jsdoc/assets'
                },
                destination: '../<%= pkg.name %>-docs@<%= pkg.version %>/jsdoc'
            }
        }
    });
};
