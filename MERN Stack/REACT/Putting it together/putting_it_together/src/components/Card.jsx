import { useState } from "react";

const Card =(props) => {
    //create Age State
    const [age,setAge] = useState(props.age);
    //update the age by one
    const increaseAge = () => {setAge(age+1)};

  return (
    <div>
      <h3>
        {props.lastName}
        {props.firstName}
      </h3>
      <p>Age : {age}</p>
      <p>hair Color: {props.hairColor}</p>
      <button onClick={increaseAge}>Birthday Button for {props.firstName} {props.lastName} </button>
    </div>
  );
}
export default Card