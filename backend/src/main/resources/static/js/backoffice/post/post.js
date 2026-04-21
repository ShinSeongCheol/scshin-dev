const onClickWrite = () => {
    location.href = '/backoffice/post/new';
}

document.addEventListener('DOMContentLoaded', () => {
   const writeButton = document.querySelector('#write-button');
   if (writeButton) {
       writeButton.addEventListener('click', onClickWrite);
   }
});