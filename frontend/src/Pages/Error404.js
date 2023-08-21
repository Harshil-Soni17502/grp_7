import React from 'react';
import Container from '@mui/material/Container';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import ErrorOutlineIcon from '@mui/icons-material/ErrorOutline';


function Error404 () {
  return(
    <Container maxWidth="lg">
    <Box
      display="flex"
      flexDirection="column"
      alignItems="center"
      justifyContent="center"
      height="100vh"
    >
      <ErrorOutlineIcon color="error" fontSize="large" />
      <Typography variant="h4" align="center" color="textSecondary" mt={2}>
        Oops! Page Not Found
      </Typography>
      <Typography variant="body1" align="center" color="textSecondary" mt={1}>
        The page you're looking for doesn't exist.
      </Typography>
      <Button variant="contained" sx={{margin:"20px"}} color="primary" href="/">
        Return to Home
      </Button>
    </Box>
  </Container>
  );
  }

  export default Error404;