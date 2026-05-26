document.addEventListener('DOMContentLoaded', () => {
    const category_items = document.querySelector('#category-items');
    if (category_items) {
        category_items.addEventListener('click', onClickCategoryItem)
    }
    const create_button = document.querySelector('#create-button');
    if (create_button) {
        create_button.addEventListener('click', onClickCreateButton)
    }
});

const onClickCategoryItem = (event) => {
    const row = event.target.closest('.category-row');
    if (!row) return;

    const categoryId = row.dataset.categoryId;
    location.href = `/backoffice/category/edit/${categoryId}`;
}

const onClickCreateButton = event => {
    location.href = '/backoffice/category/new';
}