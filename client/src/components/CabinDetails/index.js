import React, {Component} from 'react';
import './styles.css';

class CabinDetails extends Component {
    render() {
        return (
            <div className='container'>
                <h1>Pesemiskabiini detailid</h1>
                <div className='details'>
                <table>
                    <tbody>
                    <tr>
                        <td className='alignRight'>Pesemiskabiini kood: </td>
                        <td>kood</td>
                    </tr>
                    <tr>
                        <td className='alignRight'>Nimetus: </td>
                        <td>data</td>
                    </tr>
                    <tr>
                        <td className='alignRight'>Max auto pikkus: </td>
                        <td>data</td>
                    </tr>
                    <tr>
                        <td className='alignRight'>Hoone kood: </td>
                        <td>data</td>
                    </tr>
                    <tr>
                        <td className='alignRight'>Tüüp: </td>
                        <td>data</td>
                    </tr>
                    <tr>
                        <td className='alignRight'>Hetkeseisund: </td>
                        <td>data</td>
                    </tr>
                    <tr>
                        <td className='alignRight'>Registreerimis aeg: </td>
                        <td>data</td>
                    </tr>
                    <tr>
                        <td className='alignRight'>Registreerija nimi: </td>
                        <td>data</td>
                    </tr>
                    <tr>
                        <td className='alignRight'>Registreerija email: </td>
                        <td>data</td>
                    </tr>
                    <tr>
                        <td className='alignRight'>Kategooriad: </td>
                        <td>data</td>
                    </tr>
                    <tr>
                        <td className='alignRight'>Kategooria tüüp: </td>
                        <td>data</td>
                    </tr>
                    </tbody>
                </table>
                </div>
            </div>
        );
    }
}

export default CabinDetails;
