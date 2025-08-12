// Contact Form Validation
function validateForm(event) {
    event.preventDefault();
    const name = document.getElementById('name').value.trim();
    const email = document.getElementById('email').value.trim();
    const message = document.getElementById('message').value.trim();

    if (name && email && message) {
        alert('Thank you for your message! We will get back to you soon.');
        document.getElementById('contact-form').reset();
    } else {
        alert('Please fill out all fields.');
    }
}

// Online Ordering Basket Functionality
let cart = [];

function updateCart() {
    const cartItems = document.getElementById('cart-items');
    const cartTotal = document.getElementById('cart-total');
    const cartCount = document.getElementById('cart-count');

    cartItems.innerHTML = '';
    let total = 0;

    cart.forEach((item, index) => {
        const li = document.createElement('li');
        li.innerHTML = `${item.name} - £${item.price} <button onclick="removeFromCart(${index})">Remove</button>`;
        cartItems.appendChild(li);
        total += parseFloat(item.price);
    });

    cartTotal.textContent = total.toFixed(2);
    cartCount.textContent = cart.length;
}

function addToCart(name, price) {
    cart.push({ name, price });
    updateCart();
    toggleCart(); // Open basket after adding
}

function removeFromCart(index) {
    cart.splice(index, 1);
    updateCart();
}

function toggleCart() {
    const modal = document.getElementById('cart-modal');
    modal.style.display = modal.style.display === 'block' ? 'none' : 'block';
}

function prepareOrder(event) {
    // Set hidden field with order details
    const total = document.getElementById('cart-total').textContent;
    const orderDetails = cart.map(item => `${item.name}: £${item.price}`).join('\n') + `\n\nTotal: £${total}`;
    document.getElementById('order_details').value = orderDetails;

    // Form will submit to Formspree, no preventDefault here
}

// Event Listeners
document.querySelectorAll('.add-to-cart').forEach(button => {
    button.addEventListener('click', () => {
        const item = button.parentElement;
        const name = item.getAttribute('data-name');
        const price = item.getAttribute('data-price');
        addToCart(name, price);
    });
});

// Close modal when clicking outside
window.onclick = function(event) {
    const modal = document.getElementById('cart-modal');
    if (event.target === modal) {
        modal.style.display = 'none';
    }
}

// Fixed header scroll effect
window.addEventListener('scroll', () => {
    const header = document.querySelector('.fixed-header');
    header.classList.toggle('scrolled', window.scrollY > 50);
});
