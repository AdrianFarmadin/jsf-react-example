import React from 'react'

const ProductInfo = React.createClass({
    render: function() {
        return (
            <div>
                {this.props.name} {this.props.price}
            </div>
        )
    }
})

export default ProductInfo
