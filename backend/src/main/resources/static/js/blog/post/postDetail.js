let markdown_editor;

document.addEventListener('DOMContentLoaded', () => {
    const content = document.querySelector('#content');
    if (content) {
        const markdown = content.textContent;
        content.innerHTML = marked.parse(markdown);
    }
});