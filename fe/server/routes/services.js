const path = require( 'path' );
const cwd = process.cwd();
const express = require( 'express' );
const router = express.Router();

module.exports = function () {

    'use strict';

    // GET other first-level global pages
    router.get( [ '/:service' ], ( req, res ) => {

        const json = `services/${req.params.service}.json`;

        res.sendFile( path.resolve( cwd, json ) );
    });

    return router;
};