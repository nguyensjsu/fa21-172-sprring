import React, { Component, Fragment, useEffect } from 'react'
import axios from 'axios'
import '../styles/EmployeeDashboard.css'

const api = axios.create({
  baseURL: 'http://localhost:8090/customers'
})

class Home extends Component {
  constructor() {
    super()
    this.getCustomers();

    // check for customer data
    api.get('/').then(res => {
      console.log(res.data)
      this.setState({ customers: res.data })
    })
  }

  // array of all customers
  state = {
    customers: []
  }

  // get all customers
  getCustomers = async () => {
    let data = await api.get('/').then(({ data }) => data)
    console.log(data)
    this.setState({ customers: data })
  }

  // updates the customer's password
  updatePassword = async (id, val) => {
    let data = await api.patch('/', { password: val })
    this.getCustomers()
  }

  render() {
    return (
      <div class='wrapper'>
        <div class='employeeDashboardContent'>
          <div class='sectionTitle'>
            <p>Password Change Requests</p>
          </div>

          <table>
            <tr>
              <th>First Name</th>
              <th>Last Name</th>
              <th>Email</th>
              <th>Allow Password Change</th>
            </tr>

            {this.state.customers.map(customer =>
              <tr key={customer.id}>
                <td>{customer.firstname}</td>
                <td>{customer.lastname}</td>
                <td>{customer.email}</td>
                <td><button onClick={() => this.updatePassword(customer.id, '${customer.password}lol')} type='button'>Allow Change</button></td>
              </tr>
            )}
          </table>

          <div class='sectionTitle'>
            <p>All Orders</p>
          </div>

          <table>
            <tr>
              <th>Order ID</th>
              <th>First Name</th>
              <th>Last Name</th>
              <th>Drink</th>
              <th>Drink Size</th>
              <th>Milk/No Milk</th>
            </tr>

            {this.state.customers.map(customer =>
              <tr key={customer.id}>
                <td>12345</td>
                <td>{customer.firstname}</td>
                <td>{customer.lastname}</td>
                <td>Green Tea</td>
                <td>Large</td>
                <td>Milk</td>
              </tr>
            )}
          </table>
        </div>
      </div>
    )
  }
}

export default Home
