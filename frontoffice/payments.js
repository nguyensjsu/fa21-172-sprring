import React, {useState, useEffect} from 'react';
import axois from 'axois';


export default function Payments(props)
{
    /*
    payment variables:
        firstname
        lastname
        address
        city
        state
        zip
        phonenumber
        cardnumber
        expmonth
        expyear
        cvv
        email
        notes
        cost
    */
    const [firstname, setFirstname] = useState("");
    const [lastname, setLastname] = useState("");
    const [address, setAddress] = useState("");
    const [city, setCity] = useState("");
    const [state, setState] = useState("");
    const [zip, setZip] = useState("");
    const [phonenumber, setPhonenumber] = useState("");
    const [cardnumber, setCardnumber] = useState("");
    const [expmonth, setExpmonth] = useState("");
    const [expyear, setExpyear] = useState("");
    const [cvv, setCvv] = useState("");
    const [email, setEmail] = useState(""); 
    const [cost, setCost] = useState("");

   

    function validate_state(state)
    {
        const states = ["AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", 
        "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", 
        "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", 
        "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", 
        "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"];

        return states.includes(state);
    }

    // validation methods
    function validate_zip(zip)
    {
        return new RegExp('^[0-9]{5}(?:-[0-9]{4})?$').test(zip);
    }

    function validate_phonenumber(phonenumber)
    {
        return new RegExp('/^(\()?\d{3}(\))?(-|\s)?\d{3}(-|\s)\d{4}$/').test(phonenumber);
    }

    function validate_email(email)
    {
        return new RegExp('^[a-zA-Z0-9._:$!%-]+@[a-zA-Z0-9.-]+.[a-zA-Z]$').test(email);
    }

    function validate_cardnumber(cardnumber)
    {
        return new RegExp('^[(0-9)-]{16}$').test(cardnumber);
    }

    function validate_expmonth(expmonth)
    {
        const month = parseInt(expmonth);
        return ( (month > 0) && (month < 13) && (expmonth.length == 2) );
    }

    function validate_expyear(expyear)
    {
        return new RegExp('^[0-9]{4}$').test(expyear);
    }

    function validate_cvv(cvv)
    {
        return new RegExp('^[0-9]{3}$').test(cvv);
    }



    return (
        <div>

        </div>
    );
}