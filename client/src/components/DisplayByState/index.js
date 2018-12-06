import React, {Component} from 'react';

class DisplayByState extends Component {
    constructor(props) {
        super(props);
        this.state = {
            allStates: {},
            isLoaded: false
        }
    }

    getStatement = () => {
        const api = 'http://localhost:8080/api/cabin/getStatement';
        fetch(api).then(response => response.json())
            .then(data => {
                this.setState( {
                    allStates: data,
                    isLoaded: true
                });
            });
    };

    componentWillMount() {
        this.getStatement();
    }

    render() {
        return (
            <div>
                <h1>Pesemiskabiinide koondaruanne</h1>
                <table>
                    <tbody>
                    <tr>
                        <th>Pesemiskabiini seisund</th>
                        <th>Pesemiskabiinide arv seisundis</th>
                    </tr>
                    </tbody>
                </table>

                    {Object.keys(this.state.allStates).map((key) =>
                    <table>
                        <tbody key={this.state.allStates[key].id}>
                        <tr>
                            <td>{this.state.allStates[key].state}</td>
                            <td>{this.state.allStates[key].total}</td>
                        </tr>
                        </tbody>
                    </table>
                    )}
            </div>
        );
    }
}


export default DisplayByState;
