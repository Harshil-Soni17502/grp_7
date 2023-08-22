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

const sampleAccountData = [{
    accountNo: '123456789',
    accountType: 'Savings',
    balance: 2500,
    ownerEmail: 'john@example.com',
    transactions: [
      { transactionId: 1, fromAccount: '987654321', toAccount: '123456789', amount: 500 },
      { transactionId: 2, fromAccount: '555555555', toAccount: '123456789', amount: 1000 },
      { transactionId: 3, fromAccount: '123456789', toAccount: '444444444', amount: 700 },
    ],
  },];

const AdminViewAccountPage = () => {
  const [searchAccount, setSearchAccount] = useState('');
  const [accountData, setAccountData] = useState(null);
  const [errors, setErrors] = useState({
    accountNo:'',
  });

  const handleSearch = () => {
    const numberRegex = /^\d+$/;
    const validAccountNo = numberRegex.test(searchAccount);
    let newError = {
      accountNo:'',
    };
    if(!validAccountNo){
      newError.accountNo = 'Account no. is not valid';
      setErrors(newError);
    }
    else{
      const foundAccount = sampleAccountData.find(account => account.accountNo === searchAccount);
      setAccountData(foundAccount);
    }
  };

  return (
    <Container maxWidth="lg">
          <Paper elevation={3} sx={{ padding: 3, marginTop: 3, textAlign:'center' }}>
        <Typography variant="h4" gutterBottom>
          View Account Details
        </Typography>
      {/* </Paper>
      <Paper elevation={3} sx={{ padding: 3, marginTop: 3, display: "flex", justifyContent: "center", alignItems: "center" }}> */}
        <Grid container>
          <Grid item xs={12}>
            <TextField
              label="Account No"
              value={searchAccount}
              onChange={(e) => setSearchAccount(e.target.value)}
              error={!!errors.accountNo}
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

      {accountData && (
        <div>

          <Paper elevation={3} sx={{ padding: 3, marginTop: 3 }}>
            <Typography variant="h5" gutterBottom sx={{ fontWeight: 'bold', textAlign:'center', margin:'30px' }}>
              Account Information
            </Typography>
            <TableContainer>
              <Table>
                <TableBody>
                <TableRow>
                    <TableCell><strong>Account no:</strong></TableCell>
                    <TableCell>{accountData.accountNo}</TableCell>
                  </TableRow>
                  <TableRow>
                    <TableCell><strong>Account type:</strong></TableCell>
                    <TableCell>{accountData.accountType}</TableCell>
                  </TableRow>
                  <TableRow>
                    <TableCell><strong>Balance:</strong></TableCell>
                    <TableCell>{accountData.balance}</TableCell>
                  </TableRow>
                  <TableRow>
                    <TableCell><strong>Owner Email:</strong></TableCell>
                    <TableCell>{accountData.ownerEmail}</TableCell>
                  </TableRow>
                </TableBody>
              </Table>
            </TableContainer>
          </Paper>

          <Paper elevation={3} sx={{ padding: 3, marginTop: 3 }}>
            <Typography variant="h5" gutterBottom  sx={{ fontWeight: 'bold', textAlign:'center', margin:'20px' }}>
              Transactions
            </Typography>
            <TableContainer>
              <Table>
                <TableHead>
                  <TableRow>
                    <TableCell>From Account</TableCell>
                    <TableCell>To Account</TableCell>
                    <TableCell>Amount</TableCell>
                  </TableRow>
                </TableHead>
                <TableBody>
                  {accountData.transactions.map(transaction => (
                    <TableRow key={transaction.transactionId}>
                      <TableCell>{transaction.fromAccount}</TableCell>
                      <TableCell>{transaction.toAccount}</TableCell>
                      <TableCell>{transaction.amount}</TableCell>
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

export default AdminViewAccountPage;
