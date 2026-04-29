document.addEventListener('DOMContentLoaded', () => {
    const preview_button = document.querySelector('#preview-button')
    if (preview_button) {
        preview_button.addEventListener('click', previewPost);
    }

    const preview_modal_close_button = document.querySelector('#preview-modal-close-button');
    if (preview_modal_close_button) {
        preview_modal_close_button.addEventListener('click', closePreviewPost);
    }

    const upload_button = document.querySelector('#upload-button');
    if (upload_button) {
        upload_button.addEventListener('click', editPost);
    }
});

const previewPost = () => {
    const preview_modal = document.querySelector('#preview-modal');
    const preview_modal_content = document.querySelector('#preview-modal-content');
    if (preview_modal && preview_modal_content) {
        const post_form = document.querySelector('#post-form');
        const form_data = new FormData(post_form);
        const content = form_data.get('content');

        preview_modal_content.innerHTML = marked.parse(content);

        preview_modal.showModal();
    }
}

const closePreviewPost = () => {
    const preview_modal = document.querySelector('#preview-modal');
    if (preview_modal) {
        preview_modal.close();
    }
}

const editPost = async (e) => {
    e.preventDefault();

    const token = document.querySelector('meta[name="_csrf"]').content;
    const header = document.querySelector('meta[name="_csrf_header"]').content;

    const post_form = document.querySelector('#post-form');
    const form_data = new FormData(post_form);

    const data = {
        title: form_data.get('title'),
        content: form_data.get('content')
    }

    try {
        const response = await fetch(`/backoffice/post/edit/${form_data.get('id')}`, {
            method: 'PATCH',
            headers: {
                'Content-type': 'application/json',
                [header]: token
            },
            body: JSON.stringify(data)
        })

        if (response.ok) {
            location.href = '/backoffice/post'
        }else {
            console.log(response);
        }
    }catch(e) {
        console.error(e);
    }
}