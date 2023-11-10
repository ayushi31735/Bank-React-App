import React, { useEffect, useState } from 'react';
import Modal from 'react-bootstrap/Modal';
import AddAccount from '../../component/Account/AddAccount';

const OpenAccountModal = ({ value }) => {

    const [show, setShow] = useState(false);
    const [customerid,setCustomerid] = useState();

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);


    const setNewCustomer = (()=>{
        setCustomerid(value)
    })

    console.log("Customerid--->",customerid)

    useEffect(() => {
        setNewCustomer(value)
    },[value])


    useEffect(() => {
        handleShow(true)
    },[customerid])


    return (
        <>
          <Modal show={show} onHide={handleClose}>
            <Modal.Header closeButton>
              <Modal.Title>Update Details</Modal.Title>
            </Modal.Header>
            <Modal.Body><AddAccount value={customerid} /></Modal.Body>
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

export default OpenAccountModal;
