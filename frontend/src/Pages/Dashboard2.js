import * as React from 'react';
import ArticleIcon from '@mui/icons-material/Article';
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
import AccountSummary from './AccountSummary';
import AddBeneficiary from './AddBeneficiary';
import OpenAccount from './OpenAccount';
import CallReceivedIcon from '@mui/icons-material/CallReceived';
import Withdraw from '../Withdraw';
import ViewStatment from './ViewStatement';
const drawerWidth = 240;

const AppBar = MuiAppBar;


// TODO remove, this demo shouldn't need to reset the theme.
const defaultTheme = createTheme();

export default function Dashboard2() {
  const [open, setOpen] = React.useState(true);

  const [account, setAccount] = React.useState('');
  const [accounts, setAccounts] = React.useState(JSON.parse(localStorage.getItem("accounts")));

  const [selectedPageNo, setSelectedPageNo ] = React.useState(1)

  const handleChangeAccount = (event) => {
      setAccount(event.target.value);
    };


  return (
    <div className="page-container">
      <div >
        <ThemeProvider theme={defaultTheme}>
          <Box sx={{ display: 'flex' }}>
            <CssBaseline />
            <AppBar position="absolute" 
            open={open}
            >
              <Toolbar
              sx={{
                pr: 'px', // keep right padding when drawer closed
              }}
              >
                {/* <IconButton
                  edge="start"
                  color="inherit"
                  aria-label="open drawer"
                  onClick={toggleDrawer}
                  sx={{
                    marginRight: '36px',
                    ...(open && { display: 'none' }),
                  }}
                >
                  {/* <MenuIcon />
                </IconButton> */}
                <Typography
                  component="h1"
                  variant="h6"
                  color="inherit"
                  noWrap
                  sx={{ flexGrow: 1 , paddingLeft:"50px"}}
                >
                  WELLS FARGO ONLINE 
                </Typography>
                <FormControl sx={{width:"200px", marginRight:"10px"}}>
                <InputLabel id="demo-simple-select-label">Select account</InputLabel>
                <Select
                    labelId="demo-simple-select-label"
                    id="demo-simple-select"
                    value={account}
                    label="Account"
                    onChange={handleChangeAccount}
                    sx={{ height:"50px"}}
                >
                  {accounts.map((eachAccount,index)=>(
                    <MenuItem key={eachAccount.id} value={eachAccount.id}>
                      {eachAccount.id}
                    </MenuItem>
                  ))}
                </Select>
                </FormControl>
              </Toolbar>
            </AppBar>
            {/* <Drawer variant="permanent" open={open}> */}
              <Toolbar
                sx={{
                  display: 'flex',
                  alignItems: 'center',
                  justifyContent: 'flex-end',
                  px: [1],
                }}
              >
              
              </Toolbar>
              <Divider />
              <List component="nav" sx={{paddingTop: "70px", width:"280px", paddingLeft:"0px"}}>
              <Link to="#" style={{ color: 'black', textDecoration: 'none' , width:"150px"}}>
                  <ListItemButton variant="outlined" disabled={(account === "")} onClick={() => setSelectedPageNo(1)}>
                    <ListItemIcon>
                      <AccountBalanceIcon />
                    </ListItemIcon>
                    <ListItemText primary="User Home" />
                  </ListItemButton>
                </Link>

                <Link to="#" style={{ color: 'black', textDecoration: 'none', width:"150px" }}>
                  <ListItemButton variant="outlined" disabled={(account === "")} onClick={() => setSelectedPageNo(2)}>
                    <ListItemIcon>
                      <ArticleIcon />
                    </ListItemIcon>
                    <ListItemText primary="View Statement" />
                  </ListItemButton>
                </Link>

                <Link to="#" style={{ color: 'black', textDecoration: 'none', width:"150px" }}>
                  <ListItemButton variant="outlined" disabled={(account === "")} onClick={() => setSelectedPageNo(3)}>
                    <ListItemIcon>
                      <BarChartIcon />
                    </ListItemIcon>
                    <ListItemText primary="Make Transaction" />
                  </ListItemButton>
                </Link>

                <Link to="#" style={{ color: 'black', textDecoration: 'none', width:"150px" }}>
                  <ListItemButton variant="outlined" disabled={(account === "")} onClick={() => setSelectedPageNo(4)}>
                    <ListItemIcon>
                      <CallReceivedIcon />
                    </ListItemIcon>
                    <ListItemText primary="Withdraw" />
                  </ListItemButton>
                </Link>

                <Link to="#" style={{ color: 'black', textDecoration: 'none' , width:"150px", margin:"0px" }}>
                  <ListItemButton variant="outlined" disabled={(account === "")} onClick={() => setSelectedPageNo(5)}>
                    <ListItemIcon>
                      <PeopleIcon />
                    </ListItemIcon>
                    <ListItemText primary="Add Beneficiary" />
                  </ListItemButton>
                </Link>

                <Link to="#" style={{ color: 'black', textDecoration: 'none' , width:"150px"}}>
                  <ListItemButton variant="outlined" onClick={() => setSelectedPageNo(6)}>
                    <ListItemIcon>
                      <DashboardIcon />
                    </ListItemIcon>
                    <ListItemText primary="Create Account" />
                  </ListItemButton>
                </Link>

                {/* <AbButton /> <br />
                  <MakeTransaction /> <br />
                  <UserHome /> <br />
                  <ViewAccounts /> */}
              </List>
            {/* </Drawer> */}
            <Box
              component="main"
              sx={{
                backgroundColor: (theme) =>
                  theme.palette.mode === 'light'
                    ? theme.palette.grey[100]
                    : theme.palette.grey[900],
                flexGrow: 1,
                height: '100vh',
                overflow: 'auto',
              }}
            >
              <Toolbar />
              <Container maxWidth="lg" sx={{ mt: 4, mb: 4 }}>
                
                {
                    selectedPageNo===1 &&
                    <AccountSummary/>
                }
                {
                    selectedPageNo===2 &&
                    <MakeTransaction/>
                }

{
                    selectedPageNo===3 &&
                    <ViewStatment/>
                }
{
                    selectedPageNo===4 &&
                    <Withdraw/>
                }
{
                    selectedPageNo===5 &&
                    <AddBeneficiary/>
                }

{
                    selectedPageNo===6 &&
                    <OpenAccount/>
                }
                
                
            
        


              </Container>
            </Box>
          </Box>
        </ThemeProvider >
      </div>
      
    </div> 
  );
}

