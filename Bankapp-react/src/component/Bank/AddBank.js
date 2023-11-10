import React, { useState } from 'react';
import { createBank } from '../../Service/Bank';
import './AddBank.css';

const AddBank = () => {

    const[bankName, setBankName] = useState("")
    const[branch, setBranch] = useState("")
    const[abbrevation, setAbbrevation] = useState("")

    const handleMySubmit =  async () => {
    
        if(bankName ==="")
        {
            alert("Please Enter Bank Name")
        }

        else if(branch ==="")
        {
            alert("Please Enter Bank Branch")
        }

        else if(abbrevation ==="")
        {
            alert("Please Enter Bank Abbrevation")
        }
        else{
            let response = await createBank(bankName,branch,abbrevation)
            if(response.data)
            {
                alert("Bank Added Successfully")
            }
        }
    
    }


  return (
    <form>
        <h4 align="center">Add Bank</h4>
        <div className="bankname">
            <label className="form-label">BankName</label>
            <input type="text" className="form-control" aria-describedby="emailHelp"
                onChange={(e) => {
                    setBankName(e.target.value)
                }}/>
        </div>
        <div className="branch">
            <label className="form-label">Branch</label>
            <input type="text" className="form-control" aria-describedby="emailHelp"
            onChange={(e) => {
                setBranch(e.target.value)
            }}/>
        </div>
        <div className="abbrevation">
            <label className="form-label">Abbrevation</label>
            <input type="text" className="form-control" aria-describedby="emailHelp"
            onChange={(e) => {
                setAbbrevation(e.target.value)
            }}/>
        </div><br />
        <button type="submit" className="btn btn-primary" onClick={handleMySubmit}>Submit</button>
    </form>
  )
}

export default AddBank