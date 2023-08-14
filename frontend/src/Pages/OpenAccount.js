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
import { createTheme, ThemeProvider } from '@mui/material/styles';
import axios from 'axios';
import { ToastContainer, toast } from 'react-toastify';
  import 'react-toastify/dist/ReactToastify.css';


const defaultTheme = createTheme();

export default function OpenAccount() {
  const client = axios.create({
    baseURL: "http://localhost:8086/account/create",
    headers: {
      'Access-Control-Allow-Origin':'*',
    }
  })

  

    const [errors, setErrors] = React.useState({
        email:'',
        mobileNumber:'',
        aadhar: '',
        password: '',
        agreetnc: '',
      })

  const handleSubmit = (event) => {
    event.preventDefault();
    let newErrors = {
        password: '',
        agreetnc: '',
      };
    if(transactionPassword !== confirmTransactionPassword){
        newErrors.password = 'Password does not match with confirm password'
      }
  
      setErrors(newErrors);
  
      if(transactionPassword === confirmTransactionPassword){
        addAccount();
      }
  };

  const addAccount = async () => {
    let body = {
      userId: "3",
      transactionPassword: transactionPassword,
      accountType: accountType,
    };
    console.log(body);
    let response  = await client.post("",body);
    if(response.status === 200 && response.data == "OK"){
      toast.success("Account Created Successfully!");
    }
    else{
      toast.error("Some error occured!")
    }
    console.log(response)
  }

  const [accountType, setAccountType] = React.useState();
  const [transactionPassword, setTransactionPassword] = React.useState();
  const [confirmTransactionPassword, setConfirmTransactionPassword] = React.useState();
  const [openingBalance, setOpeningBalance] = React.useState();

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
          Open an Account
          </Typography>
          <ToastContainer />
          <Box component="form"  onSubmit={handleSubmit} sx={{ mt: 3 }}>
            <Grid container spacing={2}>
              
            <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  id="accountType"
                  label="Account Type"
                  name="accountType"
                  onChange={e=>setAccountType(e.target.value)}
                />
              </Grid>
              
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  name="transactionPassword"
                  label="Set Transaction Password"
                  type="password"
                  id="transactionPassword"
                  autoComplete="new-password"
                  onChange={e=>setTransactionPassword(e.target.value)}
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  name="confirmTransactionPassword"
                  label="Confirm Transaction Password"
                  type="password"
                  id="confirmTransactionPassword"
                  autoComplete="new-password"
                  onChange={e=>setConfirmTransactionPassword(e.target.value)}
                  error={!!errors.password}
                  helperText={errors.password}
                />
              </Grid>

              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  name="openingBalance"
                  label="Opening Balance"
                  id="openingBalance"
                  onChange={e=>setOpeningBalance(e.target.value)}
                />
              </Grid>
              <Grid item xs={12}>
                <FormControlLabel
                required
                  control={<Checkbox required value="allowExtraEmails" color="primary" />}
                  label="I agree to terms and conditions."
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

