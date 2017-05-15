/*
 * Stylelint Configuration
 *
 * See all options: http://stylelint.io/user-guide/configuration/
 */
module.exports = function ( grunt ) {

    grunt.config( 'stylelint', {
        options: {},
        src: [
            'library/styles/**/*.css',
            'components/**/styles/*.css'
        ]
    });
};