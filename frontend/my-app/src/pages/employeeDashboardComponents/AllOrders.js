import React, { Component, Fragment, useEffect } from 'react'
import { Redirect } from 'react-router-dom'
import axios from 'axios'
import '../../styles/EmployeeDashboard.css'

const api = axios.create({
  baseURL: 'http://localhost:8090/customers'
})

class AllOrders extends Component {
  constructor() {
    super()

    this.state = {
      requests: [],
      firstname: '',
      lastname: '',
      email: ''
    }

    this.handleChange = this.handleChange.bind(this)
    this.handleSubmit = this.handleSubmit.bind(this)
  }

  componentDidMount() {
    axios.get('http://localhost:8090/customers/requests')
      .then(response => {
        console.log(response)
          this.setState({
            requests: response.data
          })
        console.log(this.state)
      })
      .catch(error => {
        console.log(error)
      })
  }

  handleChange = (e) => {
    this.setState({ [e.target.name]: e.target.value })
  }

  // handles POST request when form is submitted
  handleSubmit = (e) => {
    e.preventDefault()
    console.log(this.state)
    axios
      .post('http://localhost:8090/customer/approve-request', this.state)
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
    const { firstname, lastname, email } = this.state
    
    return (
      <div>
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

            {/* {this.state.customers.map(customer =>
              <tr key={customer.id}>
                <td>12345</td>
                <td>{customer.firstname}</td>
                <td>{customer.lastname}</td>
                <td>Green Tea</td>
                <td>Large</td>
                <td>Milk</td>
              </tr>
            )} */}
          </table>
        </div>
    )
  }
}

export default AllOrders
