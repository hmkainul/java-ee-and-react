import React from "react";
import { Formula } from "./Formula";

export function Compounds({ compounds, select }) {
  return (
    <ul>
      {compounds.map(compound => {
        return (
          <li key={compound.name}>
            <a
              href={"#compounds/" + compound.name}
              onClick={event => {
                event.preventDefault();
                select(compound);
              }}
            >
              <Formula compound={compound} />
            </a>
          </li>
        );
      })}
    </ul>
  );
}
