import React, { useState } from "react";
import "./App.css";
import { Container } from "./Container";
import { Login } from "./Login";
import { NavigationBar } from "./NavigationBar";

function App() {
  const [error, setError] = useState();
  const [location, setLocation] = useState();
  const [token, setToken] = useState();

  return (
    <div className="container">
      {token && (
        <NavigationBar
          location={location}
          setLocation={setLocation}
          logout={() => setToken(null)}
        />
      )}
      {error && (
        <div className="alert" onClick={() => setError(null)}>
          <span className="close">&times;</span>
          {error.message}
        </div>
      )}
      <div className="container-body">
        {token ? (
          <Container
            setError={setError}
            location={location}
            setLocation={setLocation}
            token={token}
          />
        ) : (
          <Login
            setError={setError}
            setToken={token => {
              setToken(token);
              setLocation("elements");
            }}
          />
        )}
      </div>
    </div>
  );
}

export default App;
