import { Nav } from 'react-bootstrap';
import Container from 'react-bootstrap/Container';
import Navbar from 'react-bootstrap/Navbar';
import './CustomerNavbar.css';

function ColorSchemesExample() {
    return (
        <Navbar className="bg-body-tertiary">
          <Container>
            <Navbar.Brand href="/userDashboard/:username">FUTURE BANK</Navbar.Brand>
            <Navbar.Toggle />
            <Navbar.Collapse className="justify-content-end">
              <Navbar.Text>
                <Nav className="navbar-links">
                    <Nav.Link href="/userDashboard/createProfile">Create Profile</Nav.Link>
                    <Nav.Link href="/userDashboard/editProfile">Edit Profile</Nav.Link>
                    <Nav.Link href="/userDashboard/passbook">Passbook</Nav.Link>
                    <Nav.Link href="/userDashboard/transaction">Transaction</Nav.Link>
                    <Nav.Link href="/" onClick={(e)=> {localStorage.clear()}}>LogOut</Nav.Link>
                </Nav>
              </Navbar.Text>
            </Navbar.Collapse>
          </Container>
        </Navbar>
      );

}

export default ColorSchemesExample;