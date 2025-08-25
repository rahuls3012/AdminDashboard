import { useState } from "react"
import "./register.css"
import { data, Navigate } from "react-router-dom";
import axios from 'axios';
import { useNavigate,Link } from 'react-router-dom';
import { BASE_URL } from "../config.js";

function Login(){
  const navigate=useNavigate();
  const [user, setUser] = useState({ mail: '', password: '' });
  const handleChange = (e) => {
    setUser({ ...user, [e.target.name]: e.target.value });
  };
  const handleLogin = async (e) => {
  e.preventDefault();
  try {
    const response = await axios.post(`${BASE_URL}/auth/login`, user);
    const token = response.data.token;
    localStorage.setItem('token', token);
    axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
    console.log('Login successful! Token stored in localStorage.',token);
    navigate("/userlist");
  } catch (error) {
    alert('Login failed: ' + (error.response.data || error.message));
  }
};

   return(
     <>
     <form className="registerform "onSubmit={handleLogin}>
   <div className="mb-3 " >
     <label for="exampleInputEmail1" className="mailform-label">Email address</label>
    <input type="email" onChange={handleChange}  className="form-control" name="mail" aria-describedby="emailHelp"/>
  </div>
  <div className="mb-3">
    <label for="exampleInputPassword1" className="form-label">Password</label>
    <input type="password" onChange={handleChange} className="form-control" name="password"/>
  </div>
  <div className="d-flex justify-content-between">
  <button  type="submit" className="me-1 btn btn-primary">Login</button>
 
  <Link to={"/login"}><button className="btn btn-secondary" id="registerbtn">Register</button></Link>
  
  </div>
  
  
</form>
   </>

   
)
}
export default Login