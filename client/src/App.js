import React, { Component } from 'react';
import './App.css';
import Login from './components/Login';
import DisplayAll from './components/DisplayAll';
import {Router, Route, IndexRoute, hashHistory} from 'react-router';
import Layout from './components/Layout';
import DisplayByState from './components/DisplayByState';
import EndCabin from './components/EndCabin';
import CabinDetails from './components/CabinDetails';

class App extends Component {
  render() {
    return (
      <div className="App">
          <Router history={hashHistory}>
              <Route path='/' component={Layout}>
                  <IndexRoute/>
                  <Route path='login' component={Login}/>
                  <Route path='all' component={DisplayAll}/>
                  <Route path='states' component={DisplayByState}/>
                  <Route path='endCabin' component={EndCabin}/>
                  <Route path='details' component={CabinDetails}/>
              </Route>
          </Router>
      </div>
    );
  }
}

export default App;
