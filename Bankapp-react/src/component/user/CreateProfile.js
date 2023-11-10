import React, { useState } from 'react'
import { addCustomer } from '../../Service/Customer'
import CustomerNavbar from '../../Shared/customerNavbar/CustomerNavbar'
import './CreateProfile.css'

const CreateProfile = () => {

    const[firstName, setFirstName] = useState("")
    const[lastName, setLastName] = useState()
    const[email, SetEmail] = useState()
    const[contact, setContact] = useState("")

    let userid = localStorage.getItem('userid')

    const handleMySubmit =  async (e) => {
        e.preventDefault()
    
        if(firstName === "")
        {
            alert("Please Enter FirstName")
        }
        if(contact === "")
        {
            alert("Please Enter Contact Number")
        }
        if(userid === "")
        {
            alert("Please Enter User Id")
        }

        try {
            
            let response = await addCustomer(firstName,lastName,email,contact,userid)
            if(response)
            {
                console.log("Customer",response.data)
                localStorage.setItem('customerid',response.data.customerid)
                // alert("Customer Added Successfully")
            }
        } catch (error) {
            console.log("Error Message--->",error)
            alert(error.message)
        }
    
    }

  return (
    <>
    <div className='create-profile'>
        <div>
            <CustomerNavbar />
        </div>

        <div className='profile-form'>
            <form>
                <div className='addcust'>
                    <h4 align="center">Profile Details</h4>
                    <div className="firstname">
                        <label className="form-label">First Name</label>
                        <input type="text" className="form-control" aria-describedby="emailHelp"
                            onChange={(e) => {
                            setFirstName(e.target.value)
                        }}/>
                    </div>
                    <div className="lastname">
                        <label className="form-label">Last Name</label>
                        <input type="text" className="form-control" aria-describedby="emailHelp"
                            onChange={(e) => {
                            setLastName(e.target.value)
                        }}/>
                    </div>
                    <div className="email">
                        <label className="form-label">Email Id</label>
                        <input type="text" className="form-control" aria-describedby="emailHelp"
                            onChange={(e) => {
                            SetEmail(e.target.value)
                        }}/>
                    </div>
                    <div className="contact">
                        <label className="form-label">Contact Number</label>
                        <input type="text" className="form-control" aria-describedby="emailHelp"
                            onChange={(e) => {
                            setContact(e.target.value)
                        }}/>
                    </div>
                    <div className="userid">
                        <label className="form-label">User Id</label>
                        <input type="text" disabled={true} className="form-control" aria-describedby="emailHelp" value={userid}/>
                    </div><br />
                    <button type="submit" className="btn btn-primary" onClick={handleMySubmit}>Submit</button>
                </div>
            </form>
        </div>
    </div>
    </>
  )
}

export default CreateProfile