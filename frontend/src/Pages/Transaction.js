import * as React from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import FormControlLabel from '@mui/material/FormControlLabel';
import Checkbox from '@mui/material/Checkbox';
import Link from '@mui/material/Link';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import axios from 'axios';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { useState } from "react";
import FormControl from '@mui/material/FormControl';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import Select from '@mui/material/Select';

const defaultTheme = createTheme();

export default function Transaction() {

    const baseURL = "http://localhost:3000/posts";

    const [amount, setamount] = useState("");
    const [transactionType, settransactionType] = useState("");
    const [id, setid] = useState("");
    const [transactionTimestamp, settransactionTimestamp] = useState("");
    const [fromAccount, setfromAccount] = useState("");
    const [toAccount, settoAccount] = useState("");

    const handleSubmit = (event) => {
        event.preventDefault();

        axios.post(
            baseURL,
            {
                amount: amount,
                transactionType: transactionType,
                id: id,
                transactionTimestamp: transactionTimestamp,
                fromAccount: fromAccount,
                toAccount: toAccount
            }
        )
            .then(
                alert("Transaction Successful")
            )
    };

    //   const client = axios.create({
    //     baseURL: "http://localhost:3308/beneficiary/insert",
    //     headers: {
    //       'Access-Control-Allow-Origin':'*',
    //     }
    //   })



    //     const [errors, setErrors] = React.useState({
    //         beneficiaryAccount:'',
    //       })

    //   const handleSubmit = (event) => {
    //     event.preventDefault();
    //     let newErrors = {
    //         beneficiaryAccount:'',
    //       };
    //         addBeneficiary();

    //   };

    //   const addBeneficiary = async () => {
    //     let body = {
    //         beneficiaryName: beneficiaryName,
    //         beneficiaryAccountNo: beneficiaryAccount,
    //       associatedAccountNo: "2",
    //     };
    //     console.log(body);
    //     let response  = await client.post("",body);
    //     if(response.status === 200 && response.data == "OK"){
    //       toast.success("Beneficiary Added Successfully!");
    //     }
    //     else{
    //       toast.error("Some error occured!")
    //     }
    //     console.log(response)
    //   }

    //   const [beneficiaryName, setBeneficiaryName] = React.useState("");
    //   const [beneficiaryAccount, setBeneficiaryAccount] = React.useState("");

    return (
        <ThemeProvider theme={defaultTheme}>
            <Container component="main" maxWidth="xs">
                <CssBaseline />
                <Box
                    sx={{
                        marginTop: 8,
                        display: 'flex',
                        flexDirection: 'column',
                        alignItems: 'center',
                    }}
                >
                    <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
                        <LockOutlinedIcon />
                    </Avatar>
                    <Typography component="h1" variant="h5">
                        Make Transaction
                    </Typography>
                    <ToastContainer />
                    <Box component="form" /*onSubmit={handleSubmit}*/ sx={{ mt: 3 }}>
                        <Grid container spacing={2}>

                            <Grid item xs={12}>
                                <TextField
                                    required
                                    fullWidth
                                    id="amount"
                                    label="Enter Amount"
                                    name="amount"
                                    onChange={e => setamount(e.target.value)}
                                />
                            </Grid>

                            <Grid item xs={12}>
                                {/* <TextField
                                    required
                                    fullWidth
                                    id="transactionType"
                                    label="Transaction Type"
                                    name="transactionType"
                                    onChange={e => settransactionType(e.target.value)}
                                /> */}
                                <FormControl fullWidth>
                                    <InputLabel required id="demo-simple-select-label">Transaction Type</InputLabel>
                                    <Select
                                        labelId="demo-simple-select-label"
                                        id="demo-simple-select"
                                        value={transactionType}
                                        label="Transaction Type"
                                        onChange={(event) => {
                                            settransactionType(event.target.value);
                                        }}
                                    >
                                        <MenuItem value={"NEFT"}>NEFT</MenuItem>
                                        <MenuItem value={"RTGS"}>RTGS</MenuItem>
                                        <MenuItem value={"IMPS"}>IMPS</MenuItem>
                                    </Select>
                                </FormControl>
                            </Grid>

                            <Grid item xs={12}>
                                <TextField
                                    required
                                    fullWidth
                                    id="id"
                                    label="Enter your User ID"
                                    name="id"
                                    onChange={e => setid(e.target.value)}
                                />
                            </Grid>

                            <Grid item xs={12}>
                                <TextField
                                    required
                                    fullWidth
                                    id="transactionTimestamp"
                                    label="Transaction TimeStamp"
                                    name="transactionTimestamp"
                                    onChange={e => settransactionTimestamp(e.target.value)}
                                />
                            </Grid>

                            <Grid item xs={12}>
                                <TextField
                                    required
                                    fullWidth
                                    id="fromAccount"
                                    label="Enter your Account No."
                                    name="fromAccount"
                                    onChange={e => setfromAccount(e.target.value)}
                                />
                            </Grid>

                            <Grid item xs={12}>
                                <TextField
                                    required
                                    fullWidth
                                    id="toAccount"
                                    label="Enter the Account no. of the receiver"
                                    name="toAccount"
                                    onChange={e => settoAccount(e.target.value)}
                                />
                            </Grid>

                        </Grid>
                        <Button
                            type="submit"
                            fullWidth
                            variant="contained"
                            sx={{ mt: 3, mb: 2 }}
                        >
                            Submit
                        </Button>
                    </Box>
                </Box>
            </Container>
        </ThemeProvider>
    );
}