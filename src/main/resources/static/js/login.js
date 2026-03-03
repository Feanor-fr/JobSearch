document.addEventListener('DOMContentLoaded', function () {

    const form = document.getElementById('login-form');

    form.addEventListener('submit', function () {

        const formData = new FormData(form);
        const user = Object.fromEntries(formData);

        localStorage.setItem('user', JSON.stringify({
            username: user.username
        }));
    });

});