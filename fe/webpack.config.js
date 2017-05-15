/**
 * Webpack configuration
 *
 * @docs http://webpack.github.io/docs/configuration.html
 */
const webpack = require( 'webpack' );
const validate = require( 'webpack-validator' );
const LiveReloadPlugin = require( 'webpack-livereload-plugin' );

const brand = process.env.BRAND || 'library';
const brandFolder = brand === 'library' ? 'library' : 'brand';

module.exports = validate({

    // http://webpack.github.io/docs/configuration.html#devtool
    devtool: 'cheap-eval-source-map',

    // http://webpack.github.io/docs/configuration.html#entry
    entry: {
        'js/mediators/common': [
            `${process.cwd()}/${brandFolder}/js/mediators/common.js`,
            `${process.cwd()}/node_modules/picturefill/dist/picturefill.js`
        ],
        'js/mediators/example': [
            `${process.cwd()}/${brandFolder}/js/mediators/example.js`
        ]
    },

    // http://webpack.github.io/docs/configuration.html#output
    output: {

        // http://webpack.github.io/docs/configuration.html#output-path
        path: `${process.cwd()}/${brandFolder}`,

        // http://webpack.github.io/docs/configuration.html#output-publicpath
        publicPath: `/${brandFolder}/`,

        // http://webpack.github.io/docs/configuration.html#output-filename
        filename: '[name].js'
    },

    // http://webpack.github.io/docs/configuration.html#module
    module: {

        // http://webpack.github.io/docs/configuration.html#module-loaders
        loaders: [

            // https://github.com/babel/babel-loader
            {
                test: /\.js?$/,
                exclude: /(node_modules)/,
                loader: 'babel'
            }
        ]
    },

    // http://webpack.github.io/docs/using-plugins.html
    plugins: [

        // http://webpack.github.io/docs/list-of-plugins.html#defineplugin
        new webpack.DefinePlugin({
            'process.env': {
                'NODE_ENV': JSON.stringify( 'development' )
            }
        }),

        // https://github.com/statianzo/webpack-livereload-plugin
        new LiveReloadPlugin( { appendScriptTag: true } ),

        // http://webpack.github.io/docs/list-of-plugins.html#commonschunkplugin
        new webpack.optimize.CommonsChunkPlugin({
            name: 'js/mediators/common',
            minChunks: 2
        }),

        // http://webpack.github.io/docs/list-of-plugins.html#occurrenceorderplugin
        new webpack.optimize.OccurenceOrderPlugin(),

        // http://webpack.github.io/docs/list-of-plugins.html#dedupeplugin
        new webpack.optimize.DedupePlugin(),

        // https://webpack.github.io/docs/list-of-plugins.html#aggressivemergingplugin
        new webpack.optimize.AggressiveMergingPlugin()
    ]
}, { quiet: true } );