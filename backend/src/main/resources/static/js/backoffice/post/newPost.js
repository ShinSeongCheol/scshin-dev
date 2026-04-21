document.addEventListener('DOMContentLoaded', () => {
    const content = document.querySelector('#content');
    if (content) {
        content.innerHTML = marked.parse("# Hello");
    }
});

