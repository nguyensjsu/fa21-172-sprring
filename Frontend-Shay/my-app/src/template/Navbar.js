import React from 'react';
// import css
import './Navbar.css'

// import Gong Cha Logo
import gongchaLogo from '../images/gongchaLogo.jpg';

function Navbar() {
    return (
        <div>
            { /*Gong Cha Logo above navbar*/ }
            <div class="gonchaLogo">
                <a href="Home.html"><img class="gongchaLogoImg" src={gongchaLogo} alt="Gong Cha Logo" /></a>
            </div>

            { /*navbar*/ }
            <div class="navbar">
                <ul class="navbarList">
                    <li class="navbarItem"><a href="Home.html">Home</a></li>
                    <li class="navbarItem"><a href="Menu.html">Menu</a></li>
                    <li class="navbarItem"><a href="CustomerLogin.html">Customer Login</a></li>
                    <li class="navbarItem"><a href="EmployeeLogin.html">Employee Login</a></li>
                </ul>
            </div>
        </div>
    );
}

export default Navbar;