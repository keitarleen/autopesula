import React, {Component} from 'react';
import './styles.css';
import {Link} from 'react-router';

class Login extends Component {
    render() {
        return (
            <div className='container-centre'>
                <h1>Login</h1>
                <input />
                <input />
                <Link to='all'><button>Login</button></Link>
                {this.props.children}
            </div>
        );
    }
}


export default Login;
