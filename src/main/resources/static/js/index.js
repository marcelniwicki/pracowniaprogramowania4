const getProducts = () => {
    return fetch('/api/products')
        .then(response => response.json())
    ;
}

const createHtmlProductElement = (product) => {
    const template = `
        <li>
            <h5>${product.title}</h5>
            <span>${product.price}</span>
            <button data-productId="${product.price}">Add to cart</button>
        </li>
    `;

    return createHtmlFromString(template.trim());
}

const createHtmlFromString = (htmlAsString) => {
    let div = document.createElement('div');
    div.innerHTML = htmlAsString;
    return div.firstChild;
}

const getCurrentOffer = () => {
    return fetch('/api/sales/current-offer')
        .then(response => response.json())
    ;
}

const refreshOffer = (offer) => {
      const cart = document.querySelector('.cart');
      cart.querySelector('.cart__total').textContent = offer.total;
      cart.querySelector('.cart__itemsCount').textContent = offer.itemsCount;
}

const addToCart = (productId) => {}
const acceptOffer = () => {}

(() => {
    const productsList = document.querySelector('.products');
    getProducts()
        .then(products => {
            products
                .map(product => createHtmlProductElement(product))
                .forEach(productHtmlEl => productsList.appendChild(productHtmlEl))
        });


    getCurrentOffer()
        .then(offer => refreshOffer(offer));
})();