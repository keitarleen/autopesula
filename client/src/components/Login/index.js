import React, {Component} from 'react';
import './styles.css';
import DisplayAll from '../DisplayAll';
import {Link} from 'react-router';
import NavBar from '../NavBar';

class Login extends Component {
    constructor(props) {
        super(props);
        this.state = {
            user: ' ',
            pwd: ' ',
            isManager: false
        };
    }

    checkUser = () => {
        const api = 'http://localhost:8080/api/cabin/manager/' + this.refs.user.value + '/' + this.refs.pwd.value;
        fetch(api)
            .then(response => response.json())
            .then(data => {
                this.setState({
                    isManager: data.isManager
                });
                console.log(this.state.isManager);
            });
    };

    componentDidUpdate() {
        if (this.state.isManager) {
            this.props.router.push({
                pathname: '/all',
                state: { auth: this.state.isManager }
            });
        }
    }

    render() {
        return (
            <div className='container'>
                <NavBar/>
                <div className='container-centre'>
                {this.props.children}
                <h1>Login</h1>
                <form>
                    <label>ward.richard@comvoy.co.uk</label>
                <input ref='user' type='text' placeholder='Kasutajanimi'/>
                    <label>incididunt</label>
                    <input ref='pwd' type='password' placeholder='Parool'/>
                <button type='submit' className='btnDefault' onClick={this.checkUser}>Login</button>
                </form>
                    {this.props.children}
                </div>
            </div>
        );
    }
}


export default Login;
