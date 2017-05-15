/*
 * md2html Configuration
 *
 * See all options: https://github.com/bylexus/grunt-md2html
 */
module.exports = function ( grunt ) {

    'use strict';

    grunt.config( 'md2html', {
        options: {
            layout: 'grunt/md2html/default.html',
            files: [
                '**/*.md',
                '!jsdoc/*',
                '!assembled/*'
            ],
            templateData: {
                title: function ( document ) {

                    var exp = new RegExp( '\<h1 .*\>(.+)\<\/h1\>', 'g' );

                    var regex = exp.exec( document );

                    return regex[1];
                }
            }
        },
        dev: {
            files: [{
                expand: true,
                cwd: 'docs',
                src: '<%= md2html.options.files %>',
                dest: 'docs-generated',
                ext: '.html'
            }]
        },
        dist: {
            files: [{
                expand: true,
                cwd: 'docs',
                src: '<%= md2html.options.files %>',
                dest: '<%= buildPath.docs %>',
                ext: '.html'
            }]
        }
    });
};
