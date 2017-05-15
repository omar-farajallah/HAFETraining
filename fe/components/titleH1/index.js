'use strict';

import React from 'react';

const TitleH1 = ( props ) => {

    return (

        <div className='title-component' data-nav_enable={ props.isCampaignHeaderRequired === 'true' ? 'isChecked' : 'notChecked' } data-nav_title={ ( props.campaignHeaderText && props.campaignHeaderText.length > 0 ) ? props.campaignHeaderText : '' }>
            <div className='inner-container'>
                <span className='icon-heading'></span>
                <h1>{ props.title }</h1>
                { props.dividerType === 'text' ? (
                    <span className='title-decorator'>{ props.dividerText }</span>
                ) : (
                    <span className='title-decorator-image'></span>
                )}
                { props.description.length > 0 &&
                    <p>{ props.description }</p>
                }
            </div>
        </div>
    );
};

TitleH1.propTypes = {
    title: React.PropTypes.string.isRequired,
    dividerType: React.PropTypes.string,
    dividerText: React.PropTypes.string,
    description: React.PropTypes.string,
    isHeader: React.PropTypes.string,
    campaignHeaderText: React.PropTypes.string
};

export default TitleH1;