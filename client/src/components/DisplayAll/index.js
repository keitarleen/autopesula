import React, {Component} from 'react';
import './styles.css';
import CabinDetails from '../CabinDetails';
import NavBarAuth from '../NavBarAuth';
import NavBar from '../NavBar';

class DisplayAll extends Component {
    constructor(props) {
        super(props);
        this.state = {
            allCabins: {},
            isLoaded: false,
            cabinDetails: {},
            exists: false,
            auth: false
        }
    }

    getAll = () => {
        const api = 'http://localhost:8080/api/cabin/getAll';
        fetch(api)
            .then(response => response.json())
            .then(data => {
                this.setState( {
                    allCabins: data,
                    isLoaded: true
                });
            });
    };

    getDetails = (id) => {
        const url = 'http://localhost:8080/api/cabin/getDetails/' + id;
        fetch(url).then(response => response.json())
            .then(data => {
            this.setState( {
                cabinDetails: data,
                exists: true
            });
        });
    };

    componentWillMount() {
        this.getAll();
    }

    render() {
        console.log(this.state);
        if (this.state.exists === true) {
            return <CabinDetails details={this.state.cabinDetails}/>
        }
        else
            return (
                <div className='container'>
                    <NavBarAuth/>
                    <h1>Kõik pesemiskabiinid</h1>
                    <div className='allCabins'>
                        <table>
                            <tbody>
                            <tr>
                                <th className='code'>Kabiini kood</th>
                                <th className='short'>Kabiini nimi</th>
                                <th className='short'>Kabiini tüüp</th>
                                <th className='short'>Hetkeseisund</th>
                            </tr>
                            </tbody>
                        </table>

                        {Object.keys(this.state.allCabins).map((key) =>
                            <table className="data">
                                <tbody>
                                <tr onClick={() => {this.getDetails(this.state.allCabins[key].id)}} key={this.state.allCabins[key].id}>
                                    <td className='code'>{this.state.allCabins[key].id}</td>
                                    <td className='short'>{this.state.allCabins[key].name}</td>
                                    <td className='short'>{this.state.allCabins[key].cabinType}</td>
                                    <td className='short'>{this.state.allCabins[key].state}</td>
                                </tr>
                                </tbody>
                            </table>
                        )}
                    </div>
                </div>
            );
        }

}


export default DisplayAll;
