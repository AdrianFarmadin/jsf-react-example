package eu.farmadin.jsf_react_example.controller;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

@ELBeanName(value = "language")
@Named("language")
@SessionScoped
public class LanguageController implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String SELECTED_LANGUAGE = "selectedLanguage";

    final Logger logger = LoggerFactory.getLogger(LanguageController.class);

    private String localeCode;

    private static Map<String,Locale> countries;
    static{
        countries = new LinkedHashMap<>();
        countries.put("English", Locale.ENGLISH);
        countries.put("Slovak", new Locale("sk", "SK"));
    }

    public Map<String, Locale> getCountriesInMap() {
        return countries;
    }


    public String getLocaleCode() {
        logger.debug("LocaleCode: {}", localeCode);
        Cookie languageCookie = findLanguageCookie();
        if(languageCookie==null) {
            return localeCode;
        }
        for (Locale locale : countries.values()) {
            if(locale.getLanguage().equalsIgnoreCase(languageCookie.getValue())) {
                FacesContext.getCurrentInstance()
                        .getViewRoot().setLocale(locale);
                localeCode = locale.toString();
            }
        }
        return localeCode;
    }


    public void setLocaleCode(String localeCode) {
        this.localeCode = localeCode;
    }

    public void countryLocaleCodeChanged(ValueChangeEvent e){
        String newLocaleValue = e.getNewValue().toString();
        for (Map.Entry<String, Locale> entry : countries.entrySet()) {
            if(entry.getValue().toString().equals(newLocaleValue)){
                FacesContext.getCurrentInstance()
                        .getViewRoot().setLocale(entry.getValue());
                HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                Cookie languageCookie = findLanguageCookie();
                if(languageCookie!=null) {
                    languageCookie.setValue(entry.getValue().getLanguage());
                }
                Cookie cookie = new Cookie(SELECTED_LANGUAGE, entry.getValue().getLanguage());
                response.addCookie(cookie);
            }
        }
    }

    private Cookie findLanguageCookie() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        if(request == null || request.getCookies() == null) {
            return null;
        }
        for(Cookie cookie : request.getCookies()) {
            logger.trace("Cookies {} : {}", cookie.getName(), cookie.getValue());
            if(cookie.getName().equalsIgnoreCase(SELECTED_LANGUAGE)) {
                logger.debug("Language cookies found <{}>", cookie.getValue());
                return cookie;
            }
        }
        return null;
    }

}
