import React, {Component} from 'react';
import './styles.css';

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
        console.log(api);
        fetch(api).then(response => response);
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
                <h1>Lõpeta pesemiskabiin</h1>
                <p>Hetkel pole ühtegi lõpetamisel pesemiskabiini</p>
            </div>
        }
        else return (
            <div>
                <h1>Lõpeta pesemiskabiin</h1>
                <table>
                    <tbody>
                    <tr>
                        <th>Pesemiskabiini kood</th>
                        <th>Pesemiskabiini nimi</th>
                        <th>Seisund</th>
                        <th className='filled'> </th>
                    </tr>
                    </tbody>
                </table>

                {Object.keys(this.state.allActiveInactive).map((key) =>
                    <table key={this.state.allActiveInactive[key].id}>
                        <tbody>
                        <tr>
                            <td>{this.state.allActiveInactive[key].id}</td>
                            <td>{this.state.allActiveInactive[key].name}</td>
                            <td>{this.state.allActiveInactive[key].state}</td>
                            <td><button className='btnDelete' onClick={() => {this.endCabin(this.state.allActiveInactive[key].id)}}>Lõpeta</button></td>
                        </tr>
                        </tbody>
                    </table>
                )}
            </div>
        );
    }
}


export default EndCabin;
