import axios from "axios";
import { useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import { BASE_URL } from "../config";

function UpdateUser(){
    const token=localStorage.getItem('token');
    const [data,setData]=useState({});
    const {id}=useParams();
    const navigate=useNavigate();
    console.log((`${BASE_URL}/ace/getuser?id=${id}`));
    useEffect(()=>{
        axios.get(`${BASE_URL}/ace/getuser?id=${id}`,{
            headers:{
                Authorization:`Bearer ${token}`,
            },
        })
        .then(res=>{setData(res.data)
            console.log(res.data);
        })
        .catch(err=>console.log(err));
    }, []);
    const handleChange=(e)=>{
         setData({ ...data, [e.target.name]: e.target.value });
    };
    const handleSubmit=(e)=>{
        e.preventDefault();
        axios.put(`${BASE_URL}/ace/update?id=${id}`, data, {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        })
        .then(res => {
            console.log(res.data);
            navigate("/userlist");
        })
        .catch(err => console.log(err));
    };
    return (
        <div className="my-5 d-flex w-100 flex-column align-items-center  bg-light vh-100">
            <div className="shadow px-5 pt-3 pb-5 rounded bg-yellow w-50">
            <h2>Update User</h2>
            <form onSubmit={handleSubmit} className="w-50 mx-auto">
                <div className="mb-3">
                    <label htmlFor="name" className="form-label">Name</label>
                    <input type="text" className="form-control"value={data.name} name="name" onChange={handleChange} />
                </div>
                <div className="mb-3">
                    <label htmlFor="email" className="form-label">Email</label>
                    <input type="email" className="form-control" value={data.mail} name="mail" onChange={handleChange} />
                </div>
                <div className="mb-3">
                    <label htmlFor="mobile" className="form-label">Mobile</label>
                    <input type="tel" className="form-control" maxLength={10} minLength={10} value={data.mobile} name="mobile" onChange={handleChange} />
                </div>
                <div className="d-flex justify-content-between">
                    <button type="submit" className="btn btn-success">Update User</button>
                    <Link to="/userlist" className="btn btn-primary">Cancel</Link>
                    
                   
                </div>
                
            </form>
            </div>
        </div>
    );
}
export default UpdateUser;
