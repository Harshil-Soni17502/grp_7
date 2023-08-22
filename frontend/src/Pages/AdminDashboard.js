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
import ViewStatment from './ViewStatement';
import DoneIcon from '@mui/icons-material/Done';
import SearchIcon from '@mui/icons-material/Search';
import PersonIcon from '@mui/icons-material/Person';
import AdminApprovalPage from './AdminApprovePage';
import AdminViewUserPage from './AdminViewUserPage';
import AdminViewAccountPage from './AdminViewAccountPage';
const drawerWidth = 240;

const AppBar = MuiAppBar;


// TODO remove, this demo shouldn't need to reset the theme.
const defaultTheme = createTheme();

export default function Dashboard2() {
  const [open, setOpen] = React.useState(true);

  const [account, setAccount] = React.useState('');

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
                  WELLS FARGO ONLINE - ADMIN 
                </Typography>
                
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
              <List component="nav" sx={{paddingTop: "70px", width:"300px", paddingLeft:"0px"}}>
              <Link to="#" style={{ color: 'black', textDecoration: 'none' , width:"150px"}}>
                  <ListItemButton variant="outlined" onClick={() => setSelectedPageNo(1)}>
                    <ListItemIcon>
                      <DoneIcon />
                    </ListItemIcon>
                    <ListItemText primary="Approve Accounts" />
                  </ListItemButton>
                </Link>
                
                <Link to="#" style={{ color: 'black', textDecoration: 'none', width:"150px" }}>
                  <ListItemButton variant="outlined"  onClick={() => setSelectedPageNo(2)}>
                    <ListItemIcon>
                      <PersonIcon />
                    </ListItemIcon>
                    <ListItemText primary="View Users" />
                  </ListItemButton>
                </Link>

                <Link to="#" style={{ color: 'black', textDecoration: 'none', width:"150px" }}>
                  <ListItemButton variant="outlined"  onClick={() => setSelectedPageNo(3)}>
                    <ListItemIcon>
                      <SearchIcon />
                    </ListItemIcon>
                    <ListItemText primary="View Accounts" />
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
                    <AdminApprovalPage/>
                }
                {
                    selectedPageNo===2 &&
                    <AdminViewUserPage/>
                }

{
                    selectedPageNo===3 &&
                    <AdminViewAccountPage/>
                }

                
            
        


              </Container>
            </Box>
          </Box>
        </ThemeProvider >
      </div>
      
    </div> 
  );
}

