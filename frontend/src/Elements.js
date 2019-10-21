import React from "react";

export function Elements({ elements, select }) {
  return (
    <div>
      {elements.map(element => {
        return (
          <button
            type="button"
            className="element"
            key={element.number}
            onClick={() => {
              element.exist = true;
              select(element);
            }}
          >
            {element.symbol}
          </button>
        );
      })}
      <button
        type="button"
        className="plus"
        onClick={() => select({ exist: false })}
      >
        +
      </button>
    </div>
  );
}
