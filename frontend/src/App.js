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
import Transaction from './Pages/Transaction';
import AdminLogin from './Pages/AdminLogin';
import AdminDashboard from './Pages/AdminDashboard';

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
          <Route path="transaction" element={<Transaction />} />
          <Route path="adminLogin" element={<AdminLogin />} />
          <Route path="adminDashboard" element={<AdminDashboard />} />
          <Route path="*" element={<Error404 />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(<App />);
