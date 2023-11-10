import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { getListOfAccounts } from '../../Service/Account';
import { getCustomer } from '../../Service/Customer';
import CustomerNavbar from '../../Shared/customerNavbar/CustomerNavbar';
import './OtherTransaction.css';

const SelfTransaction = () => {

    const [amount,setAmount] = useState("")
    const[fromAccount,setFromAccount] = useState("")
    const[toAccount,setToAccount] = useState(true)
    const [customerid,setCustomerid] = useState()
    const [accounts,setAccounts] = useState ()
    const [numberOfAccounts,setNumberOfAccounts] = useState()

    const navigate=new useNavigate();

    let userid = localStorage.getItem('userid')
    const handleCustomer = async () => {
        try {
            let response = await getCustomer(userid)
            if(response)
            {
                setCustomerid(response.data.customerid)
            }
        } catch (error) {
            alert(error.response.data.message)
        }
        
    }

    const handleAccounts = async () => {
        try {
            let response = await getListOfAccounts(customerid)
            console.log("Accounts--->",response)
            setAccounts(response.data)
            setNumberOfAccounts(response.data.length)
        } catch (error) {
            
        }
    }

    const handleMySubmit = () => {
        
    }

    useEffect(()=>{
        handleCustomer()
    },[])

    useEffect(()=>{
        handleAccounts()
    },[])

    let accountOptions
    let accountarray = []

    if(numberOfAccounts > 0)
    {
        for(let index=1;index<=numberOfAccounts;index++)
        {
            accounts.map(a => {
                accountarray.push(a.accountnumber)
            })
        }
        accountOptions = accountarray.map(p => {
            return (
                <option value={p} selected={p == accounts}>{p}</option>
            )
        })
    }

  return (
    <>

    <div>
        <CustomerNavbar />
    </div>
    <div className='u-transaction'>
        <div class="card">
            <div class="card-header">
                <h2 align="center"><b>FUTURE BANK</b></h2>
                <h4 align="center">Self Transaction</h4>
            </div>
            <div class="card-body">
                <form>

                <select class="form-select" aria-label="Default select example">
                    <option selected>Transaction Type</option>
                    <option value="1">Credit</option>
                    <option value="2">Debit</option>
                    <option value="2">Transfer</option>
                </select><br/>
                
                {/* <select class="form-select" aria-label="Default select example"
                onChange={(e) => {
                     setFromAccount(e.target.value)
                 }}>
                <option selected>From Account (Debit/Transfer)</option>
                    {accountOptions}
                </select><br />

                <select class="form-select" aria-label="Default select example"
                    onChange={(e) => {
                        setToAccount(e.target.value)
                    }}>
                    <option selected>To Account (Credit/Transfer)</option>
                    {accountOptions}
                </select><br /> */}

                    <div className="mb-3">
                        <label for="exampleInputPassword1" className="form-label">From Account</label>
                        <input type="password" className="form-control" 
                            onChange={(e) => {
                            setFromAccount(e.target.value)
                        }}/>
                    </div>
                    <div className="mb-3">
                        <label for="exampleInputPassword1" className="form-label">To Account</label>
                        <input type="password" className="form-control" 
                            onChange={(e) => {
                            setToAccount(e.target.value)
                        }}/>
                    </div>
                    <div className="mb-3">
                        <label for="exampleInputEmail1" className="form-label">Amount</label>
                        <input type="text" className="form-control" aria-describedby="emailHelp"
                            onChange={(e) => {
                            setAmount(e.target.value)
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

export default SelfTransaction