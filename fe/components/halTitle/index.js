'use strict';

import React from 'react';

const HalTitle = ( props ) => {

    return (

        <div className='halTitle-component'>
            <h1>{ props.title }</h1>
        </div>
    );
};

HalTitle.propTypes = {
    title: React.PropTypes.string
};

export default HalTitle;