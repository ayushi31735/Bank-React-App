import React from 'react';

const PageSize = ({totalrecord,setPageSize,setTotalpage,pageSize}) => {
  return (
    <div className="f-pagesize">
              <label for="floatingSelect">Pagesize</label>
              <select
                className="form-select"
                id="floatingSelect"
                aria-label="Floating label select example"
                onChange={(e) => {
                  setPageSize(e.target.value);
                  setTotalpage(Math.ceil(totalrecord/e.target.value))
                }}>
                <option selected>Page Size</option>
                <option value="2" selected={2 == pageSize}>2</option>
                <option value="4" selected={4 == pageSize}>4</option>
                <option value="5" selected={5 == pageSize}>5</option>
                <option value="10" selected={10 == pageSize}>10</option>
              </select>
            </div>
  )
}

export default PageSize