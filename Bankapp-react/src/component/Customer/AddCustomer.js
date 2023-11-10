import React, { useState } from 'react'
import { addCustomer } from '../../Service/Customer'
import './AddCustomer.css'

const AddCustomer = () => {

    const[firstName, setFirstName] = useState("")
    const[lastName, setLastName] = useState()
    const[email, SetEmail] = useState()
    const[contact, setContact] = useState("")
    const[userid, setUserid] = useState("")

    const handleMySubmit =  async (e) => {
        e.preventDefault()
    
        if(firstName === "")
        {
            alert("Please Enter FirstName")
            return
        }
        if(contact === "")
        {
            alert("Please Enter Contact Number")
            return
        }

        try {
            let response = await addCustomer(firstName,lastName,email,contact,userid)
            if(response)
            {
                alert("Customer Added Successfully")
            }
        } catch (error) {
            alert(error.response.data.message)
            
        }
    }

  return (
    <form>
        <div className='addcust'>
            <h4 align="center">Add Customer</h4>
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
            <input type="text" className="form-control" aria-describedby="emailHelp"
                onChange={(e) => {
                    setUserid(e.target.value)
                }}/>
        </div><br />
        <button type="submit" className="btn btn-primary" onClick={handleMySubmit}>Submit</button>
        </div>
    </form>
  )
}

export default AddCustomer