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
  

  const [beneficiary, setBeneficiary] = useState('');
  const [beneficiaryList, setBeneficiaryList] = useState([]);
  const [amount, setAmount] = useState('');
  const [transactionType,setTransactiontype] = useState('')
  const [password, setPassword] = useState('');
  const [errors, setErrors] = useState({
    password: '',
    amount: '',
  });

  useEffect(()=>{
    const params = {
      associatedAccountNo: props.account
    }

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
    setErrors({...errors, amount:''});
  };

  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
    setErrors({...errors, password:''});
  };

  const handleTransactionTypeChange = (event) => {
    setTransactiontype(event.target.value);
  }

  const handleSubmit = (event) => {
    event.preventDefault();
    // console.log("beneficiaryState" + beneficiary)
    // const foundBeneficiary = beneficiaryList.find(beneficiary1 => beneficiary1.beneficiaryName === beneficiary);
    // console.log("found befeiciary"+ foundBeneficiary)
    // const foundBeneficiaryId = foundBeneficiary ? foundBeneficiary.beneficiaryAccount.id : null;
    client.post("/transaction/make",{
      fromAccountNo:props.account,
      toAccountNo: beneficiary, //todo error
      transactionType: transactionType,
      amount:amount,
      password: password,
    }).then(
      response => {
        
        if(response.status===200){
        console.log("success")
        setBeneficiary("");
        setAmount("");
        setTransactiontype("");
        setPassword("");
        toast.success("Transaction Success");}
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
    
    // Process form submission here
  };

  return (
    <Container maxWidth="lg">
      <Paper elevation={3} sx={{ padding: 3, marginTop: 3 }}>
        <ToastContainer/>
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
          <FormControl fullWidth sx={{ marginBottom: 2 }}>
            <InputLabel>Select Transaction Type</InputLabel>
            <Select
              value={transactionType}
              onChange={handleTransactionTypeChange}
            >

                <MenuItem value={"NEFT"}>NEFT</MenuItem>
                <MenuItem value={"RTGS"}>RTGS</MenuItem>
                <MenuItem value={"IMPS"}>IMPS</MenuItem>

            </Select>
          </FormControl>
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


export default MakeTransaction;
