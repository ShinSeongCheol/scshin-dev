document.addEventListener('DOMContentLoaded', () => {
    const create_button = document.querySelector('#create-button');
    if (create_button) {
        create_button.addEventListener('click', onClickCreateButton)
    }
});

const onClickCreateButton = event => {
    location.href = '/backoffice/category/new';
}