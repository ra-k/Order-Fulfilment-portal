//import { Container } from "reactstrap";
import Base from "../components/Base";
import img1 from  '../images/order.jpg';
const Home=()=>{
    return (

        // <Base>
        //      <Container>
        //         <h1>ORDER FULLFILLMENT PORTEL</h1>
        //         <p></p>
        //         </Container> 
        // </Base>



      <Base>
        <div className="container-fluid" >
          <img className="img-fluid" src={img1} alt=""/>
        </div>
        </Base>
    );

};



       




export default Home;

