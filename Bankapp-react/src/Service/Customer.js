import axios from "axios"

export const getAllCustomers = async (pageNumber,pageSize) => {
    try {
        let response = await axios.get("http://localhost:8080/bankapp/customers/customersdto" , {
        params: {
            pageSize: pageSize,
            pageNumber: pageNumber
        },
        headers :{
            Authorization: 'Bearer '+localStorage.getItem('authentication')
        }
    })
    return response
    } catch (error) {
        throw error
    }
    
}

export const addCustomer = async (firstName,lastName,email,contact,userid)=> {
    try{
        console.log("Inside Service--->",firstName,lastName,email,contact,userid)
        let response = await axios.post('http://localhost:8080/bankapp/customers',{
        firstname:firstName,
        lastname:lastName,
        contact:contact,
        email:email,
        userid:userid
    },{
        params:{},
        headers:{}
    }
    )
    return response
    }catch(error) {
        console.log("Error inside Service--->",error)
        throw error
    }
    
}

export const customerRegisteration = async (username,password) => {
    let response = await axios.post('http://localhost:8080/api/auth/registeruser',{
        username:username,
        password:password
    },{
        params:{},
        headers:{}
    }
    )
    return response
}

export const getCustomer = async (userid) => {
    try {
        let response = await axios.get("http://localhost:8080/bankapp/customers/byuser" , {
        params: {
            userid:userid
        },
        headers :{
            Authorization: 'Bearer '+localStorage.getItem('authentication')
        }
    })
    console.log("Response inside customer---> ",response)
    return response
    } catch (error) {
        throw error
    }
    
}

export const updatePassword = async (userid,username,password)=> {
    try {
        let response = await axios.put('http://localhost:8080/bankapp/user',{
            userid:userid,
            username:username,
            password:password
        },{
            params:{},
            headers:{
                Authorization: 'Bearer '+localStorage.getItem('authentication')
            }
        }
    )
    return response
    } catch (error) {
        throw error
    }
    
}

export const UserId = async (customerid) => {
    try {
        let response = await axios.get("http://localhost:8080/bankapp/customers/userid" , {
        params: {
            customerid:customerid
        },
        headers :{
            Authorization: 'Bearer '+localStorage.getItem('authentication')
        }
    })
    return response
    } catch (error) {
        throw error
    }
    
}

export const updateCustomer = async (customerid,firstName,lastName,email,contact,userid)=> {
    try {
        let response = await axios.put('http://localhost:8080/bankapp/customers',{
            customerid:customerid,
            firstname:firstName,
            lastname:lastName,
            email:email,
            contact:contact,
            userid:userid,
            active:true
        },{
            params:{},
            headers:{
                Authorization: 'Bearer '+localStorage.getItem('authentication')
            }
        }
    )
    return response
    } catch (error) {
        throw error
    }
    
}

export const DeleteCustomerService = async (customerid)=> {
    try {
        let response = await axios.delete('http://localhost:8080/bankapp/customers',{
        params :{
            customerid:customerid
        },
        headers :{
            Authorization: 'Bearer '+localStorage.getItem('authentication')
        }}
    )
    return response
    } catch (error) {
        throw error
    }
    
}