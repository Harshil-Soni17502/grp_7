import React, { useEffect, useState } from 'react';
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
import axios from 'axios';
import { ToastContainer, toast } from 'react-toastify';

function MakeTransaction(props) {

  const client = axios.create({
    baseURL: "http://localhost:3308",
    headers: {
      'Access-Control-Allow-Origin':'*',
      'Authorization': "Bearer " + localStorage.getItem('jwtToken')
    }
  })
  console.log(props.account)
  const params = {
    associatedAccountNo: props.account
  }

  const [beneficiary, setBeneficiary] = useState('');
  const [beneficiaryList, setBeneficiaryList] = useState([]);
  const [amount, setAmount] = useState('');
  const [password, setPassword] = useState('');

  useEffect(()=>{
    client.get("/beneficiary/get",{params:params}).then(
      response =>{
      setBeneficiaryList(response.data)
      console.log(response.data);
      }
      )

  },[])

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
    // console.log("beneficiaryState" + beneficiary)
    // const foundBeneficiary = beneficiaryList.find(beneficiary1 => beneficiary1.beneficiaryName === beneficiary);
    // console.log("found befeiciary"+ foundBeneficiary)
    // const foundBeneficiaryId = foundBeneficiary ? foundBeneficiary.beneficiaryAccount.id : null;
    client.post("/transaction/make",{
      fromAccountNo:props.account,
      toAccountNo: beneficiary, //todo error
      transactionType: 'NEFT',
      amount:amount
    }).then(
      response => {
        if(response.code===200)
        toast.success("Transaction Success");
      }
    )
    // Process form submission here
  };

  return (
    <Container maxWidth="lg">
      <Paper elevation={3} sx={{ padding: 3, marginTop: 3 }}>
        <Typography variant="h4" align="center" sx={{padding:"20px"}}>
          Transact
        </Typography>
        <form onSubmit={handleSubmit}>
          <FormControl fullWidth sx={{ marginBottom: 2 }}>
            <InputLabel>Select Beneficiary</InputLabel>
            <Select
              value={beneficiary}
              onChange={handleBeneficiaryChange}
            >
              {
                // {accounts.map((eachAccount,index)=>(
                //   <MenuItem key={eachAccount.id} value={eachAccount.id}>
                //     {eachAccount.id}
                //   </MenuItem>
                // ))}
                
                beneficiaryList.map((eachBeneficiary,index)=>(
                  <MenuItem value={eachBeneficiary.beneficiaryAccount.id}>{eachBeneficiary.beneficiaryName}</MenuItem>
                ))
                
              }
              
              {/* <MenuItem value="beneficiary2">Beneficiary 2</MenuItem>
              <MenuItem value="beneficiary3">Beneficiary 3</MenuItem> */}
            </Select>
          </FormControl>
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


export default MakeTransaction;
