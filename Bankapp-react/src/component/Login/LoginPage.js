import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Login from '../../Service/Login';
import './LoginPage.css';

const LoginPage = () => {

    const [password,setPassword] = useState("")
    const[username,setUsername] = useState("")
    const[valid, setValid] = useState(true)
    const navigate=new useNavigate();

    const handleMySubmit = async(e) =>{
        e.preventDefault()

        let response

        if(username ==="")
        {
            alert("Please Enter username")
            navigate('/')
        }
        else if(password ==="")
        {
            alert("Please Enter Password")
            navigate('/')
        }
        else{
            response = await Login(username,password)
            console.log("Response Login--->",response)

            if(response.data)
            {
                if(response.headers['auth'] == "Bad credentials")
                {
                    setValid(false)
                    alert("Invalid Credentials")
                }
                // else
                // {
                //     setValid("valid")
                // }
            }

            console.log("Valid--->",valid)
            if(valid == false)
            {
                navigate('/')
            }

            if(response.data)
            {
                localStorage.setItem('authentication',response.headers['auth'])
                localStorage.setItem('username',response.data.username)
                localStorage.setItem('userid',response.data.userid)
                localStorage.setItem('password',response.data.password)

                if(response.data.roles[0].rolename  == "ROLE_ADMIN"){
                    navigate(`/adminDashboard/${response.data.username}`)
                }
                if(response.data.roles[0].rolename=='ROLE_USER'){
                    navigate(`/userDashboard/${response.data.username}`)
                }
    
            }
        }
    }


  return (
    <>
    <div className='u-login'>
        <div class="card">
            <div class="card-header">
                <h2 align="center"><b>FUTURE BANK</b></h2>
                <h4 align="center">User Login</h4>
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
                        <input type="password" className="form-control" 
                            onChange={(e) => {
                            setPassword(e.target.value)
                        }}/>
                    </div>
                    <button type="submit" className="btn btn-primary" onClick={handleMySubmit}>Submit</button>
                </form>
            </div>
            <div class="card-footer text-body-secondary">
                Not Registered? <a href="/customerRegisteration">Register Here</a>
            </div>
        </div>
    </div>
    </>
  )
}

export default LoginPage