import React from 'react';
import { Link } from 'react-router-dom';
import Button from '@mui/material/Button';

function UserHome() {
  return (
    <div>
      <Link to="/AddBeneficiary">
        <Button variant = "outlined">User Home</Button>
      </Link>
    </div>
  );
}

export default UserHome;
