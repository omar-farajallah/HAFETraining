module.exports = function ( grunt ) {

    'use strict';

    // Auto-loads Grunt plugins
    require( 'load-grunt-tasks' )( grunt );

    // Measure execution time
    require( 'time-grunt' )( grunt );

    /* Configure */
    grunt.initConfig({
        pkg: grunt.file.readJSON( 'package.json' ),
        buildRoot: '../',
        buildName: {
            docs: '<%= pkg.name %>-docs@<%= pkg.version %>',
            dev: '<%= pkg.name %>-dev@<%= pkg.version %>',
            dist: '<%= pkg.name %>@<%= pkg.version %>'
        },
        buildPath: {
            docs: '<%= buildRoot %><%= buildName.docs %>/',
            dev: '<%= buildRoot %><%= buildName.dev %>/',
            dist: '<%= buildRoot %><%= buildName.dist %>/'
        }
    });

    /* Load tasks */
    grunt.loadTasks( 'grunt' );
    grunt.loadTasks( 'grunt/mocha' );
    grunt.loadTasks( 'grunt/postcss' );

    /* Task aliases */
    grunt.registerTask( 'default', 'Build for testing.', [
        'lint',
        'postcss:default'
    ]);

    grunt.registerTask( 'build', 'Build task', [
        'lint',
        'clean:css',
        'postcss:default'
    ]);

    grunt.registerTask( 'build:dist', 'Build task', [
        'lint',
        'clean:dist',
        'copy:dist',
        'webpack:dist',
        'postcss:dist',
        'webpack:server',
        'zip'
        // 'docs:dist'
    ]);

    grunt.registerTask( 'lint', 'Run all linters.', [
        'eslint',
        'stylelint'
    ]);


    grunt.registerTask( 'docs', 'Run all documentation tasks', function ( target ) {

        const arg = target || 'default';
        const taskList = [
            `jsdoc:${ arg }`
        ];

        if ( arg === 'dist' ) {
            taskList.push( `md2html:${ arg }` );
            taskList.push( `sitemap:${ arg }` );
        }

        grunt.task.run( taskList );
    });

    grunt.registerTask( 'tasks', 'Display all available tasks.', [ 'availabletasks' ] );
    grunt.registerTask( 'coverage', 'Generate Istanbul coverage reports', [ 'shell:coverage' ] );
};
