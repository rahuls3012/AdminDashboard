import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import Register from './login/register'
import {createBrowserRouter,RouterProvider} from "react-router-dom"
import Navbar from './components/navbar'
import Login from './login/login'
import UserList from './components/userList'
import AddUser from './components/adduser'
import UpdateUser from './components/updateuser'

function App() {
 const router=createBrowserRouter([
 {
 path:"/",
 element:<><Navbar/>
 <Login></Login></>
 },
 {
  path:"/login",
  element:<>
  <Navbar></Navbar>
 <Register/>
  </>
 },
 {
  path:"/userlist",
  element:<>
  <Navbar/>
  <UserList/>
  </>
 },
 {
  path:"/adduser",
  element:<>
  <Navbar/>
  <AddUser/>
  </>
 },
 {
  path:"/updateuser/:id",
  element:<>
  <Navbar/>
  <UpdateUser/>
  </>
 }
 ]);
 return( <>
 <RouterProvider router={router} />
  </>
  );
}

export default App
