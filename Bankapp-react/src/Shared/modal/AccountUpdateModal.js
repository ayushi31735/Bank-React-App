import React, { useEffect, useState } from 'react';
import Modal from 'react-bootstrap/Modal';
import UpdateAccount from '../../component/Account/UpdateAccount';

const AccountUpdateModal = ({ value }) => {

    const [show, setShow] = useState(false);
    const [account,setAccount] = useState();

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

    console.log("Account Value Modal--->",value)

    const setUpdateAccount = (()=>{
        setAccount(value)
    })

    useEffect(() => {
        setUpdateAccount(value)
    },[value])
    console.log("Set account--->",account)


    useEffect(() => {
        handleShow(true)
    },[value])

    return (
        <>
          <Modal show={show} onHide={handleClose}>
            <Modal.Header closeButton>
              <Modal.Title>Update Details</Modal.Title>
            </Modal.Header>
            <Modal.Body><UpdateAccount value={account}/></Modal.Body>
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

export default AccountUpdateModal;
