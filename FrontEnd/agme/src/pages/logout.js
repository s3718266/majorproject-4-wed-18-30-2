import React from 'react';

function Logout() {

    localStorage.removeItem('auth_token');
    window.location = "sign-in";

    // TODO: Send HTTP POST to destroy the auth_token at backend

}

export default Logout;
