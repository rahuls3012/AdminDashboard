import { Link, useNavigate } from "react-router-dom";
import { useState } from "react";
import axios from "axios";
import { BASE_URL } from "../config";
function AddUser() {
    const navigate = useNavigate();
    const [formData, setFormData] = useState({
        name: "",
        mail: "",
        mobile: ""
    });
    const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };
  const handleSubmit=(e)=>{
    e.preventDefault();
    axios.post(`${BASE_URL}/ace/adduser`,formData,
    {
        headers:{
            Authorization:`Bearer ${localStorage.getItem('token')}`,
        },
    })
    .then(res=>{
        alert("User added successfully");
        navigate("/userlist");
    })
    .catch(err=>{
        alert("Error adding user");
    });
  }

    return (
        <div className="my-5 d-flex w-100 flex-column align-items-center  bg-light vh-100">
            <div className="shadow px-5 pt-3 pb-5 rounded bg-yellow w-50">
            <h2>Add User</h2>
            <form onSubmit={handleSubmit} className="w-50 mx-auto">
                <div className="mb-3">
                    <label htmlFor="name" className="form-label">Name</label>
                    <input type="text" className="form-control" name="name" onChange={handleChange} />
                </div>
                <div className="mb-3">
                    <label htmlFor="email" className="form-label">Email</label>
                    <input type="email" className="form-control" name="mail" onChange={handleChange} />
                </div>
                <div className="mb-3">
                    <label htmlFor="mobile" className="form-label">Mobile</label>
                    <input type="text" className="form-control" name="mobile" onChange={handleChange} />
                </div>
                <div className="d-flex justify-content-between">
                    <button type="submit" className="btn btn-success">Add User</button>
                    <Link to="" className="btn btn-primary">Cancel</Link>
                   
                </div>
                
            </form>
            </div>
        </div>
    );
}
export default AddUser
