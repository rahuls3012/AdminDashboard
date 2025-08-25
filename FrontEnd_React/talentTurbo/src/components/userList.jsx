import axios from "axios";
import { useEffect,useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { BASE_URL } from "../config";


function UserList() {
    const [users, setUsers] = useState([]);
    const token=localStorage.getItem('token');
    const navigate=useNavigate();
    //console.log(BASE_URL);
useEffect(() => {
   axios.get(`${BASE_URL}/ace/showuser`,
    {
        headers:{
            Authorization:`Bearer ${token}`,
        },
} )
.then(res=>{console.log(res.data);
    setUsers(res.data);
})
.catch(err=>console.log(err));
  }, []);
  const handleDelete = (id) => {
    const confirmDelete = window.confirm("Are you sure you want to delete this user?");
    if (confirmDelete) {
      axios.delete(`${BASE_URL}/ace/delete?id=${id}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then(res => {
        console.log(res.data);
        setUsers(users.filter(user => user.id !== id));
        navigate("/userlist");

      })
      .catch(err => console.log(err));
    }
  };
  return (
    <div className="d-flex flex-column align-items-center  bg-light vh-100">
        <h2 >User List</h2>
        <div className="w-75 rounded bg-white border shadow p-4">
        <div className="d-flex justify-content-end">
            <Link to="/adduser" className="btn btn-success">Add User</Link>
        </div>
        <table className="table">
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Mail</th>
                <th>Mobile</th>
            </tr>
            </thead>
            <tbody>
            {users.map((user) => (
                <tr key={user.id}>
                <td>{user.id}</td>
                <td>{user.name}</td>
                <td>{user.mail}</td>
                <td>{user.mobile}</td>
                <td><Link to={`/updateuser/${user.id}`} className="btn btn-sm btn-primary">update</Link></td>
                <td><button onClick={() => handleDelete(user.id)} className="btn btn-sm btn-danger">delete</button></td>
                </tr>
            ))}
            </tbody>
        </table>
        </div>
    
    </div>
  );
}
export default UserList
