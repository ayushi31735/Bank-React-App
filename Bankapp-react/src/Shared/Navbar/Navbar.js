import { Nav } from 'react-bootstrap';
import Container from 'react-bootstrap/Container';
import Navbar from 'react-bootstrap/Navbar';
import './Navbar.css';

function ColorSchemesExample() {
  return (
    <Navbar className="bg-body-tertiary">
      <Container>
        <Navbar.Brand href="/adminDashboard/:username">FUTURE BANK</Navbar.Brand>
        <Navbar.Toggle />
        <Navbar.Collapse className="justify-content-end">
          <Navbar.Text>
          <Nav className="navbar-links">
            <Nav.Link href="/adminDashboard/banks">Bank</Nav.Link>
            <Nav.Link href="/adminDashboard/customers">Customer</Nav.Link>
            <Nav.Link href="/adminDashboard/accounts">Account</Nav.Link>
            <Nav.Link href="/" onClick={(e)=> {localStorage.clear()}}>LogOut</Nav.Link>
          </Nav>
          </Navbar.Text>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );

}

export default ColorSchemesExample;