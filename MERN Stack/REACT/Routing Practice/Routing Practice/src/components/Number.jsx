import { useParams } from "react-router-dom";

const Number = ()=> {
    const {num} = useParams();
    return (
        <div>
            <h1>The Number is : {num}</h1>
        </div>
    )

}
export default Number;