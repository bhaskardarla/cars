const currency = new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD' });
const tbody = document.querySelector('#cars');
const message = document.querySelector('#message');

async function loadCars(query = '') {
  const url = query ? `/api/cars/search?query=${encodeURIComponent(query)}` : '/api/cars';
  const cars = await (await fetch(url)).json();
  tbody.innerHTML = cars.length ? cars.map(car => `<article class="car-card"><img src="${car.imageUrl || '/images/showroom-sedan.png'}" alt="${car.color} ${car.make} ${car.model}" /><div class="card-body"><div class="card-heading"><div><p>${car.year} · ${car.id}</p><h3>${car.make} ${car.model}</h3></div><strong>${currency.format(car.price)}</strong></div><div class="specs"><span>${car.fuelType}</span><span>${car.transmission}</span><span>${Number(car.mileage).toLocaleString()} km</span><span>${car.color}</span></div></div></article>`).join('') : '<p class="empty">No cars found.</p>';
  document.querySelector('#car-count').textContent = cars.length;
  if (!query) document.querySelector('#total-value').textContent = currency.format(await (await fetch('/api/cars/total-value')).json());
}

document.querySelector('#search').addEventListener('input', event => loadCars(event.target.value.trim()));
document.querySelector('#car-form').addEventListener('submit', async event => {
  event.preventDefault();
  const data = Object.fromEntries(new FormData(event.target));
  data.year = Number(data.year); data.price = Number(data.price); data.mileage = Number(data.mileage); data.imageUrl = data.imageUrl || '/images/showroom-sedan.png';
  const response = await fetch('/api/cars', { method: 'POST', headers: { 'Content-Type': 'application/json' }, body: JSON.stringify(data) });
  if (response.ok) { event.target.reset(); message.textContent = 'Car added successfully.'; loadCars(); }
  else { message.textContent = `Could not add car: ${await response.text()}`; }
});
loadCars();
