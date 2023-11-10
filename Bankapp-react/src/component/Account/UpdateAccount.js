import React, { useEffect, useState } from 'react'
import { UpdateAccountService } from '../../Service/Account'
import { GetBankBranch, GetBankid } from '../../Service/Bank'

const UpdateAccount = ({value}) => {

    const [balance, setBalance] = useState(value.balance)
    const [bankid, setBankid] = useState()
    const [accountnumber, SetAccountNumber] = useState(value.accountnumber)
    const [bankname,setBankname] = useState(value.bankname)
    const [bank,setBank] = useState()
    const [branch,setBranch] = useState()
    const [numberOfBranch,setNumberOfBranch] = useState()
    const [firstname,setFirstname] = useState(value.firstname)
    const [lastname,setLastname] = useState(value.lastname)
    const [ifsc,setIfsc] = useState(value.ifsccode)


    console.log("Value--->",value)
    const handleMySubmit = async () => {
        try {
            let response = await UpdateAccountService(accountnumber,balance,bankid)
            if(response)
            {
                alert("Account Updated Successfully")
                return
            }
        } catch (error) {
            alert(error.response.data.message)
            return
        }
    }

    const getBankBranch = async (bankname) => {
        try {
            let response = await GetBankBranch(bankname)
            if(response)
            {
                console.log("Response Branch--->",response)
                setBank(response.data)
                setNumberOfBranch(response.data.length)
            }
        } catch (error) {
            alert(error.response.data.message)
        }
    }

    const getBankid = async (branch) => {
        try {
            if(branch)
            {
                let response = await GetBankid(bankname,branch)
                console.log("Bank Id--->",response)
                if(response)
                {
                    setBankid(response.data)
                }
            }
        } catch (error) {
            alert(error.response.data.message)
        }
    } 

    useEffect(() => {
        getBankBranch(bankname)
    },[bankname])

    useEffect(() => {
        getBankid(branch)
    },[branch])

    let branchOptions
    let branchArray = []
    
    if(numberOfBranch > 0)
    {
        for(let index = 1; index <= numberOfBranch;index++)
        {
            bank.map(b => {
                branchArray.push(b.branch)
            })
        }
        branchOptions = branchArray.map(p => {
            return (
                <option value={p} selected={p == branch}>{p}</option>
            )
        })
    }

  return (
    <form>
        <div className='addcust'>
            <h4 align="center">Update Account</h4>
            <div className="customerid">
                <label className="form-label">Account Number</label>
                <input type="text" disabled={true} className="form-control" aria-describedby="emailHelp" value={accountnumber}/>
            </div>
            <div className="First Name">
                <label className="form-label">First Name</label>
                <input type="text" disabled={true} className="form-control" aria-describedby="emailHelp" value={firstname}/>
            </div>
            <div className="Last Name">
                <label className="form-label">Last Name</label>
                <input type="text" disabled={true} className="form-control" aria-describedby="emailHelp" value={lastname}/>
            </div>
            <div className="Bank Name">
                <label className="form-label">Bank Name</label>
                <input type="text" disabled={true} className="form-control" aria-describedby="emailHelp" value={bankname}/>
            </div><br />
            <select class="form-select" aria-label="Default select example"
                onChange={(e) => {
                    setBranch(e.target.value)
                }}>
                <option selected>Select Branch</option>
                {branchOptions}
            </select><br />
            <div className="balance">
                <label className="form-label">Balance</label>
                <input type="text" disabled={true} className="form-control" aria-describedby="emailHelp" value={balance}/>
            </div>
            <div className="Ifsc">
                <label className="form-label">IFSC Code</label>
                <input type="text" disabled={true} className="form-control" aria-describedby="emailHelp" value={ifsc}/>
            </div>
            <br />
            <button type="submit" className="btn btn-primary" onClick={handleMySubmit}>Submit</button>
        </div>
    </form>
  )
}

export default UpdateAccount