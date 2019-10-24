import React, { useEffect, useState } from "react";
import { addToCompound } from "./addToCompound";
import { get, remove, save } from "./service";
import { Compound } from "./Compound";
import { Compounds } from "./Compounds";
import { Element } from "./Element";
import { Elements } from "./Elements";
import { NewCompound } from "./NewCompound";
import { PeriodicTable } from "./PeriodicTable";

export function Container({ setError, location, setLocation, token }) {
  const [compound, setCompound] = useState();
  const [compounds, setCompounds] = useState([]);
  const [element, setElement] = useState({});
  const [elements, setElements] = useState([]);
  const [newCompound, setNewCompound] = useState({});
  const [previous, setPrevious] = useState();

  const COMPOUND = "compound";
  const COMPOUNDS = "compounds";
  const ELEMENT = "element";
  const ELEMENTS = "elements";

  useEffect(() => {
    get(token, ELEMENTS, setElements, setError);
    get(token, COMPOUNDS, setCompounds, setError);
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

  function saveCompound(compound) {
    save(
      token,
      COMPOUNDS,
      result => {
        result.exist = true;
        setNewCompound(result);
        setCompounds(compounds.concat(result));
      },
      setError,
      compound
    );
  }

  function removeCompound(id) {
    remove(
      token,
      COMPOUNDS,
      () => {
        setCompounds(compounds.filter(c => c.id !== id));
        setNewCompound({});
        back();
      },
      setError,
      id
    );
  }

  function select(element) {
    if (!element.exist) {
      setElement(element);
      navigate(ELEMENT);
      return;
    }
    setNewCompound(addToCompound(newCompound, element, compounds));
  }

  function editCompound() {
    if (newCompound.single) {
      setElement(newCompound.parts[0].element);
      navigate(ELEMENT);
    } else if (newCompound.exist) {
      setCompound(newCompound);
      navigate(COMPOUND);
    } else {
      saveCompound(newCompound);
    }
  }

  return (
    <>
      {location === ELEMENTS && (
        <div>
          <NewCompound
            clear={() => setNewCompound({})}
            compound={newCompound}
            select={editCompound}
          />
          <Elements elements={elements} select={select} />
        </div>
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
      {location === COMPOUNDS && (
        <Compounds
          compounds={compounds}
          select={compound => {
            setCompound(compound);
            navigate(COMPOUND);
          }}
        />
      )}
      {location === COMPOUND && (
        <Compound
          close={back}
          compound={compound}
          copy={() => {
            setNewCompound(compound);
            navigate(ELEMENTS);
          }}
          remove={removeCompound}
        />
      )}
      {location === "table" && (
        <PeriodicTable
          elements={elements}
          select={element => {
            element.exist = true;
            setElement(element);
            navigate("element");
          }}
        />
      )}
    </>
  );
}
