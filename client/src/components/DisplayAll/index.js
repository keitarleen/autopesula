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
                    <th>Id</th>
                    <th>Nimi</th>
                    <th>Tüüp</th>
                    <th>Olek</th>
                </tr>
                </tbody>
                </table>

                    {Object.keys(this.state.allCabins).map((key,id) =>
                        <table >
                            <tbody key={id}>
                                <tr>
                                    <td>{this.state.allCabins[key].id}</td>
                                    <td>{this.state.allCabins[key].name}</td>
                                    <td>{this.state.allCabins[key].type}</td>
                                    <td>{this.state.allCabins[key].state}</td>
                                </tr>
                            </tbody>
                        </table>
                )}

            </div>
        );
    }
}


export default DisplayAll;
