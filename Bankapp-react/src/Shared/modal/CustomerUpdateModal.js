import React, { useEffect, useState } from 'react';
import Modal from 'react-bootstrap/Modal';
import UpdateCustomer from '../../component/Customer/UpdateCustomer';

const CustomerUpdateModal = ({ value, changeshow, closeModal}) => {

    const [show, setShow] = useState(false);
    const [customer,setCustomer] = useState();
    console.log("Customer--->",value)

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

    console.log("Customer Value Modal--->",value)

    const setUpdateCustomer = (()=>{
        setCustomer(value)
    })

    useEffect(() => {
        setUpdateCustomer(value)
    },[value])


    useEffect(() => {
        handleShow(true)
    },[value])


    return (
        <>
          <Modal show={show} onHide={handleClose}>
            <Modal.Header closeButton>
              <Modal.Title>Update Details</Modal.Title>
            </Modal.Header>
            <Modal.Body><UpdateCustomer value={customer}/></Modal.Body>
            {/* <Modal.Footer>
              <Button variant="secondary" onClick={handleClose}>
                Close
              </Button>
              <Button variant="primary" onClick={handleClose}>
                Save Changes
              </Button>
            </Modal.Footer> */}
          </Modal>
        </>
      );
};

export default CustomerUpdateModal;
