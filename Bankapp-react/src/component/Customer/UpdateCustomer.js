import React, { useEffect, useState } from 'react'
import { UserId, updateCustomer } from '../../Service/Customer'

const UpdateCustomer = ({value}) => {

    const[firstName, setFirstName] = useState(value.firstname)
    const[lastName, setLastName] = useState(value.lastname)
    const[email, SetEmail] = useState(value.email)
    const[contact, setContact] = useState(value.contact)
    const[userid, setUserid] = useState("")
    
    console.log("Customer--->",value)

    let customerid = value.customerid

    const handleMySubmit = () => {
        try {
            let response = updateCustomer(customerid,firstName,lastName,email,contact,userid)
            if(response)
            {
                alert('Customer Updated Successfully')
                return
            }
        } catch (error) {
            alert(error.response.data.message)
        }

        }

    const getUserId = async (customerid) => {
        try {
            let response = await UserId(customerid)
            console.log("Response--->",response)
            if(response)
            {
                setUserid(response.data)
            }
        } catch (error) {
            alert(error.response.data.message)
        }
    }

    console.log("UserId--->",userid)
    useEffect (() => {
        getUserId(customerid)
    },[value])

  return (
    <form>
        <div className='addcust'>
            <h4 align="center">Update Customer</h4>
        <div className="firstname">
            <label className="form-label">First Name</label>
            <input type="text" className="form-control" aria-describedby="emailHelp" value={firstName}
                onChange={(e) => {
                    setFirstName(e.target.value)
                }}/>
        </div>
        <div className="lastname">
            <label className="form-label">Last Name</label>
            <input type="text" className="form-control" aria-describedby="emailHelp" value={lastName}
            onChange={(e) => {
                setLastName(e.target.value)
            }}/>
        </div>
        <div className="email">
            <label className="form-label">Email Id</label>
            <input type="text" className="form-control" aria-describedby="emailHelp" value={email}
            onChange={(e) => {
                SetEmail(e.target.value)
            }}/>
        </div>
        <div className="contact">
            <label className="form-label">Contact Number</label>
            <input type="text" className="form-control" aria-describedby="emailHelp" value={contact}
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
  )
}

export default UpdateCustomer