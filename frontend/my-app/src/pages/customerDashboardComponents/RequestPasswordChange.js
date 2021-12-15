import React, { Component, Fragment, useEffect } from 'react'
// allow for links to other webpages
import { Link, Redirect } from 'react-router-dom'
import axios from 'axios'
import '../../styles/CustomerDashboard.css'

class Home extends Component {
  constructor() {
    super()

    this.state = {
      oldpassword: '',
      newpassword: ''
    }

    this.handleChange = this.handleChange.bind(this)
    this.handleSubmit = this.handleSubmit.bind(this)
  }

  // sets the state when inputs in sign up form are filled
  handleChange = (e) => {
    this.setState({ [e.target.name]: e.target.value })
  }

  // handles POST request when form is submitted
  handleSubmit = (e) => {
    e.preventDefault()
    axios
      .post('http://localhost:8090/changePassword', this.state)
      .then((response) => {
        console.log(response)
        const {temp} = this.state
        this.setState({
          ...temp,
          status: response.data
        })
        console.log(this.state)
      })
      .catch((error) => {
        console.log(error)
      })
  }

  render() {
    const { oldpassword, newpassword } = this.state

    return (
        <div>
          <div class='sectionTitle'>
            <p>Request Password Change</p>
          </div>

          <div >
            <form 
              class='passChangeForm' 
              onSubmit={this.handleSubmit}
            >
              <label for='oldpassword'>Current Password:</label>
              <br></br>
              <input
                onChange={this.handleChange}
                type='password'
                id='oldpassword'
                name='oldpassword'
                // placeholder='your current password'
                value={oldpassword}
                size='70'
              ></input>
              <br></br>
              <br></br>

              <label for='newpassword'>New Password:</label>
              <br></br>
              <input
                onChange={this.handleChange}
                type='password'
                id='newpassword'
                name='newpassword'
                // placeholder='your new password'
                value={newpassword}
                size='70'
              ></input>
              <br></br>
              <br></br>

              <br></br>

              <button class='submit' type='submit'>
                Submit
              </button>
            </form>
          </div>
      </div>
    )
  }
}

export default Home
