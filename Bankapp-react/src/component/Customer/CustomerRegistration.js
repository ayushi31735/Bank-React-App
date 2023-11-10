import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { customerRegisteration } from '../../Service/Customer';
import '.././Login/LoginPage.css';

const CustomerRegistration = () => {

    const [password,setPassword] = useState("")
    const[username,setUsername] = useState("")
    const navigate=new useNavigate();

    const handleMySubmit = async(e) =>{
        e.preventDefault()

        let response

        if(username ==="")
        {
            alert("Please Enter username")
            navigate('/customerRegisteration')
        }
        else if(password ==="")
        {
            alert("Please Enter Password")
            navigate('/customerRegisteration')
        }
        else{
            response = await customerRegisteration(username,password)
            console.log("Response Login--->",response)

            if(response.data)
            {
                alert('Registeration Successful')
                navigate('/')
            }
        }
    }


  return (
    <>
    <div className='customer-registeration'>
        <div class="card">
            <div class="card-header">
                <h2 align="center"><b>APNA BANK</b></h2>
                <h4 align="center">Customer Registeration</h4>
            </div>
            <div class="card-body">
                <form>
                    <div className="mb-3">
                        <label for="exampleInputEmail1" className="form-label">Username</label>
                        <input type="text" className="form-control" aria-describedby="emailHelp"
                            onChange={(e) => {
                            setUsername(e.target.value)
                        }}/>
                    </div>
                    <div className="mb-3">
                        <label for="exampleInputPassword1" className="form-label">Password</label>
                        <input type="text" className="form-control" 
                            onChange={(e) => {
                            setPassword(e.target.value)
                        }}/>
                    </div>
                    <button type="submit" className="btn btn-primary" onClick={handleMySubmit}>Submit</button>
                    {/* <button type="submit" className="btn btn-primary" href={navigate('/')}>Back</button> */}
                </form>
            </div>
        </div>
    </div>
    </>
  )
}

export default CustomerRegistration