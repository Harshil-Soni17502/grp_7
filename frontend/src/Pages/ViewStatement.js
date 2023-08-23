import ArticleIcon from '@mui/icons-material/Article';
import * as React from 'react';
import { styled, createTheme, ThemeProvider } from '@mui/material/styles';
import CssBaseline from '@mui/material/CssBaseline';
import MuiDrawer from '@mui/material/Drawer';
import Box from '@mui/material/Box';
import MuiAppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import List from '@mui/material/List';
import Typography from '@mui/material/Typography';
import Divider from '@mui/material/Divider';
import IconButton from '@mui/material/IconButton';
import Badge from '@mui/material/Badge';
import { useState } from 'react';
import Container from '@mui/material/Container';
import Grid from '@mui/material/Grid';
import {TextField, Button } from '@mui/material';

import Paper from '@mui/material/Paper';
import TableContainer from '@mui/material/TableContainer';
import Table from '@mui/material/Table';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import TableCell from '@mui/material/TableCell';
import TableBody from '@mui/material/TableBody';
//mport Link from '@mui/material/Link';
import MenuIcon from '@mui/icons-material/Menu';
import ChevronLeftIcon from '@mui/icons-material/ChevronLeft';
import NotificationsIcon from '@mui/icons-material/Notifications';
import { mainListItems } from './DashboardComponents/listItems';
import Chart from './DashboardComponents/Chart';
import Deposits from './DashboardComponents/Deposits';
import Orders from './DashboardComponents/Orders';
import AbButton from './abButton';
import MakeTransaction from './makeTransaction';
import UserHome from './userHome';
import ViewAccounts from './viewAccounts';
import { ListItemButton } from '@mui/material';
import { Link } from 'react-router-dom';
import ListItemText from '@mui/material/ListItemText';
import ListItemIcon from '@mui/material/ListItemIcon';
import PeopleIcon from '@mui/icons-material/People';
import BarChartIcon from '@mui/icons-material/BarChart';
import DashboardIcon from '@mui/icons-material/Dashboard';
import './DropdownPage.css';
import FormControl from '@mui/material/FormControl';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import Select from '@mui/material/Select';
import AccountBalanceIcon from '@mui/icons-material/AccountBalance';
import axios from 'axios';
import { ToastContainer, toast } from 'react-toastify';
import ListItem from '@mui/material/ListItem';



export default function ViewStatment(props) {
    
    const [accountDetails, setAccountDetails] = React.useState({
        accountNo: '1234567890',
        accountBalance: '$5000',
        accountType: 'Savings',
        recentTransactions: [
          'Transaction 1: -$100',
          'Transaction 2: +$300',
          'Transaction 3: -$50',
        ],
      });

      const client = axios.create({
        baseURL: "http://localhost:3308/transaction/getTransactionsBetweenFor",
        headers: {
          'Access-Control-Allow-Origin':'*',
          'Authorization': "Bearer " + localStorage.getItem('jwtToken')
        }
      })

    const [startDate, setStartDate] = useState('');
    const [endDate, setEndDate] = useState('');
    
    const handleStartDateChange = (event) => {
        setStartDate(event.target.value);
    };
    
    const handleEndDateChange = (event) => {
        setEndDate(event.target.value);
    };
    
    const handleSubmit = (e) => {
        e.preventDefault();
        client.get("",{params:{
          t1: startDate,
          t2: endDate,
          accountNo: props.account
        }}).then(
          response => {
            if(response.status===200){
              console.log(response.data)
              setAccountDetails({
                recentTransactions: response.data
              })
            }
          }
        ).catch(e => {
          const response = e.response;
          if(response.status===400){
            if(response.data.message==='Account doesn\'t exist'){
              toast.error(response.data.message);
            }
            else{
              toast.error('Check form fields');
              console.log(response.data);
            }
          }
          else if(response.status===500){
            toast.error('Internal server error');
            console.log(response.data);
          }
          else{
            toast.error('Unexpected error');
            console.log(response.data);
          }
        });
        // Do something with the selected dates (startDate and endDate)
        console.log('Selected Start Date:', startDate);
        console.log('Selected End Date:', endDate);
    };

  return (
    
    <Container maxWidth='lg'>
        <Paper elevation={3} sx={{ padding: 3, marginTop: 3 }}>
         <Typography sx={{paddingBottom:"20px", paddingTop:"10px", textAlign:"center"}} variant="h4" gutterBottom>
        Request Statement
      </Typography>
      <form onSubmit={handleSubmit}>
        <Grid container spacing={2}>
          <Grid item xs={12} sm={6}>
            <TextField
              label="Start Date"
              type="date"
              value={startDate}
              onChange={handleStartDateChange}
              fullWidth
              InputLabelProps={{
                shrink: true,
              }}
            />
          </Grid>
          <Grid item xs={12} sm={6}>
            <TextField
              label="End Date"
              type="date"
              value={endDate}
              onChange={handleEndDateChange}
              fullWidth
              InputLabelProps={{
                shrink: true,
              }}
            />
          </Grid>
          <Grid item lg={12} sm={12} md={12} xs={12} sx={{display: 'flex' ,justifyContent:"center", alignItems:"center"}}>
          <Button type="submit" variant="contained" color="primary" sx={{ marginTop: 2 }}>
              Request
        </Button>
          </Grid>

        </Grid>
        
      </form>
      </Paper>
    
    
      <Paper elevation={3} sx={{ padding: 3, marginTop: 3 }}>
        <Typography variant="subtitle1">Recent Transactions:</Typography>
        <TableContainer component={Paper}>
          <Table sx={{ minWidth: 650 }}>
            <TableHead>
              <TableRow>
                <TableCell align="center" sx={{ width: '50%', border: '1px solid rgba(224, 224, 224, 1)' }}>
                  From Account
                </TableCell>
                <TableCell align="center" sx={{ width: '50%', border: '1px solid rgba(224, 224, 224, 1)' }}>
                  To Account
                </TableCell>
                <TableCell align="center" sx={{ width: '100px', border: '1px solid rgba(224, 224, 224, 1)' }}>
                  Amount
                </TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {accountDetails.recentTransactions.map((transaction, index) => (
                <TableRow key={index}>
                  <TableCell align="center" sx={{ border: '1px solid rgba(224, 224, 224, 1)' }}>
                  {(transaction.fromAccount!==undefined)&&transaction.fromAccount.id}
                  </TableCell>
                  <TableCell align="center" sx={{ border: '1px solid rgba(224, 224, 224, 1)' }}>
                  {(transaction.toAccount!==undefined)&&transaction.toAccount.id}
                  </TableCell>
                  <TableCell align="center" sx={{ border: '1px solid rgba(224, 224, 224, 1)' }}>
                    {transaction.amount}
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      </Paper>
  </Container>
  );
}




