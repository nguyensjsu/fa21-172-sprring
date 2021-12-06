import React, { Component, Fragment, useEffect } from 'react'
import axios from 'axios'
import '../styles/Login.css'

class Home extends Component {
  constructor() {
    super()
  }
  render() {
    return (
      <div class='wrapper'>
        <div class='menuContent'>
          <h2>Employee Log In</h2>

          <form action='/action_page.php'>
            <label for='lname'>Username:</label>
            <br></br>
            <input
              type='text'
              id='uname'
              name='uname'
              value='JohnDoe101'
              size='70'
            ></input>
            <br></br>
            <br></br>

            <label for='lname'>Password:</label>
            <br></br>
            <input
              type='password'
              id='pass'
              name='pass'
              value='password'
              size='70'
            ></input>
            <br></br>
            <br></br>

            <br></br>

            <input class='submit' type='submit' value='Submit'></input>
          </form>
        </div>
      </div>
    )
  }
}

export default Home
