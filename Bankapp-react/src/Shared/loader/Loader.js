import React from 'react'
import './Loader.css'

const Loader = ({isShow}) => {
  return (
    <div style={
        isShow ? {
            visibility: 'visible'
        } : {
            visibility: 'hidden'
        }
    }>
        <div className="loader"></div>
    </div>
  )
}

export default Loader