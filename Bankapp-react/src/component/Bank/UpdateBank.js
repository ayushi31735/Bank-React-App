import React, { useState } from 'react'
import { updateBankService } from '../../Service/Bank'

const UpdateBank = ({value}) => {

    const[bankid,setBankid] = useState(value.bankid)
    const[bankName, setBankName] = useState(value.bankName)
    const[branch, setBranch] = useState(value.branch)
    const[abbrevation, setAbbrevation] = useState(value.abbrevation)
    const[ifsc,setIfsc] = useState(value.ifsc)

    console.log("Update Bank--->",value)

    const updateBank = async (e) => {
        e.preventDefault()
        let response = await updateBankService(bankid,bankName,branch,abbrevation,ifsc)
        if(response)
        {
            alert("Bank Updated Successfully")
            return
        }
    }


    return (
    <form>
        <h4 align="center">Update Bank</h4>
        <div className="bankname">
            <label className="form-label">Bank Id</label>
            <input type="text" disabled={true} className="form-control" aria-describedby="emailHelp" value={bankid}
                onChange={(e) => {
                    setBankid(e.target.value)
                }}/>
        </div>
        <div className="bankname">
            <label className="form-label">BankName</label>
            <input type="text" className="form-control" aria-describedby="emailHelp" value={bankName}
                onChange={(e) => {
                    setBankName(e.target.value)
                    console.log("BankName--->",bankName)
                }}/>
        </div>
        <div className="branch">
            <label className="form-label">Branch</label>
            <input type="text" className="form-control" aria-describedby="emailHelp" value={branch}
            onChange={(e) => {
                setBranch(e.target.value)
            }}/>
        </div>
        <div className="abbrevation">
            <label className="form-label">Abbrevation</label>
            <input type="text" className="form-control" aria-describedby="emailHelp" value={abbrevation}
            onChange={(e) => {
                setAbbrevation(e.target.value)
            }}/>
        </div>
        <div className="Ifsc">
            <label className="form-label">IFSC Code</label>
            <input type="text" className="form-control" aria-describedby="emailHelp" value={ifsc}
            onChange={(e) => {
                setIfsc(e.target.value)
            }}/>
        </div>
        <br />
        <button type="submit" className="btn btn-primary" onClick={updateBank}>Save Changes</button>
    </form>
  )
}

export default UpdateBank