import axios from "axios";

export const GetAllBanks = async (pageNumber=0,pageSize=10)=> {
    try {
        let response = await axios.get('http://localhost:8080/bankapp/banks',{
        params:
        {
            pageSize:pageSize,
            pageNumber:pageNumber
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


export const createBank = async (bankname,branch,abbrevation)=> {
    try {
        let response = await axios.post('http://localhost:8080/bankapp/banks',{
        bankname:bankname,
        branch:branch,
        abbrevation:abbrevation
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

export const deleteBankService = async (bankid)=> {
    try {
        let response = await axios.delete('http://localhost:8080/bankapp/banks',{
        params :{
            bankid:bankid
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

export const updateBankService = async (bankid,bankName,branch,abbrevation,ifsc)=> {
    try {
        let response = await axios.put('http://localhost:8080/bankapp/banks',{
        
            bankid:bankid,
            bankname:bankName,
            branch:branch,
            abbrevation:abbrevation,
            ifscCode:ifsc,
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

export const GetBankBranch = async (bankname)=> {
    try {
        let response = await axios.get('http://localhost:8080/bankapp/banks/bybankname',{
        params:
        {
            bankname:bankname
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

export const GetBankid = async (bankname,branch)=> {
    try {
        let response = await axios.get('http://localhost:8080/bankapp/banks/bankid',{
        params:
        {
            bankname:bankname,
            branch:branch
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