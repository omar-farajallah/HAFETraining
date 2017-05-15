// This is referenced from the EnzymeJS site: http://airbnb.io/enzyme/docs/guides/jsdom.html

const { jsdom } = require( 'jsdom' );

const exposedProperties = [
    'window',
    'navigator',
    'document'
];

global.document = jsdom( '' );
global.window = document.defaultView;

Object.keys( document.defaultView ).forEach( ( property ) => {

    if ( typeof global[ property ] === 'undefined' ) {

        exposedProperties.push( property );
        global[ property ] = document.defaultView[ property ];
    }
});

global.navigator = {
    userAgent: 'node.js'
};

window.scrollTo = null;

// https://github.com/akiran/react-slick#test-setup
window.matchMedia = window.matchMedia || function () {
    return {
        matches: false,
        addListener: function () {},
        removeListener: function () {}
    };
};

global.matchMedia = window.matchMedia;

window.scrollTo = function ( xOffset, yOffset ) {

    window.pageXOffset = xOffset;
    window.pageYOffset = yOffset;
};

// Below line is to initialize window scroll position in Jenkins(Linux). Without this, First window.scrollTo call in a test script will return 0, 0 as position
window.scrollTo(0, 0);