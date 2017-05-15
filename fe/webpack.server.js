/**
 * Webpack configuration
 *
 * @docs http://webpack.github.io/docs/configuration.html
 */
const fs = require( 'fs' );
const path = require( 'path' );
const pkg = require( './package.json' );
const webpack = require( 'webpack' );
const validate = require( 'webpack-validator' );
const WrapperPlugin = require( 'wrapper-webpack-plugin' );

const brand = process.env.BRAND || 'components';
const brandFolder = brand === 'components' ? 'components' : 'brand';
const componentFolder = `${process.cwd()}/${brandFolder}/`;
const entries = {
    'ssr/server': `${process.cwd()}/library/js/ssr/server.js`
};
// Read components directory and create entries based on directory names
fs.readdirSync( componentFolder ).forEach( folder => {

    if ( /^example/.test( folder ) ) return;

    if ( /.DS_Store/.test( folder ) ) return;

    entries[ `components/${ folder }` ] = [ `${ componentFolder }/${ folder }/index.js` ];
});

module.exports = validate({

    // http://webpack.github.io/docs/configuration.html#entry
    entry: entries,

    // http://webpack.github.io/docs/configuration.html#output
    output: {

        // http://webpack.github.io/docs/configuration.html#output-path
        path: `${process.cwd()}/../${pkg.name}@${pkg.version}/library/js`,

        // http://webpack.github.io/docs/configuration.html#output-filename
        filename: '[name]/index.js'
    },

    // http://webpack.github.io/docs/configuration.html#module
    module: {

        // http://webpack.github.io/docs/configuration.html#module-loaders
        loaders: [

            // https://github.com/babel/babel-loader
            {
                test: /\.js$/,
                exclude: /(node_modules)/,
                loader: 'babel-loader'
            }
        ]
    },

    // http://webpack.github.io/docs/using-plugins.html
    plugins: [

        // http://webpack.github.io/docs/list-of-plugins.html#defineplugin
        new webpack.DefinePlugin({
            'process.env': {
                'NODE_ENV': JSON.stringify( 'production' )
            }
        }),

        // https://www.npmjs.com/package/wrapper-webpack-plugin
        new WrapperPlugin({
            header: function ( fileName ) {

                const dirArray = path.parse( fileName ).dir.split( '/' );
                const name = dirArray[ dirArray.length - 1 ];
                const serverHeader = `var globalComponents = {};\nvar server =\n`;
                const componentHeader = `globalComponents[ '${ name }' ] =\n`;

                return ( /\bssr\b/.test( name ) ) ? serverHeader : componentHeader;
            }
        }),

        // http://webpack.github.io/docs/list-of-plugins.html#commonschunkplugin
        new webpack.optimize.CommonsChunkPlugin({
            name: 'ssr/server',
            filename: 'ssr/server.js',
            minChunks: 'Infinity'
        })
    ]
}, { quiet: true } );