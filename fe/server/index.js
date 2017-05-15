const PORT = normalizePort( process.env.PORT || 3000 );
const app = require( './app' )();
const server = require( 'http' ).createServer( app );

/*
 * MIDDLEWARE
 */

/*
 * INITIALIZE SERVER
 */
server.listen( PORT );
server.on( 'error', onError );
server.on( 'listening', onListening );


/**
 * Normalize a port into a number, string, or false.
 *
 * @param {num} val normalizes to number
 * @returns {string} boolean or false
 */
function normalizePort( val ) {

    const port = parseInt( val, 10 );

    // named pipe
    if ( isNaN( port ) ) return val;

    // port number
    if ( port >= 0 ) return port;

    return false;
}


// Event listener for HTTP server "listening" event.

function onListening() {

    console.log( `Server running at http://localhost:${server.address().port} in '${process.env.NODE_ENV}' mode` );
}


// Event listener for HTTP server "error" event.

function onError( error ) {

    if ( error.syscall !== 'listen' ) {

        throw error;
    }

    const bind = typeof PORT === 'string' ? `Pipe ${PORT}` : `Port ${PORT}`;

    // Handle specific listen errors with friendly messages
    switch ( error.code ) {

        case 'EACCES':
            console.error( `${bind} requires elevated privileges` );
            process.exit( 1 );
            break;

        case 'EADDRINUSE':
            console.error( `${bind} is already in use` );
            process.exit( 1 );
            break;

        default:
            throw error;
    }
}
