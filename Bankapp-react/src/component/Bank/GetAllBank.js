import React, { useEffect, useState } from "react";
import { useNavigate } from 'react-router-dom';
import { GetAllBanks, deleteBankService } from "../../Service/Bank";
import { validateUser as validateAdmin } from "../../Service/Login";
import Navbar from '../../Shared/Navbar/Navbar';
import PageSize from "../../Shared/PageSize";
import PaginationApp from "../../Shared/PaginationApp";
import Loader from "../../Shared/loader/Loader";
import UpdateModal from "../../Shared/modal/UpdateModal";
import Table from "../../Shared/table/Table";
import AddBank from "./AddBank";
import './GetAllBank.css';

const GetAllbank = () => {

  const [pageSize, setPageSize] = useState(4);
  const [pageNumber, setPageNumber] = useState(0);
  const [data, setData] = useState([]);
  const [totalrecord,setTotalrecord] = useState();
  const [totalpage,setTotalpage]=useState();
  const [isUserValid,setIsUserValid] = useState(false);
  const [isUpdate,setIsUpdate] = useState(false);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [bank,setBank] = useState();
  const [isLoading, setIsLoading] = useState(true)

  const navigate=new useNavigate();

  const showbank = async () => {

    try {
      let response = await GetAllBanks(pageNumber, pageSize);

      console.log("Response Value",response);
      if (response.data) {
      console.log("response=="+response.data.content);
      setData(response.data.content);
      setTotalrecord(response.headers['x-total-count'])
      console.log("total records ---> ",totalrecord)
      setTotalpage(Math.ceil(response.headers['x-total-count']/pageSize))
    }
    } catch (error) {
      alert(error)
    }finally {
      setIsLoading(false)
    }
  };

  const updateBank = (bankToBeUpdated) => {
    console.log("Get BAnk Update--->",bankToBeUpdated)
    setBank(bankToBeUpdated)
    setIsModalOpen(true)
  };
  

  const deleteBank = async (bankToBeDeleted)=> {
    console.log("delete--->",bankToBeDeleted)
    let bankid = bankToBeDeleted.bankid
    console.log("Bankid--->",bankid)
    let response = await deleteBankService(bankid)
    if(response)
    {
      alert("Bank Deleted Successfully")
      return
    }
  }

  const validateUser = async() =>{
    const authToken = localStorage.getItem('authentication')
    if(!authToken)
    {
      setIsUserValid(false)
    }
    console.log("authtoken--->"+authToken)
    let resp = await validateAdmin(authToken)
    console.log(resp)

    if(resp.data.role[0].authority !='ROLE_ADMIN')
    {
        setIsUserValid(false)
    }
    setIsUserValid(true)
    return
 }

  useEffect(()=>{
    validateUser()
  },[])

  useEffect(() => {
    showbank()
  } , [totalrecord,pageSize,pageNumber])


  if(isUserValid)
  {
    return (

      <>
        <Loader isShow={isLoading}/>
        <div>
          {isModalOpen && <UpdateModal
            value={bank} changeshow={true} closeModal={() => setIsModalOpen(false)} 
          />}
        
          <div>
            <Navbar />
          </div>
  
          <div className="formforbank">
  
            <div className="b-addform" >
              <AddBank />
            </div>
          
            <div className="pagination">
              <PaginationApp 
                totalpage={totalpage}
                setpage={setPageNumber} 
                pageNumber={pageNumber} 
                getData={showbank}
                pageSize={pageSize}>
              </PaginationApp>
          
              
              <PageSize totalrecord={totalrecord} 
                setPageSize={setPageSize}
                setTotalpage={setTotalpage}
                pageSize={pageSize}>
              </PageSize>
            </div>
  
            <div className="display-table">
              <h4 align="center">Bank Details</h4>
              <Table data={data} deleteFunction={deleteBank} updateFunction={updateBank}>
              </Table>
            </div>
  
          </div>
          
        </div>
      </>
    );
  }
  else{
    return (
      <>
      <a href='/'>Please Login First</a>
       
      </>
 
    )
  }
  
};

export default GetAllbank;