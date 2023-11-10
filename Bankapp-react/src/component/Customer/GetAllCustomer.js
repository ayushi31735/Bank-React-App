import React, { useEffect, useState } from "react";
import { DeleteCustomerService, getAllCustomers } from "../../Service/Customer";
import Navbar from '../../Shared/Navbar/Navbar';
import PageSize from "../../Shared/PageSize";
import PaginationApp from "../../Shared/PaginationApp";
import Loader from "../../Shared/loader/Loader";
import CustomerUpdateModal from "../../Shared/modal/CustomerUpdateModal";
import OpenAccountModal from "../../Shared/modal/OpenAccountModal";
import AccountTable from "../../Shared/table/AccountTable";
import AddCustomer from "./AddCustomer";
import './GetAllCustomer.css';

const GetAllCustomer = () => {

    const [pageSize, setPageSize] = useState(4)
    const [pageNumber, setPageNumber] = useState(0)
    const [data, setData] = useState([])
    const [totalrecord,setTotalrecord] = useState()
    const [totalpage,setTotalpage]=useState()
    const [checkPage,setCheckPage] = useState("Customer");
    const [isLoading, setIsLoading] = useState(true)
    const [customerid,setCustomerid] = useState()
    const [isOpenAccountModal,setIsAccountModal] = useState(false)
    const [isOpenUpdateModal,setIsOpenUpdateModal] = useState(false)
    const [customer,setCustomer] = useState()

    const getAllCustomer = async () => {
      try {
        let response = await getAllCustomers(pageNumber, pageSize);

        if (response.data) {
          console.log("Response--->",response)
            setData(response.data.content);
            setTotalrecord(response.headers['x-total-count'])
            setTotalpage(Math.ceil(response.headers['x-total-count']/pageSize))
        }
      } catch (error) {
        alert(error)
      }
      finally {
        setIsLoading(false)
      }
        
    }

    const updateCustomer = (customerToBeUpdated) => {
      console.log("Update--->",customerToBeUpdated)
      setCustomer(customerToBeUpdated)
      setIsOpenUpdateModal(true)
    }

    console.log("Customer--->",customer)

    const deleteCustomer = async (customerToBeDeleted) => {
      let customerid = customerToBeDeleted.customerid
      try {
        let response = await DeleteCustomerService(customerid)
        if(response)
        {
          alert("Customer Deleted Successfully")
          return
        }
      } catch (error) {
        
      }
    }

    const openAccount = (customer) => {
      console.log("Customer--->",customer)
      setCustomerid(customer.customerid)
      setIsAccountModal(true)
    }

    console.log("customerid--->",customerid)
    useEffect(() => {
        getAllCustomer()
    } , [totalrecord,pageSize,pageNumber])

  return (
    <>
        <Loader isShow={isLoading}/>
        <div>
        {isOpenAccountModal && <OpenAccountModal
            value={customerid}/>}

        {isOpenUpdateModal && <CustomerUpdateModal
            value={customer}
            checkPage={checkPage} changeshow={true} closeModal={() => setIsOpenUpdateModal(false)} 
          />}

      <div>
        <Navbar />
      </div>
        <div className="formforbank">
          <div className="addformCustomer" >
            <AddCustomer />
          </div>
        
        <div className="pagination">
            <PaginationApp 
                totalpage={totalpage}
                setpage={setPageNumber} 
                pageNumber={pageNumber} 
                getData={getAllCustomer}
                pageSize={pageSize}>
            </PaginationApp>
        
            <PageSize totalrecord={totalrecord} 
                  setPageSize={setPageSize}
                  setTotalpage={setTotalpage}
                  pageSize={pageSize}></PageSize>

        </div>

        <div className="display-table">
        <h4 align="center">Customer Details</h4>
          <AccountTable 
              data={data} 
              updateFunction={updateCustomer}  
              deleteFunction={deleteCustomer}
              openAccountFunction={openAccount}
              checkPage={checkPage}>          
          </AccountTable>
            
        </div>
      </div>
    </div>
    </>
    )
}

export default GetAllCustomer