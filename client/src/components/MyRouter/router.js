import React, {Component} from 'react';
import { BrowserRouter, Route } from 'react-router-dom'
import PropTypes from 'prop-types';
import DisplayAll from '../DisplayAll';

class MyRouter extends Component {
    render() {
        return (
            <BrowserRouter>
                <Route path='/' component={DisplayAll} />
            </BrowserRouter>
        );
    }
}

MyRouter.propTypes = {};

export default MyRouter;
