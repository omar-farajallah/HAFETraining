/*
 * React Modal
 *
 * For examples and features: https://github.com/reactjs/react-modal
 */

'use strict';

import React from 'react';
import ReactModal from 'react-modal';

const Modal = React.createClass({

    propTypes: {
        className: React.PropTypes.string,
        closeLabel: React.PropTypes.string,
        contentLabel: React.PropTypes.string.isRequired,
        isOpen: React.PropTypes.bool.isRequired,
        onRequestClose: React.PropTypes.func.isRequired
    },

    getDefaultProps() {

        return {
            className: 'modal',
            contentLabel: 'Modal',
            overlayClassName: 'modal-overlay'
        };
    },

    render() {

        return (
            <ReactModal { ...this.props }>
                <div className="close" onClick={ this.props.onRequestClose }>{ this.props.closeLabel }</div>
                { this.props.children }
            </ReactModal>
        );
    }
});

export default Modal;
