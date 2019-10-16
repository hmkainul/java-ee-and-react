import React from "react";

export function NavigationBar({ location, setLocation, logout }) {
  const items = [
    { id: "elements", text: "Elements" },
    { id: "table", text: "Table" },
    { id: "compounds", text: "Compounds" }
  ];
  return (
    <nav>
      <ul>
        {items.map(({ id, text }) => {
          const styles = {};
          if (id === location) {
            styles.className = "active";
          }
          return (
            <li key={id}>
              <a
                href={"#" + id}
                onClick={event => {
                  event.preventDefault();
                  setLocation(id);
                }}
                {...styles}
              >
                {text}
              </a>
            </li>
          );
        })}
        <li style={{ float: "right" }}>
          <a
            href="#logout"
            onClick={event => {
              event.preventDefault();
              logout();
            }}
          >
            Log out
          </a>
        </li>
      </ul>
    </nav>
  );
}
