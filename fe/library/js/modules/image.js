/*
 * React Modal
 *
 * For examples and features: https://github.com/reactjs/react-modal
 */
'use strict';

import React from 'react';

class Image extends React.PureComponent {

    constructor( props ) {

        super( props );
    }

    buildSourceTags( image ) {

        return Object.keys( image )
        .filter( key => key !== 'alt' )
        .sort()
        .reverse()
        .map( key => {

            const srcSet = Object.keys( image[ key ] ).map( res => `${ image[ key ][ res ] } ${ res }` ).join( ',' );

            return <source key={ key } srcSet={ srcSet } media={ `(min-width: ${ key }px)` }/>;
        });
    }

    render() {

        const { alt } = this.props;
        const baseImage = this.props[ '0' ][ '2x' ];
        const sourceTags = this.buildSourceTags( this.props );

        return (
            <picture>
                { sourceTags }
                <img srcSet={ baseImage } alt={ alt } title={ alt }/>
            </picture>
        );
    }
}

Image.propTypes = {
    alt: React.PropTypes.string.isRequired,
    '0': React.PropTypes.object.isRequired
};

export default Image;
