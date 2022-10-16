import React from 'react'

function Card(props) {
  return (
    <div style={props.cards}>
        <img src={props.img} style={props.style} alt />
        <p>{props.title}</p>

    </div>
  )
}

export default Card