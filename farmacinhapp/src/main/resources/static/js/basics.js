const themeIcon = document.getElementById('themeModeIcon');

document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('ano-atual-footer').innerText = new Date().getFullYear();

    themeIcon.onclick = () => {
        themeIcon.textContent = themeIcon.textContent === 'light_mode' ? 'dark_mode' : 'light_mode';
        const theme = themeIcon.textContent.includes('light') ? 'light' : 'dark';
        document.body.setAttribute('data-theme', theme);
    }
});