const init = () => {
    loadTheme();
    themeToggle.addEventListener('click', toggleTheme);
}

const loadTheme = () => {
    const currentTheme = localStorage.getItem('theme') || 'light';
    document.documentElement.setAttribute('data-theme', currentTheme);
}

const toggleTheme = () => {
    const currentTheme = document.documentElement.getAttribute('data-theme');
    const theme = currentTheme === 'dark' ? 'light' : 'dark';
    document.documentElement.setAttribute('data-theme', theme);
    localStorage.setItem('theme', theme);
}

// DOM Element
const themeToggle = document.getElementById('themeToggle');

document.addEventListener('DOMContentLoaded', init);