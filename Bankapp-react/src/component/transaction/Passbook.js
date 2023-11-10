import React, { useEffect, useState } from 'react'
import { getCustomer } from '../../Service/Customer'
import { GetTransactions } from '../../Service/Transaction'
import CustomerNavbar from '../../Shared/customerNavbar/CustomerNavbar'
import Loader from "../../Shared/loader/Loader"
import AccountList from './AccountList'
import './Passbook.css'

const Passbook = () => {

    const [customerid,setCustomerid] = useState()
    const [transaction,setTransaction] = useState()
    const [isLoading, setIsLoading] = useState(true)

    let userid = localStorage.getItem('userid')
    console.log("Userid--->",userid)
    const handleCustomer = async () => {
        try {
            console.log("Userid--->",userid)
            let response = await getCustomer(userid)
            console.log("Response--->",response)
            if(response)
            {
                setCustomerid(response.data.customerid)
            }
        } catch (error) {
            alert(error.response.data.message)
        }finally {
            setIsLoading(false)
          }
        
    }

    const showPassbook = async (value) => {
        let accountnumber = value.accountnumber
        let response = await GetTransactions(accountnumber)
        if(response)
        {
            setTransaction(response.data)
        }
    }

    console.log("CustomerId--->",customerid)

    useEffect(()=>{
        handleCustomer()
    },[])

    return (
        <>
            <Loader isShow={isLoading}/>
            <div className='passbook-page'>
                <div>
                    <CustomerNavbar />
                </div>

                <div className='passbook'>
                    {customerid&&
                    <AccountList customerid={customerid} showPassbook={showPassbook} data={transaction}/>}
                    {/* <div>
                        {transaction&&
                        <TransactionTable data={transaction} />}
                    </div> */}
                </div>

            
            </div>
        </>
    )
}

export default Passbook