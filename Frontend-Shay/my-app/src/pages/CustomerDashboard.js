import React, { Component, Fragment, useEffect } from 'react'
import axios from 'axios'
import '../styles/CustomerDashboard.css'
import greenTeaImg from '../images/greenTea.jpg'
import blackTeaImg from '../images/blackTea.jpg'
import thaiTeaImg from '../images/thaiTea.jpg'

class Home extends Component {
  constructor() {
    super()
  }
  render() {
    return (
      <div class='wrapper'>
        <div class='menuContent'>
          <div class='sectionTitle'>
            <p>Request Password Change</p>
          </div>

          <div class='passChangeSection'>
            <form action='/action_page.php'>
              <label for='lname'>Current Password:</label>
              <br></br>
              <input
                type='password'
                id='uname'
                name='uname'
                value='password'
                size='70'
              ></input>
              <br></br>
              <br></br>

              <label for='lname'>New Password:</label>
              <br></br>
              <input
                type='password'
                id='pass'
                name='pass'
                value='password1'
                size='70'
              ></input>
              <br></br>
              <br></br>

              <br></br>

              <input class='submit' type='submit' value='Submit'></input>
            </form>
          </div>

          <div class='sectionTitle'>
            <p>Past Orders</p>
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

          <div class='sectionTitle'>
            <p>Order Here</p>
          </div>

          <div class='menuItem'>
            <img class='menuItemImage' src={greenTeaImg} alt='Green Tea'></img>
            <div class='menuDetails'>
              <h3 class='menuItemName'>Green Tea</h3>
              <form action='/action_page.php'>
                <p>Drink size:</p> {' '}
                <input
                  type='radio'
                  id='medium'
                  name='drink_size'
                  value='Medium'
                ></input>
                  <label for='html'>Medium $4.00</label> {' '}
                <input
                  type='radio'
                  id='large'
                  name='drink_size'
                  value='Large'
                ></input>
                  <label for='html'>Large $5.00</label>
                <br></br>
                <p>Milk/no milk:</p> {' '}
                <input
                  type='radio'
                  id='milk'
                  name='with_milk'
                  value='Milk'
                ></input>
                  <label for='html'>Milk?</label>
                <br></br>
                <br></br>
                <input type='button' value='Add to cart'></input>
              </form>
            </div>
          </div>

          <div class='menuItem'>
            <img class='menuItemImage' src={blackTeaImg} alt='Black Tea'></img>
            <div class='menuDetails'>
              <h3 class='menuItemName'>Black Tea</h3>
              <form action='/action_page.php'>
                <p>Drink size:</p> {' '}
                <input
                  type='radio'
                  id='medium'
                  name='drink_size'
                  value='Medium'
                ></input>
                  <label for='html'>Medium $4.25</label> {' '}
                <input
                  type='radio'
                  id='large'
                  name='drink_size'
                  value='Large'
                ></input>
                  <label for='html'>Large $5.00</label>
                <br></br>
                <p>Milk/no milk:</p> {' '}
                <input
                  type='radio'
                  id='milk'
                  name='with_milk'
                  value='Milk'
                ></input>
                  <label for='html'>Milk?</label>
                <br></br>
                <br></br>
                <input type='button' value='Add to cart'></input>
              </form>
            </div>
          </div>

          <div class='menuItem'>
            <img class='menuItemImage' src={thaiTeaImg} alt='Thai Tea'></img>
            <div class='menuDetails'>
              <h3 class='menuItemName'>Thai Tea</h3>
              <form action='/action_page.php'>
                <p>Drink size:</p> {' '}
                <input
                  type='radio'
                  id='medium'
                  name='drink_size'
                  value='Medium'
                ></input>
                  <label for='html'>Medium $4.50</label> {' '}
                <input
                  type='radio'
                  id='large'
                  name='drink_size'
                  value='Large'
                ></input>
                  <label for='html'>Large $5.00</label>
                <br></br>
                <p>Milk/no milk:</p> {' '}
                <input
                  type='radio'
                  id='milk'
                  name='with_milk'
                  value='Milk'
                ></input>
                  <label for='html'>Milk?</label>
                <br></br>
                <br></br>
                <input type='button' value='Add to cart'></input>
              </form>
            </div>
          </div>
        </div>
      </div>
    )
  }
}

export default Home
