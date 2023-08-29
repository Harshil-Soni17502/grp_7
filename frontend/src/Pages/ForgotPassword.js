import * as React from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import FormControlLabel from '@mui/material/FormControlLabel';
import Checkbox from '@mui/material/Checkbox';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';

import axios from 'axios';
import 'react-toastify/dist/ReactToastify.css';

import { ToastContainer, toast } from 'react-toastify';
import { useNavigate } from 'react-router-dom';
// import { Toast } from 'react-toastify/dist/components';
// import { Toast } from 'react-toastify/dist/components';

const defaultTheme = createTheme();

export default function ForgotPassword(props) {

  
  const client = axios.create({
    baseURL: "http://localhost:3308/user/changePassword",
    headers: {
      'Access-Control-Allow-Origin': '*',
    }
  })
  const OTP = "1234"
  const [checked, setChecked] = React.useState(false)
  const [errors, setErrors] = React.useState({
    email: '',
    mobileNumber: '',
    aadhar: '',
    password: '',
  })

//   React.useEffect(()=>{
//     if(localStorage.getItem("userId")!==null && localStorage.getItem("jwtToken")!==null && new Date() < new Date(localStorage.getItem("timeToExpiry"))){
//       navigate('/dashboard2');
//     }
//   },[]);

  const handleSendOtp = (event) => {
    console.log("handleSendOTP")
    event.preventDefault();

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    const validEmail = emailRegex.test(emailId);

    let newErrors = {
      email: '',
      mobileNumber: '',
      aadhar: '',
      password: '',
    };

    if (!validEmail) {
      newErrors.email = 'Invalid email address';
    }

    setErrors(newErrors);

    if (validEmail) {
      toast.success(OTP);
      setIsOtpSent(true);
    }

  };

  const handleSubmit =(event) => {
    console.log("handleSubmit")
    event.preventDefault();

    if(otp!==OTP){
        toast.error("wrong OTP");
        return;
    }

    if(password!==confirmPassword){
        toast.error("Reconfirm your password")
        return;
    }

    client.get("",{params:{
        email: emailId,
        newPassword: password
    }}).then( response => {
        if(response.status ===200){
            if(response.data==="OK"){
                toast.success("Completed");
                navigate("/");
            }
            toast.error("Email does not exist")

        }
    }
    ).catch(e => {
        toast.error(e)
    }
    )

  }

  const [title, setTitle] = React.useState('');
  const [lastName, setLastName] = React.useState('');
  const [firstName, setFirstName] = React.useState('');
  const [emailId, setEmailId] = React.useState('');
  const [mobileNumber, setMobileNumber] = React.useState('');
  const [residentialAddress, setResidentialAddress] = React.useState('');
  const [permanentAddress, setPermanentAddress] = React.useState('');
  const [occupation, setOccupation] = React.useState('');
  const [income, setIncome] = React.useState();
  const [aadhar, setAadhar] = React.useState('');
  const [dob, setDob] = React.useState();
  const [otp,setOtp] = React.useState();
  const [password, setPassword] = React.useState();
  const [confirmPassword, setConfirmPassword] = React.useState();
  const [isOtpSent, setIsOtpSent] = React.useState(false)
  const navigate = useNavigate();





  return (
    <ThemeProvider theme={defaultTheme}>
      <Container component="main" maxWidth="xs" sx={{paddingBottom:"40px"}}>
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
            Reset Password
            </Typography>
          <ToastContainer />
          {!isOtpSent &&(
            <>
          <Box component="form"
            onSubmit={handleSendOtp}
            sx={{ mt: 3 }}>
            <Grid container spacing={2}>
             

              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  id="email"
                  label="Email Address"
                  name="email"
                  autoComplete="email"
                  onChange={e => setEmailId(e.target.value)}
                  error={!!errors.email}
                  helperText={errors.email}
                />
              </Grid>
              </Grid>
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
            >
              Confirm
            </Button>
            <Grid container justifyContent="flex-end">
              <Grid item>
              <Button variant='text' onClick={()=>{navigate("/")}}>
                      {" Remember password? Sign in"}
              </Button>
                
              </Grid>
            </Grid>
            </Box>
            </>
         )}
          

            {(isOtpSent)&&(
                <> 
                 <Box component="form"
            onSubmit={handleSubmit}
            sx={{ mt: 3 }}>
            <Grid container spacing={2}>
            <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  name="OTP"
                  label="Enter OTP"
                  type="password"
                  id="OTP"
                  onChange={e => setOtp(e.target.value)}
                  error={!!errors.password}
                  helperText={errors.password}
                />
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
                  onChange={e => setPassword(e.target.value)}
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
                  onChange={e => setConfirmPassword(e.target.value)}
                  error={!!errors.password}
                  helperText={errors.password}
                />
              </Grid>


              {/* <Grid item xs={12}>
                <FormControlLabel
                  control={<Checkbox value="accpetTnc" color="primary" />}
                  label="I agree to terms and conditions."
                  onChange={(event) => setChecked(event.target.checked)}
                />
              </Grid> */}
              </Grid>
              <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
            >
              Reset password
            </Button>
              </Box>
              </> 
            )}
           
          
        </Box>
         
      </Container>
    </ThemeProvider>
  );
}


// create table users (userId int not null, password varchar(30) not null, permanent_address  varchar(30) not null, residential_address  varchar(30) not null, dob date not null, mobile_number varchar(30) not null, ) 