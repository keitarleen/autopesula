import React, {Component} from 'react';
import './styles.css';
import NavBarAuth from '../NavBarAuth';

class EndCabin extends Component {
    constructor(props) {
        super(props);
        this.state = {
            allActiveInactive: {},
            isLoaded: false,
            isEnd: false
        }
    }

    endCabin = (id) => {
        const api = 'http://localhost:8080/api/cabin/endCabin/' + id;
        fetch(api).then(response => {
            console.log(response);
        });

        window.location.reload();
    };

    getActiveInActive = () => {
        const api = 'http://localhost:8080/api/cabin/getActiveInactive';
        fetch(api).then(response => response.json())
            .then(data => {
                this.setState({
                    allActiveInactive: data,
                    isLoaded: true
                });
            });
    };

    componentWillMount() {
        this.getActiveInActive();
    }

    render() {
        if (Object.keys(this.state.allActiveInactive).length === 0) {
            return <div>
                <NavBarAuth/>
                <div className='container'>
                <h1 className='centre'>Lõpeta pesemiskabiin</h1>
                <p className='centre'>Hetkel pole ühtegi lõpetamisel pesemiskabiini</p>
                </div>
            </div>
        }
        else return (
            <div>
                <NavBarAuth/>
                <div className='container'>
                <h1 className='centre'>Lõpeta pesemiskabiin</h1>
                <div className='endCabin'>
                <table>
                    <tbody>
                    <tr>
                        <th className='short'>Pesemiskabiini kood</th>
                        <th className='medium'>Pesemiskabiini nimi</th>
                        <th className='short'>Seisund</th>
                        <th className='btn filled'> </th>
                    </tr>
                    </tbody>
                </table>

                {Object.keys(this.state.allActiveInactive).map((key) =>
                    <table key={this.state.allActiveInactive[key].id}>
                        <tbody>
                        <tr>
                            <td className='short'>{this.state.allActiveInactive[key].id}</td>
                            <td className='medium'>{this.state.allActiveInactive[key].name}</td>
                            <td className='short'>{this.state.allActiveInactive[key].state}</td>
                            <td className='btn'><button className='btnDelete' onClick={() => {this.endCabin(this.state.allActiveInactive[key].id)}}>Lõpeta</button></td>
                        </tr>
                        </tbody>
                    </table>
                )}
                </div>
                </div>
            </div>
        );
    }
}


export default EndCabin;
