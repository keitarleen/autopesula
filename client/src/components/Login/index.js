import React, {Component} from 'react';
import './styles.css';
import {Link, Redirect} from 'react-router';

class Login extends Component {
    constructor(props) {
        super(props);
        this.state = {
            user: '',
            pwd: '',
            toAll: false
        };
    }

    checkUser = () => {
        if (this.refs.user.value === 'juhataja' && this.refs.pwd.value === 'admin') {
            this.setState({toAll: true});
        }
        else { alert('sa pole juhataja lol, aga läheb ikka edasi xd'); }
    };

    render() {
        if (this.state.toAll === true) {
            return <Redirect push to='/all'/>;
        }
        return (
            <div className='container-centre'>
                <h1>Login</h1>
                <input ref='user' type='text' placeholder='Kasutajanimi'/>
                <input ref='pwd' type='password' placeholder='Parool'/>
                <button className='btnDefault' onClick={this.checkUser}>Login</button>
                {this.props.children}
            </div>
        );
    }
}


export default Login;
