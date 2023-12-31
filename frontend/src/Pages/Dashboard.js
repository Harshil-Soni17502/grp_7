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

//   return (
//     <div className="page-container">
//       <div className={`content ${isOptionSelected ? 'visible' : 'hidden'}`}>
//         <ThemeProvider theme={defaultTheme}>
//           <Box sx={{ display: 'flex' }}>
//             <CssBaseline />
//             <AppBar position="absolute" 
//             open={open}
//             >
//               <Toolbar
//               sx={{
//                 pr: 'px', // keep right padding when drawer closed
//               }}
//               >
//                 <IconButton
//                   edge="start"
//                   color="inherit"
//                   aria-label="open drawer"
//                   onClick={toggleDrawer}
//                   sx={{
//                     marginRight: '36px',
//                     ...(open && { display: 'none' }),
//                   }}
//                 >
//                   <MenuIcon />
//                 </IconButton>
//                 <Typography
//                   component="h1"
//                   variant="h6"
//                   color="inherit"
//                   noWrap
//                   sx={{ flexGrow: 1 }}
//                 >
//                   Dashboard
//                 </Typography>
//                 <IconButton color="inherit">
//                   <Badge badgeContent={4} color="secondary">
//                     <NotificationsIcon />
//                   </Badge>
//                 </IconButton>
//               </Toolbar>
//             </AppBar>
//             <Drawer variant="permanent" open={open}>
//               <Toolbar
//                 sx={{
//                   display: 'flex',
//                   alignItems: 'center',
//                   justifyContent: 'flex-end',
//                   px: [1],
//                 }}
//               >
//                 <IconButton onClick={toggleDrawer}>
//                   <ChevronLeftIcon />
//                 </IconButton>
//               </Toolbar>
//               <Divider />
//               <List component="nav">
//                 {/* <SelectButton> <br /> */}
//                 <Link to="/AddBeneficiary" style={{ color: 'black', textDecoration: 'none' }}>
//                   <ListItemButton variant="outlined">
//                     <ListItemIcon>
//                       <PeopleIcon />
//                     </ListItemIcon>
//                     <ListItemText primary="Add Beneficiary" />
//                   </ListItemButton>
//                 </Link>

//                 <Link to="/Transaction" style={{ color: 'black', textDecoration: 'none' }}>
//                   <ListItemButton variant="outlined">
//                     <ListItemIcon>
//                       <BarChartIcon />
//                     </ListItemIcon>
//                     <ListItemText primary="Make Transaction" />
//                   </ListItemButton>
//                 </Link>

//                 <Link to="/Transaction" style={{ color: 'black', textDecoration: 'none' }}>
//                   <ListItemButton variant="outlined">
//                     <ListItemIcon>
//                       <DashboardIcon />
//                     </ListItemIcon>
//                     <ListItemText primary="User Home" />
//                   </ListItemButton>
//                 </Link>

//                 {/* <AbButton /> <br />
//                   <MakeTransaction /> <br />
//                   <UserHome /> <br />
//                   <ViewAccounts /> */}
//               </List>
//             </Drawer>
//             <Box
//               component="main"
//               sx={{
//                 backgroundColor: (theme) =>
//                   theme.palette.mode === 'light'
//                     ? theme.palette.grey[100]
//                     : theme.palette.grey[900],
//                 flexGrow: 1,
//                 height: '100vh',
//                 overflow: 'auto',
//               }}
//             >
//               <Toolbar />
//               <Container maxWidth="lg" sx={{ mt: 4, mb: 4 }}>
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
//             </Box>
//           </Box>
//         </ThemeProvider >
//       </div>
//       <div className={`overlay ${isOptionSelected ? 'hidden' : ''}`} />
//       {/* <select value={selectedOption} onChange={(event) => { setSelectedOption(event.target.value) }}>
//         <option value="">Select an option</option>
//         <option value="option1">Option 1</option>
//         <option value="option2">Option 2</option>
//         <option value="option3">Option 3</option>
//       </select> */}
//       <FormControl fullWidth>
//         <InputLabel required id="demo-simple-select-label">Select your Account #</InputLabel>
//         <Select
//           labelId="demo-simple-select-label"
//           id="demo-simple-select"
//           value={selectedOption}
//           label="Select your account"
//           onChange={(event) => {
//             setSelectedOption(event.target.value);
//           }}
//         >
// <<<<<<< HEAD
//           <Toolbar />
//           <Container maxWidth="lg" sx={{ mt: 4, mb: 4 }}>
//             <Grid container spacing={3}>
//               {/* Chart */}
//               {/* <Grid item xs={12} md={8} lg={9}>
//                 <Paper
//                   sx={{
//                     p: 2,
//                     display: 'flex',
//                     flexDirection: 'column',
//                     height: 240,
//                   }}
//                 >
//                   <Chart />
//                 </Paper>
//               </Grid> */}
//               {/* Recent Deposits */}
//               <Grid item xs={12} md={4} lg={3}>
//                 <Paper
//                   sx={{
//                     p: 2,
//                     display: 'flex',
//                     flexDirection: 'column',
//                     height: 180,
//                   }}
//                 >
//                   <Deposits />
//                 </Paper>
//               </Grid>
//               {/* Recent Orders */}
//               <Grid item xs={12}>
//                 <Paper sx={{ p: 2, display: 'flex', flexDirection: 'column' }}>
//                   <AddBeneficiary />
//                 </Paper>
//               </Grid>
//             </Grid>

//           </Container>
//         </Box>
//       </Box>
//     </ThemeProvider>
// =======
//           <MenuItem value={"Ac1"}>Account #1</MenuItem>
//           <MenuItem value={"Ac2"}>Account #2</MenuItem>
//           <MenuItem value={"Ac3"}>Account #3</MenuItem>
//         </Select>
//       </FormControl>
//     </div>
// >>>>>>> db3c05ba5535fb0be58ccf5f0949ffc14986e375
//   );
// }
