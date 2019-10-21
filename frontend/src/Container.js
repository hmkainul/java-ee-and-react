import React, { useEffect, useState } from "react";
import { get, remove, save } from "./service";
import { Element } from "./Element";
import { Elements } from "./Elements";

export function Container({ setError, location, setLocation, token }) {
  const [element, setElement] = useState({});
  const [elements, setElements] = useState([]);
  const [previous, setPrevious] = useState();

  const ELEMENT = "element";
  const ELEMENTS = "elements";

  useEffect(() => {
    get(token, ELEMENTS, setElements, setError);
  }, [token, setError]);

  function navigate(to) {
    setPrevious(location);
    setLocation(to);
  }

  function back() {
    navigate(previous);
  }

  function saveElement(element) {
    save(
      token,
      ELEMENTS,
      result => {
        setElements(
          elements
            .filter(e => e.number !== result.number)
            .concat(result)
            .sort((x, y) => x.number - y.number)
        );
        back();
      },
      setError,
      element
    );
  }

  function removeElement(number) {
    remove(
      token,
      ELEMENTS,
      () => {
        setElements(elements.filter(e => e.number !== number));
        back();
      },
      setError,
      number
    );
  }

  function select(element) {
    setElement(element);
    navigate(ELEMENT);
  }

  return (
    <>
      {location === ELEMENTS && (
        <Elements elements={elements} select={select} />
      )}
      {location === ELEMENT && (
        <Element
          close={back}
          element={element}
          setElement={setElement}
          remove={removeElement}
          save={saveElement}
        />
      )}
    </>
  );
}
