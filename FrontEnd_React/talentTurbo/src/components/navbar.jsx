import {Link} from "react-router-dom"
function Navbar(){
  return(
    <nav className=" navbar bg-secondary d-flex justify-content-around">
      
       <div><h2 >UserManagementSystem</h2></div>
       <div><button className="btn btn-primary"><Link to="/" className="text-white text-decoration-none">logout</Link></button></div>

    </nav>
  )
}
export default Navbar;