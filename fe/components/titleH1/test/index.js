import React from 'react';
import { mount } from 'enzyme';
import { assert } from 'chai';

import Title from '../index';

suite( '<Title/>', () => {

    let wrapper;
    const customData = {
        'title': 'Expectations, Exceeded',
        'dividerType': 'text',
        'dividerText': 'xxxxxxxxxxx',
        'description': 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitat.',
        'id': ''
    };

    const customDataIcon = {
        'title': 'Expectations, Exceeded',
        'dividerType': 'icon',
        'dividerText': 'xxxxxxxxxxx',
        'description': '',
        'id': ''
    };

    setup( () => {

        wrapper = mount( <Title { ...customData }/> );
    });

    test( 'Title H1 contains one <h1> element', () => {

        assert.equal( wrapper.find( 'h1' ).length, 1 );
    });

    test( '<h1> element should not be empty', () => {

        assert.isAbove( wrapper.find( 'h1' ).text().length, 0 );
    });

    test( 'Title decorator contains title decorator text element', () => {

        wrapper = mount( <Title { ...customData }/> );

        assert.equal( wrapper.find( '.title-decorator' ).length, 1 );
    });

    test( 'Title decorator CSS driven', () => {

        wrapper = mount( <Title { ...customDataIcon }/> );

        assert.equal( wrapper.find( '.title-decorator-image' ).length, 1 );
    });


    test( 'Title H1 contains one <p> element', () => {

        wrapper = mount( <Title { ...customData }/> );

        assert.equal( wrapper.find( 'p' ).length, 1 );
    });

    test( 'Show Description if text length is greater then 1', () => {

        assert.isAbove( wrapper.find( 'p' ).text().length, 0 );
    });

});

