export function addToCompound(compound, element, compounds) {
  if (isEmpty(compound)) {
    return single(element);
  }
  const result = copy(compound);
  updatePart(result, element);
  const existing = findExisting(result, compounds);
  return existing ? existing : result;
}

function isEmpty(compound) {
  return !compound.parts || compound.parts.length < 1;
}

function single(element) {
  return {
    exist: true,
    name: element.symbol,
    parts: [singlePart(element)],
    single: true
  };
}

function singlePart(element) {
  return {
    element,
    count: 1
  };
}

function copy(compound) {
  return {
    parts: compound.parts.map(part => ({ ...part }))
  };
}

function updatePart(compound, element) {
  const part = findPart(compound, element);
  if (part) {
    part.count += 1;
  } else {
    compound.parts.push(singlePart(element));
    sort(compound);
  }
}

function findPart(compound, element) {
  return compound.parts.find(part => part.element.number === element.number);
}

function sort(compound) {
  let hasCarbon = compound.parts.find(isCarbon);
  compound.parts = compound.parts.sort((a, b) => {
    if (hasCarbon) {
      if (isCarbon(a)) return -1;
      if (isCarbon(b)) return 1;
      if (isHydrogen(a)) return -1;
      if (isHydrogen(b)) return 1;
    }
    if (a.element.symbol < b.element.symbol) return -1;
    return 1;
  });
}

function isCarbon(part) {
  return part.element.symbol === "C";
}

function isHydrogen(part) {
  return part.element.symbol === "H";
}

function findExisting(compound, compounds) {
  const existing = compounds.find(
    otherCompound => name(otherCompound) === name(compound)
  );
  if (existing) {
    existing.exist = true;
  }
  return existing;
}

function name(compound) {
  if (!compound.name) {
    sort(compound);
    compound.name = compound.parts
      .map(part => part.element.symbol + part.count)
      .join("");
  }
  return compound.name;
}
