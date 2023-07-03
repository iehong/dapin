import "./App.less";
import { useEffect, useRef, useState } from "react";
import Sc from "./comp/Sc";
import CountUp from "react-countup";
import axios from "axios";

const App = () => {
  const [up, setUp] = useState(0);
  const old = useRef(0);
  const [ltop, setLtop] = useState([]);
  const [rtop, setRtop] = useState([]);
  const [lbottom, setLbottom] = useState([]);
  const [mbottom, setMbottom] = useState([]);
  const [rbottom, setRbottom] = useState([]);
  const getTotal = () =>
    axios("/api/total").then((res) => {
      setUp(res.data.data);
    });
  const getAll = () =>
    axios("/api/all").then((res) => {
      if (res.data.data.length <= 14) {
        setLtop(res.data.data.slice(0, 7));
        setRtop(res.data.data.slice(7, 14));
      } else {
        setLtop(res.data.data.slice(0, Math.floor(res.data.data.length / 2)));
        setRtop(
          res.data.data.slice(
            Math.floor(res.data.data.length / 2),
            res.data.data.length
          )
        );
      }
    });
  const getRankHy = () =>
    axios("/api/rankhy").then((res) => setMbottom(res.data.data));
  const getRankLh = () =>
    axios("/api/ranklh").then((res) => setLbottom(res.data.data));
  const getRankJb = () =>
    axios("/api/rankjb").then((res) => setRbottom(res.data.data));
  useEffect(() => {
    getTotal();
    getAll();
    getRankHy();
    getRankLh();
    getRankJb();
    const total = setInterval(() => getTotal(), 1000 * 3600);
    const all = setInterval(() => getAll(), 1000 * 3600 * 1.1);
    const rankhy = setInterval(() => getRankHy(), 1000 * 3600 * 0.8);
    const ranklh = setInterval(() => getRankLh(), 1000 * 3600 * 0.9);
    const rankjb = setInterval(() => getRankJb(), 1000 * 3600 * 0.9);
    return () => {
      clearInterval(total);
      clearInterval(all);
      clearInterval(rankhy);
      clearInterval(ranklh);
      clearInterval(rankjb);
    };
  }, []);
  useEffect(() => {
    old.current = up;
  }, [up]);
  return (
    <div className="h-screen bg-center bg-no-repeat bg-cover relative App overflow-hidden">
      <div className="relative w-[100%] h-[100%] c"></div>
      <div className="absolute w-[30rem] h-[14.7rem] left-[1.65rem] bottom-[27.4rem]">
        <Sc
          data={ltop}
          row={7}
          rowh="2.1rem"
          renderItem={(i: any) => (
            <div className="text-white flex justify-between text-lg leading-[2.1rem]">
              <span className="text-center text-ellipsis w-[8.5rem] ml-1">
                {i[0]}
              </span>
              <span className="text-center w-[8.5rem]">{i[1]}</span>
              <span className="text-center w-[8.5rem] mr-2">{i[2]}</span>
            </div>
          )}
        />
      </div>
      <div className="absolute flex w-[30rem] text-white h-[15rem] left-[35.05rem] bottom-[27.4rem] justify-center items-center">
        <CountUp
          className="text-7xl"
          start={old.current}
          end={up}
          duration={1}
        />
      </div>
      <div className="absolute w-[30rem] h-[14.7rem] right-[1.65rem] bottom-[27.4rem]">
        <Sc
          data={rtop}
          row={7}
          rowh="2.1rem"
          renderItem={(i: any) => (
            <div className="text-white flex justify-between text-lg leading-[2.1rem]">
              <span className="text-center text-ellipsis w-[8.5rem] ml-1">
                {i[0]}
              </span>
              <span className="text-center w-[8.5rem]">{i[1]}</span>
              <span className="text-center w-[8.5rem] mr-2">{i[2]}</span>
            </div>
          )}
        />
      </div>
      <div className="absolute w-[30rem] h-[13.8rem] left-[1.65rem] bottom-[3.8rem]">
        <Sc
          data={lbottom}
          row={6}
          rowh="2.3rem"
          renderItem={(i: any, index: any) => (
            <div
              className="flex justify-between text-lg leading-[2.3rem] font-sans"
              style={{
                color: index < 4 ? "#FFD700" : index > 7 ? "#bfbfbf" : "white",
              }}
            >
              <span className="text-center text-ellipsis w-[6.5rem] ml-1">
                {index}
              </span>
              <span className="text-center truncate w-[8rem] pl-[5px] pr-[5px]">
                {i[0]}
              </span>
              <span className="text-center w-[6rem]">{i[1]}</span>
              <span className="text-center w-[7rem] mr-2">{i[2]}</span>
            </div>
          )}
        />
      </div>
      <div className="absolute w-[30rem] h-[13.8rem] left-[35.05rem] bottom-[3.8rem]">
        <Sc
          data={mbottom}
          row={6}
          rowh="2.3rem"
          renderItem={(i: any, index: any) => (
            <div
              className="flex justify-between text-lg leading-[2.3rem] font-sans"
              style={{
                color: index < 4 ? "#FFD700" : index > 7 ? "#bfbfbf" : "white",
              }}
            >
              <span className="text-center w-[6.5rem] ml-1">{index}</span>
              <span className="text-center truncate w-[8rem] pl-[5px] pr-[5px]">
                {i[0]}
              </span>
              <span className="text-center w-[6rem]">{i[1]}</span>
              <span className="text-center w-[7rem] mr-2">{i[2]}</span>
            </div>
          )}
        />
      </div>
      <div className="absolute w-[30rem] h-[13.8rem] right-[1.65rem] bottom-[3.8rem]">
        <Sc
          data={rbottom}
          row={6}
          rowh="2.3rem"
          renderItem={(i: any, index: any) => (
            <div
              className="flex justify-between text-lg leading-[2.3rem] font-sans"
              style={{
                color: index < 4 ? "#FFD700" : index > 7 ? "#bfbfbf" : "white",
              }}
            >
              <span className="text-center w-[6.5rem] ml-1">{index}</span>
              <span className="text-center truncate w-[8rem] pl-[5px] pr-[5px]">
                {i[0]}
              </span>
              <span className="text-center w-[6rem]">{i[1]}</span>
              <span className="text-center w-[7rem] mr-2">{i[2]}</span>
            </div>
          )}
        />
      </div>
    </div>
  );
};

export default App;
