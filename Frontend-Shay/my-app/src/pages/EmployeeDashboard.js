import React, { Component, Fragment, useEffect } from 'react'
import axios from 'axios'
import '../styles/EmployeeDashboard.css'

class Home extends Component {
  constructor() {
    super()
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
            <tr>
              <td>John</td>
              <td>Doe</td>
              <td>john@doe.com</td>
              <td>
                <button type='button'>Allow Change</button>
              </td>
            </tr>
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
            <tr>
              <td>12345</td>
              <td>John</td>
              <td>Doe</td>
              <td>Green Tea</td>
              <td>Large</td>
              <td>Milk</td>
            </tr>
          </table>
        </div>
      </div>
    )
  }
}

export default Home
