// import React, { useState } from 'react';
// import {
//   Container,
//   Typography,
//   TextField,
//   Table,
//   TableBody,
//   TableCell,
//   TableContainer,
//   TableHead,
//   TableRow,
//   Paper,
//   Grid,
 
//   Button,
// } from '@mui/material';
// import SearchIcon from '@mui/icons-material/Search';




// const sampleUserData = [
//     {
//       id: 1,
//       displayName: 'John Doe',
//       email: 'john@example.com',
//       accounts: [
//         { accountId: 101, balance: 1000, type: 'Savings', status: 'Active' },
//         { accountId: 102, balance: 1500, type: 'Checking', status: 'Active' },
//       ],
//     },
//     {
//       id: 2,
//       displayName: 'Jane Smith',
//       email: 'jane@example.com',
//       accounts: [
//         { accountId: 201, balance: 2000, type: 'Savings', status: 'Active' },
//       ],
//     },
//     // Add more user data as needed
//   ];

// const AdminViewUserPage = () => {
//     const [searchEmail, setSearchEmail] = useState('');
//     const [userData, setUserData] = useState(null);
  
//     const handleSearch = () => {
//       const foundUser = sampleUserData.find(user => user.email === searchEmail);
//       setUserData(foundUser);
//     };
  
//     return (
//       <Container maxWidth="lg">
//         <Paper elevation={3} sx={{ padding: 3, margin:"3", textAlign:'center' }}>
//         <Typography variant="h4" gutterBottom>
//           User Details
//         </Typography>
//         </Paper>
//         <Paper elevation={3} sx={{ padding: 3, marginTop: 3, display:"flex", justifyContent:"center", alignItems:"center"  }}>
//         <Grid container>
//             <Grid item xs={12}>
//             <TextField
//           label="Search User Email"
//           value={searchEmail}
//           onChange={(e) => setSearchEmail(e.target.value)}
//           fullWidth
//           margin="normal"
//         />
//             </Grid>
//             <Grid item xs={12} sx={{display:"flex", justifyContent:"center",alignItems:"center"}}>
//             <Button
//             variant='contained'
//             color='success'
//         onClick={handleSearch}
//         startIcon={<SearchIcon />}
//       >
//         Search
//       </Button>
//             </Grid>
        
//         </Grid>
        
//         </Paper>
       

//         {userData && (
//           <div>
//              <Paper elevation={3} sx={{ padding: 3, marginTop: 3}}>
//             <Typography variant="h4" gutterBottom sx={{ textAlign:'center', fontWeight:'bold'}}>
//               {userData.displayName}
//             </Typography>
//             <Typography variant="subtitle1" gutterBottom sx={{ textAlign:'center', marginBottom:'30px'}}>
//               {userData.email}
//             </Typography>
//             <TableContainer>
//               <Table>
//                 <TableHead>
//                   <TableRow>
//                     <TableCell>Account ID</TableCell>
//                     <TableCell>Account Balance</TableCell>
//                     <TableCell>Account Type</TableCell>
//                     <TableCell>Account Status</TableCell>
//                   </TableRow>
//                 </TableHead>
//                 <TableBody>
//                   {userData.accounts.map(account => (
//                     <TableRow key={account.accountId}>
//                       <TableCell>{account.accountId}</TableCell>
//                       <TableCell>${account.balance}</TableCell>
//                       <TableCell>{account.type}</TableCell>
//                       <TableCell>{account.status}</TableCell>
//                     </TableRow>
//                   ))}
//                 </TableBody>
//               </Table>
//             </TableContainer>
//             </Paper>
//           </div>
          
//         )}
        
//       </Container>
//     );

// };

// export default AdminViewUserPage;

import React, { useState } from 'react';
import {
  Container,
  Typography,
  TextField,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
  Grid,
  Button,
} from '@mui/material';
import SearchIcon from '@mui/icons-material/Search';
import axios from 'axios';
// const sampleUserData = [
//   {
//     id: 1,
//     displayName: 'John Doe',
//     email: 'john@example.com',
//     mobileNumber: '123-456-7890',
//     aadharNumber: '1234-5678-9012',
//     permanentAddress: '123 Main St, City',
//     residentialAddress: '456 Elm St, Town',
//     occupation: 'Engineer',
//     totalGrossIncome: '$80,000',
//     dateOfBirth: '1990-05-15',
//     accounts: [
//       { accountId: 101, balance: 1000, type: 'Savings', status: 'Active' },
//       { accountId: 102, balance: 1500, type: 'Checking', status: 'Active' },
//     ],
//   },
//   // Add more user data as needed
// ];

