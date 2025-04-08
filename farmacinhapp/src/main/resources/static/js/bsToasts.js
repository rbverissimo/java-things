import { Toast } from '/webjars/bootstrap/5.3.3/js/bootstrap.esm.min.js';

document.addEventListener('DOMContentLoaded', () => {

    const toastElements = document.querySelectorAll('.toast');
    toastElements.forEach(toastEl => {
        const options = {
            animation: true, // default
            autohide: false,  // default
            //delay: 5000    // default
        };
        const toastInstance = new Toast(toastEl, options);
        console.log(toastInstance);
        toastInstance.show();
    })
});