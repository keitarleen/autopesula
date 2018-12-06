import React, {Component} from 'react';
import NavBar from '../NavBar';
import './styles.css';

class Basic extends Component {
    render() {
        return (
            <div className="container">
                <NavBar/>
                {this.props.children}
            </div>
        );
    }
}

export default Basic;
