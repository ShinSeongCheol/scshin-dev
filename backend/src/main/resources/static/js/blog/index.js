document.addEventListener("DOMContentLoaded", () => {
    const postCards = document.querySelectorAll("#postCards");
    postCards.forEach((postCard) => {
        postCard.addEventListener('click', () => {
            const postId = postCard.getAttribute('data-post-id');
            if (postId) {
                location.href = `/${postId}`;
            }
        });
    })
})