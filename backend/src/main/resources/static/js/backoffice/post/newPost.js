let markdown_editor;

document.addEventListener('DOMContentLoaded', () => {
    const content = document.querySelector('#content');
    if (content) {
        content.innerHTML = marked.parse("# Hello");
    }

    const post_text_area = document.querySelector('#post-text-area');
    if (post_text_area) {

        let markdown_editor_config = {
            element: post_text_area,
            uploadImage: true,
            imageMaxSize: 1024 * 1024 * 100,
            imageUploadEndpoint: `${location.origin}/image/upload`,
            imageUploadFunction: async function(file, onSuccess, onError) {
                try {
                    const token = document.querySelector('meta[name="_csrf"]').content;
                    const header = document.querySelector('meta[name="_csrf_header"]').content;
                    const formData = new FormData();
                    formData.append("image", file);

                    const response = await fetch(`${location.origin}/image/upload`, {
                        method: "POST",
                        headers: {
                            [header]: token
                        },
                        body: formData
                    });

                    if (!response.ok) {
                        onError(response.status);
                    }

                    const result = await response.json();

                    onSuccess(`${location.origin}${result.filePath}`);
                }catch (error) {
                    console.error(error);
                    onError("이미지 업로드 중 오류가 발생했습니다.");
                }
            }
        }
        markdown_editor = new EasyMDE(markdown_editor_config);
    }

    const upload_button = document.querySelector('#upload-button');
    if (upload_button) {
        upload_button.addEventListener('click', uploadPost);
    }
});

const uploadPost = async (e) => {
    e.preventDefault();

    const token = document.querySelector('meta[name="_csrf"]').content;
    const header = document.querySelector('meta[name="_csrf_header"]').content;

    const post_form = document.querySelector('#post-form');
    const form_data = new FormData(post_form);

    const data = {
        title: form_data.get('title'),
        content: markdown_editor.value()
    }

    try {
        const response = await fetch('/backoffice/post/new', {
            method: 'POST',
            headers: {
                'Content-type': 'application/json',
                [header]: token
            },
            body: JSON.stringify(data)
        })

        if (response.ok) {
            location.href="/backoffice/post";
        }else {
            console.log(response)
        }
    }catch (e) {
        console.error(e)
    }
}

