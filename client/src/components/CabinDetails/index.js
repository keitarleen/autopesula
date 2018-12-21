import React, {Component} from 'react';
import './styles.css';
import NavBarAuth from '../NavBarAuth';

class CabinDetails extends Component {

    goBack = () => {
        window.location.reload();
    };

    render() {
        return (
            <div className='container'>
                <NavBarAuth/>
                <div className=''>

                    <h1 className='centre'>Pesemiskabiini detailid</h1>
                    <div className='details'>
                        <table>
                            <tbody>
                            <tr>
                                <td className='alignRight'>Pesemiskabiini kood:</td>
                                <td>{this.props.details.id}</td>
                            </tr>
                            <tr>
                                <td className='alignRight'>Nimetus:</td>
                                <td>{this.props.details.name}</td>
                            </tr>
                            <tr>
                                <td className='alignRight'>Max auto pikkus:</td>
                                <td>{this.props.details.carLength} mm</td>
                            </tr>
                            <tr>
                                <td className='alignRight'>Hoone kood:</td>
                                <td>{this.props.details.building}</td>
                            </tr>
                            <tr>
                                <td className='alignRight'>Tüüp:</td>
                                <td>{this.props.details.type}</td>
                            </tr>
                            <tr>
                                <td className='alignRight'>Hetkeseisund:</td>
                                <td>{this.props.details.state}</td>
                            </tr>
                            <tr>
                                <td className='alignRight'>Registreerimisaeg:</td>
                                <td>{this.props.details.registration}</td>
                            </tr>
                            <tr>
                                <td className='alignRight'>Registreerija nimi:</td>
                                <td>{this.props.details.employee}</td>
                            </tr>
                            <tr>
                                <td className='alignRight'>Registreerija email:</td>
                                <td>{this.props.details.email}</td>
                            </tr>
                            <tr>
                                <td className='alignRight top'>Kategooriad:</td>

                                {Object.values(this.props.details.categoryType).map((type) =>
                                    <td className='category' key={type}>{type}</td>
                                )}
                            </tr>
                            </tbody>
                        </table>
                        <div className='container-centre'>
                        <button className='btnDefault long' onClick={() => {
                            this.goBack()
                        }}>Tagasi Kõikide pesemiskabiinide vaatesse
                        </button>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default CabinDetails;
