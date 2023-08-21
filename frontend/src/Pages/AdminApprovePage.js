import React, { useState } from 'react';
import { Container, Typography, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Button } from '@mui/material';
import CheckIcon from '@mui/icons-material/Check';
import ClearIcon from '@mui/icons-material/Clear';
const AdminApprovalPage = () => {
  const [accounts, setAccounts] = useState([
    {
      accountId: 1,
      accountType: 'Savings',
      openingBalance: 1000,
      clientName: 'John Doe',
      clientEmail: 'john@example.com',
      status: 'Pending',
    },
    {
      accountId: 2,
      accountType: 'Checking',
      openingBalance: 1500,
      clientName: 'Jane Smith',
      clientEmail: 'jane@example.com',
      status: 'Pending',
    },
    ,
    {
      accountId: 2,
      accountType: 'Checking',
      openingBalance: 1500,
      clientName: 'Jane Smith',
      clientEmail: 'jane@example.com',
      status: 'Pending',
    },
    ,
    {
      accountId: 2,
      accountType: 'Checking',
      openingBalance: 1500,
      clientName: 'Jane Smith',
      clientEmail: 'jane@example.com',
      status: 'Pending',
    },
    ,
    {
      accountId: 2,
      accountType: 'Checking',
      openingBalance: 1500,
      clientName: 'Jane Smith',
      clientEmail: 'jane@example.com',
      status: 'Pending',
    },
    ,
    {
      accountId: 2,
      accountType: 'Checking',
      openingBalance: 1500,
      clientName: 'Jane Smith',
      clientEmail: 'jane@example.com',
      status: 'Pending',
    },
    ,
    {
      accountId: 2,
      accountType: 'Checking',
      openingBalance: 1500,
      clientName: 'Jane Smith',
      clientEmail: 'jane@example.com',
      status: 'Pending',
    },
    ,
    {
      accountId: 2,
      accountType: 'Checking',
      openingBalance: 1500,
      clientName: 'Jane Smith',
      clientEmail: 'jane@example.com',
      status: 'Pending',
    },
    ,
    {
      accountId: 2,
      accountType: 'Checking',
      openingBalance: 1500,
      clientName: 'Jane Smith',
      clientEmail: 'jane@example.com',
      status: 'Pending',
    },
    ,
    {
      accountId: 2,
      accountType: 'Checking',
      openingBalance: 1500,
      clientName: 'Jane Smith',
      clientEmail: 'jane@example.com',
      status: 'Pending',
    },
    // Add more account objects as needed
  ]);

  const handleApprove = (accountId) => {
    setAccounts(prevAccounts =>
      prevAccounts.map(account =>
        account.accountId === accountId
          ? { ...account, status: 'Approved' }
          : account
      )
    );
  };

  const handleReject = (accountId) => {
    setAccounts(prevAccounts =>
      prevAccounts.map(account =>
        account.accountId === accountId
          ? { ...account, status: 'Rejected' }
          : account
      )
    );
  };

  return (
    <Container maxWidth="lg">
            <Paper elevation={3} sx={{ padding: 3, marginTop: 3 }}>

      <Typography variant="h4" style={{margin:"20px", textAlign:'center'}} gutterBottom>
        Admin Account Approval
      </Typography>
      <TableContainer component={Paper}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>Account ID</TableCell>
              <TableCell>Account Type</TableCell>
              <TableCell>Opening Balance</TableCell>
              <TableCell>Client Name</TableCell>
              <TableCell>Client Email</TableCell>
              <TableCell>Status</TableCell>
              <TableCell>Action</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {accounts.map(account => (
              <TableRow key={account.accountId}>
                <TableCell>{account.accountId}</TableCell>
                <TableCell>{account.accountType}</TableCell>
                <TableCell>${account.openingBalance}</TableCell>
                <TableCell>{account.clientName}</TableCell>
                <TableCell>{account.clientEmail}</TableCell>
                <TableCell>{account.status}</TableCell>
                <TableCell>
                  {account.status === 'Pending' && (
                    <>
                      <Button
                      style={{
                        backgroundColor:"green",
                        paddingRight: '0',
                        margin: '5px',
                        // marginLeft: '8px',
                        paddingLeft: '10px',
                        
                      }}
                        variant="contained"
                        // color="Green"
                        startIcon={<CheckIcon />}
                        onClick={() => handleApprove(account.accountId)}
                      />
                       
                      
                      <Button
                      style={{
                        backgroundColor:"red",
                        
                        margin: '5px',
                        // marginLeft: '8px',
                        paddingRight: '0',
                        paddingLeft: '10px',
                      }}
                        variant="contained"
                        color="error"
                        startIcon={<ClearIcon />}
                        onClick={() => handleReject(account.accountId)}/>
                      
                    </>
                  )}
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
      </Paper>
    </Container>
  );
};

export default AdminApprovalPage;