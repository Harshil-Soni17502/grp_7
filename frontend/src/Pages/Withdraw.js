import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import Container from '@mui/material/Container';
import Typography from '@mui/material/Typography';
import TextField from '@mui/material/TextField';
import FormControl from '@mui/material/FormControl';
import InputLabel from '@mui/material/InputLabel';
import Select from '@mui/material/Select';
import MenuItem from '@mui/material/MenuItem';
import Button from '@mui/material/Button';
import Paper from '@mui/material/Paper';
import { useThemeProps } from '@mui/material';
import { toast, ToastContainer } from 'react-toastify';
import axios from 'axios';

function Withdraw(props) {

  const client = axios.create({
    baseURL: "http://localhost:3308/transaction/withdraw",
    headers: {
      'Access-Control-Allow-Origin':'*',
      'Authorization':`Bearer ${localStorage.getItem("jwtToken")}`,
    }
  })

  const [amount, setAmount] = useState('');
  const [password, setPassword] = useState('');
  const [errors, setErrors] = useState({
    password: '',
    amount: '',
  });


  const handleAmountChange = (event) => {
    setAmount(event.target.value);
    setErrors({...errors, amount:''});
  };

  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
    setErrors({...errors, password:''});
  };

  const handleSubmit = (event) => {
    client.post("",{
      fromAccountNo: props.account,
      amount: amount,
      password: password,
    }).then(
      response => {
      if(response.status===200){
        setAmount("");
        setPassword("");
        toast.success("Withdrawal Successful")
      }
    }
    ).catch(e => {
      const response = e.response;
      if(response.status===400){
        toast.error(response.data.message);
        let newError = {
          password: '',
          amount: '',
        }
        if(response.data.message==='Insufficient balance'){
            newError.amount = response.data.message;
        }
        else if(response.data.message==='Incorrect password'){
            newError.password = response.data.message;
        }
        else{
          console.log(response.data);
        }
        setErrors(newError);
      }
      else if(response.status===500){
        toast.error("Internal server error");
        console.log(response.data);
      }
      else{
        toast.error("Unexpected error");
        console.log(response.data);
      }
    });

    event.preventDefault();
    // Process form submission here
  };

  return (
    <Container maxWidth="lg">
      <ToastContainer />
      <Paper elevation={3} sx={{ padding: 3, marginTop: 3 }}>
        
        <Typography variant="h4" align="center" sx={{padding:"20px"}}>
          Withdraw
        </Typography>
        <form onSubmit={handleSubmit}>
          
          <TextField
            label="Enter Amount"
            fullWidth
            value={amount}
            onChange={handleAmountChange}
            type="number"
            sx={{ marginBottom: 2 }}
            error={!!errors.amount}
            helperText={errors.amount}
          />
          <TextField
            label="Transaction Password"
            fullWidth
            value={password}
            onChange={handlePasswordChange}
            type="password"
            sx={{ marginBottom: 2 }}
            error={!!errors.password}
            helperText={errors.password}
          />
          <Button
            variant="contained"
            color="primary"
            type="submit"
          >
            Submit
          </Button>
        </form>
      </Paper>
    </Container>
  );
}


export default Withdraw;
