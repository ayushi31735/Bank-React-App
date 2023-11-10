import React, { useEffect, useState } from 'react'
import { getListOfAccounts } from '../../Service/Account'
import CustomerTable from '../../Shared/table/CustomerTable'
import TransactionTable from '../../Shared/table/TransactionTable'
import './AccountList.css'

const AccountList = ({customerid,showPassbook,data}) => {

    const [accounts,setAccounts] = useState ()

    console.log("Cutomerid--->",customerid)

    const handleAccounts = async () => {
        try {
            let response = await getListOfAccounts(customerid)
            console.log("Accounts--->",response)
            setAccounts(response.data)
        } catch (error) {
            
        }
    }
    console.log("Data--->",accounts)

    useEffect(()=>{
        handleAccounts()
    },[])

  return (
    <>
        <div className='account'>
            <div className="display-table">
                <h4 align="center">Passbook</h4>
                {accounts&& 
                <CustomerTable data={accounts} showPassbook={showPassbook} />}
                <div>
                    {data&&
                    <TransactionTable data={data} />}
                </div>
            </div>
        </div>
    </>
  )
}

export default AccountList