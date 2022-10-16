import React, {memo} from 'react';
import '../Styles/HomePageStyle.css';
// import {GrUserAdmin} from "react-icons/gi";
// import {FiUserCheck} from "react-icons/fi";
// import {RiDashboardLine} from "react-icons/ri";
// import {BsSearch} from "react-icons/bi";

//components
import ProductMenu from '../../src/pages/components/ProductMenu';
import Apartments from '../../src/pages/components/Apartments';

//dummy data
import {productMenuItems} from '../dummy_data/productMenuList';
import { listOfApartments } from './../dummy_data/apartments';
import estateVideo from '.././videos/myestate_video.mp4';
// import aboutUsImage from '../images/myestate_vacation_lodge.jpg';


const HomePage = () => {
  return (
    <div className="homepage">
      <div className="videoContainer">
        <video className="introVideo" preload autoPlay muted loop>
          <source src={estateVideo} type="video/mp4"/>
            Your browser does not support the video tag
        </video>
        <div className="topLeftText">
          <p>Rent an apartment</p>
        </div> 
        <div className="bottomLeftText">
          <p>Lease a land</p>
        </div> 
        <div className="midLeftText">
          <p>Save Money to Buy/Rent</p>
        </div> 
        <div className="topRightText">
          <p>Sell your land/house</p>
        </div> 
        <div className="midRightText">
          <p>100% Secure & Trusted Online Payment</p>
        </div> 
        <div className="bottomRightText">
          <p>Fully Digital</p>
        </div> 
        <div className="midText">
          <p>Auction and get maximum <br/> value for your Property</p>
        </div> 
      </div>

      {/* <div className="section">
        <h1 className="sectionTitle">What makes Us unique</h1>
        <div className="contentWrapper">
          <div className="itemWrapper">
            <h3>Vision</h3>
            <p className="text">Our products and services extend beyond our local borders. 
            We acknowledge good and adequate housing to be a right for everyone and we are
             committed to simplify and harmonize the hassle of every potential 
            <b>landowners</b>, <b>house owners</b>, <b>office sourcing</b> and <b>apartment
                and office-space renters</b>to achieve their dreams</p>
            <h3>Mission</h3>
            <p className="text"><em>In 5 years</em>, we aim to provide a simplified and fully digital 
            platform for everyone to have access to affordable homes anywhere in the world. </p>

            <h3>Core Values</h3>
            <p className="text">Our <em>Core values</em> are <b>integrity</b>, 
              <b>Trustworthiness</b>, <b>Customer Service</b>, <b>Solution driven</b>.
             </p>
          </div>
          <img className="sectionImage" src={aboutUsImage} alt="" />
        </div>
      </div> */}

      <div className="productSection">
        <h1 className="sectionTitle">What we offer</h1>
        <div className="productFlexContainer">
          {
          productMenuItems.map((item) => 
          <ProductMenu key={item.id} item = {item} />)}
        </div>
      </div>

      <div className="section">
        <h1 className="sectionTitle">Featured Properties</h1>
        <div className="property-container">
          {
            listOfApartments.map((apartment)=>
              <Apartments index={apartment.id} apartment={apartment} />
            )
          }
        </div>
      </div>

    </div>
  )
}

export default memo(HomePage);