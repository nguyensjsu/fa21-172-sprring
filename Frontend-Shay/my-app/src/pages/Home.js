import React, { Component, Fragment, useEffect } from 'react'
import axios from 'axios'
import '../styles/Home.css'

class Home extends Component {
  constructor() {
    super()
  }
  render() {
    return (
      <div class='wrapper'>
        <div class='homeContent'>
          <h2>Welcome to Gong Cha!</h2>

          <p>View our drinks on the menu tab</p>
          <p>If you want to order a drink, log in as a customer</p>
          <p>If you an employee, please log in</p>
        </div>
      </div>
    )
  }
}

export default Home
