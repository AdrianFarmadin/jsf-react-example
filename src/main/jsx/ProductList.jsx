import React from 'react'
import { FormattedMessage } from 'react-intl';
import { withCookies, Cookies } from 'react-cookie';
import { instanceOf } from 'prop-types';
import ProductFetchService from './Providers/ProductFetchService.jsx'
import ProductInfo from './ProductInfo.jsx'

import './ProductList.less'

const ProductList = React.createClass({
    propTypes: {
        cookies: instanceOf(Cookies).isRequired
    },
    getInitialState: function() {
        const { cookies } = this.props;
        return {
            language: cookies.get('selectedLanguage'),
            products: []
        };
    },
    componentDidMount: function() {
        ProductFetchService.getProducts().then(response => {
            this.setState((prevState) => (
                { ...prevState,
                    products: response.products
                }
            ));
        });
    },
    render: function() {
        return (
            <div className="ProductList">
                <FormattedMessage
                    id={ 'welcome' }
                    defaultMessage={ 'Default welcome!' }
                />
                {this.state.products.map((product, i) => {
                    return (<ProductInfo key={i} {...product} />)
                })}
            </div>
        )
    }
})

export default withCookies(ProductList);
