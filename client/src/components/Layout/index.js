import React, {Component} from 'react';
import './styles.css';
import {Link} from 'react-router';

class Basic extends Component {
    constructor(props) {
        super(props);
        this.state = {}
    }

    render() {
        return (
            <div className="container">
                <div className='navLogin'>
                    <button className='login'><Link to='/login'>Login</Link>
                    </button>
                    {this.props.children}
                </div>
            </div>
        );
    }
}

export default Basic;
