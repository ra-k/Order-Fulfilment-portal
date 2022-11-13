import React from "react";
//import { Dropdown } from 'reactstrap'
import Base from "../../components/Base";
import { toast } from "react-toastify";

// const Upload = ()=>{

//   return (

//     <Base>
//   <div>
//     <hi> upload the xml file</hi>
//   </div>

//     </Base>
//   )
// }
//export default Upload;

import axios from "axios";
import { Component } from "react";
class Upload extends Component {
  constructor() {
    super();
    this.state = {
      file: "",
      account: "",
      employee: {},
    };
  }

  handleFileChange = (e) => {
    this.setState({
      [e.target.name]: e.target.files[0],
    });
  };

  handleSubmit = async (e) => {
    e.preventDefault();

    const formData = new FormData();
    for (let name in this.state) {
      formData.append(name, this.state[name]);
    }

    axios
      .post("http://localhost:8081/api/v1/order/upload-file", formData, {
        headers: { Authorization: `Bearer ${localStorage.getItem("token")}` },
      })
      .then((response) => {
        //update the state with the response data
        console.log(localStorage.getItem("token"));

        console.log(response);

        this.setState({
          employee: response.data,

          //result: response.data
          //this.props.push
        });
        toast.success("uploaded ...");
      })
      .catch((err) => {
        console.log(err);
        this.setState({
          error: (
            <div className="alert alert-warning" style={{ marginTop: "5%" }}>
              Please upload the correct xml file
            </div>
          ),
        });
        if (err.response.status == 400) toast.error(err.response.data.message);
        else if (err.response.status == 500) toast.error(err.response.data);
      });
  };

  render() {
    return (
      <Base>
        <form
          onSubmit={this.handleSubmit}
          encType="multipart/form-data"
          method="POST"
        >
           <div className="container">
          <input
          className="form-control mt-4"
            name="file"
            type="file"
            accept=".xml"
            onChange={this.handleFileChange}
          ></input>
          <button type="submit" className="btn btn-primary mt-2">Upload</button >
          
          <div class="form-row mt-5" >
            <div class="form-group col-md-6">
              <label for="id">ID</label>
              <input
              readOnly
                type="text"
                value={this.state.employee.id}
                class="form-control"
                id="id"
              
              />
            </div>
            <div class="form-group col-md-6">
              <label for="account">Account</label>
              <input
              readOnly
                type="text"
                value={this.state.employee.account}
                class="form-control"
                id="account"
               
              />
            </div>
          </div>
          <div class="form-group col-md-6">
            <label for="due_date">Due Date</label>
            <input
            readOnly
              type="text"
              value={this.state.employee.due_date}
              class="form-control"
              id="due_date"
              
            />
          </div>

          
          </div>
        </form>
      </Base>
    );
  }
}

export default Upload;
