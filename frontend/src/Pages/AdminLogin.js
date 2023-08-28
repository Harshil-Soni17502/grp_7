import * as React from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import FormControlLabel from '@mui/material/FormControlLabel';
import Checkbox from '@mui/material/Checkbox';
import Link from '@mui/material/Link';
import Paper from '@mui/material/Paper';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import { useState } from "react";
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import axios from "axios";
import { useNavigate } from 'react-router-dom';
const defaultTheme = createTheme();

export default function AdminLogin() {

  const client = axios.create({
    baseURL: "http://localhost:3308/user/login",
    headers: {
      'Access-Control-Allow-Origin':'*',
    }
  })

  const [email, setemail] = useState("");
  const [password, setpassword] = useState("");
  const navigate = useNavigate();

  const login = async () => {
    let body = {
      email: email,
      password: password,
    };
    console.log(body);
    try{
      let response  = await client.post("",body);
      if(response.status === 200){
        toast.success("Login successful");
        localStorage.setItem("jwtToken", response.data.jwtToken);
        localStorage.setItem("timeToExpiry", response.data.timeToExpiry);
        localStorage.setItem("userId", response.data.userId);
        localStorage.setItem("userName", response.data.userName);
        localStorage.setItem("account", JSON.stringify(response.data.account));
        localStorage.setItem("accountBeneficiaryMap", JSON.stringify(response.data.accountBeneficiaryMap));
        navigate("/admindashboard");
      }
    }
    catch(e){
      const response = e.response;
      if(response.status===400){
        toast.error("Invalid credentials!");
        console.log(response.data);
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
  };

  const handleSubmit = (event) => {
    event.preventDefault();

    
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    const validEmail = emailRegex.test(email);


    if(validEmail){
      
      login();
    }
    else{
      toast.error("Invalid email")
    }
  }

    //Fconst data = new FormData(event.currentTarget);

    let regobj = { email, password };
    console.log(regobj);

    // fetch("http://localhost:8000/user",{
    //   method:"POST",
    //   headers:{'content-type':'application/json'},
    //   body:JSON.stringify(regobj)
    // }).then((res)=>{
    //     toast.success('Registered successfully.')
    // }).catch((err)=>{
    //     toast.error('Failed :'+err.message);
    // });
  

  return (
    <ThemeProvider theme={defaultTheme}>
      <Grid container component="main" sx={{ height: '100vh' }}>
        <CssBaseline />
        <ToastContainer/>
        <Grid
          item
          xs={false}
          sm={4}
          md={7}
          sx={{
            backgroundImage: 'url(https://s.yimg.com/uu/api/res/1.2/IPKZTK7K3WAXgZG0BKHzKg--~B/aD0yMzQxO3c9MzUwODtzbT0xO2FwcGlkPXl0YWNoeW9u/http://media.zenfs.com/en-US/homerun/footwear_news_642/da6e0e85ba62072b372739867f118977)',
            backgroundRepeat: 'no-repeat',
            backgroundColor: (t) =>
              t.palette.mode === 'light' ? t.palette.grey[50] : t.palette.grey[900],
            backgroundSize: 'cover',
            backgroundPosition: 'center',
          }}
        />
        <Grid item xs={12} sm={8} md={5} component={Paper} elevation={6} square>
          <Box
            sx={{
              my: 8,
              mx: 4,
              display: 'flex',
              flexDirection: 'column',
              alignItems: 'center',
            }}
          >
            <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
              <LockOutlinedIcon />
            </Avatar>
            <Typography component="h1" variant="h5">
              Wells Fargo Admin Sign In
            </Typography>
            <Box component="form" noValidate onSubmit={handleSubmit} sx={{ mt: 1 }}>
              <TextField
                margin="normal"
                required
                fullWidth
                id="email"
                label="Email Address"
                name="email"
                value={email}
                onChange={e => setemail(e.target.value)}
                autoComplete="email"
                autoFocus
              />
              <TextField
                margin="normal"
                required
                fullWidth
                name="password"
                label="Password"
                type="password"
                id="password"
                value={password}
                onChange={e => setpassword(e.target.value)}
                autoComplete="current-password"
              />
              <Button
                type="submit"
                fullWidth
                variant="contained"
                sx={{ mt: 3, mb: 2 }}
              >
                Sign In
              </Button>
              <Grid container>
                <Grid item xs>
                  <Link href="#" variant="body2">
                    Forgot password?
                  </Link>
                </Grid>
                <Grid item>
                  <Link href="#" variant="body2">
                    {"Don't have an account? Sign Up"}
                  </Link>
                </Grid>
              </Grid>
            </Box>
          </Box>
        </Grid>
      </Grid>
    </ThemeProvider>
  );
}