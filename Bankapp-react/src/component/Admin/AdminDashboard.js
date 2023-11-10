import React from 'react';
import { useParams } from 'react-router-dom';
import Navbar from '../../Shared/Navbar/Navbar';
import './AdminDashboard.css';

const AdminDashboard = () => {

  const params = useParams()
  console.log("Params--->",params)

  return (
    <>
        <div className='admindashboard'>
            <div>
                <Navbar />
            </div>
            <div className='dashboard'>
                <h1>WELCOME ADMIN! {params.username}</h1>
            </div>
        </div>
    </>
  )
}

export default AdminDashboard