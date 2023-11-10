import axios from 'axios'

export const Login = async (username,password) => {
    let response = await axios.post('http://localhost:8080/api/auth/login' , {
        username:username,
        password:password
    })
    return response
}

export const validateUser = async (token)=>{
    console.log(token)
    let response = await axios.get('http://localhost:8080/api/auth/validate',{
        headers:{
            Authorization :'Bearer ' + token
        }
    })

    console.log(response)
    return response
}

export default Login