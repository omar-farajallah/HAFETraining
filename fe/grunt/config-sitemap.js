/*
 * Sitemap
 *
 * Task that will take the generated version of the documentation (from a particular build process) and
 * creates link to each files so there is a single place to find and read all of the docs.
 */
module.exports = function ( grunt ) {

    'use strict';

    grunt.config( 'sitemap', {
        options: {
            dev: {
                dest: 'docs-generated',
                basepath: 'docs-generated',
                src: [
                    'docs-generated/**/*.html'
                ]
            },
            dist: {
                dest: '<%= buildPath.docs %>',
                basepath: '<%= buildPath.docs %>',
                src: [
                    '<%= buildPath.docs %>**/*.html',
                    '!<%= buildPath.docs %>index.html'
                ]
            }
        }
    });


    grunt.registerTask( 'sitemap', 'Creates a Sitemap for documentation.', function ( buildType ) {

        var fs = require( 'fs' );
        var path = require( 'path' );
        var globby = require( 'globby' );

        var options = this.options()[buildType];
        var dest = options.dest;
        var src = options.src;
        var basepath = options.basepath;

        var done = this.async();

        grunt.task.requires( `md2html:${buildType}` );

        globby( src ).then( function ( files ) {

            // create html markup for file
            var header = '<!DOCTYPE html><html><head><title>Documentation Sitemap</title></head><body><h1>Documentation Index</h1>';
            var footer = '</body></html>';
            var listStart = '<ul>';
            var listEnd = '</ul>';

            files.forEach( function ( file ) {

                var fileObj = path.parse( file );
                var filename = fileObj.name;
                var dir = fileObj.dir.replace( `${basepath}/`, '' );
                var relPath = file.replace( `${dest}/`, '' );

                var listItem = `<li>${dir}<ul><li><a href="${relPath}">${filename}</a></li></ul></li>`;

                // append listItem to ul
                listStart += listItem;
            });

            var data = header + listStart + listEnd + footer;

            return data;

        }).then( function ( data ) {

            // create sitemap file
            fs.writeFile( `${ dest }/index.html`, data, 'utf-8', function ( error ) {

                if ( error )  throw error;

                grunt.log.writeln( 'Your documentation sitemap file is being created: ', `${ dest }/index.html` );

                done();
            });
        });
    });
};
