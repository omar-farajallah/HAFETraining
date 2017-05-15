/*
 * Compress Configuration
 *
 * See all options: https://github.com/gruntjs/grunt-contrib-compress
 */
module.exports = function ( grunt ) {

    grunt.config( 'zip', {
        'long-format': {
            src: [
                '<%= buildPath.dist %>**/*'
            ],
            dest: '<%= buildRoot %><%= buildName.dist %>.zip'
        }
    });
};