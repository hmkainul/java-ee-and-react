import React from "react";

export function Formula({ compound }) {
  return (
    <span>
      {compound.parts &&
        compound.parts.map(({ element, count }) => {
          return (
            <React.Fragment key={element.symbol}>
              {element.symbol}
              {count > 1 && <sub style={{ fontSize: "smaller" }}>{count}</sub>}
            </React.Fragment>
          );
        })}
    </span>
  );
}
