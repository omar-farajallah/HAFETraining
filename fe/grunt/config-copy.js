/*
 * Copy Configuration
 *
 * See all options: https://github.com/gruntjs/grunt-contrib-copy
 */
module.exports = function ( grunt ) {

    const files = [
        '**/*',
        '!grunt*/**',
        '!library/css/**',
        '!library/styles/**',
        '!components/**',
        '!library/js/**',
        'library/js/config/**',
        'library/js/vendor/**',
        'library/js/ssr/polyfill.js',
        '!pages/example/**',
        '!node_modules/**',
        '!reports/**',
        '!server/**',
        '!Gruntfile.js',
        '!npm-debug.log',
        '!package.json',
        '!stylelint.config.js',
        '!webpack.*.js',
        '!yarn.lock',
        '!.*',
        '!*.sublime-*',
        '.htaccess'
    ];

    grunt.config( 'copy', {
        dev: {
            src: files,
            dest: '<%= buildPath.dev %>'
        },
        dist: {
            src: [
                ...files,
                '!.htaccess',
                '!docs/**',
                '!resources/**',
                '!services/**'
            ],
            dest: '<%= buildPath.dist %>'
        }
    });
};
