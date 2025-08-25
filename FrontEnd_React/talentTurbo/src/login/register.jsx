import { useState } from "react"
import "./register.css"
import { data } from "react-router-dom";
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { BASE_URL } from "../config.js";

const Register = () => {
  
  const [user, setUser] = useState({ mail: '', password: '' });
  const navigate = useNavigate();

  const handleChange = (e) => {
    setUser({ ...user, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.post(`${BASE_URL}/auth/register`, user,{
        headers: {
          'Content-Type': 'application/json'
        }
      });
      alert('Registration successful!');
      navigate('/');
    } catch (err) {
      const errorMessage=(err.response.data);
      alert('Registration failed: ' + errorMessage);
      console.log(user)
      console.log(errorMessage);
    }
  };

   return(
     <>
     <form className="registerform "onSubmit={handleSubmit}>
   <div className="mb-3 " >
     <label for="exampleInputEmail1" className="mailform-label">Email address</label>
    <input type="email"name="mail" onChange={handleChange}  className="form-control" id="InputEmail1" aria-describedby="emailHelp"/>
  </div>
  <div className="mb-3">
    <label for="exampleInputPassword1" className="form-label">Password</label>
    <input type="password"name="password" onChange={handleChange} className="form-control" id="InputPassword1"/>
  </div>
  <button  type="submit" className="me-1 btn btn-primary">Register</button>
  <label htmlFor=""> for new account</label>
  
  
</form>
   </>

   
)
}
export default Register