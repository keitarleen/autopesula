import React, {Component} from 'react';
import './styles.css';

class EndCabin extends Component {
    constructor(props) {
        super(props);
        this.state = {
            allActiveInactive: {},
            isLoaded: false
        }
    }

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
        return (
            <div>
                <h1>Lõpeta pesemiskabiin</h1>
                <table>
                    <tbody>
                    <th>Pesemiskabiini kood</th>
                    <th>Pesemiskabiini nimi</th>
                    <th>Seisund</th>
                    <th></th>
                    </tbody>
                </table>

                {Object.keys(this.state.allActiveInactive).map((key) =>
                    <table key={this.state.allActiveInactive[key].id}>
                        <tbody>
                        <td>{this.state.allActiveInactive[key].id}</td>
                        <td>{this.state.allActiveInactive[key].name}</td>
                        <td>{this.state.allActiveInactive[key].state}</td>
                        <td><button>Lõpeta</button></td>
                        </tbody>
                    </table>
                )}
            </div>
        );
    }
}


export default EndCabin;
