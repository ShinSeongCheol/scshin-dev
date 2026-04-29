let markdown_editor;

document.addEventListener('DOMContentLoaded', () => {
    const upload_button = document.querySelector('#upload-button');
    if (upload_button) {
        upload_button.addEventListener('click', editPost);
    }

    const post_text_area = document.querySelector('#post-text-area');
    if (post_text_area) {
        markdown_editor = new EasyMDE({element: post_text_area});
    }
});

const editPost = async (e) => {
    e.preventDefault();

    const token = document.querySelector('meta[name="_csrf"]').content;
    const header = document.querySelector('meta[name="_csrf_header"]').content;

    const post_form = document.querySelector('#post-form');
    const form_data = new FormData(post_form);

    const data = {
        title: form_data.get('title'),
        content: markdown_editor.value(),
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