import React, { useState, useEffect } from 'react';
import { Container, Typography, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Button } from '@mui/material';
import CheckIcon from '@mui/icons-material/Check';
import ClearIcon from '@mui/icons-material/Clear';
import axios from 'axios';
import { toast, ToastContainer } from 'react-toastify';
const AdminApprovalPage = () => {

  const client = axios.create({
    baseURL: "http://localhost:3308/admin",
    headers: {
      'Access-Control-Allow-Origin':'*',
      'Authorization': "Bearer " + localStorage.getItem('jwtToken')
    }
  })

  const [accounts, setAccounts] = useState([
    // {
    //   accountId: 1,
    //   accountType: 'Savings',
    //   openingBalance: 1000,
    //   clientName: 'John Doe',
    //   clientEmail: 'john@example.com',
    //   status: 'Pending',
    // },
    // {
    //   accountId: 2,
    //   accountType: 'Checking',
    //   openingBalance: 1500,
    //   clientName: 'Jane Smith',
    //   clientEmail: 'jane@example.com',
    //   status: 'Pending',
    // },

    // Add more account objects as needed
  ]);

  useEffect(()=>{
    client.get("/getPendingAccounts",{}).then(
      response => {
        if(response.status===200){
          setAccounts(response.data);
        }
      }
    )
  },[])

  const handleApprove = (accountId) => {
    client.post("/approveAccount",{id:accountId}).then(
      response => {
        if(response.status===200){
          toast.success("approved")
          // setAccounts(prevAccounts =>
          //   prevAccounts.map(account =>
          //     account.accountId === accountId
          //       ? { ...account, isApproved: 'Approved' }
          //       : account
          //   )
          // );
          setAccounts((prevAccounts) =>
          prevAccounts.filter((account) => account.id !== accountId)
        );
          

        }
      }
    )


    
  };

  const handleReject = (accountId) => {
    // setAccounts(prevAccounts =>
    //   prevAccounts.map(account =>
    //     account.accountId === accountId
    //       ? { ...account, isApproved: 'Rejected' }
    //       : account
    //   )
    // );
    toast.success("rejected")
    setAccounts((prevAccounts) =>
          prevAccounts.filter((account) => account.id !== accountId)
        );
  };

  return (
    <Container maxWidth="lg">
            <Paper elevation={3} sx={{ padding: 3, marginTop: 3 }}>
      <ToastContainer/>
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
              <TableCell>Active?</TableCell>
              <TableCell>Action</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {accounts.map(account => (
              <TableRow key={account.id}>
                <TableCell>{account.id}</TableCell>
                <TableCell>{account.accountType}</TableCell>
                <TableCell>{account.openingBalance}$</TableCell>
                <TableCell>{account.name}</TableCell>
                <TableCell>{account.email}</TableCell>
                <TableCell>{account.isApproved}</TableCell>
                <TableCell>
                  {
                  //!account.isApproved  && 
                  (
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
                        onClick={() => handleApprove(account.id)}
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
                        onClick={() => handleReject(account.id)}/>
                      
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