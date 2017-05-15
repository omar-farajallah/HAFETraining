import React from 'react';
import { render } from 'react-dom';

const componentData = SR.components.data;

export default ( Component, key ) => {

    Array.prototype.map.call(
        document.querySelectorAll( `[data-type="${ key }"]` ),
        el => {

            const props = componentData.filter( cmp => cmp.id === el.getAttribute( 'data-id' ) )[0];

            if ( props && props.attributes ) {
                render( <Component {...props.attributes}/>, el );
            }
        }
    );
};