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
import Container from '@mui/material/Container';
import Grid from '@mui/material/Grid';
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


// import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
// import ListItemText from '@mui/material/ListItemText';

// export default function AccountSummary(){
//     return(
//         <Container maxWidth="lg" sx={{ mt: 4, mb: 4 }}>
//                 <Grid container spacing={3}>
//                   {/* Chart */}
//                   {/* <Grid item xs={12} md={8} lg={9}>
//                     <Paper
//                       sx={{
//                         p: 2,
//                         display: 'flex',
//                         flexDirection: 'column',
//                         height: 240,
//                       }}
//                     >
//                       <Chart />
//                     </Paper>
//                   </Grid> */}
//                   {/* Recent Deposits */}
//                   <Grid item xs={12} md={4} lg={3}>
//                     <Paper
//                       sx={{
//                         p: 2,
//                         display: 'flex',
//                         flexDirection: 'column',
//                         height: 180,
//                       }}
//                     >
//                       <Deposits />
//                     </Paper>
//                   </Grid>
//                   {/* Recent Orders */}
//                   <Grid item xs={12}>
//                     <Paper sx={{ p: 2, display: 'flex', flexDirection: 'column' }}>
//                       <Orders />
//                     </Paper>
//                   </Grid>
//                 </Grid>

//               </Container>
//     )
// }


export default function AccountSummary() {
  const [accountDetails] = React.useState({
    accountNo: '1234567890',
    accountBalance: '$5000',
    accountType: 'Savings',
    recentTransactions: [
      'Transaction 1: -$100',
      'Transaction 2: +$300',
      'Transaction 3: -$50',
    ],
  });

  return (
    // <Container maxWidth="sm">
    //   <Typography variant="h4" align="center" mt={2}>
    //     Account Summary
    //   </Typography>
    //   <List>
    //     <ListItem>
    //       <ListItemText primary={`Account No: ${accountDetails.accountNo}`} />
    //     </ListItem>
    //     <Divider />
    //     <ListItem>
    //       <ListItemText primary={`Account Balance: ${accountDetails.accountBalance}`} />
    //     </ListItem>
    //     <Divider />
    //     <ListItem>
    //       <ListItemText primary={`Account Type: ${accountDetails.accountType}`} />
    //     </ListItem>
    //     <Divider />
    //     <ListItem>
    //       <ListItemText primary="Recent Transactions:" />
    //     </ListItem>
    //     {accountDetails.recentTransactions.map((transaction, index) => (
    //       <ListItem key={index}>
    //         <ListItemText primary={transaction} />
    //       </ListItem>
    //     ))}
    //   </List>
    // </Container>
    <Container maxWidth='lg'>
    <Paper elevation={3} sx={{ padding: 3, marginBottom: 3 }}>
      <Typography variant="h4" align="center">
        Account Summary
      </Typography>
    </Paper>
    <Paper elevation={3} sx={{ padding: 3, marginBottom: 3 }}>
      <Typography variant="subtitle1">Account No:</Typography>
      <Typography variant="body1">{accountDetails.accountNo}</Typography>
      <Divider sx={{ marginY: 2 }} />
      <Typography variant="subtitle1">Account Balance:</Typography>
      <Typography variant="body1">{accountDetails.accountBalance}</Typography>
      <Divider sx={{ marginY: 2 }} />
      <Typography variant="subtitle1">Account Type:</Typography>
      <Typography variant="body1">{accountDetails.accountType}</Typography>
    </Paper>
    {/* <Paper elevation={3} sx={{ padding: 3, marginTop: 3 }}>
        <Typography variant="subtitle1">Recent Transactions:</Typography>
        <TableContainer component={Paper}>
          <Table sx={{ minWidth: 650 }}>
            <TableHead>
              <TableRow>
                <TableCell align="center" sx={{ border: '1px solid rgba(224, 224, 224, 1)' }}>
                  From Account
                </TableCell>
                <TableCell align="center" sx={{ border: '1px solid rgba(224, 224, 224, 1)' }}>
                  To Account
                </TableCell>
                <TableCell align="center" sx={{ border: '1px solid rgba(224, 224, 224, 1)' }}>
                  Amount
                </TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {accountDetails.recentTransactions.map((transaction, index) => (
                <TableRow key={index}>
                  <TableCell align="center" sx={{ border: '1px solid rgba(224, 224, 224, 1)' }}>
                    {transaction.fromAccount}
                  </TableCell>
                  <TableCell align="center" sx={{ border: '1px solid rgba(224, 224, 224, 1)' }}>
                    {transaction.toAccount}
                  </TableCell>
                  <TableCell align="center" sx={{ border: '1px solid rgba(224, 224, 224, 1)' }}>
                    {transaction.amount}
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      </Paper> */}

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
                    {transaction.fromAccount}
                  </TableCell>
                  <TableCell align="center" sx={{ border: '1px solid rgba(224, 224, 224, 1)' }}>
                    {transaction.toAccount}
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




