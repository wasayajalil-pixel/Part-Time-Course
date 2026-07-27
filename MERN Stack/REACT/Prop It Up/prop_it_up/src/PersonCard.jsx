function PersonCard(props) {
  return (
    <div
      style={{
        border: "2px solid black",
        padding: "15px",
        margin: "10px",
        width: "300px",
        textAlign:"center"
      }}
    >
      <h2>
        {props.firstName} {props.lastName}
      </h2>

      <p>Age: {props.age}</p>

      <p>Hair Color: {props.hairColor}</p>
    </div>
  );
}

export default PersonCard;