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

function Withdraw() {
  const [beneficiary, setBeneficiary] = useState('');
  const [amount, setAmount] = useState('');
  const [password, setPassword] = useState('');

  const handleBeneficiaryChange = (event) => {
    setBeneficiary(event.target.value);
  };

  const handleAmountChange = (event) => {
    setAmount(event.target.value);
  };

  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    // Process form submission here
  };

  return (
    <Container maxWidth="lg">
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
          />
          <TextField
            label="Transaction Password"
            fullWidth
            value={password}
            onChange={handlePasswordChange}
            type="password"
            sx={{ marginBottom: 2 }}
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
