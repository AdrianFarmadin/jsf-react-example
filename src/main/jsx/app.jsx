import React from 'react'
import ReactDOM from 'react-dom'

import { IntlProvider, addLocaleData } from 'react-intl';
import { CookiesProvider } from 'react-cookie';
import en from 'react-intl/locale-data/en';
import sk from 'react-intl/locale-data/sk';
import localeData from './i18n/data.json';

addLocaleData([...en, ...sk]);

import ProductList from './ProductList.jsx'

import './app.less'

const language = getLanguageCookie();

const messages = localeData[language];

ReactDOM.render(
    <IntlProvider locale={language} messages={messages}>
        <CookiesProvider>
            <ProductList who="React"/>
        </CookiesProvider>
    </IntlProvider>,
    document.getElementById('app')
)

function getCookie(cookiename) {
    // Get name followed by anything except a semicolon
    var cookiestring=RegExp(""+cookiename+"[^;]+").exec(document.cookie);
    // Return everything after the equal sign, or an empty string if the cookie name not found
    return decodeURIComponent(!!cookiestring ? cookiestring.toString().replace(/^[^=]+./,"") : "");
}

function getLanguageCookie() {
    let languageCookie = getCookie('selectedLanguage');
    if(languageCookie) {
        return languageCookie;
    } else {
        return 'en';
    }
}

