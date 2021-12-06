import React from 'react';
import '../styles/SignUp.css'

function SignUp() {
    return (
        /*main content*/
        <div className="wrapper">
            <div class="signUpContent">
                <h2>Sign up!</h2>

                <label for="fname">First name:</label><br />
                <input type="text" id="fname" name="fname" value="John" size="70" /><br /><br />

                <label for="lname">Last name:</label><br />
                <input type="text" id="lname" name="lname" value="Doe" size="70" /><br /><br />

                <label for="lname">Username:</label><br />
                <input type="text" id="uname" name="uname" value="JohnDoe101" size="70" /><br /><br />

                <label for="lname">E-mail:</label><br />
                <input type="email" id="email" name="email" value="john@doe.com" size="70" /><br /><br />

                <label for="lname">Password:</label><br />
                <input type="password" id="lname" name="lname" value="password" size="70" /><br /><br />
                
                <br />

                <input class="submit" type="submit" value="Submit" />

            </div>
        </div>
    );
}

export default SignUp;