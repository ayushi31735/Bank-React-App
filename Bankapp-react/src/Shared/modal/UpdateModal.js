import React, { useEffect, useState } from 'react';
import Modal from 'react-bootstrap/Modal';
import UpdateBank from '../../component/Bank/UpdateBank';

const UpdateModal = ({ value }) => {

    const [show, setShow] = useState(false);
    const [bank,setBank] = useState();

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

    console.log("Bank Value Modal--->",value)

    const setUpdateBank = (()=>{
        setBank(value)
    })

    useEffect(() => {
        setUpdateBank(value)
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
            <Modal.Body><UpdateBank value={bank}/></Modal.Body>
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

export default UpdateModal;
