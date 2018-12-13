import React, {Component} from 'react';
import './styles.css';

class DisplayAll extends Component {
    constructor(props) {
        super(props);
        this.state = {
            allCabins: {},
            isLoaded: false
        }
    }

    getAll = () => {
        const api = 'http://localhost:8080/api/cabin/getAll';
        fetch(api).then(response => response.json())
            .then(data => {
                this.setState( {
                    allCabins: data,
                    isLoaded: true
                });
            });
    };

    componentWillMount() {
        this.getAll();
    }

    render() {
        return (
            <div className='container'>
                <h1>Kõik pesemiskabiinid</h1>
                <table>
                <tbody>
                <tr>
                    <th className='code'>Kabiini kood</th>
                    <th>Kabiini nimi</th>
                    <th>Kabiini tüüp</th>
                    <th className='carLength'>Max auto pikkus (mm)</th>
                    <th className='code'>Hoone kood</th>
                    <th>Hetkeseisund</th>
                    <th>Registreerija</th>
                    <th className='regEmail'>Reg email</th>
                </tr>
                </tbody>
                </table>

                    {Object.keys(this.state.allCabins).map((key) =>
                        <table >
                            <tbody key={this.state.allCabins[key].id}>
                                <tr>
                                    <td className='code'>{this.state.allCabins[key].id}</td>
                                    <td>{this.state.allCabins[key].name}</td>
                                    <td>{this.state.allCabins[key].cabinType}</td>
                                    <td className='carLength'>{this.state.allCabins[key].carLength}</td>
                                    <td className='code'>{this.state.allCabins[key].building}</td>
                                    <td>{this.state.allCabins[key].state}</td>
                                    <td>{this.state.allCabins[key].employee}</td>
                                    <td className='regEmail'>{this.state.allCabins[key].email}</td>
                                </tr>
                            </tbody>
                        </table>
                )}

            </div>
        );
    }
}


export default DisplayAll;
