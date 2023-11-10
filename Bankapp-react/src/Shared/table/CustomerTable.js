import React from 'react';
import './Table.css';

let headerdata = <></>
function CustomerTable({data, showPassbook}) {

    console.log("Data--->",data)
  let keys = []

    if (data && data.length > 0) {
      keys = Object.keys(data[0])
      keys.push('Passbook')
      
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
                      showPassbook(value)
                    }}>View</button></td>

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
export default CustomerTable