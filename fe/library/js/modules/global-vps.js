/*
* Docs can be viewed at https://github.com/ryanfitzer/ViewportJS
*/
'use strict';

import viewport from 'viewportjs';

const globalViewport = viewport([
    {
        name: 'small',
        width: [ 20, 23.44 ] // ( min-width:320px ) and ( max-width:375px )
    },
    {
        name: 'medium',
        width: [ 23.5, 48 ] // ( min-width:376px ) and ( max-width:768px )
    },
    {
        name: 'large',
        width: [ 48.06, 90 ] // ( min-width:769px ) and ( max-width:1440px )
    }
], 'em' );

export default globalViewport;
