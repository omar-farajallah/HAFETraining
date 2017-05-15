/*
 * Clean Configuration
 *
 * See all options: https://github.com/gruntjs/grunt-contrib-clean
 */
module.exports = function ( grunt ) {

    grunt.config( 'clean', {
        options: {
            force: true
        },
        css: [
            'library/css'
        ],
        dev: [
            '<%= buildPath.dev %>'
        ],
        dist: [
            '<%= buildPath.docs %>',
            '<%= buildPath.dist %>'
        ]
    });
};