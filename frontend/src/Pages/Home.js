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



const Home = () => {
  const defaultTheme = createTheme();
    return (
      <ThemeProvider theme={defaultTheme}>
        <Container>
        <Grid
          container
          spacing={4}
          justifyContent="center"
          alignItems="center"
          style={{ minHeight: '100vh' }}
        >
          <Grid item xs={6} >
            <Typography variant="h3" align="center">
              Bells Bargo Online
            </Typography>
          </Grid>
          <Grid item xs={3} md={6} spacing={4} >
            <Button variant="contained" color="primary" fullWidth>
              Login
            </Button>
            
            <Button variant="outlined" color="primary" fullWidth>
              Sign Up
            </Button>
          </Grid>
        </Grid>
      </Container>
    </ThemeProvider>
    )
  };
  
  export default Home;