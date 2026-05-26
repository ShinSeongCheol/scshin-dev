document.addEventListener('DOMContentLoaded', () => {
    const postItems = document.querySelector('#post-items');
    if (postItems) {
        postItems.addEventListener('click', onClickPostItem)
    }

    const writeButton = document.querySelector('#write-button');
    if (writeButton) {
       writeButton.addEventListener('click', onClickWrite);
    }
});

const onClickPostItem = (event) => {
    const row = event.target.closest('.post-row');
    if (!row) return;

    const post_id = row.dataset.postId;
    location.href = `/backoffice/post/edit/${post_id}`;
}

const onClickWrite = () => {
    location.href = '/backoffice/post/new';
}
