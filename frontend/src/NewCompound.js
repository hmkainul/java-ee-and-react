import React from "react";
import { Formula } from "./Formula";

export function NewCompound({ clear, compound, select }) {
  return (
    <div className="compound">
      {compound.parts && (
        <>
          <button type="button" onClick={select}>
            {compound.exist ? "Edit" : "Create"}
          </button>
          <button type="button" className="secondary" onClick={clear}>
            Clear
          </button>
          <p className="big">
            <Formula compound={compound} />
          </p>
        </>
      )}
    </div>
  );
}
