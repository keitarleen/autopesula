import React, {Component} from 'react';
import './styles.css';
import {Link} from 'react-router';

class Basic extends Component {
    constructor(props) {
        super(props);
        this.state = {
        }
    }
    handleClick = () => {
        this.refs.login.style.display = 'none';
    };

    render() {
        return (
            <div className="container">
                <button onClick={this.handleClick} ref='login'><Link to='/login'>Login</Link></button>
                {this.props.children}
            </div>
        );
    }
}

export default Basic;
