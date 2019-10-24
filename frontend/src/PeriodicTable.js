import React from "react";

export function PeriodicTable({ elements, select }) {
  return (
    <table>
      <Header />
      <tbody>
        {rows().map((row, index) => {
          return (
            <React.Fragment key={"row" + index}>
              <Line
                elements={elements}
                index={index}
                row={row}
                select={select}
              />
              {(index === 6 || index === 7) && <EmptyLine />}
            </React.Fragment>
          );
        })}
      </tbody>
    </table>
  );
}

function rows() {
  return [...Array(118)]
    .map((_, i) => i + 1)
    .sort((a, b) => sortNumber(a) - sortNumber(b))
    .reduce(splitToRows, []);
}

const left = new Set([1, 3, 11, 19, 37, 55, 87, 58, 90]);

function sortNumber(n) {
  if ((58 <= n && n <= 71) || (90 <= n && n <= 103)) {
    n += 118;
  }
  return n;
}

function splitToRows(total, current) {
  if (left.has(current)) {
    total.push([current]);
  } else {
    total[total.length - 1].push(current);
  }
  return total;
}

function Header() {
  return (
    <thead>
      <tr>
        {[...Array(18)].map((_, i) => (
          <td key={"group" + i}>{i + 1}</td>
        ))}
      </tr>
    </thead>
  );
}

function Line({ elements, index, row, select }) {
  const isBottomLines = index === 7 || index === 8;
  return (
    <tr key={index}>
      {isBottomLines && <Empty colSpan="3" />}
      {row.map(i => {
        const element = elements.find(e => e.number === i);
        return (
          <React.Fragment key={"k" + i}>
            <Cell element={element} select={select} />
            {i === 1 && <EmptyBlock />}
          </React.Fragment>
        );
      })}
      {isBottomLines && <Empty />}
    </tr>
  );
}

function Cell({ element, select }) {
  return element ? (
    <td
      className="element"
      onClick={() => select(element)}
      onMouseDown={event => event.preventDefault()}
    >
      {element.symbol}
    </td>
  ) : (
    <td>?</td>
  );
}

function EmptyBlock() {
  return (
    <>
      <Empty />
      <Empty colSpan="7" rowSpan="3" />
      <Empty colSpan="3" rowSpan="3" />
      <Empty colSpan="5" />
    </>
  );
}

function Empty(props) {
  return <td className="empty" {...props} />;
}

function EmptyLine() {
  return (
    <tr>
      <Empty colSpan="18" />
    </tr>
  );
}
