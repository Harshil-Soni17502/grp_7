import React from 'react';
import { Link } from 'react-router-dom';
import Button from '@mui/material/Button';

function MakeTransaction() {
  return (
    <div>
      <Link to="/Transaction">
        <Button variant = "outlined">Make Transaction</Button>
      </Link>
    </div>
  );
}

export default MakeTransaction;
