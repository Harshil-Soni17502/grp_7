import React from 'react';
import { Link } from 'react-router-dom';
import Button from '@mui/material/Button';

function AbButton() {
  return (
    <div>
      <Link to="/AddBeneficiary">
        <Button variant = "outlined">Add Beneficiary</Button>
      </Link>
    </div>
  );
}

export default AbButton;
