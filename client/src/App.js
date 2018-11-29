import React, { Component } from 'react';
import './App.css';
import NavBar from '../src/components/NavBar';
import Login from './components/Login';
import DisplayAll from './components/DisplayAll';
import {Router, Route, IndexRoute, hashHistory} from 'react-router';

class App extends Component {
  render() {
    return (
      <div className="App">
          <NavBar/>
          <Router history={hashHistory}>
              <Route path='/' component={Login}>
                <IndexRoute/>
                  <Route path='all' component={DisplayAll}/>
              </Route>
          </Router>
      </div>
    );
  }
}

export default App;
