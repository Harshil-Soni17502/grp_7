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

export default function WithdrawTransaction() {
  const client = axios.create({
    baseURL: "http://localhost:3308/transaction/withdraw",
    headers: {
      'Access-Control-Allow-Origin':'*',
      'Authorization':`Bearer ${localStorage.getItem("jwtToken")}`,
    }
  })

  

    const [errors, setErrors] = React.useState({
        password:'',
      })

  const handleSubmit = (event) => {
    event.preventDefault();
    let newErrors = {
        password:'',
      };
        withdraw();
      
  };

  const withdraw = async () => {
    // const accNo = JSON.parse(localStorage.getItem("account")||[])[0].id;
    // console.log(accNo)
    // let body = {
    //     beneficiaryName: beneficiaryName,
    //     beneficiaryAccountNo: beneficiaryAccount,
    //     associatedAccountNo: accNo.toString(),
    // };
    // console.log(body);
    // let response  = await client.post("",body);
    // if(response.status === 200 && response.data == "OK"){
    //   toast.success("Beneficiary Added Successfully!");
    // }
    // else{
    //   toast.error("Some error occured!")
    // }
    // console.log(response)
  }

  const [withdrawalAmount, setWithdrawalAmount] = React.useState("");
  const [password, setPassword] = React.useState("");

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
          Withdraw Money
          </Typography>
          <ToastContainer />
          <Box component="form"  onSubmit={handleSubmit} sx={{ mt: 3 }}>
            <Grid container spacing={2}>
              
            <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  id="withdarwalMaount"
                  label="Withdrawal Amount"
                  name="withdarwalMaount"
                  onChange={e=>setWithdrawalAmount(e.target.value)}
                />
              </Grid>

              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  id="password"
                  label="Password"
                  name="password"
                  onChange={e=>setPassword(e.target.value)}
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

