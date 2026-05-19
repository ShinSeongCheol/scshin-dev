document.addEventListener('DOMContentLoaded', () => {
    const cancel_button = document.querySelector('#cancel-button');
    if (cancel_button) {
        cancel_button.addEventListener('click', () => {
            location.href = "/backoffice/category";
        })
    }

    const category_form = document.querySelector('#category-form');
    if (category_form) {
        category_form.addEventListener('submit', onSubmitHandle);
    }
})

const onSubmitHandle = async (e) => {
    e.preventDefault();

    const token = document.querySelector('meta[name="_csrf"]').content;
    const header = document.querySelector('meta[name="_csrf_header"]').content;

    const category_form = e.target;
    const formData = new FormData(category_form);
    const data = {
        parentCategoryId: formData.get('parent-category-id') === '' ? null : formData.get('parent-category-id'),
        categoryName: formData.get('category-name'),
        slug: formData.get('slug'),
        description: formData.get('description'),
        useYn: formData.get('use_yn') ? 'Y' : 'N',
    }

    await fetch('/backoffice/category/new', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            [header]: token
        },
        body: JSON.stringify(data),
    })
}