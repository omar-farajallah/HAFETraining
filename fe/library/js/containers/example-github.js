'use strict';

import github from '../../../components/example-github';
import { addGithubRepos, removeGithubRepos, requestData } from '../../../components/example-github/redux';
import { connect } from 'react-redux';

// const githubApi = '';
const mapStateToProps = state => {
    console.log(state);
    const {
        title,
        items = [],
        isFetching = false,
        totalVisible = 3,
        error = undefined
    } = state.github;

    const visibleItems = ( items.length > 0 ) ? items.slice( 0, totalVisible ) : [];

    return {
        title,
        error,
        items,
        isFetching,
        visibleItems
    };
};

const mapDispatchToProps = dispatch => {

    return {
        addGithubRepos() {
            dispatch( addGithubRepos() );
        },
        removeGithubRepos() {
            dispatch( removeGithubRepos() );
        },
        requestData() {
            console.log( 'SR.services.github:', SR.services.github.url );
            dispatch( requestData( SR.services.github.url ) );
        }
    };
};

export default connect( mapStateToProps, mapDispatchToProps )( github );
