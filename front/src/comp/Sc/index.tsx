import { useEffect, useRef, useState } from "react";

const Sc = ({ data, row, renderItem, rowh }: any) => {
  const [counter, setCounter] = useState(0);
  const timer = useRef(0);
  const scroll = data.length > row;
  const num = rowh.replace(/[a-z]+/g, "");
  const px = rowh.replace(num, "");
  const t = Math.ceil(Math.random() * 1000) + 3500;
  useEffect(() => {
    if (scroll) {
      timer.current = setInterval(() => setCounter((prev) => prev - 1), t);
      return () => clearInterval(timer.current);
    }
  }, [scroll]);
  const begin = () => {
    if (!scroll) return;
    timer.current = setInterval(() => setCounter((prev) => prev - 1), t);
  };
  const stop = () => clearInterval(timer.current);
  useEffect(() => {
    if (counter <= -1 - data.length + row) setCounter(0);
  }, [counter]);
  return (
    <div className="relative w-[100%] h-[100%] overflow-hidden">
      <div
        onMouseEnter={stop}
        onMouseLeave={begin}
        className="transition-all ease-in-out delay-150 duration-500 absolute w-[100%] h-[100%]"
        style={{ top: counter * num + px }}
      >
        {data.map((item: any, index: any) => {
          return <div key={index}>{renderItem(item, index + 1)}</div>;
        })}
      </div>
    </div>
  );
};

export default Sc;
