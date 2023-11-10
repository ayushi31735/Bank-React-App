import React, { useEffect, useState } from 'react'
import { addAccount } from '../../Service/Account'
import { GetBankBranch, GetBankid } from '../../Service/Bank'

const AddAccount = ({value}) => {

    const [balance, setBalance] = useState("")
    const [bankid, setBankid] = useState("")
    const [customerid, SetCustomerid] = useState(value)
    const [bankname,setBankname] = useState()
    const [bank,setBank] = useState()
    const [branch,setBranch] = useState()
    const [numberOfBranch,setNumberOfBranch] = useState()

    const handleMySubmit =  async () => {
        try {
            if(balance === "")
            {
                alert("Please Enter Balance")
            }
            else
            {
                let response = await addAccount(balance,bankid,customerid)
                if(response.data)
                {
                    alert("Account Added Successfully")
                }
            }
            } 
            catch (error) 
            {
                alert(error.response.data.message)
            }
    }

    const getBankBranch = async (bankname) => {
        try {
            if(bankname)
            {
                let response = await GetBankBranch(bankname)
                if(response)
                {
                    console.log("Response Branch--->",response)
                    setBank(response.data)
                    setNumberOfBranch(response.data.length)
                }
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
        for(let index = 1; index <= 1 ; index++)
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
    console.log("bankname--->",bankname)
    console.log("Branch-->",branch)
    console.log("Bank--->",bank)
    console.log("BankId--->",bankid)

  return (
    <form>
        <div className='addcust'>
            <h4 align="center">Add Account</h4>
            <div className="customerid">
                <label className="form-label">Customer Id</label>
                <input type="text" disabled={true} className="form-control" aria-describedby="emailHelp" value={customerid}
                    onChange={(e) => {
                    SetCustomerid(e.target.value)
                }}/>
            </div><br/>
            <select class="form-select" aria-label="Default select example" 
                onChange={(e) => {
                    setBankname(e.target.value)
                }}>
                <option selected>Select Bank</option>
                <option value="State Bank Of India">State Bank Of India</option>
                <option value="Punjab National Bank">Punjab National Bank</option>
                <option value="Bank Of India">Bank Of India</option>
                <option value="Bank Of Baroda">Bank Of Baroda</option>
                <option value="ICICI Bank">ICICI Bank</option>
            </select><br />
            <select class="form-select" aria-label="Default select example"
                onChange={(e) => {
                    setBranch(e.target.value)
                }}>
                <option selected>Select Branch</option>
                {branchOptions}
            </select><br />
            <div className="balance">
                <label className="form-label">Balance</label>
                <input type="text" className="form-control" aria-describedby="emailHelp"
                    onChange={(e) => {
                        setBalance(e.target.value)
                    }}/>
            </div>
        
            <br />
            <button type="submit" className="btn btn-primary" onClick={handleMySubmit}>Submit</button>
        </div>
    </form>
  )
}

export default AddAccount