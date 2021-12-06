import React, { Component } from 'react'
// import css
import '../styles/SignUp.css'

// import axios
import axios from 'axios'

const api = axios.create({
  baseURL: 'http://localhost:8080/customers',
})

class SignUp extends Component {
  constructor() {
    super()
    this.getCustomers()
  }

  // holder for customer object in RestAPI
  state = {
    customers: [],
  }

  // get all customers
  getCustomers = async () => {
    let data = await api.get('/').then(({ data }) => data)
    console.log(data)
    this.setState({ customers: data })
  }

  // create a customer and save to RestAPI
  createCustomer = async () => {
    let res = await api.post('/register', {
      id: 123,
      firstname: 'John',
      lastname: 'Doe',
      username: 'JohnDoe101',
      email: 'john@doe.com',
      password: 'password',
      loggedIn: false,
    })
    console.log(res)
    this.getCustomers()
  }

  render() {
    return (
      /*main content*/
      <div className='wrapper'>
        <div class='signUpContent'>
          <h2>Sign up!</h2>

          {/*Customers*/}
          <button onClick={this.createCustomer}>createCustomer</button>
          {this.state.customers.map((customer) => (
            <h2 key={customer.id}>{customer.firstname}</h2>
          ))}

          <label for='fname'>First name:</label>
          <br />
          <input type='text' id='fname' name='fname' value='John' size='70' />
          <br />
          <br />

          <label for='lname'>Last name:</label>
          <br />
          <input type='text' id='lname' name='lname' value='Doe' size='70' />
          <br />
          <br />

          <label for='lname'>Username:</label>
          <br />
          <input
            type='text'
            id='uname'
            name='uname'
            value='JohnDoe101'
            size='70'
          />
          <br />
          <br />

          <label for='lname'>E-mail:</label>
          <br />
          <input
            type='email'
            id='email'
            name='email'
            value='john@doe.com'
            size='70'
          />
          <br />
          <br />

          <label for='lname'>Password:</label>
          <br />
          <input
            type='password'
            id='lname'
            name='lname'
            value='password'
            size='70'
          />
          <br />
          <br />

          <br />

          <input class='submit' type='submit' value='Submit' />
        </div>
      </div>
    )
  }
}

export default SignUp
