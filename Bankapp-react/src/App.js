import { Route, Routes } from 'react-router-dom';
import './App.css';
import UpdateModal from './Shared/modal/UpdateModal';
import GetAllAccount from './component/Account/GetAllAccount';
import AdminDashboard from './component/Admin/AdminDashboard';
import GetAllBank from './component/Bank/GetAllBank';
import CustomerRegistration from './component/Customer/CustomerRegistration';
import GetAllCustomer from './component/Customer/GetAllCustomer';
import LoginPage from './component/Login/LoginPage';
import Passbook from './component/transaction/Passbook';
import Transaction from './component/transaction/Transaction';
import CreateProfile from './component/user/CreateProfile';
import CustomerDashboard from './component/user/CustomerDashboard';
import EditUser from './component/user/EditUser';

function App() {
  return (
    <>
      {/* <GetAllBank /> */}
      <Routes>
        <Route exact path="/" element={<LoginPage />}/>
        <Route exact path="/adminDashboard/:username" element={<AdminDashboard/>}/>
        <Route exact path="/adminDashboard/banks" element={<GetAllBank />}/>
        <Route exact path="/adminDashboard/banks/updateBank" element={<UpdateModal />}/>
        <Route exact path="/adminDashboard/customers" element={<GetAllCustomer />}/>
        <Route exact path="/adminDashboard/accounts" element={<GetAllAccount />}/>

        <Route exact path="/userDashboard/:username" element={<CustomerDashboard />}/>
        <Route exact path="/customerRegisteration" element={<CustomerRegistration />}/>
        <Route exact path="/userDashboard/createProfile" element={<CreateProfile />}/>
        <Route exact path="/userDashboard/transaction" element={<Transaction />}/>
        <Route exact path="/userDashboard/passbook" element={<Passbook />}/>
        <Route exact path="/userDashboard/editProfile" element={<EditUser />}/>
      </Routes>
    </>
  );
}

export default App;
