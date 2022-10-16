import React, {memo} from 'react';
import '../../Styles/HomePageStyle.css';

const ProductMenu = (props) => {
  return (
        <div className="productWrapper">
              <img className="productImage" src={props.item.image} alt="" />
              <div className="productDesc"><p>{props.item.info}</p></div>
              <div className="productTextWrapper"><h3 className="productText">{props.item.title}</h3></div>
        </div>
    )
}

export default memo(ProductMenu);