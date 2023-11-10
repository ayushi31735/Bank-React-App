import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { updatePassword } from '../../Service/Customer';
import CustomerNavbar from '../../Shared/customerNavbar/CustomerNavbar';

const EditUser = () => {

    const navigate=new useNavigate();

    const [userid,setUserid] = useState()
    const [username,setUsername] = useState()
    const [password,setPassword] = useState()

    let storeduserid = localStorage.getItem("userid")
    let storedusername = localStorage.getItem("username")
    let storedpassword = localStorage.getItem("password")


    const setData = () => {
        setUserid(storeduserid)
        setUsername(storedusername)
        setPassword(storedpassword)
    }
   
    console.log("Data--->",storeduserid,setUsername,setPassword)
    console.log("New --->", userid,username,password)

    useEffect(()=>{
        console.log("UseEffect")
        setData()
    },[])

    const handleMySubmit = async () => {
        try {
            let response = await updatePassword(userid,username,password)
            if(response.data == "Password Updated Successfully")
            {
                alert("Password Updated Successfully")
                navigate('/')
            }
        } catch (error) {
            alert(error.response.data.message)
        }
    }

  return (
    <>

        <div>
            <CustomerNavbar />
        </div>
    <div className='u-login'>
        <div class="card">
            <div class="card-header">
                <h2 align="center"><b>FUTURE BANK</b></h2>
                <h4 align="center">Edit Password</h4>
            </div>
            <div class="card-body">
                <form>
                    <div className="mb-3">
                        <label for="exampleInputEmail1" className="form-label">Username</label>
                        <input type="text" disabled={true} className="form-control" aria-describedby="emailHelp" value={username}
                            onChange={(e) => {
                            setUsername(e.target.value)
                        }}/>
                    </div>
                    <div className="mb-3">
                        <label for="exampleInputPassword1" className="form-label">Password</label>
                        <input type="password" className="form-control" 
                            onChange={(e) => {
                            setPassword(e.target.value)
                        }}/>
                    </div>
                    <button type="submit" className="btn btn-primary" onClick={handleMySubmit}>Submit</button>
                </form>
            </div>
        </div>
    </div>
    </>
  )
}

export default EditUser