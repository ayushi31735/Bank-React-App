import axios from "axios"

export const getAllAccounts = async (pageNumber,pageSize) => {
    try {
        let response = await axios.get("http://localhost:8080/bankapp/accounts/dto" , {
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

export const addAccount = async (balance,bankid,customerid)=> {
    try {
        let response = await axios.post('http://localhost:8080/bankapp/accounts',{
        balance:balance,
        bankid:bankid,
        customerid:customerid
    },{
        params:{},
        headers:{}
    }
    )
    return response
    } catch (error) {
        throw error
    }
    
}

export const getListOfAccounts = async (customerid) => {
    try {
        let response = await axios.get("http://localhost:8080/bankapp/customers/accounts" , {
        params: {
            customerid:customerid
        },
        headers :{
            Authorization: 'Bearer '+localStorage.getItem('authentication')
        }
    })
    console.log("Response list--->",response)
    return response
    } catch (error) {
        throw error
    }
    
}

export const DeleteAccountService = async (accountnumber)=> {
    try {
        let response = await axios.delete('http://localhost:8080/bankapp/accounts',{
        params :{
            accountnumber:accountnumber
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

export const UpdateAccountService = async (accountnumber,balance,bankid)=> {
    try {
        let response = await axios.put('http://localhost:8080/bankapp/accounts/bank',{
            accountnumber:accountnumber,
            balance:balance,
            bankid:bankid
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