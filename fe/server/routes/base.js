const path = require( 'path' );
const cwd = process.cwd();
const express = require( 'express' );
const router = express.Router();

module.exports = function () {

    'use strict';

    // GET other first-level global pages
    router.get( [ '/', '/:page' ], ( req, res ) => {

        const view = req.params.page || 'index.html';

        res.sendFile( path.resolve( cwd, view ) );
    });

    return router;
};