import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Button from '@mui/material/Button';
import CameraIcon from '@mui/icons-material/PhotoCamera';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import CssBaseline from '@mui/material/CssBaseline';
import Grid from '@mui/material/Grid';
import Stack from '@mui/material/Stack';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import Link from '@mui/material/Link';
import { Hidden, SvgIcon } from '@mui/material';
import { BrowserRouter, Routes, Route, useNavigate } from "react-router-dom";
import { createTheme, ThemeProvider } from '@mui/material/styles';
import Register from './Register';
import Login from './Login';
import {ReactComponent as WellsLogo} from "../ImageAssets/wellslogo.svg"

function Copyright() {
  return (
    <Typography variant="body2" color="text.secondary" align="center">
      {'Copyright © '}
      <Link color="inherit" href="https://mui.com/">
        Wells Fargo Bank
      </Link>{' '}
      {new Date().getFullYear()}
      {'.'}
    </Typography>
  );
}

const cards = [1, 2, 3, 4, 5, 6, 7, 8, 9];

// TODO remove, this demo shouldn't need to reset the theme.
const defaultTheme = createTheme();

export default function Album() {

  const navigate = useNavigate();

  const navigateToLogin = () => {
    navigate('/Login');
  };

  const navigateToRegister = () => {
    navigate('/Register');
  };

  const [pageNo,setPageNo] = React.useState(1)



  return (
    <ThemeProvider theme={defaultTheme}>
      <CssBaseline />
      
      <AppBar position="relative">
        <Toolbar sx={{backgroundColor:'#FCCC44',display:'flex', alignItems :'center', margin:0,padding:0}} >
          <SvgIcon sx={{width:'96px',height:'96px', margin:0,padding:0, overflow:'hidden',
        objectFit:'crop'}}>
            <WellsLogo/>

          </SvgIcon>
          {/* <Typography variant="h6" color="inherit" sx={{paddinLeft:'30px',}}>
            WELLS FARGO
          </Typography> */}
        </Toolbar>
      </AppBar>
      <main>
        {/* Hero unit */}
        <Box
          sx={{
            backgroundImage: 'url(https://th.bing.com/th/id/OIP.qvL4tHrDO_Wh2F2hipSt_gAAAA?pid=ImgDet&rs=1)',
            bgcolor: 'background.paper',
            height: '300px',
            pt: 8,
            pb: 6,
          }}
        >
          
        </Box>
      </main>
        {
    (pageNo===1)&&
        <Login callback={setPageNo}/>
    
}{
    (pageNo===2)&&
        <Register callback={setPageNo}/>
    
}

      {/* <Stack
              sx={{ pt: 4, height:'1000px' }}
              direction="row"
              spacing={2}
              justifyContent="center"
            >
              <Button onClick={navigateToLogin} variant="contained">Login</Button>
              <Button onClick={navigateToRegister} variant="contained">Register</Button>
            </Stack> */}
      {/* Footer */}
      <Box sx={{ bgcolor: '#FCCC44', p: 1 }} component="footer">
      
        <Typography
          variant="subtitle1"
          align="center"
          color="text.secondary"
          component="p"
        >
          Online Banking App and its contents are for the express use of internal purposes.
        </Typography>
        <Copyright />
      </Box>
      {/* End footer */}
    </ThemeProvider >
  );
}