/**
 *
 * A sample module that limits the addition and subtraction of an amount between 0 and a max value.
 *
 * @module SampleModule
 *
 */
'use strict';

function SampleModule( config ) {

    this.init( config );
}

SampleModule.prototype = {

    /**
     *
     * Initializes module with a max value, and sets its counter to 0 unless a minimum counter value is declared
     *
     * @method init
     * @param {Object} config - Values in which to set the sample module up
     * @param {Number} [config.min] - Minimum value of the counter. Default value is 0
     * @param {Number} config.max - Maximum value of the counter
     *
     */
    init( config ) {

        config = config || {};

        this.min = config.min || 0;
        this.counter = config.min || 0;
        this.max = config.max;
    },

    /**
     * Adds value to the internal counter
     *
     * @method addValue
     * @param {Number} amount - Amount to increase by
     *
     */
    addValue( amount ) {

        if ( this.counter + amount > this.max ) {

            this.displayError();

        }
        else {

            if ( this.error ) {

                this.removeError();
            }

            this.counter += amount;
        }

    },

    /**
     *
     * Subtrats value from the counter. Will display an error if {@param amount} and the counter reach past max value
     *
     * @method subValue
     * @param {Number} amount - Amount to decrease by
     *
     */
    subValue( amount ) {

        if ( this.counter - amount < this.min ) {

            this.displayError();

        }
        else {

            if ( this.error ) {

                this.removeError();
            }

            this.counter -= amount;
        }
    },
    /**
     *
     * Function to display error
     *
     * @method displayError
     * @param {String} [message] Message that the error should display
     *
     */
    displayError( message ) {

        this.error = message ? message : 'You can\'t do that!';
    },

    /**
     * Sets error to empty string
     *
     * @method removeError
     */
    removeError() {

        this.error = '';
    }
};

export default ( config ) => {

    return new SampleModule( config );
};