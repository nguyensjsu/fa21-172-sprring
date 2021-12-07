import React, { Component, Fragment, useEffect } from 'react'
import axios from 'axios'
import '../styles/Payment.css'

// const api = axios.create({
//     baseURL: 'http://' // ???
// })

class Payment extends Component {

    constructor() {
        super()
        // this.getPayments()
        this.state = {
            firstname: '',
            lastname: '',
            address: '',
            city: '',
            state: '',
            zip: '',
            phonenumber: '',
            cardnumber: '',
            expmonth: '',
            expyear: '',
            cvv: '',
            email: '',
            notes: ''
        } 
    
        this.handleChange = this.handleChange.bind(this)
        // this.handleSubmit = this.handleSubmit.bind(this)
      }

    // sets the state when inputs in sign up form are filled
    handleChange = e => {
        this.setState({ [e.target.name]: e.target.value })
    }

    render()
    {
        // display the payment form
        return (
            <div>
                <h2>hello world!</h2>
                <form onSubmit={this.handleSubmit}>
                    <div className='payment-form'>
                        <div>
                            <label for="firstname">First name: </label>
                            <br />
                            <input onChange={this.handleChange}
                                id='firstname'
                                type='text'
                                name='firstname'
                                placeholder='John' />
                            <br />
                            <label for="lastname">Last name: </label>
                            <br />
                            <input onChange={this.handleChange}
                                id='lastname'
                                type='text'
                                name='lastname'
                                placeholder='Doe' />
                            <br />
                            <label for="address">Address: </label>
                            <br />
                            <input onChange={this.handleChange}
                                id='address'
                                type='text'
                                name='address'
                                placeholder='542 W. 15th Street' />
                            <br />
                            <label for="city">City: </label>
                            <br />
                            <input onChange={this.handleChange}
                                id='city'
                                type='text'
                                name='city'
                                placeholder='New York' />
                            <br />
                            <label for="state">State: </label>
                            <br />
                            <input onChange={this.handleChange}
                                id='state'
                                type='text'
                                name='state'
                                placeholder='NY' />
                            <br />
                            <label for="zip">Zip code: </label>
                            <br />
                            <input onChange={this.handleChange}
                                id='zip'
                                type='text'
                                name='zip'
                                placeholder='10001' />
                            <br />
                            <label for="phonenumber">Phone number: </label>
                            <br />
                            <input onChange={this.handleChange}
                                id='phonenumber'
                                type='text'
                                name='phonenumber'
                                placeholder='(408) 123-0456' />
                            <br />
                        </div>
                        
                        <div>
                            {/* valid card information used as placeholders here */}
                            <label for="cardnumber">Card number: </label>
                            <br />
                            <input onChange={this.handleChange}
                                id='cardnumber'
                                type='text'
                                name='cardnumber'
                                placeholder='4622-9431-2701-3713' /> 
                            <br />
                            <label for="expmonth">Expiration month: </label>
                            <br />
                            <input onChange={this.handleChange}
                                id='expmonth'
                                type='text'
                                name='expmonth'
                                placeholder='December' /> 
                            <br />
                            <label for="expyear">Expiration year: </label>
                            <br />
                            <input onChange={this.handleChange}
                                id='expyear'
                                type='text'
                                name='expyear'
                                placeholder='2022' /> 
                            <br />
                            <label for="cvv">Cvv: </label>
                            <br />
                            <input onChange={this.handleChange}
                                id='cvv'
                                type='text'
                                name='cvv'
                                placeholder='043' /> 
                            <br />
                            <label for="email">Email: </label>
                            <br />
                            <input onChange={this.handleChange}
                                id='email'
                                type='email'
                                name='email'
                                placeholder='john@example.com' /> 
                            <br />
                            <label for="notes">Notes: </label>
                            <br />
                            <input onChange={this.handleChange}
                                id='notes'
                                type='text'
                                name='notes'
                                placeholder='special instructions' /> 
                            <br />
                            <br />
                        </div>
                    </div>

                    <button type='submit'>Submit</button>
                </form>
            </div>
        )
    }
}

export default Payment