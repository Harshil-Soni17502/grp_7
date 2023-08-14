import logo from './logo.svg';
import './App.css';
import { BrowserRouter, Routes, Route } from "react-router-dom"; 
import Login from "./Pages/Login";
import Layout from "./Pages/Layout";
import Register from "./Pages/Register";
import Home from "./Pages/Home";
import Error404 from "./Pages/Error404";
import OpenAccount from "./Pages/OpenAccount";
import ReactDOM from "react-dom/client";
import AddBeneficiary from './Pages/AddBeneficiary';
import Dashboard from './Pages/Dashboard';

export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<Home />} />
          <Route path="login" element={<Login />} />
          <Route path="register" element={<Register />} />
          <Route path="openAccount" element={<OpenAccount />} />
          <Route path="addBeneficiary" element={<AddBeneficiary />} />
          <Route path="dashboard" element={<Dashboard />} />
          <Route path="*" element={<Error404 />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(<App />);
