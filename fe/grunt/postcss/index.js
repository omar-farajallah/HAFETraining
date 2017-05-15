/*
 * PostCSS
 *
 * See all options: https://github.com/nDmitry/grunt-postcss
 */
module.exports = function ( grunt ) {

    'use strict';

    const postcssUrl = require('postcss-url');
    const { processors } = require( './processors' );
    const cssnano = require( 'cssnano' );
    const brand = process.env.BRAND || 'library';
    const brandFolder = brand === 'library' ? 'library' : 'brand';
    const brandPathName =  brand === 'library' ? '/platform/' : `/${brand}/`;
    const srcFiles = `${brandFolder}/styles/**/index.css`;

    const rename = ( dest = '', src ) => dest + src.replace( 'styles', 'css' );

    const clientLibRoot = '/etc/designs/carnival';
    const updatePath = ( url, decl, from, dirname, to, options ) => {

        var newpath = url;

        var regex = /(.png)|(.jpg)|(.otf)|(.woff)|(.ttf)|(.svg)/;

        if (regex.test(url)) {

            newpath = url.replace(/(\.\.\/)|(library\/)/g, '');

            newpath = clientLibRoot + brandPathName + newpath;
        }

        return newpath;
    };

    grunt.config( 'postcss', {
        options: {
            processors: processors
        },
        default: {
            map: true,
            expand: true,
            src: srcFiles,
            rename: rename
        },
        dev: {
            map: true,
            expand: true,
            src: srcFiles,
            dest: '<%= buildPath.dev %>',
            rename: rename
        },
        dist: {
            map: false,
            expand: true,
            src: srcFiles,
            dest: '<%= buildPath.dist %>',
            rename: rename,
            options: {
                processors: [
                    ...processors,
                    postcssUrl({
                        url: updatePath
                    }),
                    cssnano
                ]
            }
        }
    });
};
