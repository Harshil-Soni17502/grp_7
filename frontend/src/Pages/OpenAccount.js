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
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import { useState } from "react";
import {toast} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import axios from "axios";


const defaultTheme = createTheme();

export default function OpenAccount() {

  const baseURL="http://localhost:3000/posts";

  const [title, settitle] = useState("");
  const [firstName, setfirstName] = useState("");
  const [lastName, setlastName] = useState("");
  const [email, setemail] = useState("");
  const [password, setpassword] = useState("");
  const [permanentAddress, setpermanentAddress] = useState("");
  const [residentialAddress, setresidentialAddress] = useState("");
  const [occupation, setoccupation] = useState("");
  const [totalGrossCompensation, settotalGrossCompensation] = useState("");
  const [aadhar, setaadhar] = useState("");
  const [mobile, setmobile] = useState("");
  const [dob, setdob] = useState("");

  const handleSubmit = (event) => {
    event.preventDefault();
    // const data = new FormData(event.currentTarget);
    
    axios.post(
      baseURL,
      {
        title:title,
        firstName:firstName,
        lastName:lastName,
        email:email,
        password:password,
        permanentAddress:permanentAddress,
        residentialAddress:residentialAddress,
        occupation:occupation,
        totalGrossCompensation:totalGrossCompensation,
        aadhar:aadhar,
        mobile:mobile,
        dob:dob
      }
    )
    .then(
      alert("Registered successfully")
    )
      
    let regobj={title,firstName,lastName,email,password,permanentAddress,residentialAddress,occupation,totalGrossCompensation,aadhar,mobile,dob};
    console.log(regobj);

  };


  // const handleChange = (event) => {
  //   setTitle(event.target.value);
  // };

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
          Open a Savings Account
          </Typography>
          <Typography component="h3" variant="h5">
          Personal Details
          </Typography>
          <Box component="form" noValidate onSubmit={handleSubmit} sx={{ mt: 3 }}>
            <Grid container spacing={2}>
              <Grid item xs={12} sm={6}>
                <FormControl fullWidth>
                <InputLabel required id="demo-simple-select-label">Title</InputLabel>
                <Select
                    labelId="demo-simple-select-label"
                    id="demo-simple-select"
                    value={title}
                    onChange={e => settitle(e.target.value)}
                    label="Title"
              
                >
                    <MenuItem value={"Mr"}>Mr</MenuItem>
                    <MenuItem value={"Mrs"}>Mrs</MenuItem>
                    <MenuItem value={"Miss"}>Miss</MenuItem>
                </Select>
                </FormControl>
              </Grid>
              <Grid item xs={12} sm={6}>
                <TextField
                  autoComplete="given-name"
                  name="firstName"
                  required
                  fullWidth
                  id="firstName"
                  label="First Name"
                  value={firstName}
                  onChange={e => setfirstName(e.target.value)}
                  autoFocus
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  id="lastName"
                  label="Last Name"
                  name="lastName"
                  value={lastName}
                  onChange={e => setlastName(e.target.value)}
                  autoComplete="family-name"
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  id="email"
                  label="Email Address"
                  name="email"
                  value={email}
                  onChange={e => setemail(e.target.value)}
                  autoComplete="email"
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  id="password"
                  label="Password"
                  name="password"
                  value={password}
                  onChange={e => setpassword(e.target.value)}
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  name="permanentAddress"
                  label="Permanent Address"
                  id="permanentAddress"
                  value={permanentAddress}
                  onChange={e => setpermanentAddress(e.target.value)}
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  name="residentialAddress"
                  label="Residential Address"
                  id="residentialAddress"
                  value={residentialAddress}
                  onChange={e => setresidentialAddress(e.target.value)}
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  name="occupation"
                  label="Occupation"
                  id="occupation"
                  value={occupation}
                  onChange={e => setoccupation(e.target.value)}
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  name="totalGrossCompensation"
                  label="Total Gross Compensation"
                  id="totalGrossCompensation"
                  value={totalGrossCompensation}
                  onChange={e => settotalGrossCompensation(e.target.value)}
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  name="aadhar"
                  label="Aadhar Card Number"
                  id="aadhar"
                  value={aadhar}
                  onChange={e => setaadhar(e.target.value)}
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  name="mobile"
                  label="Mobile Number"
                  id="mobile"
                  value={mobile}
                  onChange={e => setmobile(e.target.value)}
                />
              </Grid>
              <Grid item xs={12}>
              <Typography component="h6" variant="subtitle1">
          Date Of Birth
          </Typography>
              <input value={dob} type='date' onChange={e => setdob(e.target.value)}/>
              
                  
              </Grid>
              
              
              <Grid item xs={12}>
                <FormControlLabel
                  control={<Checkbox value="allowExtraEmails" color="primary" />}
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

