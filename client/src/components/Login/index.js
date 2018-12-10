import React, {Component} from 'react';
import './styles.css';
import {Link} from 'react-router';

class Login extends Component {

    checkUser = () => {
        if (this.refs.user.value === 'juhataja' && this.refs.pwd.value === 'admin') {
            console.log('clicked');
            return ;
        }
        else {alert('sa pole juhataja lol, aga läheb ikka edasi xd'); }
    };

    render() {
        return (
            <div className='container-centre'>
                <h1>Login</h1>
                <input ref='user' type='text' placeholder='Kasutajanimi'/>
                <input ref='pwd' type='password' placeholder='Parool'/>
                <Link to='all'><button className='btnDefault' onClick={this.checkUser}>Login</button></Link>
                {this.props.children}
            </div>
        );
    }
}


export default Login;
