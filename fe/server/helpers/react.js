'use strict';

const fs = require( 'fs' );
const shortid = require( 'shortid' );
const renderReact = require( '../utils/render-react' );

const cwd = process.cwd();

const brand = process.env.BRAND || 'library';
const brandFolder = brand === 'library' ? 'library' : 'brand';


const componentsDir = `${ cwd }/components/`;

const brandDir = `${ cwd }/${brandFolder}/components/`;

module.exports = options => {

    const dataType = options.hash[ 'type' ];

    // Allow for custom JSON file.  Defaults to component name
    const dataFile = options.hash[ 'json' ] || 'default';

    let platformDataPath = `${ componentsDir }${ dataType }/data/${ dataFile }.json`;

    let brandDataPath = `${ brandDir }${ dataType }/data/${ dataFile }.json`;
    
    const defaultData = {
        type: dataType,
        meta: {
            render: 'static'
        }
    };

    // Check that the file exists locally
    const dataPath = (fs.existsSync( brandDataPath)) ? brandDataPath : platformDataPath;
    let data = ( fs.existsSync( dataPath ) ) ? JSON.parse( fs.readFileSync( dataPath, 'utf-8' ) ) : defaultData;

    // Generate unique ID
    data.id = shortid.generate();
    // Stringify JSON data for inclusion into template
    data.json = JSON.stringify( data );

    // Render React component
    data.html = renderReact( data );
    // class attribute
    data.class = options.hash.class;

    return data;
};