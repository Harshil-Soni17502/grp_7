import * as React from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import FormControlLabel from '@mui/material/FormControlLabel';
import Checkbox from '@mui/material/Checkbox';
import Link from '@mui/material/Link';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider, useThemeProps } from '@mui/material/styles';
import axios from 'axios';
import { ToastContainer, toast } from 'react-toastify';
  import 'react-toastify/dist/ReactToastify.css';


const defaultTheme = createTheme();

export default function AddBeneficiary(props) {
  const client = axios.create({
    baseURL: "http://localhost:3308/beneficiary/insert",
    headers: {
      'Access-Control-Allow-Origin':'*',
      'Authorization':`Bearer ${localStorage.getItem("jwtToken")}`,
    }
  })

  

    const [errors, setErrors] = React.useState({
        beneficiaryAccount:'',
      })

  const handleSubmit = (event) => {
    event.preventDefault();
    let newErrors = {
        beneficiaryAccount:'',
      };
    console.log(typeof Number(beneficiaryAccount));
    console.log(typeof props.account);
    if(Number(beneficiaryAccount)===props.account){
      newErrors.beneficiaryAccount = 'Beneficiary account no. cannot be same as associated account no.';
      setErrors(newErrors);
    }
    else{
      addBeneficiary();
    }  
  };

  const addBeneficiary = async () => {
    //const accNo = JSON.parse(localStorage.getItem("account")||[])[0].id;
    //console.log(accNo)
    let body = {
        beneficiaryName: beneficiaryName,
        beneficiaryAccountNo: beneficiaryAccount,
        associatedAccountNo: props.account,
    };
    console.log(body);
    try{
      let response  = await client.post("",body);
      if(response.status === 200 && response.data == "OK"){
        toast.success("Beneficiary Added Successfully!");
      }
    }
    catch(e){
      const response = e.response;
      if(response.status === 400){
        if(response.data.message==='Account no does not exist'){
          toast.error("Beneficiary account no. does not exist");
          let newError = {
            beneficiaryAccount: 'Beneficiary account no. does not exist',
          }
          setErrors(newError);
        }
        else{
          toast.error("Check fields for add beneficiary!");
        }
      }
      else if(response.status === 409){
        toast.error("Beneficiary account already exists");
        let newError = {
          beneficiaryAccount: 'Beneficiary account already exists',
        }
        setErrors(newError);
      }
      else if(response.status===500){
        toast.error("Internal server error!");
        console.log(response.data);
      }
      else{
        toast.error("Unexpected error!");
        console.log(response.data);
      
    }
  }
}

  const [beneficiaryName, setBeneficiaryName] = React.useState("");
  const [beneficiaryAccount, setBeneficiaryAccount] = React.useState(0);

  return (
    <ThemeProvider theme={defaultTheme}>
      <Container component="main" maxWidth="xs">
        <CssBaseline />
        <Box
          sx={{
            marginTop: 8,
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
          }}
        >
          <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
            <LockOutlinedIcon />
          </Avatar>
          <Typography component="h1" variant="h5">
          Add Beneficiary
          </Typography>
          <ToastContainer />
          <Box component="form"  onSubmit={handleSubmit} sx={{ mt: 3 }}>
            <Grid container spacing={2}>
              
            <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  id="beneficiaryName"
                  label="Beneficiary Name"
                  name="beneficiaryName"
                  onChange={e=>setBeneficiaryName(e.target.value)}
                />
              </Grid>

              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  id="beneficiaryAccount"
                  label="Beneficiary Account"
                  name="beneficiaryAccount"
                  type="number"
                  error={!!errors.beneficiaryAccount}
                  helperText={errors.beneficiaryAccount}
                  onChange={e=>{setBeneficiaryAccount(e.target.value); setErrors({beneficiaryAccount:''});}}
                />
              </Grid>
              
            </Grid>
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
            >
              Submit
            </Button>
          </Box>
        </Box>
      </Container>
    </ThemeProvider>
  );
}
