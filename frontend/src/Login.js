import React, { useState } from "react";
import { save } from "./service";

export function Login({ setError, setToken }) {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  function handleSubmit(event) {
    event.preventDefault();
    save(
      null,
      "login",
      json => {
        setToken(json.token);
      },
      setError,
      { username, password }
    );
  }

  return (
    <form onSubmit={handleSubmit}>
      <label>
        Username
        <input
          name="username"
          onChange={event => setUsername(event.target.value)}
          required="required"
          type="text"
          value={username}
        />
      </label>
      <label>
        Password
        <input
          name="password"
          onChange={event => setPassword(event.target.value)}
          required="required"
          type="password"
          value={password}
        />
      </label>
      <div className="buttons">
        <button type="submit">Log in</button>
      </div>
    </form>
  );
}
