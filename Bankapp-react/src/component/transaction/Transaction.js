import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { credit, debit, transfer } from '../../Service/Transaction';
import CustomerNavbar from '../../Shared/customerNavbar/CustomerNavbar';
import './OtherTransaction.css';

const Transaction = () => {

    const [amount,setAmount] = useState("")
    const[fromAccount,setFromAccount] = useState("")
    const[toAccount,setToAccount] = useState(true)
    const [transactiontype,setTransactiontype] = useState()

    const navigate=new useNavigate();

    const handleMySubmit = async(e) =>{
        e.preventDefault()
        if(transactiontype == 1)
        {
            console.log("Amount--->",amount)
            console.log("toAccount--->",toAccount)
            console.log("TransactionType--->",transactiontype)
            try {
                console.log("Inside Try")
                let response = await credit(amount,toAccount)
                if(response)
                {
                    alert("Credit Successfull")
                }
            } catch (error) {
                alert(error.response.data.message)
            }
        }
        if(transactiontype == 2)
        {
            try {
                console.log("Inside Try")
                let response = await debit(amount,fromAccount)
                if(response)
                {
                    alert("Debit Successfull")
                }
            } catch (error) {
                alert(error.response.data.message)
            }
        }
        if(transactiontype == 3)
        {
            try {
                console.log("Inside Try")
                let response = await transfer(amount,toAccount,fromAccount)
                if(response)
                {
                    alert("Transfer Successfull")
                }
            } catch (error) {
                alert(error.response.data.message)
            }
        }
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
                <h4 align="center">Transaction</h4>
            </div>
            <div class="card-body">
                <form>

                <select class="form-select" aria-label="Default select example"
                    onChange={(e) => {
                        setTransactiontype(e.target.value)
                    }}>
                    <option selected>Transaction Type</option>
                    <option value="1">Credit</option>
                    <option value="2">Debit</option>
                    <option value="3">Transfer</option>
                </select><br/>

                    <div className="mb-3">
                        <label for="exampleInputPassword1" className="form-label">From Account (Debit/Transfer)</label>
                        <input type="text" className="form-control" 
                            onChange={(e) => {
                            setFromAccount(e.target.value)
                        }}/>
                    </div>
                    <div className="mb-3">
                        <label for="exampleInputPassword1" className="form-label">To Account (Credit/Transfer)</label>
                        <input type="text" className="form-control" 
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

export default Transaction