import logo from './logo.svg';
import './App.css';

function Guest() {
  return (
    <div className="Guest">
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
          This is second page
        </a>
      </header>
    </div>
  );
}

export default Guest;
