import axios from "axios"

export const credit = async (amount,toAccount)=> {
    console.log("Amount Service--->",amount)
    console.log("ToAccont Service--->",toAccount)
    try {
        let response = await axios.post('http://localhost:8080/bankapp/transactions/credit',{
        amount:amount,
        toaccount:toAccount
    },{
        params:{},
        headers:{}
    }
    )
    console.log("Response Service--->",response)
    return response
    } catch (error) {
        throw error
    }
    
}

export const debit = async (amount,fromAccount)=> {
    console.log("Amount Service--->",amount)
    console.log("ToAccont Service--->",fromAccount)
    try {
        let response = await axios.post('http://localhost:8080/bankapp/transactions/debit',{
        amount:amount,
        fromaccount:fromAccount
    },{
        params:{},
        headers:{}
    }
    )
    console.log("Response Service--->",response)
    return response
    } catch (error) {
        throw error
    }
    
}

export const transfer = async (amount,toAccount,fromAccount)=> {
    console.log("Amount Service--->",amount)
    console.log("ToAccont Service--->",toAccount)
    console.log("ToAccont Service--->",fromAccount)
    try {
        let response = await axios.post('http://localhost:8080/bankapp/transactions/transfer',{
        amount:amount,
        toaccount:toAccount,
        fromaccount:fromAccount
    },{
        params:{},
        headers:{}
    }
    )
    console.log("Response Service--->",response)
    return response
    } catch (error) {
        throw error
    }
    
}

export const GetTransactions = async (accountnumber)=> {
    try {
        let response = await axios.get('http://localhost:8080/bankapp/accounts/transactions',{
        params:
        {
            accountnumber:accountnumber
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