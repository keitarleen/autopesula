import React, {Component} from 'react';
import {Link} from 'react-router';
import './styles.css';

class NavBarAuth extends Component {
    render() {
        return (
            <div className='nav'>
                <h3>Pesemiskabiinide arvestus</h3>
                <Link to='all'><h2 className='hover'>Vaata kõiki</h2></Link>
                <Link to='states'><h2 className='hover'>Vaata seisundeid</h2></Link>
                <Link to='endCabin'><h2 className='hover'>Lõpeta pesemiskabiin</h2></Link>
                <Link to='login'><h2 className='hover'>Logout</h2></Link>
            </div>
        );
    }
}

export default NavBarAuth;
