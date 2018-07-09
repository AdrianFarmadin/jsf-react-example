
export default class ProductFetchService {
    static getProducts() {
        return fetch('/jsf-react-example-1.0-SNAPSHOT/api/product', {
            credentials: 'same-origin'
        }).then((response) => {
            if (response.status==403 || response.status==401) {
               window.location.reload(false);
            }
            return response.json();
        }).catch((error) => {
            console.log(JSON.stringify(error));
         });
    }
}