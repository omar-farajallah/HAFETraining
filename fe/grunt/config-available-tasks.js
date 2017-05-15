/*
 * Compress Configuration
 *
 * See all options: https://github.com/ben-eb/grunt-available-tasks
 */
module.exports = function ( grunt ) {

    'use strict';

    grunt.config( 'availabletasks', {
        tasks: {
            options: {
                showTasks: [
                    'user',
                    'multi',
                    'single'
                ]
            }
        }
    });
};