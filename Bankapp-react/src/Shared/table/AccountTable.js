import React, { useState } from 'react';
import './Table.css';

let headerdata = <></>
function AccountTable({data,deleteFunction,updateFunction,openAccountFunction,checkPage}) {

  const [isModalOpen, setIsModalOpen] = useState(false);

  let keys = []

  const handleEditClick = ({value}) => {
    setIsModalOpen(true)
    
  };


    if (data.length!=0) {
      keys = Object.keys(data[0])
      keys.push('Update')
      keys.push('Delete')
      keys.push("Account")
      
        headerdata = keys.map((d) => {
          return <th>{d}</th>;
        });

    }
  
    let rowofusers = <></>
    if (data.length > 0) {
        rowofusers = data.map((value,ind) => {

            return (
              
                <tr key={ind}>
                    {
                        Object.values(value).map((t) => {
                            return (
                                <td>{t}</td>
                            )
                        })
                    }
                    <td><button type="button" onClick={()=>{
                      updateFunction(value)
                    }}>Edit</button></td>
                
                    <td><button type="button" onClick={()=>{
                      deleteFunction(value)
                    }}>Delete</button></td>

                    <td><button type="button" onClick={()=>{
                      openAccountFunction(value)
                    }}>New</button></td>

                </tr>
            )

});
}

return (<>

<table class="table">
  <thead>
    <tr>
      {headerdata}
    </tr>
  </thead>
  <tbody>
    {rowofusers}
  </tbody>
</table>
</>)

}
export default AccountTable