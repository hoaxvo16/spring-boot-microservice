const form = document.getElementById('form');
const email = document.getElementById('email');
const password = document.getElementById('password');
const todoDiv = document.getElementById('todo');
const userId = document.getElementById('userId');
const BASE_PUBLIC_URL = 'http://localhost:8081/api/v1/public';
form.addEventListener('submit', async event => {
   event.preventDefault();

   const res = await fetch(`${BASE_PUBLIC_URL}/auth/login`, {
      method: 'POST',
      headers: {
         'Content-Type': 'application/json',
         // 'Content-Type': 'application/x-www-form-urlencoded',
      },
      body: JSON.stringify({
         email: email.value,
         password: password.value,
      }),
   });

   const data = await res.json();
   localStorage.setItem('Token', data.token);
});

function getToken() {
   return localStorage.getItem('Token');
}
