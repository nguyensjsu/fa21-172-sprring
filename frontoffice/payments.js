import React, {useState, useEffect} from 'react';


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


    return (
        <div>

        </div>
    );
}