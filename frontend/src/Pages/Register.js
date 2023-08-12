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
import axios from 'axios';

const defaultTheme = createTheme();

export default function Register() {

  const client = axios.create({
    baseURL: "http://localhost:8086/user/create",
    headers: {
      'Access-Control-Allow-Origin':'*',
    }
  })

  const handleSubmit = (event) => {
    console.log("handleSubmit")
    event.preventDefault();
    addUser();
  };

  const [title, setTitle] = React.useState('');
  const [lastName, setLastName] = React.useState('');
  const [firstName, setFirstName] = React.useState('');
  const [emailId, setEmailId] = React.useState('');
  const [residentialAddress, setResidentialAddress] = React.useState('');
  const [permanentAddress, setPermanentAddress] = React.useState('');
  const [occupation, setOccupation] = React.useState('');
  const [income, setIncome] = React.useState();
  const [aadhar, setAadhar] = React.useState('');
  const [dob, setDob] = React.useState();
  const [password, setPassword] = React.useState();
  const [confirmPassword, setConfirmPassword] = React.useState();

  const addUser = async () => {
    let body = {
      title: title,
      firstName: firstName,
      lastName: lastName,
      fullPermanentAddress: permanentAddress,
      fullResidentialAddress: residentialAddress,
      email: emailId,
      password: password,
      occupation: occupation,
      totalGrossCompensation: 99.90,
      aadharCardNumber: aadhar,
      dateOfBirth: dob,
      mobileNumber: "9121991219"
    };
    console.log(body);
    let response  = await client.post("",{
      title: title,
      firstName: firstName,
      lastName: lastName,
      fullPermanentAddress: permanentAddress,
      fullResidentialAddress: residentialAddress,
      email: emailId,
      password: password,
      occupation: occupation,
      totalGrossCompensation: 99.90,
      aadharCardNumber: aadhar,
      dateOfBirth: dob,
      mobileNumber: "9121991219"
    });
    console.log(response)
  }

  

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
          Register for Internet Banking
          </Typography>
          <Box component="form" noValidate
           onSubmit={handleSubmit} 
           sx={{ mt: 3 }}>
            <Grid container spacing={2}>
            <Grid item xs={12} sm={6}>
                <FormControl fullWidth>
                <InputLabel required id="demo-simple-select-label">Title</InputLabel>
                <Select
                    labelId="demo-simple-select-label"
                    id="demo-simple-select"
                    value={title}
                    label="Ttle"
                    onChange={(event) => {
                      setTitle(event.target.value);
                    }}
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
                  onChange={e=>setFirstName(e.target.value)}
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
                  autoComplete="family-name"
                  onChange={e=>setLastName(e.target.value)}
                />
              </Grid>
              
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  id="email"
                  label="Email Address"
                  name="email"
                  autoComplete="email"
                  onChange={e=>setEmailId(e.target.value)}
                />
              </Grid>
              
              
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  name="residentialAddress"
                  label="Residential Address"
                  id="residentialAddress"
                  onChange={e=>setResidentialAddress(e.target.value)}
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  name="permanentAddress"
                  label="Permanent Address"
                  onChange={e=>setPermanentAddress(e.target.value)}
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  name="occupation"
                  label="Occupation"
                  id="occupation"
                  onChange={e=>setOccupation(e.target.value)}
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  name="grossIncome"
                  label="Gross Income"
                  id="grossIncome"
                  onChange={e=>setIncome(e.target.value)}
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  name="aadhar"
                  label="Aadhar Card Number"
                  id="aadhar"
                  onChange={e=>setAadhar(e.target.value)}
                />
              </Grid>
              <Grid item xs={12}>
              <Typography component="h6" variant="subtitle1">
          Date Of Birth
          </Typography>
              <TextField type='date' onChange={e=>setDob(e.target.value)}></TextField>
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  name="password"
                  label="Set Login Password"
                  type="password"
                  id="password"
                  autoComplete="new-password"
                  onChange={e=>setPassword(e.target.value)}
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  name="confirmPassword"
                  label="Confirm Login Password"
                  type="password"
                  id="confirmPassword"
                  autoComplete="new-password"
                  onChange={e=>setConfirmPassword(e.target.value)}
                />
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
              Register
            </Button>
            <Grid container justifyContent="flex-end">
              <Grid item>
                <Link href="#" variant="body2">
                  Already have an account? Sign in
                </Link>
              </Grid>
            </Grid>
          </Box>
        </Box>
      </Container>
    </ThemeProvider>
  );
}


// create table users (userId int not null, password varchar(30) not null, permanent_address  varchar(30) not null, residential_address  varchar(30) not null, dob date not null, mobile_number varchar(30) not null, ) 