import React from "react";

export function Element({ close, element, setElement, remove, save }) {
  function handleChange({ target }) {
    element[target.name] = target.value;
    setElement({
      exist: element.exist,
      number: element.number,
      symbol: element.symbol,
      name: element.name,
      weight: element.weight
    });
  }

  const disabled = {};
  if (element.exist) disabled.disabled = "disabled";

  return (
    <>
      <h1>Element</h1>
      <form
        onSubmit={event => {
          event.preventDefault();
          save(element);
        }}
      >
        <label>
          Number
          <input
            {...disabled}
            name="number"
            type="number"
            value={element.number || ""}
            onChange={handleChange}
            required="required"
          />
        </label>
        <label>
          Symbol
          <input
            name="symbol"
            type="text"
            value={element.symbol || ""}
            onChange={handleChange}
            required="required"
          />
        </label>
        <label>
          Name
          <input
            name="name"
            type="text"
            value={element.name || ""}
            onChange={handleChange}
            required="required"
          />
        </label>
        <label>
          Weight
          <input
            name="weight"
            type="number"
            value={element.weight || ""}
            onChange={handleChange}
            required="required"
          />
        </label>
        <div className="buttons">
          {element.exist && (
            <button
              className="tertiary"
              type="button"
              onClick={() => remove(element.number)}
            >
              Remove
            </button>
          )}
          <button className="secondary" type="button" onClick={close}>
            Close
          </button>
          <button type="submit">Save</button>
        </div>
      </form>
    </>
  );
}
