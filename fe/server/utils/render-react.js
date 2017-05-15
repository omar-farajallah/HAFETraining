/* eslint-disable new-cap */
/* eslint-disable no-undef */
const React = require( 'react' );
const { renderToString, renderToStaticMarkup } = require( 'react-dom/server' );

const brandFolder = process.env.BRAND === 'library' ? 'library' : 'brand';


const componentsDir = `${ process.cwd() }/components/`;

const brandDir = `${ process.cwd() }/${brandFolder}/components/`;

function renderReact( props ) {

    let Module;

    try {
        Module = require( `${ componentsDir }${ props.type }` ).default;
    }
    catch (e) {
        Module = require( `${ brandDir }${ props.type }` ).default;
    }

    const Component = React.createFactory( Module );
    const fn = props.meta.render === 'static' ? renderToStaticMarkup : renderToString;

    return fn( Component( props.attributes ) );
}

module.exports = renderReact;