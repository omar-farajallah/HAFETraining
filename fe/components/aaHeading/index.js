'use strict';

import React from 'react';

const AAHeading = ( props ) => {

    return (

        <div className='aaheading'>
          <h1>{ props.title }</h1>
        </div>
    );
};

AAHeading.propTypes = {
    title: React.PropTypes.string
};

export default AAHeading;