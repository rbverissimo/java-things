const inputEmail = document.getElementById("floatingInput");
const dynamicLink = document.getElementById("dynamicLink");
const baseUrl = dynamicLink.getAttribute("href");

document.addEventListener('DOMContentLoaded', () => {
    console.log(baseUrl);

    inputEmail.addEventListener('input', () => {
        const queryValue = inputEmail.value;
        const encodedQuery = encodeURIComponent(queryValue);
        const newUrl = baseUrl+'?email='+encodedQuery;
        dynamicLink.href = newUrl;
    });

});