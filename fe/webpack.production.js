/**
 * Webpack configuration
 *
 * Documentation: http://webpack.github.io/docs/configuration.html
 */
const webpack = require( 'webpack' );
const validate = require( 'webpack-validator' );
const cloneDeep = require( 'lodash.clonedeep' );

const pkg = require( './package.json' );
const config = cloneDeep( require( './webpack.config' ) );

const brand = process.env.BRAND || 'library';
const brandFolder = brand === 'library' ? 'library' : 'brand';

config.devtool = 'source-map';
config.output.path = `${process.cwd()}/../${pkg.name}@${pkg.version}/${brandFolder}`;
config.plugins = [

    // http://webpack.github.io/docs/list-of-plugins.html#defineplugin
    new webpack.DefinePlugin({
        'process.env': {
            'NODE_ENV': JSON.stringify( 'production' )
        }
    }),

    // http://webpack.github.io/docs/list-of-plugins.html#uglifyjsplugin
    new webpack.optimize.UglifyJsPlugin({
        'screw_ie8': true,
        sourceMap: false,
        compress: {
            warnings: false
        }
    }),

    ...config.plugins
];

module.exports = validate( config, { quiet: true } );