/**
 * Webpack configuration
 *
 * Documentation: http://webpack.github.io/docs/configuration.html
 */
const validate = require( 'webpack-validator' );
const cloneDeep = require( 'lodash.clonedeep' );

const pkg = require( './package.json' );
const config = cloneDeep( require( './webpack.config' ) );

const brand = process.env.BRAND || 'library';
const brandFolder = brand === 'library' ? 'library' : 'brand';

config.output.path = `${process.cwd()}/../${pkg.name}-dev@${pkg.version}/${brandFolder}`;

module.exports = validate( config, { quiet: true } );