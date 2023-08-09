import logo from './logo.svg';
import Home from './component/home'
import Login from './component/login'
import './App.css';
import { BrowserRouter as Router, Routes, Route, Link} from 'react-router-dom'


function App() {
  return (
    <Router>

    
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>

        <ul>
          <li>
            <Link to='/home'>Home</Link>
          </li>
          <li>
            <Link to='/login'>Login</Link>
          </li>
        </ul>
      </header>
    </div>
    <Routes>
      <Route exact path ='/' element ={<Home/>}></Route>
      <Route exact path ='/home' element ={<Home/>}></Route>
      <Route exact path ='/login' element ={<Login/>}></Route>
    </Routes>
    </Router>
  );
}

export default App;
