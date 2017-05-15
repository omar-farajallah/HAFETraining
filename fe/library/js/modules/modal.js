/*
 * React Modal
 *
 * For examples and features: https://github.com/davidtheclark/react-aria-modal
 */
'use strict';

import React from 'react';
import ReactAriaModal from 'react-aria-modal';
import scroller from './scroller';

class Modal extends React.PureComponent {

    constructor( props ) {

        super( props );

        this.entered = false;
        this.underlayClass = 'aria-modal-underlay';
        this.dialogClass = 'aria-modal';
        this.state = {
            hideLabel: '',
            showBackToTop: false,
            verticallyCenter: ( this.props.verticallyCenter !== undefined ) ? this.props.verticallyCenter : true
        };
    }

    componentDidMount() {

        const mqlLVP = window.matchMedia( '(min-width: 48.063em)' );

        this.handleResize( mqlLVP );

        mqlLVP.addListener( mql => {

            this.handleResize( mql );
        });
    }

    hideLabel( bool ) {

        this.setState({
            hideLabel: bool
        });
    }

    handleScroll = ( e ) => {

        const topPosition = this.content.getBoundingClientRect().top;

        if ( topPosition > -20 && this.state.hideLabel ) this.hideLabel( false );
        if ( topPosition <= -20 && !this.state.hideLabel ) this.hideLabel( true );
    }

    handleResize( mql ) {

        this.setState({
            verticallyCenter: ( mql.matches )
        });

        this.checkContentHeight();
    }

    checkContentHeight = () => {

        if ( this.entered && this.content.offsetHeight > window.innerHeight + 70 ) {

            this.setState({
                showBackToTop: true
            });
        }
    }

    scrollToTop = () => {

        this.content.scrollIntoView( true );
    }

    onEnter = () => {

        this.entered = true;
        this.content = document.querySelector( '.modal-content' );
        this.scroller = scroller({
            element: document.querySelector( '.aria-modal' ),
            callback: this.handleScroll
        });

        if ( this.props.onEnter ) this.props.onEnter();

        this.checkContentHeight();

        const picturefill = require('picturefill');

        picturefill();
    }

    onExit = () => {

        this.entered = false;
        this.hideLabel( false );
    }

    getApplicationNode() {

        return document.querySelector( '.main-wrapper' );
    }

    render() {

        const { onExit, closeLabel, children, underlayClass, dialogClass, backtopLabel, contentLabel } = this.props;
        const dialog = ( dialogClass ) ? `${ this.dialogClass } ${ dialogClass }` : this.dialogClass;
        const underlay = ( underlayClass ) ? `${ this.underlayClass } ${ underlayClass }` : this.underlayClass;

        return (
            <ReactAriaModal
                { ...this.props }
                dialogClass={ dialog }
                underlayClass={ underlay }
                onEnter={ this.onEnter }
                titleText={ contentLabel }
                verticallyCenter={ this.state.verticallyCenter }
                getApplicationNode={ this.getApplicationNode }
                initialFocus='.modal-content'
            >
                <div className="modal-content">
                    { children }
                    { this.state.showBackToTop &&
                        <div className='back-to-top' onClick={ this.scrollToTop }>{ backtopLabel }</div>
                    }
                </div>
                <button className='close' onClick={ onExit } ><span className={ `close-label${ this.state.hideLabel ? ' fade-out' : '' }` }>{ closeLabel }</span></button>
            </ReactAriaModal>
        );
    }
}

Modal.propTypes = {
    onExit: React.PropTypes.func.isRequired,
    dialogClass: React.PropTypes.string,
    underlayClass: React.PropTypes.string,
    closeLabel: React.PropTypes.string.isRequired,
    backtopLabel: React.PropTypes.string.isRequired,
    mounted: React.PropTypes.bool.isRequired,
    contentLabel: React.PropTypes.string.isRequired,
    verticallyCenter: React.PropTypes.bool
};

Modal.defaultProps = {
    mounted: false,
    closeLabel: 'Close', // Make global
    backtopLabel: 'Back to Top', // Make global
    underlayColor: false
};

export default Modal;
