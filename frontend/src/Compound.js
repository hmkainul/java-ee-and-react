import React from "react";
import { Formula } from "./Formula";

export function Compound({ close, compound, copy, remove }) {
  return (
    <>
      <h1>Compound</h1>
      <p>
        <Formula compound={compound} />
      </p>
      <div className="buttons">
        <button
          className="tertiary"
          type="button"
          onClick={() => remove(compound.id)}
        >
          Remove
        </button>
        <button className="secondary" type="button" onClick={close}>
          Close
        </button>
        <button type="button" onClick={copy}>
          Copy
        </button>
      </div>
    </>
  );
}
