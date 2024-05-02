import React from "react";
import { Navigate } from "react-router-dom";

const PrivateRoute = ({ component: Component, authenticated, ...rest }) =>
  authenticated ? (
    <Component {...rest} />
  ) : (
    <Navigate
      to={{
        pathname: "/login"
      }}
    />
  );

export default PrivateRoute;
