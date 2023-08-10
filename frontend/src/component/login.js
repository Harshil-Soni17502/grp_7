import React from 'react';
import {
    MDBContainer,
    MDBInput,
    MDBCheckbox,
    MDBBtn,
    MDBIcon
  } from 'mdb-react-ui-kit';
import { Router, useNavigate } from 'react-router-dom';

function LoginPage(){

    const navigate = useNavigate();

    const onSignIn = () =>{
        navigate("/home")
    }

    return (
        <MDBContainer className="p-1 my-5 d-flex flex-column justify-content-center w-50" >
    
        <h3 className='text-center mb-4'>
            Online Banking Portal
        </h3>
      <MDBInput wrapperClass='mb-4' label='Email address' id='form1' type='email'/>
      <MDBInput wrapperClass='mb-4' label='Password' id='form2' type='password'/>

      <div className="d-flex justify-content-between mx-3 mb-4">
        <MDBCheckbox name='flexCheck' value='' id='flexCheckDefault' label='Remember me' />
        <a href="!#">Forgot password?</a>
      </div>

      <MDBBtn className="mb-4" onClick={onSignIn}>Sign in</MDBBtn>

    </MDBContainer>
    )
}

export default LoginPage;