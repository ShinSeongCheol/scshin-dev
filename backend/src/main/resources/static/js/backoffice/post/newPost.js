document.addEventListener('DOMContentLoaded', () => {
    const content = document.querySelector('#content');
    if (content) {
        content.innerHTML = marked.parse("# Hello");
    }

    const preview_button = document.querySelector('#preview-button')
    if (preview_button) {

    }

    const upload_button = document.querySelector('#upload-button');
    if (upload_button) {
        upload_button.addEventListener('click', uploadPost);
    }
});

const uploadPost = (e) => {
    e.preventDefault();

    const token = document.querySelector('meta[name="_csrf"]').content;
    const header = document.querySelector('meta[name="_csrf_header"]').content;

    const post_form = document.querySelector('#post-form');
    const form_data = new FormData(post_form);

    const data = {
        title: form_data.get('title'),
        content: form_data.get('content')
    }

    fetch('/backoffice/post/new', {
        method: 'POST',
        headers: {
            'Content-type': 'application/json',
            [header]: token
        },
        body: JSON.stringify(data)
    })
}

