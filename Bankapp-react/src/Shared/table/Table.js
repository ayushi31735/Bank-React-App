import React from 'react';
import './Table.css';

let headerdata = <></>
function Table({data,deleteFunction,updateFunction}) {

  let keys = []


    if (data.length!=0) {
      keys = Object.keys(data[0])
      keys.push('Update')
      keys.push('Delete')
      
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
export default Table