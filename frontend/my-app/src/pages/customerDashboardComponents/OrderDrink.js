import React, { Component, Fragment, useEffect } from 'react'
// allow for links to other webpages
import { Link, Redirect } from 'react-router-dom'
import axios from 'axios'
import '../../styles/CustomerDashboard.css'
import greenTeaImg from '../../images/greenTea.jpg'
import blackTeaImg from '../../images/blackTea.jpg'
import thaiTeaImg from '../../images/thaiTea.jpg'

class Home extends Component {
  constructor() {
    super()

    this.state = {
      drink: '',
      milk: '',
      drinkSize: '',
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

    console.log(this.state)
    axios
      .post('http://localhost:8080/order/register', this.state)
      .then((response) => {
        console.log(response)
        const { temp } = this.state
        this.setState({
          ...temp,
          status: response.data,
        })
        console.log(this.state)
      })
      .catch((error) => {
        console.log(error)
      })
  }

  render() {
    const { drink, milk, drinkSize } = this.state

    return (
      <div>
        <div class='sectionTitle'>
          <p>Order Here</p>
        </div>

        {/* Green Tea */}
        <div class='menuItem'>
          <img class='menuItemImage' src={greenTeaImg} alt='Green Tea'></img>
          <div class='menuDetails'>
            <h3 class='menuItemName'>Green Tea</h3>
            <form onSubmit={this.handleSubmit}>
              {/* Drink Type */}
              <input
                onChange={this.handleChange}
                type='hidden'
                id='drink'
                name='drink'
                value='Green Tea'
              ></input>
              {/* Drink Size */}
              <p>Drink size:</p> 
              <input
                onChange={this.handleChange}
                type='radio'
                id='drinkSize'
                name='drinkSize'
                value='Medium'
              ></input>
                <label>Medium $4.00</label>
              <input
                onChange={this.handleChange}
                type='radio'
                id='drinkSize'
                name='drinkSize'
                value='Large'
              ></input>
                <label>Large $5.00</label>
              <br></br>
              {/* Milk Options */}
              <p>Milk/no milk:</p> 
              <input
                onChange={this.handleChange}
                type='radio'
                id='milk'
                name='milk'
                value='milk'
              ></input>
                <label>Milk</label>
              <input
                onChange={this.handleChange}
                type='radio'
                id='milk'
                name='milk'
                value='no milk'
              ></input>
                <label>No milk</label>
              <br></br>
              <br></br>
              <button class='submit' type='submit'>
                Submit
              </button>
            </form>
          </div>
        </div>

        {/* Black Tea */}
        <div class='menuItem'>
          <img class='menuItemImage' src={blackTeaImg} alt='Black Tea'></img>
          <div class='menuDetails'>
            <h3 class='menuItemName'>Black Tea</h3>
            <form onSubmit={this.handleSubmit}>
              {/* Drink Type */}
              <input
                onChange={this.handleChange}
                type='hidden'
                id='drink'
                name='drink'
                value='Black Tea'
              ></input>
              {/* Drink Size */}
              <p>Drink size:</p> 
              <input
                onChange={this.handleChange}
                type='radio'
                id='drinkSize'
                name='drinkSize'
                value='Medium'
              ></input>
                <label>Medium $4.25</label> 
              <input
                onChange={this.handleChange}
                type='radio'
                id='drinkSize'
                name='drinkSize'
                value='Large'
              ></input>
                <label>Large $5.00</label>
              <br></br>
              {/* Milk Options */}
              <p>Milk/no milk:</p> 
              <input
                onChange={this.handleChange}
                type='radio'
                id='milk'
                name='milk'
                value='milk'
              ></input>
                <label>Milk</label>
              <input
                onChange={this.handleChange}
                type='radio'
                id='milk'
                name='milk'
                value='no milk'
              ></input>
                <label>No milk</label>
              <br></br>
              <br></br>
              <button class='submit' type='submit'>
                Submit
              </button>
            </form>
          </div>
        </div>

        {/* Thai Tea */}
        <div class='menuItem'>
          <img class='menuItemImage' src={thaiTeaImg} alt='Thai Tea'></img>
          <div class='menuDetails'>
            <h3 class='menuItemName'>Thai Tea</h3>
            <form onSubmit={this.handleSubmit}>
              {/* Drink Type */}
              <input
                onChange={this.handleChange}
                type='hidden'
                id='drink'
                name='drink'
                value='Thai Tea'
              ></input>
              {/* Drink Size */}
              <p>Drink size:</p> 
              <input
                onChange={this.handleChange}
                type='radio'
                id='drinkSize'
                name='drinkSize'
                value='Medium'
              ></input>
                <label>Medium $4.50</label> 
              <input
                onChange={this.handleChange}
                type='radio'
                id='drinkSize'
                name='drinkSize'
                value='Large'
              ></input>
                <label>Large $5.00</label>
              <br></br>
              {/* Milk Options */}
              <p>Milk/no milk:</p> 
              <input
                onChange={this.handleChange}
                type='radio'
                id='milk'
                name='milk'
                value='milk'
              ></input>
                <label>Milk</label>
              <input
                onChange={this.handleChange}
                type='radio'
                id='milk'
                name='milk'
                value='no milk'
              ></input>
                <label>No milk</label>
              <br></br>
              <br></br>
              <button class='submit' type='submit'>
                Submit
              </button>
            </form>
          </div>
        </div>
      </div>
    )
  }
}

export default Home
