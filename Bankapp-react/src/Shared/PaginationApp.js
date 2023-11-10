import React from 'react';
import Pagination from 'react-bootstrap/Pagination';

function PaginationApp({totalpage,setpage,pageNumber}) { 
    
let items = []
items.push(
  <Pagination.Prev/>
)
for (let number = 1; number <= totalpage; number++) {
  items.push(
    <Pagination.Item key={number} active={number-1 === pageNumber}  onClick={(e)=>{
        setpage(number-1)
    }
        }>
      {number}
    </Pagination.Item>,
  );
}

items.push(
  <Pagination.Next/>
)

return (
  <div>
    <Pagination>{items}</Pagination>
    <br />
  </div>
);


  
}

export default PaginationApp