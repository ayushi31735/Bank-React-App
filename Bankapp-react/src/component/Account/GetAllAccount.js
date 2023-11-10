import React, { useEffect, useState } from 'react';
import { DeleteAccountService, getAllAccounts } from '../../Service/Account';
import Navbar from '../../Shared/Navbar/Navbar';
import PageSize from '../../Shared/PageSize';
import PaginationApp from "../../Shared/PaginationApp";
import Loader from "../../Shared/loader/Loader";
import AccountUpdateModal from '../../Shared/modal/AccountUpdateModal';
import Table from "../../Shared/table/Table";
import './GetAllAccount.css';

const GetAllAccount = () => {

    const [pageSize, setPageSize] = useState(4)
    const [pageNumber, setPageNumber] = useState(0)
    const [data, setData] = useState([])
    const [totalrecord,setTotalrecord] = useState()
    const [totalpage,setTotalpage]=useState()
    const [isLoading, setIsLoading] = useState(true)
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [account,setAccount] = useState()

    const getAllAccount = async () => {
      try {
        let response = await getAllAccounts(pageNumber, pageSize);

        if (response.data) {
          console.log("Response--->",response)
            setData(response.data.content);
            setTotalrecord(response.headers['x-total-count'])
            setTotalpage(Math.ceil(response.headers['x-total-count']/pageSize))
        }
      } catch (error) {
        alert(error.response.data.message)
      }finally {
        setIsLoading(false)
      }

    }

    const updateAccount = async (accountToBeUpdated) => {
      console.log("Account--->",accountToBeUpdated)
      setAccount(accountToBeUpdated)
      setIsModalOpen(true)
    }

    const deleteAccount = async (accountToBeDeleted) => {
      let accountnumber = accountToBeDeleted.accountnumber
      try {
        let response = await DeleteAccountService(accountnumber)
        if(response)
        {
          alert("Account Deleted Successfully")
          return
        }
      } catch (error) {
        alert(error.response.data.message)
      }
    }

    useEffect(() => {
        getAllAccount()
    } , [totalrecord,pageSize,pageNumber])



  return (
    <>
      <Loader isShow={isLoading}/>

      {isModalOpen && <AccountUpdateModal
            value={account}
          />}

        <div className='account-page'>
          <div>
            <Navbar />
          </div>
          
          <div className="formforbank">
            {/* <div className="addformAccount" >
              <AddAccount />
            </div> */}
        
            <div className="pagination">
              <PaginationApp 
                totalpage={totalpage}
                setpage={setPageNumber} 
                pageNumber={pageNumber} 
                getData={getAllAccount}
                pageSize={pageSize}>
              </PaginationApp>

              <PageSize totalrecord={totalrecord} 
                  setPageSize={setPageSize}
                  setTotalpage={setTotalpage}
                  pageSize={pageSize}>
              </PageSize>

            </div>

            <div className="display-table">
              <h4 align="center">Account Details</h4>
              <Table data={data} deleteFunction={deleteAccount} updateFunction={updateAccount}></Table>
            
          </div>
        </div>
    </div>
    </>
  )
}

export default GetAllAccount