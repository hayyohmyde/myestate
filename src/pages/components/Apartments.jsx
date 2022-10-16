import React, {memo} from 'react';
import '../../Styles/HomePageStyle.css';
import {GrLocation} from "react-icons/gr";
import {MdOutlineBedroomParent, MdOutlineBathroom, MdGarage} from "react-icons/md";
import {RiLandscapeLine} from "react-icons/ri";
import {FaShare} from "react-icons/fa";
import {GiSelfLove} from "react-icons/gi";



const Apartments = (props) => {
  return (
    <div className='apartment-container'>
        <img className='apartment-image' src={props.apartment.image} alt="" />
        <div className="apartment-info-container">
            <h3 className="apartment-status">{props.apartment.status}</h3>
            <h3 className='apartment-title'>{props.apartment.name}</h3>
            <div className='apartment-location'>
                <GrLocation size={30}/>
                <p>{props.apartment.location}</p>
            </div>
            <div className='apartment-details'>
                <div><MdOutlineBedroomParent size={30}/><p>{props.apartment.bedrooms + " bedrooms"}</p></div>
                <div><MdOutlineBathroom size={30}/><p>{props.apartment.bathrooms + " bathrooms"}</p></div>
                <div><RiLandscapeLine size={30}/><p>{props.apartment.landArea}</p></div>
                <div><MdGarage size={30}/><p>{props.apartment.garage + " car space"}</p></div>
            </div>
            <p className='apartment-hidden-text'>Click for more details </p>
            <div className="apartment-hidden-container">
                <h2>{"N" + props.apartment.price}</h2>
                <div><FaShare  size={30} /><GiSelfLove  size={30}/></div>
            </div>
        </div>
    </div>
  )
}

export default memo(Apartments);