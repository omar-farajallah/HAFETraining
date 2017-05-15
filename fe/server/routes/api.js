const router = require( 'express' ).Router();
const fetchData = require( '../../library/js/modules/fetch-data' );

module.exports = function ( config ) {

    const endpoints = config.endpoints || {};

    // GET home page.
    router.get( '/', ( req, res ) => {

        res.send( 'Load API data' );
    });

    router.route( '/:data' )
        .get( ( req, res ) => {

            const data = req.params.data;

            if ( endpoints[ data ] ) {

                // fetchData( endpoints[ data ] ).then( json => res.send( json ) );
                setTimeout( () => {

                    fetchData( endpoints[ data ] ).then( json => res.send( json ) );
                }, 1500 );

            }
            else {

                res.status( 404 ).send( `${data} is not a valid API path` );
            }
        });

    return router;
};
