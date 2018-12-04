import React, {Component} from 'react';
import {Link} from 'react-router';
import NavBar from '../NavBar';
import './styles.css';

class Basic extends Component {
    render() {
        return (
            <div className="container">
                <NavBar/>
                <Link to='login'><button className='loginBtn'>Login</button></Link>
                {this.props.children}
            </div>
        );
    }
}

export default Basic;
