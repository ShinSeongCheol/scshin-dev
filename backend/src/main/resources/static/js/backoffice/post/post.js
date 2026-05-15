document.addEventListener('DOMContentLoaded', () => {
    const tableBody = document.querySelector('#table-body');
    if (tableBody) {
        tableBody.addEventListener('click', onClickTableBody)
    }

    const writeButton = document.querySelector('#write-button');
    if (writeButton) {
       writeButton.addEventListener('click', onClickWrite);
    }
});

const onClickTableBody = (event) => {
    const row = event.target.closest('tr');
    const post_id = row.cells[0].innerText;
    location.href = `/backoffice/post/edit/${post_id}`;
}

const onClickWrite = () => {
    location.href = '/backoffice/post/new';
}
