window.addEventListener('load', () => {
  // Calc totals
  const orderItems = document.querySelectorAll('.order-item')
  if (orderItems) {
    let subtotal = 0
    orderItems.forEach(orderItem => {
      subtotal += Number(orderItem.querySelector('span').innerText)
    })
    let shippingFee = 30000
    let total = subtotal + shippingFee
    document.getElementById('sub-total').innerText = subtotal.toString()
    document.querySelector('input[name="sub-total"]').value = subtotal.toString()
    document.getElementById('shipping-fee').innerText = shippingFee.toString()
    document.querySelector('input[name="shipping-fee"]').value = shippingFee.toString()
    document.getElementById('total').innerText = total.toString()
    document.querySelector('input[name="total"]').value = total.toString()
  }
})