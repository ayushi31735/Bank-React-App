import React from 'react'
import { useParams } from 'react-router-dom'
import CustomerNavbar from '../../Shared/customerNavbar/CustomerNavbar'
import './CustomerDashboard.css'

const CustomerDashboard = () => {
  const params = useParams()
  return (
    <>
        <div>
            <div className='navbar'>
                <CustomerNavbar />
            </div>

            <div className='dashboarduser'>
                <h1>WELCOME! {params.username}</h1>
            </div>

        </div>
    </>
  )
}

export default CustomerDashboard