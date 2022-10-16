import React, {memo} from 'react';
import {Link, Outlet} from "react-router-dom";
import '../.././Styles/headerStyle.css';
import logo from '../.././images/myestate_logo1.png';
import {BsSearch} from "react-icons/bs";

function Header() {
  return (
    <div className="App">
      <div className="App-header">
            <div className='App-logo-container'>
                <Link to="home"><img src={logo} className="App-logo" alt="logo" /></Link>
                <h1 className="App-name">myEstate</h1>
                <p>...<em>Properties<br /> Simplified</em></p>
                <h4 className="App-text">Rent</h4>
                <h4 className="App-text">Buy</h4>
                <h4 className="App-text">Sell</h4>
                <h4 className="App-text">Lease</h4>
                <h4 className="App-text">Save</h4><br/><br/>
            </div>
            <div className="search">
              <BsSearch className="search-icon" size={30}/>
              <input type="text" name="search" placeholder="Search.." />  
            </div>       
            <nav>
                <ul>
                    <li>
                        <Link to="login"><h2 className='login-link'>Login</h2></Link>
                    </li>
                </ul>
            </nav>
      </div>
      <Outlet />
    </div>
  )
}

export default memo(Header);