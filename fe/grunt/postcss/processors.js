'use strict';
const brand = process.env.BRAND || 'library';
const brandFolder = brand === 'library' ? 'library' : 'brand';
const mixins = require( `../../${brandFolder}/styles/config/mixins` );

const lost = require( 'lost' );
const postcssImport = require( 'postcss-import' );
const postcssMixins = require( 'postcss-mixins' );
const postcssCSSnext = require( 'postcss-cssnext' );
const postcssPxtorem = require( 'postcss-pxtorem' );
const postcssUtilities = require( 'postcss-utilities' );
const postcssFlexibility = require( 'postcss-flexibility' );
const postcssCssVariables = require( 'postcss-css-variables' );

module.exports = {
    processors: [
        postcssImport(),
        postcssMixins( mixins ),
        postcssCssVariables(),
        postcssUtilities(),
        postcssFlexibility(),
        postcssCSSnext(),
        postcssPxtorem(),
        lost()
    ]
};