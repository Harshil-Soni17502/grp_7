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
import axios from 'axios';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

const defaultTheme = createTheme();

export default function Login(props) {
  const client = axios.create({
    baseURL: "http://localhost:3308/user/login",
    headers: {
      'Access-Control-Allow-Origin':'*',
    }
  })

  const [email, setEmail] = React.useState('');
  const [password, setPassword] = React.useState('');

  const [errors, setErrors] = React.useState({
    email:'',
  })

  const handleSubmit = (event) => {
    console.log("handleSubmit")
    event.preventDefault();

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    const validEmail = emailRegex.test(email);

    let newErrors = {
      email: '',
    };

    if(!validEmail){
      newErrors.email = 'Invalid email address';
    }

    setErrors(newErrors);

    if(validEmail){
      login();
    }

  };

  const login = async () => {
    let body = {
      email: email,
      password: password,
    };
    console.log(body);
    let response  = await client.post("",body);
    if(response.status === 200 && response.data === ""){
      toast.error("Check email and password again!");
    }
    else if(response.status === 200){
      toast.success("Login successful");
      localStorage.setItem("jwtToken", response.data.jwtToken);
      localStorage.setItem("timeToExpiry", response.data.timeToExpiry);
      localStorage.setItem("userId", response.data.userId);
      localStorage.setItem("userName", response.data.userName);
      localStorage.setItem("account", JSON.stringify(response.data.account));
      localStorage.setItem("accountBeneficiaryMap", JSON.stringify(response.data.accountBeneficiaryMap));
    }
    else{
      toast.error("Some error occured!")
    }
    console.log(response)
  }


  return (
    <ThemeProvider theme={defaultTheme}>
      <Grid container component="main" sx={{ height: '100vh' }}>
        <CssBaseline />
        <Grid
          item
          xs={false}
          sm={4}
          md={7}
          sx={{
             backgroundImage: 'url(https://source.unsplash.com/random?wallpapers)',
            //backgroundImage: `url(../ImageAssets/wellslogo.svg)`,
            backgroundRepeat: 'no-repeat',
            backgroundColor: (t) =>
              t.palette.mode === 'light' ? t.palette.grey[50] : t.palette.grey[900],
            backgroundSize: 'cover',
            backgroundPosition: 'center',
          }}
        />
        <Grid item xs={12} sm={8} md={5} component={Paper} elevation={1} square>
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
              Sign in
            </Typography>
            <ToastContainer />
            <Box component="form"  onSubmit={handleSubmit} sx={{ mt: 1 }}>
              <TextField
                margin="normal"
                required
                fullWidth
                id="email"
                label="Email Address"
                name="email"
                value={email}
                autoComplete="email"
                autoFocus
                onChange={e=>setEmail(e.target.value)}
                error={!!errors.email}
                helperText={errors.email}
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
                autoComplete="current-password"
                onChange={e=>setPassword(e.target.value)}
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
                  {/* <Link href="#" variant="body2"> */}
                    <Button variant='text' onClick={()=>{props.callback(2)}}>
                      {"Don't have an account? Sign Up"}

                    </Button>
                    
                  {/* </Link> */}
                </Grid>
              </Grid>
            </Box>
          </Box>
        </Grid>
      </Grid>
    </ThemeProvider>
  );
}