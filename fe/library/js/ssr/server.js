/* eslint-disable new-cap */
/* eslint-disable no-undef */
const React = require( 'react' );
const { renderToString, renderToStaticMarkup } = require( 'react-dom/server' );

function renderReact( props ) {

    props = JSON.parse( props );

    const Component = React.createFactory( globalComponents[ props.type ].default );
    const fn = props.meta.render === 'static' ? renderToStaticMarkup : renderToString;

    return fn( Component( props.attributes ) );
}

module.exports.renderReact = renderReact;