const AdminViewUserPage = () => {

  const client = axios.create({
    baseURL: "http://localhost:3308/admin/getUserDetails",
    headers: {
      'Access-Control-Allow-Origin':'*',
    }
  })

  const [searchEmail, setSearchEmail] = useState('');
  const [userData, setUserData] = useState(null);
  const [errors, setErrors] = useState({
    email:'',
  });

  const handleSearch = () => {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    const validSearchEmail = emailRegex.test(searchEmail);
    let newError = {
      email: '',
    };
    if(!validSearchEmail){
      newError.email = 'Not a valid email';
      setErrors({...newError});
    }
    else{
      client.post("",{email:searchEmail}).then(
        response=>{
          if(response.status===200){
            const foundUserData = response.data
            setUserData(foundUserData)
          }
        }
      )

      // const foundUser = sampleUserData.find(user => user.email === searchEmail);
      // setUserData(foundUser);
    }
  };

  return (
    <Container maxWidth="lg">
          <Paper elevation={3} sx={{ padding: 3, marginTop: 3, textAlign:'center' }}>
        <Typography variant="h4" gutterBottom>
          View User Details
        </Typography>
      {/* </Paper>
      <Paper elevation={3} sx={{ padding: 3, marginTop: 3, display: "flex", justifyContent: "center", alignItems: "center" }}> */}
        <Grid container>
          <Grid item xs={12}>
            <TextField
              label="Search User Email"
              value={searchEmail}
              onChange={(e) => setSearchEmail(e.target.value)}
              error={!!errors.email}
              fullWidth
              margin="normal"
            />
          </Grid>
          <Grid item xs={12} sx={{ display: "flex", justifyContent: "center", alignItems: "center" }}>
            <Button
              variant='contained'
              color='success'
              onClick={handleSearch}
              startIcon={<SearchIcon />}
            >
              Search
            </Button>
          </Grid>
        </Grid>
      </Paper>

      {userData && (
        <div>

          <Paper elevation={3} sx={{ padding: 3, marginTop: 3 }}>
            <Typography variant="h5" gutterBottom sx={{ fontWeight: 'bold', textAlign:'center', margin:'30px' }}>
              Personal Information
            </Typography>
            <TableContainer>
              <Table>
                <TableBody>
                <TableRow>
                    <TableCell><strong>Name:</strong></TableCell>
                    <TableCell>{userData.userName}</TableCell>
                  </TableRow>
                  <TableRow>
                    <TableCell><strong>Email:</strong></TableCell>
                    <TableCell>{userData.email}</TableCell>
                  </TableRow>
                  <TableRow>
                    <TableCell><strong>Mobile Number:</strong></TableCell>
                    <TableCell>{userData.mobileNumber}</TableCell>
                  </TableRow>
                  <TableRow>
                    <TableCell><strong>Aadhar Number:</strong></TableCell>
                    <TableCell>{userData.aadharNumber}</TableCell>
                  </TableRow>
                  <TableRow>
                    <TableCell><strong>Permanent Address:</strong></TableCell>
                    <TableCell>{userData.permanentAddress}</TableCell>
                  </TableRow>
                  <TableRow>
                    <TableCell><strong>Residential Address:</strong></TableCell>
                    <TableCell>{userData.residentialAddress}</TableCell>
                  </TableRow>
                  <TableRow>
                    <TableCell><strong>Occupation:</strong></TableCell>
                    <TableCell>{userData.occupation}</TableCell>
                  </TableRow>
                  <TableRow>
                    <TableCell><strong>Total Gross Income:</strong></TableCell>
                    <TableCell>{userData.totalGrossIncome}</TableCell>
                  </TableRow>
                  <TableRow>
                    <TableCell><strong>Date of Birth:</strong></TableCell>
                    <TableCell>{userData.dateOfBirth}</TableCell>
                  </TableRow>
                </TableBody>
              </Table>
            </TableContainer>
          </Paper>

          <Paper elevation={3} sx={{ padding: 3, marginTop: 3 }}>
            <Typography variant="h5" gutterBottom  sx={{ fontWeight: 'bold', textAlign:'center', margin:'20px' }}>
              Accounts
            </Typography>
            <TableContainer>
              <Table>
                <TableHead>
                  <TableRow>
                    <TableCell>Account ID</TableCell>
                    <TableCell>Account Balance</TableCell>
                    <TableCell>Account Type</TableCell>
                    <TableCell>Active</TableCell>
                  </TableRow>
                </TableHead>
                <TableBody>
                  {userData.account.map(account => (
                    <TableRow key={account.id}>
                      <TableCell>{account.id}</TableCell>
                      <TableCell>${account.balance}</TableCell>
                      <TableCell>{account.accountType}</TableCell>
                      <TableCell>{(account.isApproved)&&"Yes"}</TableCell>
                    </TableRow>
                  ))}
                </TableBody>
              </Table>
            </TableContainer>
          </Paper>
        </div>
      )}
    </Container>
  );

};

export default AdminViewUserPage;
