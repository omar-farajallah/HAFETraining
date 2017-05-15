const express = require( 'express' );
const router = express.Router();
const brand = process.env.BRAND;

module.exports = function () {

    'use strict';

    // GET other first-level global pages
    router.get( [ '/:page' ], ( req, res ) => {

        res.render( `${ req.params.page }/index`, {
            brand,
            template: req.params.page
        });
    });

    return router;
};