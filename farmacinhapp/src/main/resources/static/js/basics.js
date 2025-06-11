const themeIcon = document.getElementById('themeModeIcon');

document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('ano-atual-footer').innerText = new Date().getFullYear();

    themeIcon.onclick = () => {
        //document.body.setAttribute('data-theme', theme);
    }
});