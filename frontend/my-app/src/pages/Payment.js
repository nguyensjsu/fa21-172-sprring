import React, { Component, Fragment, useEffect } from 'react'
import axios from 'axios'
import '../styles/Payment.css'

const api = axios.create({
    baseURL: 'http://' // ???
})

class Payment extends Component {


    render()
    {
        // display the payment form
        return (
            <h2>hello world!</h2>
        )
    }
}

export default Payment