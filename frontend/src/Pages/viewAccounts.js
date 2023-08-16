import React from 'react';
import { Link } from 'react-router-dom';
import Button from '@mui/material/Button';

function ViewAccounts() {
  return (
    <div>
      <Link to="/AddBeneficiary">
        <Button variant = "outlined">View Accounts</Button>
      </Link>
    </div>
  );
}

export default ViewAccounts;
