// import template
import Navbar from './template/Navbar';

// import pages
import SignUp from './pages/SignUp';

// import css for App.js
import './App.css';

// import router 
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';

function App() {
  return (
    <Router>
      <div className="App">
        <Navbar />
        <div className="content">
          { /* Ensures only 1 webpage is displayed */ }
          <Switch>
            <Route path="/signup">
              <SignUp />
            </Route>
          </Switch>
        </div>
      </div>
    </Router>
  );
}

export default App;
